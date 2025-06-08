/***********************************************************************
 * This file is part of iDempiere ERP Open Source                      *
 * http://www.idempiere.org                                            *
 *                                                                     *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.idempiere.fa.exceptions.AssetException;
import org.idempiere.fa.exceptions.AssetNotActiveException;

/**
 * Depreciation expenses
 */
public class MDepreciationExp extends X_A_Depreciation_Exp
{	
	/**
	 * generated serial id 
	 */
	private static final long serialVersionUID = 6731366890875525147L;
	private static CLogger s_log = CLogger.getCLogger(MDepreciationExp.class);
	private CLogger log = CLogger.getCLogger(this.getClass());
	
    /**
     * UUID based Constructor
     * @param ctx  Context
     * @param A_Depreciation_Exp_UU  UUID key
     * @param trxName Transaction
     */
    public MDepreciationExp(Properties ctx, String A_Depreciation_Exp_UU, String trxName) {
        super(ctx, A_Depreciation_Exp_UU, trxName);
    }

	/**
	 * @param ctx
	 * @param A_Depreciation_Exp_ID
	 * @param trxName
	 */
	public MDepreciationExp(Properties ctx, int A_Depreciation_Exp_ID, String trxName)
	{
		super (ctx, A_Depreciation_Exp_ID, trxName);
	}
	
	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MDepreciationExp (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}
	
	/** 
	 *  Gets depreciation expense from DB 
	 *	@param ctx	context
	 *	@param A_Depreciation_Exp_ID	depreciation expense id
	 *	@return depreciation expense or null if A_Depreciation_Exp_ID=0 or not found
	 */
	public static MDepreciationExp get(Properties ctx, int A_Depreciation_Exp_ID) {
		if (A_Depreciation_Exp_ID <= 0) {
			return null;
		}
		MDepreciationExp depexp = new MDepreciationExp(ctx, A_Depreciation_Exp_ID, null);
		if (depexp.get_ID() != A_Depreciation_Exp_ID) {
			depexp = null;
		}
		return depexp;
	}
	
	/**	
	 * Create new MDepreciationExp (not save to DB)
	 * @param ctx
	 * @param entryType
	 * @param A_Asset_ID
	 * @param A_Period
	 * @param DateAcct
	 * @param postingType
	 * @param drAcct
	 * @param crAcct
	 * @param expense
	 * @param description
	 * @param assetwk
	 * @return MDepreciationExp
	 */
	public static MDepreciationExp createEntry (Properties ctx, String entryType, int A_Asset_ID
				, int A_Period, Timestamp DateAcct, String postingType
				, int drAcct, int crAcct, BigDecimal expense
				, String description
				, MDepreciationWorkfile assetwk)
	{
		MDepreciationExp depexp = new MDepreciationExp(ctx, 0, null);
		depexp.setA_Entry_Type(entryType);
		depexp.setA_Asset_ID(A_Asset_ID);
		depexp.setDR_Account_ID(drAcct);
		depexp.setCR_Account_ID(crAcct);
		depexp.setPostingType(postingType);
		depexp.setExpense(expense);
		depexp.setDescription(Msg.parseTranslation(ctx, description));
		depexp.setA_Period(A_Period);
		depexp.setIsDepreciated(true);
		depexp.setDateAcct(DateAcct);
		depexp.setC_AcctSchema_ID(assetwk.getC_AcctSchema_ID());
		//
		depexp.updateFrom(assetwk);
		//
		if (s_log.isLoggable(Level.FINE)) s_log.fine("depexp=" + depexp);
		return depexp;
	}
	
	/**
	 * Update fields from asset work file
	 * @param wk asset work file
	 */
	public void updateFrom(MDepreciationWorkfile wk)
	{
		setA_Asset_Cost(wk.getA_Asset_Cost());
		setA_Accumulated_Depr(wk.getA_Accumulated_Depr());
		setA_Accumulated_Depr_F(wk.getA_Accumulated_Depr_F());
		setUseLifeMonths(wk.getUseLifeMonths());
		setUseLifeMonths_F(wk.getUseLifeMonths_F());
		setA_Asset_Remaining(wk.getA_Asset_Remaining());
		setA_Asset_Remaining_F(wk.getA_Asset_Remaining_F());
	}
	
