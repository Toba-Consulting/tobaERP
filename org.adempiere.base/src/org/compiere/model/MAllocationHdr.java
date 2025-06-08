/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.PeriodClosedException;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.taowi.model.MBPCInvAcctByCurrency;
import org.taowi.model.MBPVInvAcctByCurrency;

/**
 *  Allocation Model.
 * 	Allocation Trigger update of C_BPartner balance.
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: MAllocationHdr.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 *  			<li>FR [ 1866214 ]  
 *				<li> https://sourceforge.net/p/adempiere/feature-requests/298/
 * 				<li>FR [ 2520591 ] Support multiples calendar for Org 
 *				<li> https://sourceforge.net/p/adempiere/feature-requests/631/ 
 *				<li>BF [ 2880182 ] Error you can allocate a payment to invoice that was paid
 *				<li> https://sourceforge.net/p/adempiere/bugs/2181/
*/
public class MAllocationHdr extends X_C_AllocationHdr implements DocAction, DocOptions 
{
	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = -7787519874581251920L;

	/**
	 * 	Get Allocations of Payment
	 *	@param ctx context
	 *	@param C_Payment_ID payment
	 *  @param trxName transaction
	 *	@return allocations of payment
	 */
	public static MAllocationHdr[] getOfPayment (Properties ctx, int C_Payment_ID, String trxName)
	{
		String sql = "SELECT * FROM C_AllocationHdr h "
			+ "WHERE IsActive='Y'"
			+ " AND EXISTS (SELECT * FROM C_AllocationLine l "
				+ "WHERE h.C_AllocationHdr_ID=l.C_AllocationHdr_ID AND l.C_Payment_ID=?)";
		ArrayList<MAllocationHdr> list = new ArrayList<MAllocationHdr>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Payment_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MAllocationHdr(ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MAllocationHdr[] retValue = new MAllocationHdr[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getOfPayment

	/**
	 * 	Get Allocations of Invoice
	 *	@param ctx context
	 *	@param C_Invoice_ID payment
	 *  @param trxName transaction
	 *	@return allocations of payment
	 */
	public static MAllocationHdr[] getOfInvoice (Properties ctx, int C_Invoice_ID, String trxName)
	{
		String sql = "SELECT * FROM C_AllocationHdr h "
			+ "WHERE IsActive='Y'"
			+ " AND EXISTS (SELECT * FROM C_AllocationLine l "
				+ "WHERE h.C_AllocationHdr_ID=l.C_AllocationHdr_ID AND l.C_Invoice_ID=?)";
		ArrayList<MAllocationHdr> list = new ArrayList<MAllocationHdr>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Invoice_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MAllocationHdr(ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MAllocationHdr[] retValue = new MAllocationHdr[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getOfInvoice
	
	/**
	 * 	Get Allocations of Cash
	 *	@param ctx context
	 *	@param C_Cash_ID Cash ID
	 *  @param trxName transaction
	 *	@return allocations of payment
	 */
	public static MAllocationHdr[] getOfCash (Properties ctx, int C_Cash_ID, String trxName)
	{
		final String whereClause = "IsActive='Y'"
			+ " AND EXISTS (SELECT 1 FROM C_CashLine cl, C_AllocationLine al "
				+ "where cl.C_Cash_ID=? and al.C_CashLine_ID=cl.C_CashLine_ID "
						+ "and C_AllocationHdr.C_AllocationHdr_ID=al.C_AllocationHdr_ID)";
		Query query = MTable.get(ctx, I_C_AllocationHdr.Table_ID)
							.createQuery(whereClause, trxName);
		query.setParameters(C_Cash_ID);
		List<MAllocationHdr> list = query.list();
		MAllocationHdr[] retValue = new MAllocationHdr[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getOfCash
	
	/**	Logger						*/
	private static CLogger s_log = CLogger.getCLogger(MAllocationHdr.class);
		
    /**
     * UUID based Constructor
     * @param ctx  Context
     * @param C_AllocationHdr_UU  UUID key
     * @param trxName Transaction
     */
    public MAllocationHdr(Properties ctx, String C_AllocationHdr_UU, String trxName) {
        super(ctx, C_AllocationHdr_UU, trxName);
		if (Util.isEmpty(C_AllocationHdr_UU))
			setInitialDefaults();
    }

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_AllocationHdr_ID id
	 *	@param trxName transaction
	 */
	public MAllocationHdr (Properties ctx, int C_AllocationHdr_ID, String trxName)
	{
		super (ctx, C_AllocationHdr_ID, trxName);
		if (C_AllocationHdr_ID == 0)
			setInitialDefaults();
	}	//	MAllocation

	/**
	 * Set the initial defaults for a new record
	 */
	private void setInitialDefaults() {
		setDateTrx (new Timestamp(System.currentTimeMillis()));
		setDateAcct (getDateTrx());
		setDocAction (DOCACTION_Complete);
		setDocStatus (DOCSTATUS_Drafted);
		setApprovalAmt (Env.ZERO);
		setIsApproved (false);
		setIsManual (false);
		//
		setPosted (false);
		setProcessed (false);
		setProcessing(false);
		setC_DocType_ID(MDocType.getDocType("CMA"));
	}

	/**
	 * 	Mandatory New Constructor
	 *	@param ctx context
	 *	@param IsManual manual trx
	 *	@param DateTrx date (if null today)
	 *	@param C_Currency_ID currency
	 *	@param description description
	 *	@param trxName transaction
	 */
	public MAllocationHdr (Properties ctx, boolean IsManual, Timestamp DateTrx, 
		int C_Currency_ID, String description, String trxName)
	{
		this (ctx, 0, trxName);
		setIsManual(IsManual);
		if (DateTrx != null)
		{
			setDateTrx (DateTrx);
			setDateAcct (DateTrx);
		}
		setC_Currency_ID (C_Currency_ID);
		if (description != null)
			setDescription(description);
	}	//  create Allocation


	/** 
	 * 	Load Constructor
	 * 	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MAllocationHdr (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAllocation

	/**	Lines						*/
	private MAllocationLine[]	m_lines = null;
	
	/**
	 * 	Get Allocation Lines
	 *	@param requery if true requery
	 *	@return allocation lines
	 */
	public MAllocationLine[] getLines (boolean requery)
	{
		if (m_lines != null && m_lines.length != 0 && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		//
		String sql = "SELECT * FROM C_AllocationLine WHERE C_AllocationHdr_ID=?";
		ArrayList<MAllocationLine> list = new ArrayList<MAllocationLine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getC_AllocationHdr_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MAllocationLine line = new MAllocationLine(getCtx(), rs, get_TrxName());
				line.setParent(this);
				list.add (line);
			}
		} 
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		m_lines = new MAllocationLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines

	/**
	 * 	Set Processed
	 *	@param processed Processed
	 */
	@Override
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		StringBuilder sql = new StringBuilder("UPDATE C_AllocationHdr SET Processed='")
			.append((processed ? "Y" : "N"))
			.append("' WHERE C_AllocationHdr_ID=").append(getC_AllocationHdr_ID());
		int no = DB.executeUpdate(sql.toString(), get_TrxName());
		m_lines = null;
		if (log.isLoggable(Level.FINE)) log.fine(processed + " - #" + no);
	}	//	setProcessed
		
	/**
	 * 	Before Save
	 *	@param newRecord
	 *	@return save
	 */
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		//	Changed from Not to Active
		if (!newRecord && is_ValueChanged("IsActive") && isActive())
		{
			log.severe ("Cannot Re-Activate deactivated Allocations");
			return false;
		}
		return true;
	}	//	beforeSave

	private List<Integer> m_bps_beforeDelete = new ArrayList<Integer>();

	/**
	 * 	Before Delete.
	 *	@return true if acct was deleted
	 */
	@Override
	protected boolean beforeDelete ()
	{
		String trxName = get_TrxName();
		if (trxName == null || trxName.length() == 0)
			log.warning ("No transaction");
		if (isPosted())
		{
			MPeriod.testPeriodOpen(getCtx(), getDateTrx(), MDocType.DOCBASETYPE_PaymentAllocation, getAD_Org_ID());
			setPosted(false);
			MFactAcct.deleteEx (Table_ID, get_ID(), trxName);
		}
		//	Mark as Inactive
		setIsActive(false);		//	updated DB for line delete/process
		this.saveEx();

		//	Unlink
		getLines(true);
		
		m_bps_beforeDelete.clear();
		for (int i = 0; i < m_lines.length; i++)
		{
			MAllocationLine line = m_lines[i];
			int C_BPartner_ID = line.getC_BPartner_ID();
			if (! m_bps_beforeDelete.contains(C_BPartner_ID))
				m_bps_beforeDelete.add(C_BPartner_ID);
			line.deleteEx(true, trxName);
		}
		return true;
	}	//	beforeDelete

	@Override
	protected boolean afterDelete(boolean success) {
		if (success) {
			for (int C_BPartner_ID : m_bps_beforeDelete) {
				MBPartner bpartner = new MBPartner(Env.getCtx(), C_BPartner_ID, get_TrxName());
				bpartner.setTotalOpenBalance();
				bpartner.saveEx();
			}
		}
		m_bps_beforeDelete.clear();
		return super.afterDelete(success);
	}

	/**
	 * 	After Save
	 *	@param newRecord
	 *	@param success
	 *	@return success
	 */
	@Override
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		return success;
	}	//	afterSave
	
	/**
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	@Override
	public boolean processIt (String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	@Override
	public boolean unlockIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	@Override
	public boolean invalidateIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt
	
	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	@Override
	public String prepareIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		//	Std Period open?
		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), MDocType.DOCBASETYPE_PaymentAllocation, getAD_Org_ID());
		getLines(true);
		if (m_lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		
		// Stop the Document Workflow if invoice to allocate is as paid
		if (!isReversal()) {
			for (MAllocationLine line :m_lines)
			{	
				if (line.getC_Invoice_ID() != 0)
				{
					StringBuilder whereClause = new StringBuilder(I_C_Invoice.COLUMNNAME_C_Invoice_ID).append("=? AND ") 
									   .append(I_C_Invoice.COLUMNNAME_IsPaid).append("=? AND ")
									   .append(I_C_Invoice.COLUMNNAME_DocStatus).append(" NOT IN (?,?)");
					boolean InvoiceIsPaid = new Query(getCtx(), I_C_Invoice.Table_Name, whereClause.toString(), get_TrxName())
					.setClient_ID()
					.setParameters(line.getC_Invoice_ID(), "Y", X_C_Invoice.DOCSTATUS_Voided, X_C_Invoice.DOCSTATUS_Reversed)
					.match();
					if (InvoiceIsPaid && line.getAmount().signum() > 0)
						throw new  AdempiereException("@ValidationError@ @C_Invoice_ID@ @IsPaid@");
				}
			}	
		}
		
		//	Add up Amounts & validate
		BigDecimal approval = Env.ZERO;
		for (int i = 0; i < m_lines.length; i++)
		{
			MAllocationLine line = m_lines[i];
			approval = approval.add(line.getWriteOffAmt()).add(line.getDiscountAmt());
			//	Make sure there is BP
			//	@ tegar - Bank/cash Transfer
			/*
			if (line.getC_BPartner_ID() == 0)
			{
				m_processMsg = "No Business Partner";
				return DocAction.STATUS_Invalid;
			}*/

			// IDEMPIERE-1850 - validate date against related docs
			if (line.getC_Invoice_ID() > 0) {
				if (line.getC_Invoice().getDateAcct().after(getDateAcct())) {
					m_processMsg = "Wrong allocation date";
					return DocAction.STATUS_Invalid;
				}
			}
			if (line.getC_Payment_ID() > 0) {
				if (line.getC_Payment().getDateAcct().after(getDateAcct())) {
					m_processMsg = "Wrong allocation date";
					return DocAction.STATUS_Invalid;
				}
			}
		}
		setApprovalAmt(approval);
		
		//@David
		MAllocationLine [] lines = getLines(true);
		int C_Invoice_ID=0;
		int C_Payment_ID=0;
		for (MAllocationLine line : lines) {
			if (line.getC_Invoice_ID()!=0){
				C_Invoice_ID=line.getC_Invoice_ID();
				break;
			}
            else if(line.getC_Payment_ID()!=0){
                C_Payment_ID=line.getC_Payment_ID();
                break;
            }
		}
		
        if (!(C_Invoice_ID==0 && C_Payment_ID==0)) {
			
			int C_BPartner_ID=0;
			MDocType docType=null;
			if (C_Invoice_ID!=0) {
				MInvoice inv = new MInvoice(getCtx(), C_Invoice_ID, get_TrxName());
				C_BPartner_ID=inv.getC_BPartner_ID();
				docType=(MDocType)inv.getC_DocType();
			}
            else{
                MPayment payment = new MPayment(getCtx(), C_Payment_ID, get_TrxName());
                C_BPartner_ID=payment.getC_BPartner_ID();
                docType=(MDocType)payment.getC_DocType();
            }
			
			MAcctSchema [] ass = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
			String sqlWhereCustomAcctCur="C_BPartner_ID="+C_BPartner_ID+" AND C_AcctSchema_ID=? AND C_Currency_ID=?";
			String customAcctTableName=null;
			if (docType.getDocBaseType().equals(MDocType.DOCBASETYPE_ARInvoice) ||
	                docType.getDocBaseType().equals(MDocType.DOCBASETYPE_ARCreditMemo) ||
	                docType.getDocBaseType().equals(MDocType.DOCBASETYPE_ARReceipt)){
				customAcctTableName=MBPCInvAcctByCurrency.Table_Name;
			}
			else if (docType.getDocBaseType().equals(MDocType.DOCBASETYPE_APInvoice) ||
                    docType.getDocBaseType().equals(MDocType.DOCBASETYPE_APCreditMemo)||
                    docType.getDocBaseType().equals(MDocType.DOCBASETYPE_APPayment)){
					customAcctTableName=MBPVInvAcctByCurrency.Table_Name;
			}
		
			for (MAcctSchema as : ass) {
			
				int invoiceCurrency_ID=getC_Currency_ID();
				int accountingSchemaCurrency_ID=as.getC_Currency_ID();					
				Timestamp cutOffDate = MSysConfig.getTimestampValue("Custom_Acct_By_BP_Currency_Cutoff_Date");
				ArrayList<Object> params;
			
				if(accountingSchemaCurrency_ID!=invoiceCurrency_ID && getDateAcct().after(cutOffDate)){
					params = new ArrayList<Object>();
					params.add(as.getC_AcctSchema_ID());
					params.add(invoiceCurrency_ID);
				
					boolean match = new Query(getCtx(), customAcctTableName, sqlWhereCustomAcctCur, get_TrxName())
					.setParameters(params)
					.setOnlyActiveRecords(true)
					.match();
				
					if (!match) {
						String curName=DB.getSQLValueString(get_TrxName(), "SELECT ISO_Code FROM C_Currency WHERE C_Currency_ID=?", invoiceCurrency_ID);
						throw new AdempiereException("Business Partner don't have required custom acct record for Acct Schema = "+as.getName()+" Currency = "+curName);
					}
				}
			}
		}
		//@David End
		//
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	@Override
	public boolean  approveIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	@Override
	public boolean rejectIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
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
		
		//	@tegar validation set allocationdate
		SetDefinitionAllocationDate();
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		if (log.isLoggable(Level.INFO)) log.info(toString());

		//	Link
		getLines(false);
		if(!updateBP())
			return DocAction.STATUS_Invalid;
		
		for (int i = 0; i < m_lines.length; i++)
		{
			MAllocationLine line = m_lines[i];
			line.processIt(isReversal());
		}		

		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		createMatchAllocation();
		
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Void Document.
	 * 	@return true if success 
	 */
	@Override
	public boolean voidIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		
		boolean retValue = false;
		if (DOCSTATUS_Closed.equals(getDocStatus())
			|| DOCSTATUS_Reversed.equals(getDocStatus())
			|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			m_processMsg = "Document Closed: " + getDocStatus();
			setDocAction(DOCACTION_None);
			return false;
		}

		//	Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
			|| DOCSTATUS_Invalid.equals(getDocStatus())
			|| DOCSTATUS_InProgress.equals(getDocStatus())
			|| DOCSTATUS_Approved.equals(getDocStatus())
			|| DOCSTATUS_NotApproved.equals(getDocStatus()) )
		{
			// Before Void
			m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
			if (m_processMsg != null)
				return false;

			//	Set lines to 0
			MAllocationLine[] lines = getLines(true);
			if(!updateBP())
				return false;
			
			for (int i = 0; i < lines.length; i++)
			{
				MAllocationLine line = lines[i];
				line.setAmount(Env.ZERO);
				line.setDiscountAmt(Env.ZERO);
				line.setWriteOffAmt(Env.ZERO);
				line.setOverUnderAmt(Env.ZERO);
				line.saveEx();
				// Unlink invoices
				line.processIt(true);
			}			
			
			addDescription(Msg.getMsg(getCtx(), "Voided"));
			retValue = true;
		}
		else
		{
			boolean accrual = false;
			try 
			{
				MPeriod.testPeriodOpen(getCtx(), getDateTrx(), MPeriodControl.DOCBASETYPE_PaymentAllocation, getAD_Org_ID());
			}
			catch (PeriodClosedException e) 
			{
				accrual = true;
			}
			
			if (accrual)
				return reverseAccrualIt();
			else
				return reverseCorrectIt();
		}

		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;
		
		setProcessed(true);
		setDocAction(DOCACTION_None);

		return retValue;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	@return true if success 
	 */
	@Override
	public boolean closeIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;

		setDocAction(DOCACTION_None);

		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;

		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction (using original DateAcct)
	 * 	@return true if success 
	 */
	@Override
	public boolean reverseCorrectIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		// Before reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
		
		boolean retValue = reverseIt(false);

		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
		
		setDocAction(DOCACTION_None);
		return retValue;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual (using current date as DateAcct)
	 * 	@return false 
	 */
	@Override
	public boolean reverseAccrualIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		boolean retValue = reverseIt(true);

		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		setDocAction(DOCACTION_None);
		return retValue;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return false 
	 */
	@Override
	public boolean reActivateIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		// Before reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (m_processMsg != null)
			return false;	
		
		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
			return false;
		
		return false;
	}	//	reActivateIt

	/**
	 * 	String Representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		StringBuilder sb = new StringBuilder ("MAllocationHdr[");
		sb.append(get_ID()).append("-").append(getSummary()).append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	@Override
	public String getDocumentInfo()
	{
		StringBuilder msgreturn = new StringBuilder().append(Msg.getElement(getCtx(), "C_AllocationHdr_ID")).append(" ").append(getDocumentNo());
		return msgreturn.toString();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	@Override
	public File createPDF ()
	{
		try
		{
			StringBuilder msgctf = new StringBuilder().append(get_TableName()).append(get_ID()).append("_");
			File temp = File.createTempFile(msgctf.toString(), ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF file. 
	 *	@param file output file
	 *	@return not implemented, always return null
	 */
	public File createPDF (File file)
	{
		return null;
	}	//	createPDF

	/**
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	@Override
	public String getSummary()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getDocumentNo());
		//	: Total Lines = 123.00 (#1)
		sb.append(": ")
			.append(Msg.translate(getCtx(),"ApprovalAmt")).append("=").append(getApprovalAmt())
			.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary
	
	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	@Override
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	@Override
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}	//	getDoc_User_ID

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription
	
	/**
	 * 	Reverse Allocation.
	 * 	Period needs to be open.
	 *  @param accrual true for reverse accrual (current date), false for reverse correct (original accounting date)
	 *	@return true if reversed
	 */
	private boolean reverseIt(boolean accrual) 
	{
		if (!isActive()
			|| getDocStatus().equals(DOCSTATUS_Voided)	// Goodwill.co.id
			|| getDocStatus().equals(DOCSTATUS_Reversed))
		{
			// Goodwill: don't throw exception here
			//	BF: Reverse is not allowed at Payment void when Allocation is already reversed at Invoice void
			//throw new IllegalStateException("Allocation already reversed (not active)");
			log.warning("Allocation already reversed (not active)");
			return true;
		}
	

		Timestamp reversalDate = accrual ? Env.getContextAsDate(getCtx(), Env.DATE) : getDateAcct();
		if (reversalDate == null) {
			reversalDate = new Timestamp(System.currentTimeMillis());
		}
		
		//	Can we delete posting
		MPeriod.testPeriodOpen(getCtx(), reversalDate, MPeriodControl.DOCBASETYPE_PaymentAllocation, getAD_Org_ID());

		if (accrual) 
		{
			//	Deep Copy
			MAllocationHdr reversal = copyFrom (this, reversalDate, reversalDate, get_TrxName());
			if (reversal == null)
			{
				m_processMsg = "Could not create Payment Allocation Reversal";
				return false;
			}
			reversal.setReversal_ID(getC_AllocationHdr_ID());

			//	Reverse Line Amt
			MAllocationLine[] rLines = reversal.getLines(false);
			for (MAllocationLine rLine : rLines) {
				rLine.setAmount(rLine.getAmount().negate());
				rLine.setDiscountAmt(rLine.getDiscountAmt().negate());
				rLine.setWriteOffAmt(rLine.getWriteOffAmt().negate());
				rLine.setOverUnderAmt(rLine.getOverUnderAmt().negate());
				if (!rLine.save(get_TrxName()))
				{
					m_processMsg = "Could not correct Payment Allocation Reversal Line";
					return false;
				}
			}
			reversal.setReversal(true);
			reversal.setDocumentNo(getDocumentNo()+"^");		
			reversal.addDescription("{->" + getDocumentNo() + ")");
			//
			if (!DocumentEngine.processIt(reversal, DocAction.ACTION_Complete))
			{
				m_processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
				return false;
			}

			DocumentEngine.processIt(reversal, DocAction.ACTION_Close);
			reversal.setProcessing (false);
			reversal.setDocStatus(DOCSTATUS_Reversed);
			reversal.setDocAction(DOCACTION_None);
			reversal.saveEx();
			m_processMsg = reversal.getDocumentNo();
			addDescription("(" + reversal.getDocumentNo() + "<-)");
			
			// @TommyAng
			String sqlDelete = "DELETE FROM T_MatchAllocation WHERE C_AllocationHDR_ID="
					+ getC_AllocationHdr_ID();
			int no = DB.executeUpdate(sqlDelete, get_TrxName());
			log.info("Deleted Match Allocation #" + no);
			
			sqlDelete = "DELETE FROM T_MatchAllocation WHERE C_AllocationHDR_ID="
					+ reversal.get_ID();
			no = DB.executeUpdate(sqlDelete, get_TrxName());
			log.info("Deleted Match Allocation #" + no);
			// end @TommyAng
		}
		else
		{
			//	Set Inactive
			setIsActive (false);
			if ( !isPosted() )
				setPosted(true);
			setDocumentNo(getDocumentNo()+"^");
			setDocStatus(DOCSTATUS_Reversed);	//	for direct calls
			if (!save() || isActive())
				throw new IllegalStateException("Cannot de-activate allocation");
				
			//	Delete Posting
			MFactAcct.deleteEx(MAllocationHdr.Table_ID, getC_AllocationHdr_ID(), get_TrxName());
			
			//	Unlink Invoices
			getLines(true);
			if(!updateBP())
				return false;
			
			for (int i = 0; i < m_lines.length; i++)
			{
				MAllocationLine line = m_lines[i];
				line.setIsActive(false);
				line.setAmount(Env.ZERO);
				line.setDiscountAmt(Env.ZERO);
				line.setWriteOffAmt(Env.ZERO);
				line.setOverUnderAmt(Env.ZERO);
				line.saveEx();
				line.processIt(true);	//	reverse
			}			
			
			addDescription(Msg.getMsg(getCtx(), "Voided"));
		}
		
		setProcessed(true);
		setDocStatus(DOCSTATUS_Reversed);	//	may come from void
		setDocAction(DOCACTION_None);
		return true;
	}	//	reverse

	/**
	 * Update open balance of BP 
	 * @return true if updated successfully
	 */
	private boolean updateBP()
	{
		List<Integer> bps = new ArrayList<Integer>();
		getLines(false);
		for (MAllocationLine line : m_lines) {
			int C_BPartner_ID = line.getC_BPartner_ID();
			if (! bps.contains(C_BPartner_ID)) {
				bps.add(C_BPartner_ID);
				MBPartner bpartner = new MBPartner(Env.getCtx(), C_BPartner_ID, get_TrxName());
				bpartner.setTotalOpenBalance();
				bpartner.saveEx();
			}
		} // for all lines
		return true;
	}	//	updateBP
	
	/**
	 * 	Document Status is Complete, Closed or Reversed
	 *	@return true if CO, CL or RE
	 */
	public boolean isComplete()
	{
		String ds = getDocStatus();
		return DOCSTATUS_Completed.equals(ds) 
			|| DOCSTATUS_Closed.equals(ds)
			|| DOCSTATUS_Reversed.equals(ds);
	}	//	isComplete

	/**
	 * 	Create new Allocation by copying
	 * 	@param from source allocation to copy from
	 * 	@param dateAcct date of the document accounting date
	 *  @param dateTrx date of the document transaction.
	 * 	@param trxName
	 *	@return Allocation
	 */
	public static MAllocationHdr copyFrom (MAllocationHdr from, Timestamp dateAcct, Timestamp dateTrx,
		String trxName)
	{
		MAllocationHdr to = new MAllocationHdr (from.getCtx(), 0, trxName);
		PO.copyValues (from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.set_ValueNoCheck ("DocumentNo", null);
		//
		to.setDocStatus (DOCSTATUS_Drafted);		//	Draft
		to.setDocAction(DOCACTION_Complete);
		//
		to.setDateTrx (dateAcct);
		to.setDateAcct (dateTrx);
		to.setIsManual(false);
		//
		to.setIsApproved (false);
		//
		to.setPosted (false);
		to.setProcessed (false);

		to.saveEx();

		//	Lines
		if (to.copyLinesFrom(from) == 0)
			throw new AdempiereException("Could not create Allocation Lines");

		return to;
	}	//	copyFrom
	
	/**
	 * 	Copy Lines From other Allocation.
	 *	@param otherAllocation other allocation to copy from
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MAllocationHdr otherAllocation)
	{
		if (isProcessed() || isPosted() || (otherAllocation == null))
			return 0;
		MAllocationLine[] fromLines = otherAllocation.getLines(false);
		int count = 0;
		for (MAllocationLine fromLine : fromLines) {
			MAllocationLine line = new MAllocationLine (getCtx(), 0, get_TrxName());
			PO.copyValues (fromLine, line, fromLine.getAD_Client_ID(), fromLine.getAD_Org_ID());
			line.setC_AllocationHdr_ID(getC_AllocationHdr_ID());
			line.setParent(this);
			line.set_ValueNoCheck ("C_AllocationLine_ID", I_ZERO);	// new

			if (line.getC_Payment_ID() != 0)
			{
				MPayment payment = new MPayment(getCtx(), line.getC_Payment_ID(), get_TrxName());
				if (DOCSTATUS_Reversed.equals(payment.getDocStatus()))
				{
					MPayment reversal = (MPayment) payment.getReversal();
					if (reversal != null)
					{
						line.setPaymentInfo(reversal.getC_Payment_ID(), 0);
					}
				}				
			}

			line.saveEx();
			count++;
		}
		if (fromLines.length != count)
			log.log(Level.WARNING, "Line difference - From=" + fromLines.length + " <> Saved=" + count);
		return count;
	}	//	copyLinesFrom
	
	// Goodwill.co.id
	/** Reversal Flag		*/
	private boolean m_reversal = false;
	
	/**
	 * 	Set Reversal
	 *	@param reversal reversal
	 */
	private void setReversal(boolean reversal)
	{
		m_reversal = reversal;
	}	//	setReversal
	
	/**
	 * 	Is Reversal
	 *	@return true if it is a reversal document
	 */
	private boolean isReversal()
	{
		return m_reversal;
	}	//	isReversal

	/** 
	 * @param bpartnerID
	 * @param trxName
	 * @return Returns a description parsing the bpartner in allocation header and then the allocation document itself 
	 */
	public String getDescriptionForManualAllocation(int bpartnerID, String trxName)
	{
		String sysconfig_desc = MSysConfig.getValue(MSysConfig.ALLOCATION_DESCRIPTION, "@#AD_User_Name@", getAD_Client_ID());
		String description = "";
		if (sysconfig_desc.contains("@")) {
			description = Env.parseVariable(sysconfig_desc, new MBPartner(getCtx(), bpartnerID, null), trxName, true);
			description = Env.parseVariable(description, this, trxName, true);
			description = Msg.parseTranslation(getCtx(), description);
		} else
			description = Env.getContext(getCtx(), Env.AD_USER_NAME); // just to be sure

		return description;
	}
	
	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {
		
		for (int i = 0; i < options.length; i++) {
			options[i] = null;
		}

		index = 0;

		if (docStatus.equals(DocAction.STATUS_Drafted)) {
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
		} else if (docStatus.equals(DocAction.STATUS_InProgress)) {
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
		} else if (docStatus.equals(DocAction.STATUS_Completed)) {
			options[index++] = DocAction.ACTION_Reverse_Accrual;
		} else if (docStatus.equals(DocAction.STATUS_Invalid)) {
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
		}

		
		return index;
	}
	
	//@tegar set Allocationdate when giro transaction
	private void SetDefinitionAllocationDate(){
		if (!MSysConfig.getBooleanValue(MSysConfig.GIRO_ALLOCATE_ON_MATURITY_DATE, true, getAD_Client_ID()))
			return;
			
		int C_Payment_ID = 0;
		int C_Invoice_ID = 0;
		int C_AllocationLine_ID = 0;
		MPayment payment = null;

		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT C_AllocationLine_ID ");
		sql.append(" FROM C_AllocationLine ");
		sql.append(" WHERE C_AllocationHdr_ID = ? ");
		
		C_AllocationLine_ID = DB.getSQLValueEx(get_TrxName(), sql.toString(), getC_AllocationHdr_ID());

		MAllocationLine line = new MAllocationLine(getCtx(), C_AllocationLine_ID, get_TrxName());
		C_Invoice_ID = line.getC_Invoice_ID();
		C_Payment_ID = line.getC_Payment_ID();
		
		if (C_Payment_ID > 0)
			payment = new MPayment(getCtx(), C_Payment_ID, get_TrxName());
		
				
		if (C_Invoice_ID> 0 && C_Payment_ID > 0 
				&& payment.isReceipt() 
				&& payment.getTenderType().equals(MPayment.TENDERTYPE_Giro)){
			
			setDateAcct(payment.getMaturityDate());
			setDateTrx(payment.getMaturityDate());
			saveEx();
		}
	}
	
	/**
	 * @author TommyAng
	 * source : Allocation Line
	 * include : Invoice (Customer), Invoice (Vendor), AR Credit Memo, AP Credit Memo, AR Receipt, AP Payment
	 * return new MatchAllocation
	 */
	private void createMatchAllocation(){
		
		//Split Up ID and Amount Based On Amount, as ArrayList
		
		//Invoice
		ArrayList<Integer> plusInvoiceID = new ArrayList<Integer>();
		ArrayList<Integer> minInvoiceID = new ArrayList<Integer>();
		
		ArrayList<BigDecimal> plusAmount = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> minAmount = new ArrayList<BigDecimal>();
		
		ArrayList<BigDecimal> pDiscountAmt = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> nDiscountAmt = new ArrayList<BigDecimal>();
		
		ArrayList<BigDecimal> pWriteOffAmt = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> nWriteOffAmt = new ArrayList<BigDecimal>();
		//
		
		//Payment
		ArrayList<Integer> paymentID = new ArrayList<Integer>();
		ArrayList<Integer> receiptID = new ArrayList<Integer>();
		
		ArrayList<BigDecimal> paymentAmount = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> receiptAmount = new ArrayList<BigDecimal>();
		//
		
		//Charge
		int chargeID = 0;
		BigDecimal chargeAmount = Env.ZERO;
		//
		
		int plusI = 0, minI = 0, pay = 0, rec = 0, charge = 0, count = 0, matched = 0;
				
		//Line loop to get record
		//1. If Line only have Invoice ID without Payment ID and Amount is +
		//2. If Line only have Invoice ID without Payment ID and Amount is -
		//3. If Line only have Payment ID without Invoice ID and Payment is AP Payment
		//4. If Line only have Payment ID without Invoice ID and Payment is AR Receipt
		//5. If Line is Charge
		//6. If Line have both Invoice ID and Payment ID and Payment is AP Payment
		//7. If Line have both Invoice ID and Payment ID and Payment is AR Receipt
		for(MAllocationLine line : getLines(true)){
			
			if(line.getC_Payment_ID()==0 && line.getC_Invoice_ID()>0 && line.getAmount().compareTo(Env.ZERO)>0){					//1
				plusInvoiceID.add(line.getC_Invoice_ID());
				plusAmount.add(line.getAmount());
				pDiscountAmt.add(line.getDiscountAmt());
				pWriteOffAmt.add(line.getWriteOffAmt());
				plusI++;
			}
			else if(line.getC_Payment_ID()==0 && line.getC_Invoice_ID()>0 && line.getAmount().compareTo(Env.ZERO)<0){				//2
				minInvoiceID.add(line.getC_Invoice_ID());
				minAmount.add(line.getAmount());
				nDiscountAmt.add(line.getDiscountAmt());
				nWriteOffAmt.add(line.getWriteOffAmt());
				minI++;
			}			
			else if(line.getC_Invoice_ID()==0 && line.getC_Payment_ID()>0 && line.getC_Payment().getC_DocType().getDocBaseType().equals("APP")){				//3
				paymentID.add(line.getC_Payment_ID());
				paymentAmount.add(line.getAmount().abs());
				pay++;
			}
			else if(line.getC_Invoice_ID()==0 && line.getC_Payment_ID()>0 && line.getC_Payment().getC_DocType().getDocBaseType().equals("ARR")){				//4
				receiptID.add(line.getC_Payment_ID());
				receiptAmount.add(line.getAmount().abs().negate());
				rec++;
			}
			else if(line.getC_Charge_ID()>0){																						//5
				chargeID = line.getC_Charge_ID();
				chargeAmount = line.getAmount();
				charge++;
			}else if(line.getAmount().compareTo(Env.ZERO)!=0){
				X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
				match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
				
				//invoice
				match.set_CustomColumn("C_Invoice_ID", line.getC_Invoice_ID());
				match.set_CustomColumn("DiscountAmt", line.getDiscountAmt());
				match.set_CustomColumn("WriteOffAmt", line.getWriteOffAmt());
				
				MInvoice invoice = new MInvoice(getCtx(), line.getC_Invoice_ID(), get_TrxName());
				match.set_CustomColumn("C_DocType_ID", invoice.getC_DocType_ID());
				
				//payment
				match.set_CustomColumn("C_Payment_ID", line.getC_Payment_ID());
				
				MPayment payment = new MPayment(getCtx(), line.getC_Payment_ID(), get_TrxName());
				match.set_CustomColumn("Match_DocType_ID", payment.getC_DocType_ID());

				match.set_ValueOfColumn("DateAllocated", getCreated());
				match.set_CustomColumn("AllocationAmt", line.getAmount().abs());					
				match.saveEx();
				matched++;
				
				/*
				if(line.getC_Payment().getC_DocType().getDocBaseType().equals("APP")){												//6
					
					//match.set_CustomColumn("P_Payment_ID", line.getC_Payment_ID());
					//match.set_CustomColumn("P_Amount", line.getAmount().abs());
					//match.set_CustomColumn("N_Invoice_ID", line.getC_Invoice_ID());
					//match.set_CustomColumn("N_Amount", line.getAmount().abs().negate());
					match.set_CustomColumn("C_DiscountAmt", line.getDiscountAmt());
					match.set_CustomColumn("C_WriteOffAmt", line.getWriteOffAmt());
					
					//MPayment pPayment = new MPayment(getCtx(), line.getC_Payment_ID(), get_TrxName());
					//match.set_CustomColumn("P_DocType_ID", pPayment.getC_DocType_ID());
					
					//MInvoice nInvoice = new MInvoice(getCtx(), line.getC_Invoice_ID(), get_TrxName());
					//match.set_CustomColumn("N_DocType_ID", nInvoice.getC_DocType_ID());
					
				}else if(line.getC_Payment().getC_DocType().getDocBaseType().equals("ARR")){										//7
					
					//match.set_CustomColumn("N_Payment_ID", line.getC_Payment_ID());
					//match.set_CustomColumn("N_Amount", line.getAmount().abs().negate());
					//match.set_CustomColumn("P_Invoice_ID", line.getC_Invoice_ID());
					//match.set_CustomColumn("P_Amount", line.getAmount().abs());
					match.set_CustomColumn("P_DiscountAmt", line.getDiscountAmt());
					match.set_CustomColumn("P_WriteOffAmt", line.getWriteOffAmt());
					
					//MPayment nPayment = new MPayment(getCtx(), line.getC_Payment_ID(), get_TrxName());
					//match.set_CustomColumn("N_DocType_ID", nPayment.getC_DocType_ID());
										
					//MInvoice pInvoice = new MInvoice(getCtx(), line.getC_Invoice_ID(), get_TrxName());
					//match.set_CustomColumn("P_DocType_ID", pInvoice.getC_DocType_ID());
					
				}
				*/
			}
			count++;
		}
		
		int total = plusI+minI+pay+rec+charge;
		count -= matched;
		
		BigDecimal tempPaymentAmt = Env.ZERO;
		BigDecimal tempInvoiceAmt = Env.ZERO;
		
		//Loop for pInvoice
		for(int i = 0;i<plusI;i++){
			tempInvoiceAmt = plusAmount.get(i);
			
			if(!plusInvoiceID.isEmpty() && !minInvoiceID.isEmpty()){
								
				for(int j = 0;j<minInvoiceID.size();j++){
					if(j==0 && minAmount.get(j).compareTo(Env.ZERO)<0){
						//match
						X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
						match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
						
						//invoice
						match.set_CustomColumn("C_Invoice_ID", plusInvoiceID.get(i));
						match.set_CustomColumn("DiscountAmt", pDiscountAmt.get(i));
						match.set_CustomColumn("WriteOffAmt", pWriteOffAmt.get(i));
						
						MInvoice pInvoice = new MInvoice(getCtx(), plusInvoiceID.get(i), get_TrxName());
						match.set_CustomColumn("C_DocType_ID", pInvoice.getC_DocType_ID());
						
						//match.set_CustomColumn("P_Invoice_ID", plusInvoiceID.get(i));
						//match.set_CustomColumn("P_Amount", plusAmount.get(i));
						//match.set_CustomColumn("N_Invoice_ID", minInvoiceID.get(j));
						//match.set_CustomColumn("N_Amount", minAmount.get(j));
						
						//invoice1
						match.set_CustomColumn("Match_Invoice_ID", minInvoiceID.get(j));
						match.set_CustomColumn("Match_DiscountAmt", nDiscountAmt.get(j));
						match.set_CustomColumn("Match_WriteOffAmt", nWriteOffAmt.get(j));
						
						MInvoice nInvoice = new MInvoice(getCtx(), minInvoiceID.get(j), get_TrxName());
						match.set_CustomColumn("Match_DocType_ID", nInvoice.getC_DocType_ID());
						
						if(tempInvoiceAmt.compareTo(minAmount.get(j).abs())>0)
							match.set_CustomColumn("AllocationAmt", minAmount.get(j).abs());	
						else
							match.set_CustomColumn("AllocationAmt", tempInvoiceAmt);
						
						match.set_ValueOfColumn("DateAllocated", getCreated());
						match.saveEx();
						
						//match1
						X_T_MatchAllocation match1 = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
						match1.set_CustomColumn("C_AllocationHdr_ID", get_ID());
						
						//invoice
						match1.set_CustomColumn("C_Invoice_ID", minInvoiceID.get(j));
						match1.set_CustomColumn("C_DocType_ID", nInvoice.getC_DocType_ID());
						match1.set_CustomColumn("DiscountAmt", nDiscountAmt.get(j));
						match1.set_CustomColumn("WriteOffAmt", nWriteOffAmt.get(j));
						
						//invoice1
						match1.set_CustomColumn("Match_Invoice_ID", plusInvoiceID.get(i));
						match1.set_CustomColumn("Match_DocType_ID", pInvoice.getC_DocType_ID());
						match1.set_CustomColumn("Match_DiscountAmt", pDiscountAmt.get(i));
						match1.set_CustomColumn("Match_WriteOffAmt", pWriteOffAmt.get(i));
						
						//match1.set_CustomColumn("P_Invoice_ID", plusInvoiceID.get(i));
						//match1.set_CustomColumn("P_Amount", plusAmount.get(i));
						//match1.set_CustomColumn("N_Invoice_ID", minInvoiceID.get(j));
						//match1.set_CustomColumn("N_Amount", minAmount.get(j));
						
						if(tempInvoiceAmt.compareTo(minAmount.get(j).abs())>0)
							match1.set_CustomColumn("AllocationAmt", minAmount.get(j).abs());	
						else
							match1.set_CustomColumn("AllocationAmt", tempInvoiceAmt);
						
						
						
						match1.set_ValueOfColumn("DateAllocated", getCreated());
						match1.saveEx();
						//
						pDiscountAmt.set(i, Env.ZERO);
						pWriteOffAmt.set(i, Env.ZERO);
						nDiscountAmt.set(j, Env.ZERO);
						nWriteOffAmt.set(j, Env.ZERO);
						
					}
					
					
					if(j!=0 && tempInvoiceAmt.compareTo(Env.ZERO)>0 
							&& minAmount.get(j).compareTo(Env.ZERO)<0){
						//match
						X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
						match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
						
						//invoice
						match.set_CustomColumn("C_Invoice_ID", plusInvoiceID.get(i));
						match.set_CustomColumn("DiscountAmt", pDiscountAmt.get(i));
						match.set_CustomColumn("WriteOffAmt", pWriteOffAmt.get(i));
						
						MInvoice pInvoice = new MInvoice(getCtx(), plusInvoiceID.get(i), get_TrxName());
						match.set_CustomColumn("C_DocType_ID", pInvoice.getC_DocType_ID());
						
						//invoice1
						match.set_CustomColumn("Match_Invoice_ID", minInvoiceID.get(j));
						match.set_CustomColumn("Match_DiscountAmt", nDiscountAmt.get(j));
						match.set_CustomColumn("Match_WriteOffAmt", nWriteOffAmt.get(j));
						
						MInvoice nInvoice = new MInvoice(getCtx(), minInvoiceID.get(j), get_TrxName());
						match.set_CustomColumn("Match_DocType_ID", nInvoice.getC_DocType_ID());
						
						//match.set_CustomColumn("P_Invoice_ID", plusInvoiceID.get(i));
						//match.set_CustomColumn("P_Amount", plusAmount.get(i));
						//match.set_CustomColumn("N_Invoice_ID", minInvoiceID.get(j));
						//match.set_CustomColumn("N_Amount", minAmount.get(j));
						
						if(tempInvoiceAmt.compareTo(minAmount.get(j).abs())>0)
							match.set_CustomColumn("AllocationAmt", minAmount.get(j).abs());	
						else
							match.set_CustomColumn("AllocationAmt", tempInvoiceAmt);
						
						match.set_ValueOfColumn("DateAllocated", getCreated());
						match.saveEx();
						
						//match1				
						X_T_MatchAllocation match1 = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
						match1.set_CustomColumn("C_AllocationHdr_ID", get_ID());
						
						
						//invoice
						match1.set_CustomColumn("C_Invoice_ID", minInvoiceID.get(j));
						match1.set_CustomColumn("DiscountAmt", nDiscountAmt.get(j));
						match1.set_CustomColumn("WriteOffAmt", nWriteOffAmt.get(j));
						match1.set_CustomColumn("C_DocType_ID", nInvoice.getC_DocType_ID());
						
						//invoice1
						match1.set_CustomColumn("Match_Invoice_ID", plusInvoiceID.get(i));
						match1.set_CustomColumn("Match_DocType_ID", pInvoice.getC_DocType_ID());
						match1.set_CustomColumn("Match_DiscountAmt", pDiscountAmt.get(i));
						match1.set_CustomColumn("Match_WriteOffAmt", pWriteOffAmt.get(i));
						
						//match1.set_CustomColumn("P_Invoice_ID", plusInvoiceID.get(i));
						//match1.set_CustomColumn("P_Amount", plusAmount.get(i));
						//match1.set_CustomColumn("N_Invoice_ID", minInvoiceID.get(j));
						//match1.set_CustomColumn("N_Amount", minAmount.get(j));
						
						//MInvoice pInvoice = new MInvoice(getCtx(), plusInvoiceID.get(i), get_TrxName());
						//match1.set_CustomColumn("N_DocType_ID", pInvoice.getC_DocType_ID());
						
						//MInvoice nInvoice = new MInvoice(getCtx(), minInvoiceID.get(j), get_TrxName());
						//match1.set_CustomColumn("P_DocType_ID", nInvoice.getC_DocType_ID());
						
						if(tempInvoiceAmt.compareTo(minAmount.get(j).abs())>0)
							match1.set_CustomColumn("AllocationAmt", minAmount.get(j).abs());	
						else
							match1.set_CustomColumn("AllocationAmt", tempInvoiceAmt);
						
						match1.set_ValueOfColumn("DateAllocated", getCreated());
						match1.saveEx();
						//
						pDiscountAmt.set(i, Env.ZERO);
						pWriteOffAmt.set(i, Env.ZERO);
						nDiscountAmt.set(j, Env.ZERO);
						nWriteOffAmt.set(j, Env.ZERO);
													
					}
					
					tempInvoiceAmt = tempInvoiceAmt.add(minAmount.get(j));
					
					if(tempInvoiceAmt.compareTo(Env.ZERO)>0){
						minAmount.set(j, Env.ZERO);
					}else if(tempInvoiceAmt.compareTo(Env.ZERO)<0){
						plusAmount.set(i, Env.ZERO);
						minAmount.set(j, tempInvoiceAmt);
						break;
					}else if(tempInvoiceAmt.compareTo(Env.ZERO)==0){
						plusAmount.set(i, Env.ZERO);
						minAmount.set(j, Env.ZERO);
						break;
					}
				}
				
			}
			
			BigDecimal totalMinAmount = Env.ZERO;
			for(int k=0; k<minInvoiceID.size(); k++)
			{
				totalMinAmount = totalMinAmount.add(minAmount.get(k));
			}
			
			if(totalMinAmount.compareTo(Env.ZERO)==0 && chargeID>0 && tempInvoiceAmt.compareTo(Env.ZERO)>0 && chargeAmount.compareTo(Env.ZERO)>0){					
				X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
				match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
				
				match.set_CustomColumn("C_Invoice_ID", plusInvoiceID.get(i));
				match.set_CustomColumn("DiscountAmt", pDiscountAmt.get(i));
				match.set_CustomColumn("WriteOffAmt", pWriteOffAmt.get(i));
				
				MInvoice pInvoice = new MInvoice(getCtx(), plusInvoiceID.get(i), get_TrxName());
				match.set_CustomColumn("C_DocType_ID", pInvoice.getC_DocType_ID());
				
				//match.set_CustomColumn("P_Invoice_ID", plusInvoiceID.get(i));
				//match.set_CustomColumn("P_Amount", tempInvoiceAmt);
				match.set_CustomColumn("C_Charge_ID", chargeID);
				//match.set_CustomColumn("N_Amount", tempInvoiceAmt.abs().negate());
				
				match.set_CustomColumn("AllocationAmt", tempInvoiceAmt.abs());
				match.set_ValueOfColumn("DateAllocated", getCreated());
				match.saveEx();
				
				chargeAmount = chargeAmount.add(tempInvoiceAmt);
				
				pDiscountAmt.set(i, Env.ZERO);
				pWriteOffAmt.set(i, Env.ZERO);
				
			} else if(!plusInvoiceID.isEmpty() && minInvoiceID.isEmpty() && chargeID>0){
				X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
				match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
				match.set_CustomColumn("C_Invoice_ID", plusInvoiceID.get(i));
				match.set_CustomColumn("DiscountAmt", pDiscountAmt.get(i));
				match.set_CustomColumn("WriteOffAmt", pWriteOffAmt.get(i));
				
				MInvoice pInvoice = new MInvoice(getCtx(), plusInvoiceID.get(i), get_TrxName());
				match.set_CustomColumn("C_DocType_ID", pInvoice.getC_DocType_ID());
				
				//match.set_CustomColumn("P_Invoice_ID", plusInvoiceID.get(i));
				//match.set_CustomColumn("P_Amount", tempInvoiceAmt);
				match.set_CustomColumn("C_Charge_ID", chargeID);
				//match.set_CustomColumn("N_Amount", tempInvoiceAmt.abs().negate());
				
				//MInvoice pInvoice = new MInvoice(getCtx(), plusInvoiceID.get(i), get_TrxName());
				//match.set_CustomColumn("P_DocType_ID", pInvoice.getC_DocType_ID());
				
				match.set_CustomColumn("AllocationAmt", chargeAmount.abs());
				match.set_ValueOfColumn("DateAllocated", getCreated());
				match.saveEx();
				
				chargeAmount = chargeAmount.add(tempInvoiceAmt);
				
				pDiscountAmt.set(i, Env.ZERO);
				pWriteOffAmt.set(i, Env.ZERO);
				
			} /*else if(plusInvoiceID.isEmpty() && !minInvoiceID.isEmpty() && chargeID>0){
				X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
				match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
				match.set_CustomColumn("N_Invoice_ID", minInvoiceID.get(0));
				match.set_CustomColumn("N_Amount", minAmount.get(0));
				match.set_CustomColumn("C_Charge_ID", chargeID);
				match.set_CustomColumn("P_Amount", chargeAmount);
				
				MInvoice nInvoice = new MInvoice(getCtx(), minInvoiceID.get(0), get_TrxName());
				match.set_CustomColumn("N_DocType_ID", nInvoice.getC_DocType_ID());
				
				match.set_CustomColumn("AllocationAmt", chargeAmount.abs());
				
				match.set_CustomColumn("N_DiscountAmt", nDiscountAmt.get(0));
				match.set_CustomColumn("N_WriteOffAmt", nWriteOffAmt.get(0));
				
				nDiscountAmt.set(0, Env.ZERO);
				nWriteOffAmt.set(0, Env.ZERO);
				
				match.saveEx();
			}*/
		}
		//End pInvoice Loop
		
		//Loop for Payment
		for(int i = 0;i<pay;i++){
			tempPaymentAmt = paymentAmount.get(i);
			
			if(!paymentID.isEmpty() && !receiptID.isEmpty()){
				
				for(int j = 0;j<receiptID.size();j++){
					
					if(j==0 && receiptAmount.get(j).compareTo(Env.ZERO)<0){
						//match
						X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
						match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
						
						//payment
						match.set_CustomColumn("C_Payment_ID", paymentID.get(i));
						
						MPayment pPayment = new MPayment(getCtx(), paymentID.get(i), get_TrxName());
						match.set_CustomColumn("C_DocType_ID", pPayment.getC_DocType_ID());
						
						//receipt
						match.set_CustomColumn("Match_Payment_ID", receiptID.get(j));
						
						MPayment nPayment = new MPayment(getCtx(), receiptID.get(j), get_TrxName());
						match.set_CustomColumn("Match_DocType_ID", nPayment.getC_DocType_ID());
						//match.set_CustomColumn("P_Payment_ID", paymentID.get(i));
						//match.set_CustomColumn("P_Amount", paymentAmount.get(i));
						//match.set_CustomColumn("N_Payment_ID", receiptID.get(j));
						//match.set_CustomColumn("N_Amount", receiptAmount.get(j));
						
						if(tempPaymentAmt.compareTo(receiptAmount.get(j).abs())>0)
							match.set_CustomColumn("AllocationAmt", receiptAmount.get(j).abs());	
						else
							match.set_CustomColumn("AllocationAmt", tempPaymentAmt);
													
						match.set_ValueOfColumn("DateAllocated", getCreated());							
						match.saveEx();
						
						//match1
						X_T_MatchAllocation match1 = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
						match1.set_CustomColumn("C_AllocationHdr_ID", get_ID());
						
						//receipt
						match1.set_CustomColumn("C_Payment_ID", receiptID.get(j));
						match1.set_CustomColumn("C_DocType_ID", nPayment.getC_DocType_ID());
						
						//payment
						match1.set_CustomColumn("Match_Payment_ID", paymentID.get(i));
						match1.set_CustomColumn("Match_DocType_ID", pPayment.getC_DocType_ID());
						
						//match.set_CustomColumn("P_Payment_ID", paymentID.get(i));
						//match.set_CustomColumn("P_Amount", paymentAmount.get(i));
						//match.set_CustomColumn("N_Payment_ID", receiptID.get(j));
						//match.set_CustomColumn("N_Amount", receiptAmount.get(j));
						
						if(tempPaymentAmt.compareTo(receiptAmount.get(j).abs())>0)
							match1.set_CustomColumn("AllocationAmt", receiptAmount.get(j).abs());	
						else
							match1.set_CustomColumn("AllocationAmt", tempPaymentAmt);
													
						match1.set_ValueOfColumn("DateAllocated", getCreated());							
						match1.saveEx();
					}
					
					
					if(j!=0 && tempPaymentAmt.compareTo(Env.ZERO)>0 
							&& receiptAmount.get(j).compareTo(Env.ZERO)<0){
						//match
						X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
						match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
						
						//payment
						match.set_CustomColumn("C_Payment_ID", paymentID.get(i));
						
						MPayment pPayment = new MPayment(getCtx(), paymentID.get(i), get_TrxName());
						match.set_CustomColumn("C_DocType_ID", pPayment.getC_DocType_ID());
						
						//receipt
						match.set_CustomColumn("Match_Payment_ID", receiptID.get(j));
						
						MPayment nPayment = new MPayment(getCtx(), receiptID.get(j), get_TrxName());
						match.set_CustomColumn("Match_DocType_ID", nPayment.getC_DocType_ID());
						//match.set_CustomColumn("P_Payment_ID", paymentID.get(i));
						//match.set_CustomColumn("P_Amount", tempPaymentAmt);
						//match.set_CustomColumn("N_Payment_ID", receiptID.get(j));
						//match.set_CustomColumn("N_Amount", receiptAmount.get(j));
						
						if(tempPaymentAmt.compareTo(receiptAmount.get(j).abs())>0)
							match.set_CustomColumn("AllocationAmt", receiptAmount.get(j).abs());	
						else
							match.set_CustomColumn("AllocationAmt", tempPaymentAmt);
													
						match.set_ValueOfColumn("DateAllocated", getCreated());							
						match.saveEx();
						
						//match1
						X_T_MatchAllocation match1 = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
						match1.set_CustomColumn("C_AllocationHdr_ID", get_ID());
						
						//receipt
						match1.set_CustomColumn("C_Payment_ID", receiptID.get(j));
						match1.set_CustomColumn("C_DocType_ID", nPayment.getC_DocType_ID());
						
						//payment
						match1.set_CustomColumn("Match_Payment_ID", paymentID.get(i));
						match1.set_CustomColumn("Match_DocType_ID", pPayment.getC_DocType_ID());
						
						//match.set_CustomColumn("P_Payment_ID", paymentID.get(i));
						//match.set_CustomColumn("P_Amount", tempPaymentAmt);
						//match.set_CustomColumn("N_Payment_ID", receiptID.get(j));
						//match.set_CustomColumn("N_Amount", receiptAmount.get(j));
							
						if(tempPaymentAmt.compareTo(receiptAmount.get(j).abs())>0)
							match1.set_CustomColumn("AllocationAmt", receiptAmount.get(j).abs());	
						else
							match1.set_CustomColumn("AllocationAmt", tempPaymentAmt);
													
						match1.set_ValueOfColumn("DateAllocated", getCreated());
						match1.saveEx();
					}
					
					tempPaymentAmt = tempPaymentAmt.add(receiptAmount.get(j));
					
					if(tempPaymentAmt.compareTo(Env.ZERO)>0){
						receiptAmount.set(j, Env.ZERO);
					}else if(tempPaymentAmt.compareTo(Env.ZERO)<0){
						paymentAmount.set(i, Env.ZERO);
						receiptAmount.set(j, tempPaymentAmt);
						break;
					}else if(tempPaymentAmt.compareTo(Env.ZERO)==0){
						paymentAmount.set(i, Env.ZERO);
						receiptAmount.set(j, Env.ZERO);
						break;
					}
				}
			}
			
			BigDecimal totalReceiptAmount = Env.ZERO;
			for(int k=0; k<minInvoiceID.size(); k++)
			{
				totalReceiptAmount = totalReceiptAmount.add(minAmount.get(k));
			}
						
			if(totalReceiptAmount.compareTo(Env.ZERO)==0 && chargeID>0 && tempPaymentAmt.compareTo(Env.ZERO)>0 && chargeAmount.compareTo(Env.ZERO)>0){					
				X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
				match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
				
				//payment
				match.set_CustomColumn("C_Payment_ID", paymentID.get(i));
				
				MPayment pPayment = new MPayment(getCtx(), paymentID.get(i), get_TrxName());
				match.set_CustomColumn("C_DocType_ID", pPayment.getC_DocType_ID());
				//match.set_CustomColumn("P_Payment_ID", paymentID.get(i));
				//match.set_CustomColumn("P_Amount", tempPaymentAmt);
				
				//charge
				match.set_CustomColumn("C_Charge_ID", chargeID);
				//match.set_CustomColumn("N_Amount", tempPaymentAmt.abs().negate());
				
				match.set_CustomColumn("AllocationAmt", tempPaymentAmt.abs());
				match.set_ValueOfColumn("DateAllocated", getCreated());				
				match.saveEx();
				
				chargeAmount = chargeAmount.abs().subtract(tempPaymentAmt.abs());
									
			} else if(!paymentID.isEmpty() && receiptID.isEmpty() && chargeID>0){
				X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
				match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
				
				//payment
				match.set_CustomColumn("C_Payment_ID", paymentID.get(i));
				
				MPayment pPayment = new MPayment(getCtx(), paymentID.get(i), get_TrxName());
				match.set_CustomColumn("C_DocType_ID", pPayment.getC_DocType_ID());
				//match.set_CustomColumn("P_Payment_ID", paymentID.get(i));
				//match.set_CustomColumn("P_Amount", tempPaymentAmt);
				
				//charge
				match.set_CustomColumn("C_Charge_ID", chargeID);
				//match.set_CustomColumn("N_Amount", tempPaymentAmt.abs().negate());
				
				match.set_CustomColumn("AllocationAmt", tempPaymentAmt.abs());
				match.set_ValueOfColumn("DateAllocated", getCreated());				
				match.saveEx();
				
				chargeAmount = chargeAmount.abs().subtract(tempPaymentAmt.abs());
					
			}/* else if(paymentID.isEmpty() && !receiptID.isEmpty() && chargeID>0){
				X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
				match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
				match.set_CustomColumn("N_Payment_ID", receiptID.get(0));
				match.set_CustomColumn("N_Amount", receiptAmount.get(0));
				match.set_CustomColumn("C_Charge_ID", chargeID);
				match.set_CustomColumn("P_Amount", chargeAmount);
				
				MPayment nPayment = new MPayment(getCtx(), receiptID.get(0), get_TrxName());
				match.set_CustomColumn("N_DocType_ID", nPayment.getC_DocType_ID());
				
				match.set_CustomColumn("AllocationAmt", chargeAmount.abs());
				
				match.saveEx();	
			}*/
			
		}
		//End Payment Loop
			
		//Loop for nInvoice
		for(int i=0; i<minI; i++)
		{
			BigDecimal tempnInvoiceAmt = minAmount.get(i);
			
			if(tempnInvoiceAmt.compareTo(Env.ZERO)<0 && chargeID>0 && chargeAmount.compareTo(Env.ZERO)<0){
				X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
				match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
				
				//invoice
				match.set_CustomColumn("C_Invoice_ID", minInvoiceID.get(i));
				match.set_CustomColumn("DiscountAmt", nDiscountAmt.get(i));
				match.set_CustomColumn("WriteOffAmt", nWriteOffAmt.get(i));
				
				MInvoice nInvoice = new MInvoice(getCtx(), minInvoiceID.get(i), get_TrxName());
				match.set_CustomColumn("C_DocType_ID", nInvoice.getC_DocType_ID());
				
				//match.set_CustomColumn("N_Invoice_ID", minInvoiceID.get(i));
				//match.set_CustomColumn("N_Amount", tempnInvoiceAmt.abs().negate());
				
				//charge
				match.set_CustomColumn("C_Charge_ID", chargeID);
				//match.set_CustomColumn("P_Amount", tempnInvoiceAmt.abs());
				
				match.set_CustomColumn("AllocationAmt", tempnInvoiceAmt.abs());
				match.set_ValueOfColumn("DateAllocated", getCreated());
				match.saveEx();
				
				chargeAmount = chargeAmount.add(tempnInvoiceAmt);
				
				nDiscountAmt.set(0, Env.ZERO);
				nWriteOffAmt.set(0, Env.ZERO);
				
			}
		}
		//End nInvoice Loop	
		
		//Loop for Receipt
		for(int i=0; i<rec; i++)
		{
			BigDecimal tempReceiptAmt = receiptAmount.get(i);
			
			if(tempReceiptAmt.compareTo(Env.ZERO)!=0 && chargeID>0 && chargeAmount.compareTo(Env.ZERO)<0){
				X_T_MatchAllocation match = new X_T_MatchAllocation(getCtx(), 0, get_TrxName());
				match.set_CustomColumn("C_AllocationHdr_ID", get_ID());
				
				//receipt
				match.set_CustomColumn("C_Payment_ID", receiptID.get(i));
				
				MPayment nPayment = new MPayment(getCtx(), receiptID.get(i), get_TrxName());
				match.set_CustomColumn("C_DocType_ID", nPayment.getC_DocType_ID());
				//match.set_CustomColumn("N_Payment_ID", receiptID.get(i));
				//match.set_CustomColumn("N_Amount", tempReceiptAmt.abs().negate());
				
				//charge
				match.set_CustomColumn("C_Charge_ID", chargeID);
				//match.set_CustomColumn("P_Amount", tempReceiptAmt.abs());
				
				
				match.set_CustomColumn("AllocationAmt", tempReceiptAmt.abs());
				match.set_ValueOfColumn("DateAllocated", getCreated());
				match.saveEx();	
				
				chargeAmount = chargeAmount.abs().negate().add(tempPaymentAmt.abs());
				
			}
		}
		//End Receipt Loop
		
		plusInvoiceID.clear();
		minInvoiceID.clear();
		paymentID.clear();
		receiptID.clear();
		plusAmount.clear();
		minAmount.clear();
		paymentAmount.clear();
		receiptAmount.clear();
		pDiscountAmt.clear();
		nDiscountAmt.clear();
		pWriteOffAmt.clear();
		nWriteOffAmt.clear();
	}
}   //  MAllocation
