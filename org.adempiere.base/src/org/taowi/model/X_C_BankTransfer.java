/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.taowi.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_BankTransfer
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_BankTransfer extends PO implements I_C_BankTransfer, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160930L;

    /** Standard Constructor */
    public X_C_BankTransfer (Properties ctx, int C_BankTransfer_ID, String trxName)
    {
      super (ctx, C_BankTransfer_ID, trxName);
      /** if (C_BankTransfer_ID == 0)
        {
			setC_BankTransfer_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_BankTransfer (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_C_BankTransfer[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_Name)
			.getPO(getC_BankAccount_ID(), get_TrxName());	}

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BankAccount getC_BankAccountTo() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_Name)
			.getPO(getC_BankAccountTo_ID(), get_TrxName());	}

	/** Set Bank Account To.
		@param C_BankAccountTo_ID 
		Bank Account selected as fund transfer destination 
	  */
	public void setC_BankAccountTo_ID (int C_BankAccountTo_ID)
	{
		if (C_BankAccountTo_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccountTo_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccountTo_ID, Integer.valueOf(C_BankAccountTo_ID));
	}

	/** Get Bank Account To.
		@return Bank Account selected as fund transfer destination 
	  */
	public int getC_BankAccountTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccountTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_BankTransfer.
		@param C_BankTransfer_ID C_BankTransfer	  */
	public void setC_BankTransfer_ID (int C_BankTransfer_ID)
	{
		if (C_BankTransfer_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BankTransfer_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BankTransfer_ID, Integer.valueOf(C_BankTransfer_ID));
	}

	/** Get C_BankTransfer.
		@return C_BankTransfer	  */
	public int getC_BankTransfer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankTransfer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_BankTransfer_ID()));
    }

	/** Set C_BankTransfer_UU.
		@param C_BankTransfer_UU C_BankTransfer_UU	  */
	public void setC_BankTransfer_UU (String C_BankTransfer_UU)
	{
		set_Value (COLUMNNAME_C_BankTransfer_UU, C_BankTransfer_UU);
	}

	/** Get C_BankTransfer_UU.
		@return C_BankTransfer_UU	  */
	public String getC_BankTransfer_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_BankTransfer_UU);
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Payment getC_CancelTransferFrom() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_CancelTransferFrom_ID(), get_TrxName());	}

	/** Set Cancel Transfer From.
		@param C_CancelTransferFrom_ID 
		Cancel Giro Transaction and create payment to restore giro transaction that already being remitted (Cancel Transfer From based on Transfer To)
	  */
	public void setC_CancelTransferFrom_ID (int C_CancelTransferFrom_ID)
	{
		if (C_CancelTransferFrom_ID < 1) 
			set_Value (COLUMNNAME_C_CancelTransferFrom_ID, null);
		else 
			set_Value (COLUMNNAME_C_CancelTransferFrom_ID, Integer.valueOf(C_CancelTransferFrom_ID));
	}

	/** Get Cancel Transfer From.
		@return Cancel Giro Transaction and create payment to restore giro transaction that already being remitted (Cancel Transfer From based on Transfer To)
	  */
	public int getC_CancelTransferFrom_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CancelTransferFrom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Payment getC_CancelTransferTo() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_CancelTransferTo_ID(), get_TrxName());	}

	/** Set Cancel Transfer To.
		@param C_CancelTransferTo_ID Cancel Transfer To	  */
	public void setC_CancelTransferTo_ID (int C_CancelTransferTo_ID)
	{
		if (C_CancelTransferTo_ID < 1) 
			set_Value (COLUMNNAME_C_CancelTransferTo_ID, null);
		else 
			set_Value (COLUMNNAME_C_CancelTransferTo_ID, Integer.valueOf(C_CancelTransferTo_ID));
	}

	/** Get Cancel Transfer To.
		@return Cancel Transfer To	  */
	public int getC_CancelTransferTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CancelTransferTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ConversionType getC_ConversionType() throws RuntimeException
    {
		return (org.compiere.model.I_C_ConversionType)MTable.get(getCtx(), org.compiere.model.I_C_ConversionType.Table_Name)
			.getPO(getC_ConversionType_ID(), get_TrxName());	}

	/** Set Currency Type.
		@param C_ConversionType_ID 
		Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID)
	{
		if (C_ConversionType_ID < 1) 
			set_Value (COLUMNNAME_C_ConversionType_ID, null);
		else 
			set_Value (COLUMNNAME_C_ConversionType_ID, Integer.valueOf(C_ConversionType_ID));
	}

	/** Get Currency Type.
		@return Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ConversionType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Currency getC_CurrencyTo() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_CurrencyTo_ID(), get_TrxName());	}

	/** Set C_CurrencyTo_ID.
		@param C_CurrencyTo_ID C_CurrencyTo_ID	  */
	public void setC_CurrencyTo_ID (int C_CurrencyTo_ID)
	{
		if (C_CurrencyTo_ID < 1) 
			set_Value (COLUMNNAME_C_CurrencyTo_ID, null);
		else 
			set_Value (COLUMNNAME_C_CurrencyTo_ID, Integer.valueOf(C_CurrencyTo_ID));
	}

	/** Get C_CurrencyTo_ID.
		@return C_CurrencyTo_ID	  */
	public int getC_CurrencyTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CurrencyTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_Payment_ID(), get_TrxName());	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Payment getC_TransferFrom() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_TransferFrom_ID(), get_TrxName());	}

	/** Set Bank Transfer From.
		@param C_TransferFrom_ID Bank Transfer From	  */
	public void setC_TransferFrom_ID (int C_TransferFrom_ID)
	{
		if (C_TransferFrom_ID < 1) 
			set_Value (COLUMNNAME_C_TransferFrom_ID, null);
		else 
			set_Value (COLUMNNAME_C_TransferFrom_ID, Integer.valueOf(C_TransferFrom_ID));
	}

	/** Get Bank Transfer From.
		@return Bank Transfer From	  */
	public int getC_TransferFrom_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TransferFrom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Payment getC_TransferTo() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_TransferTo_ID(), get_TrxName());	}

	/** Set Bank Transfer To.
		@param C_TransferTo_ID Bank Transfer To	  */
	public void setC_TransferTo_ID (int C_TransferTo_ID)
	{
		if (C_TransferTo_ID < 1) 
			set_Value (COLUMNNAME_C_TransferTo_ID, null);
		else 
			set_Value (COLUMNNAME_C_TransferTo_ID, Integer.valueOf(C_TransferTo_ID));
	}

	/** Get Bank Transfer To.
		@return Bank Transfer To	  */
	public int getC_TransferTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TransferTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Giro No.
		@param GiroNo Giro No	  */
	public void setGiroNo (String GiroNo)
	{
		set_Value (COLUMNNAME_GiroNo, GiroNo);
	}

	/** Get Giro No.
		@return Giro No	  */
	public String getGiroNo () 
	{
		return (String)get_Value(COLUMNNAME_GiroNo);
	}

	/** Set MultiCurrency.
		@param IsMultiCurrency 
		for Multi Currency Transaction
	  */
	public void setIsMultiCurrency (boolean IsMultiCurrency)
	{
		set_Value (COLUMNNAME_IsMultiCurrency, Boolean.valueOf(IsMultiCurrency));
	}

	/** Get MultiCurrency.
		@return for Multi Currency Transaction
	  */
	public boolean isMultiCurrency () 
	{
		Object oo = get_Value(COLUMNNAME_IsMultiCurrency);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Statement date.
		@param StatementDate 
		Date of the statement
	  */
	public void setStatementDate (Timestamp StatementDate)
	{
		set_Value (COLUMNNAME_StatementDate, StatementDate);
	}

	/** Get Statement date.
		@return Date of the statement
	  */
	public Timestamp getStatementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StatementDate);
	}

	/** TransferStatus AD_Reference_ID=300047 */
	public static final int TRANSFERSTATUS_AD_Reference_ID=300047;
	/** Drafted = DR */
	public static final String TRANSFERSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String TRANSFERSTATUS_Completed = "CO";
	/** Canceled = CC */
	public static final String TRANSFERSTATUS_Canceled = "CC";
	/** Set Transfer Status.
		@param TransferStatus Transfer Status	  */
	public void setTransferStatus (String TransferStatus)
	{

		set_Value (COLUMNNAME_TransferStatus, TransferStatus);
	}

	/** Get Transfer Status.
		@return Transfer Status	  */
	public String getTransferStatus () 
	{
		return (String)get_Value(COLUMNNAME_TransferStatus);
	}

	/** Set User Rate.
		@param UserRate User Rate	  */
	public void setUserRate (BigDecimal UserRate)
	{
		set_Value (COLUMNNAME_UserRate, UserRate);
	}

	/** Get User Rate.
		@return User Rate	  */
	public BigDecimal getUserRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UserRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}