	/**	
	 *  Create Depreciation Entries
	 *  @param assetwk
	 *  @param PeriodNo
	 *  @param dateAcct
	 *  @param amt
	 *  @param amt_F
	 *  @param accumAmt
	 *  @param accumAmt_F
	 *  @param help
	 *  @param trxName
	 *  @return collection of MDepreciationExp records
	 */
	public static Collection<MDepreciationExp> createDepreciation ( 
				MDepreciationWorkfile assetwk,
				int PeriodNo, Timestamp dateAcct,
				BigDecimal amt, BigDecimal amt_F,
				BigDecimal accumAmt, BigDecimal accumAmt_F,
				String help, String entryType, String trxName)
	{
		ArrayList<MDepreciationExp> list = new ArrayList<MDepreciationExp>();
		Properties ctx = assetwk.getCtx();
		MAssetAcct assetAcct = assetwk.getA_AssetAcct(dateAcct, trxName);
		MDepreciationExp depexp = null;
		
		depexp = createEntry (assetwk.getCtx(), A_ENTRY_TYPE_Depreciation, assetwk.getA_Asset_ID(), PeriodNo, dateAcct, assetwk.getPostingType(), 
				assetAcct.getA_Depreciation_Acct(), assetAcct.getA_Accumdepreciation_Acct(), amt, "@AssetDepreciationAmt@", assetwk);
		
		if(depexp != null) {
			depexp.setAD_Org_ID(assetwk.getA_Asset().getAD_Org_ID()); // added by zuhri
			if (accumAmt != null)
				depexp.setA_Accumulated_Depr(accumAmt);
			
			if (accumAmt_F != null)
				depexp.setA_Accumulated_Depr_F(accumAmt_F);
			
			if (help != null && help.length() > 0)
				depexp.setHelp(help);
			
			if (entryType != null)
				depexp.setA_Entry_Type(entryType);

			if (depreciated(depexp.getA_Period(), assetwk.getA_Period_Start()))
				depexp.setProcessed(true);
			
			depexp.setExpense_F(amt_F);
			depexp.setA_Accumulated_Depr_Delta(amt);
			depexp.setA_Accumulated_Depr_F_Delta(amt_F);
			depexp.saveEx(assetwk.get_TrxName());
			list.add(depexp);
		}
		return list;
	}
	
	/**
	 * @author Stephan
	 * @param Current Period
	 * @param Start Period
	 * @return true if depreciated
	 */
	public static boolean depreciated(int currentPeriod, int startPeriod){
		if(currentPeriod < startPeriod)
			return true;
		else return false;
	}

	/**
	 * Process this entry and save the modified workfile.
	 */
	public void process()
	{
		if(isProcessed())
		{
			log.fine("@AlreadyProcessed@");
			return;
		}
		
		//
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(),get_TrxName(), getC_AcctSchema_ID());
		if (assetwk == null)
		{
			throw new AssetException("@NotFound@ @A_Depreciation_Workfile_ID@");
		}
		//
		String entryType = getA_Entry_Type();
		if (MDepreciationExp.A_ENTRY_TYPE_Depreciation.equals(entryType))
		{
			checkExistsNotProcessedEntries(getCtx(), getA_Asset_ID(), getDateAcct(), getPostingType(), get_TrxName());
			//
			// Check if the asset is Active:
			if (!assetwk.getAsset().getA_Asset_Status().equals(MAsset.A_ASSET_STATUS_Activated))
			{
				throw new AssetNotActiveException(assetwk.getAsset().get_ID());
			}
			//
			assetwk.adjustAccumulatedDepr(getExpense(), getExpense_F(), false);
			// Update workfile - Remaining asset cost
			assetwk.setA_Current_Period();
			assetwk.saveEx();
			//adjust to the last day of the month in before save assetwk.
			setDateAcct(assetwk.getDateAcct());
		}
		else
		{
			// nothing to do for other entry types
		}
		//
		//@phie
		/*
		 * First step : set processed true, because it will affect on the first depr entry, 
		 * 				if processed still false, there's no exp processed when workfile want to get current period and date acct
		 * Next : update workfile data (get last period on processed exp +1)
		 * Next : Update expense 
		 */
		setProcessed(true);
		saveEx();
		
		assetwk.setA_Current_Period();
		assetwk.saveEx();
		
