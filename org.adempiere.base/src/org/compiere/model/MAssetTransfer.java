/******************************************************************************
 * The contents of this file are subject to the  Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is             Compiere  ERP & CRM Smart Business Solution
 * The Initial Developer of the Original Code is Jorg Janke  and ComPiere, Inc.
 * Portions created by Jorg Janke are Copyright (C) 1999-2003 Jorg Janke, parts
 * created by ComPiere are Copyright (C) ComPiere, Inc.;   All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.idempiere.fa.exceptions.AssetAlreadyDepreciatedException;

/**
 *  Asset Transfer Model
 * @author www.arhipac.ro
 *
 */
public class MAssetTransfer extends X_A_AssetTransfer
implements DocAction, DocOptions
{
	/**
	 * generated serial id 
	 */
	private static final long serialVersionUID = 2997284714883099922L;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;
    
    /**
     * UUID based Constructor
     * @param ctx  Context
     * @param A_Asset_Transfer_UU  UUID key
     * @param trxName Transaction
     */
    public MAssetTransfer(Properties ctx, String A_AssetTransfer_UU, String trxName) {
        super(ctx, A_AssetTransfer_UU, trxName);
		if (Util.isEmpty(A_AssetTransfer_UU))
			setInitialDefaults();
    }

    /**
     * @param ctx
     * @param X_A_Asset_Transfer_ID
     * @param trxName
     */
	public MAssetTransfer (Properties ctx, int X_A_AssetTransfer_ID, String trxName)
    {
		super (ctx,X_A_AssetTransfer_ID, trxName);
		if (X_A_AssetTransfer_ID == 0)
			setInitialDefaults();
	}

	/**
	 * Set the initial defaults for a new record
	 */
	private void setInitialDefaults() {
	    setDocStatus(DOCSTATUS_Drafted);
		setDocAction(DOCACTION_Complete);
		setProcessed(false);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MAssetTransfer (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}
	
	@Override
	public boolean approveIt() {
		return false;
	}
	
	@Override
	public boolean closeIt() {
		setDocAction(DOCACTION_None);
		return true;
	}
	
	@Override
	public File createPDF() {
		return null;
	}
	
	@Override
	public BigDecimal getApprovalAmt() {
		return Env.ZERO;
	}
	
	@Override
	public int getC_Currency_ID() {
		return 0;
	}
	
	@Override
	public int getDoc_User_ID() {
		return getCreatedBy();
	}
	
	@Override
	public String getDocumentInfo() {
		return getDocumentNo() + "/" + getDateAcct();
	}
	
	@Override
	public String getProcessMsg() {
		return m_processMsg;
	}
	private String m_processMsg = null;
	
	@Override
	public String getSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append("@DocumentNo@ #").append(getDocumentNo());
		return sb.toString();
	}
	
	@Override
	public boolean invalidateIt() {
		return false;
	}
	
	@Override
	public String prepareIt()
	{
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}
		// test if period is open
		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), MDocType.DOCBASETYPE_GLJournal, getAD_Org_ID());
		
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), MAssetAcct.POSTINGTYPE_Actual);
		if (assetwk.isDepreciated(getDateAcct()))
		{
			throw new AssetAlreadyDepreciatedException();
		}
		
		if (getNew_Asset_Group_ID() <= 0) {
			throw new AdempiereException("New Asset Group is missing");
		}
		
		/*
		//doc check if the date is equal to its accounting for the expense table
		if (assetwk.getDateAcct().equals(getDateAcct()))
		{
			throw new AdempiereException("Last day of month. Accounts will be changed next month");  
		}
		*/
			
		//check if they are unprocessed records
		MDepreciationExp.checkExistsNotProcessedEntries(getCtx(), getA_Asset_ID(), getDateAcct(), MAssetAcct.POSTINGTYPE_Actual, get_TrxName());
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

			m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}
	
	@Override
	public String completeIt()
	{
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			m_justPrepared = false;
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		String sqlGetAcctSchema_ID = "SELECT C_AcctSchema_ID FROM C_AcctSchema WHERE AD_Client_ID = " + getAD_Client_ID();
		int C_AcctSchema_ID = DB.getSQLValue(get_TrxName(), sqlGetAcctSchema_ID);
		
		MAssetGroupAcct newAssetGroupAcct = MAssetGroupAcct.forA_Asset_Group_ID(getCtx(), getNew_Asset_Group_ID(), MAssetGroupAcct.POSTINGTYPE_Actual, C_AcctSchema_ID);
		
		// create new MAssetAcct
		MAssetAcct assetAcctPrev = MAssetAcct.forA_Asset_ID(getCtx(), C_AcctSchema_ID, getA_Asset_ID(), MAssetAcct.POSTINGTYPE_Actual, getDateAcct(), get_TrxName());
		MAssetAcct assetAcct = new MAssetAcct(getCtx(), 0, get_TrxName());
		PO.copyValues(assetAcctPrev, assetAcct);
		assetAcct.setA_Asset_Acct(newAssetGroupAcct.getA_Asset_Acct());
		assetAcct.setA_Accumdepreciation_Acct(newAssetGroupAcct.getA_Accumdepreciation_Acct());
		assetAcct.setA_Depreciation_Acct(newAssetGroupAcct.getA_Depreciation_Acct());
		assetAcct.setA_Disposal_Loss_Acct(newAssetGroupAcct.getA_Disposal_Loss_Acct());
		assetAcct.setA_Disposal_Revenue_Acct(newAssetGroupAcct.getA_Disposal_Revenue_Acct());
		//assetAcct.setA_Disposal_Gain_Acct(newAssetGroupAcct.getA_Disposal_Gain_Acct());
		assetAcct.setPostingType(MAssetAcct.POSTINGTYPE_Actual);
		assetAcct.setA_Depreciation_ID(newAssetGroupAcct.getA_Depreciation_ID());
		assetAcct.setValidFrom(getDateAcct());
		assetAcct.saveEx();
		
		assetAcctPrev.setIsActive(false);
		assetAcctPrev.saveEx();
		
		String sql = "UPDATE A_Depreciation_Exp SET A_Account_Number_Acct=" + newAssetGroupAcct.getA_Asset_Acct() + ","
				+ " DR_Account_ID=" + newAssetGroupAcct.getA_Asset_Acct() + ","
				+ " CR_Account_ID=" + newAssetGroupAcct.getA_Accumdepreciation_Acct() 
				+ " WHERE A_Asset_ID=?"
				+ " AND Processed='N'";
		
		DB.executeUpdate(sql, getA_Asset_ID(), get_TrxName());
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		MAsset asset = new MAsset(getCtx(), getA_Asset_ID(), get_TrxName());
		asset.setA_Asset_Group_ID(getNew_Asset_Group_ID());
		// @Stephan set org
		asset.setAD_Org_ID(get_ValueAsInt("AD_OrgTo_ID"));
		asset.saveEx();
		
		for (MDepreciationWorkfile wk : asset.getWorkfile(getA_Asset_ID())) {
			wk.setAD_Org_ID(asset.getAD_Org_ID());
			wk.saveEx();
		}
		
		for (MAssetAcct assetacct : asset.getAssetAcct(getA_Asset_ID())) {
			assetacct.setAD_Org_ID(asset.getAD_Org_ID());
			assetacct.saveEx();
		}
		
		for (MDepreciationExp depExp : asset.getDepExp(getA_Asset_ID())) {
			depExp.setAD_Org_ID(asset.getAD_Org_ID());
			depExp.saveEx();
		}
		// @Stephan end
		
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}
	
	@Override
	public boolean processIt(String action) throws Exception {
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (action, getDocAction());
	}
	
	@Override
	public boolean reActivateIt() {
		return false;
	}
	
	@Override
	public boolean rejectIt() {
		return false;
	}
	
	@Override
	public boolean reverseAccrualIt() {
		return false;
	}
	
	@Override
	public boolean reverseCorrectIt() {
		return false;
	}
	
	@Override
	public boolean unlockIt() {
		return false;
	}
	
	@Override
	public boolean voidIt() {
		return false;
	}

	@Override
	public String getDocumentNo() {
		return null;
	}
	
	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {
		index = 0;
		if (docStatus.equals(DocAction.STATUS_Drafted)) {
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;

		} else if (docStatus.equals(DocAction.STATUS_InProgress)) {
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;

		} else if (docStatus.equals(DocAction.STATUS_Invalid)) {
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
		}	
		return index;

	}
}	//	MAssetTransfer