		updateFrom(assetwk);
		saveEx();
	}
	
	/**
	 * Unprocess this entry and save the modified workfile.
	 */
	public void unProcess()
	{
		if(!isProcessed()) {
			log.fine("@AlreadyUnprocessed@");
			return;
		}

		MAsset asset = (MAsset) getA_Asset();
		if (asset.isFullyDepreciated()) {
			asset.setIsFullyDepreciated(false);
			if (asset.getA_Asset_Status().equals(MAsset.A_ASSET_STATUS_Depreciated))
				asset.setA_Asset_Status(MAsset.A_ASSET_STATUS_Activated);
		}

		MDepreciationWorkfile assetwk = getA_Depreciation_Workfile();

		if (assetwk == null) {
			throw new AssetException("@NotFound@ @A_Depreciation_Workfile_ID@");
		}

		if (MDepreciationExp.A_ENTRY_TYPE_Depreciation.equals(getA_Entry_Type())) {
			checkExistsProcessedEntries(getCtx(), getA_Asset_ID(), getDateAcct(), getPostingType(), get_TrxName());
			assetwk.adjustAccumulatedDepr(getExpense().negate(), getExpense_F(), false);
		}
		
		//@phie
		/*
		 * For Re-Active
		 * Set workfile before processed on exp become false, so work file just get the last period on processed exp
		 * Then, set processed exp to false
		 * Update asset_remaining on exp from new value on workfile
		 */
		assetwk.setA_Current_PeriodBack();
		assetwk.saveEx();
		setProcessed(false);
		updateFrom(assetwk);
		//end phie
		saveEx();
	}
	
	private MDepreciationWorkfile getA_Depreciation_Workfile()
	{
		return MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
	}
	
	@Override
	protected boolean beforeDelete()
	{
		if (isProcessed())
		{
			return false;
		}
		return true;
	}
	
	protected boolean beforeSave (boolean newRecord)
	{
		if (newRecord || is_ValueChanged(COLUMNNAME_DateAcct)) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(getDateAcct().getTime());
			int year = cal.get(Calendar.YEAR);
			setCalendarYear(year);
		}

		return true;

	}
	
	@Override
	protected boolean afterDelete(boolean success)
	{
		if (!success)
		{
			return false;
		}
		//
		if (isProcessed())
		{
			return false;
		}
		//
		return true;
	}
	
	/**
	 * @param ctx
	 * @param A_Asset_ID
	 * @param dateAcct
	 * @param postingType
	 * @param trxName
	 * @throws AssetException if there are unprocessed records
	 */
	public static void checkExistsNotProcessedEntries(Properties ctx,
													int A_Asset_ID, Timestamp dateAcct, String postingType,
													String trxName)
	{
		final String whereClause = MDepreciationExp.COLUMNNAME_A_Asset_ID+"=?"
								+" AND TRUNC("+MDepreciationExp.COLUMNNAME_DateAcct+",'MONTH')<?"
								+" AND "+MDepreciationExp.COLUMNNAME_PostingType+"=?"
								+" AND "+MDepreciationExp.COLUMNNAME_Processed+"=?";
		boolean match = new Query(ctx, MDepreciationExp.Table_Name, whereClause, trxName)
					.setParameters(new Object[]{A_Asset_ID, TimeUtil.getMonthFirstDay(dateAcct), postingType, false})
					.setOnlyActiveRecords(true)
					.match();
		if (match)
		{
			throw new AssetException("There are unprocessed records to date");
		}
	}
	
	public static void checkExistsProcessedEntries(Properties ctx,
			int A_Asset_ID, Timestamp dateAcct, String postingType,
			String trxName)
	{
		final String whereClause = MDepreciationExp.COLUMNNAME_A_Asset_ID+"=?"
				+" AND TRUNC("+MDepreciationExp.COLUMNNAME_DateAcct+",'MONTH')>?"
				+" AND "+MDepreciationExp.COLUMNNAME_PostingType+"=?"
				+" AND "+MDepreciationExp.COLUMNNAME_Processed+"=?";

		//@phie match exists if there are processed record that date acct > this date acct (month) and isProcessed = Y
		boolean match = new Query(ctx, MDepreciationExp.Table_Name, whereClause, trxName)
		.setParameters(new Object[]{A_Asset_ID, TimeUtil.getMonthFirstDay(dateAcct), postingType, true})
		.setOnlyActiveRecords(true)
		.match();
		//end @phie
		
		if (match)
			throw new AssetException("There are processed records for period after currents");

	}
	
	/**
	 * @param ctx
	 * @param A_Asset_ID
	 * @param postingType
	 * @param trxName
	 * @return list of not process MDepreciationExp records
	 */
	public static List<MDepreciationExp> getNotProcessedEntries(Properties ctx,
			int A_Asset_ID, String postingType,
			String trxName)
	{
		final String whereClause = MDepreciationExp.COLUMNNAME_A_Asset_ID+"=?"
		              +" AND "+MDepreciationExp.COLUMNNAME_PostingType+"=?"
	             	  +" AND "+MDepreciationExp.COLUMNNAME_Processed+"=?";
		List<MDepreciationExp> list = new Query(ctx, MDepreciationExp.Table_Name, whereClause, trxName)
	                      	.setParameters(new Object[]{A_Asset_ID, postingType, false})
	                      	.setOnlyActiveRecords(true)
		                    .list();
		return list;
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName()+"["+get_ID()
			+",A_Asset_ID="+getA_Asset_ID()
			+",A_Period="+getA_Period()
			+",DateAcct="+getDateAcct()
			+",Expense="+getExpense()
			+",Entry_ID="+getA_Depreciation_Entry_ID()
			+"]";
	}
}
