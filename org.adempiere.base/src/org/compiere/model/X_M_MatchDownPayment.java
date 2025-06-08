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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_MatchDownPayment
 *  @author iDempiere (generated) 
 *  @version Release 2.1 - $Id$ */
@org.adempiere.base.Model(table="M_MatchDownPayment")
public class X_M_MatchDownPayment extends PO implements I_M_MatchDownPayment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150825L;

    /** Standard Constructor */
    public X_M_MatchDownPayment (Properties ctx, int M_MatchDownPayment_ID, String trxName)
    {
      super (ctx, M_MatchDownPayment_ID, trxName);
      /** if (M_MatchDownPayment_ID == 0)
        {
			setM_MatchDownPayment_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_MatchDownPayment (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_MatchDownPayment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
    {
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Down Payment Amt.
		@param DownPaymentAmt Down Payment Amt	  */
	public void setDownPaymentAmt (BigDecimal DownPaymentAmt)
	{
		set_Value (COLUMNNAME_DownPaymentAmt, DownPaymentAmt);
	}

	/** Get Down Payment Amt.
		@return Down Payment Amt	  */
	public BigDecimal getDownPaymentAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DownPaymentAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_Invoice getDP_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getDP_Invoice_ID(), get_TrxName());	}

	/** Set Down Payment Invoice.
		@param DP_Invoice_ID Down Payment Invoice	  */
	public void setDP_Invoice_ID (int DP_Invoice_ID)
	{
		if (DP_Invoice_ID < 1) 
			set_Value (COLUMNNAME_DP_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_DP_Invoice_ID, Integer.valueOf(DP_Invoice_ID));
	}

	/** Get Down Payment Invoice.
		@return Down Payment Invoice	  */
	public int getDP_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DP_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Transaction.
		@param IsSOTrx 
		This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx)
	{
		set_ValueNoCheck (COLUMNNAME_IsSOTrx, Boolean.valueOf(IsSOTrx));
	}

	/** Get Sales Transaction.
		@return This is a Sales Transaction
	  */
	public boolean isSOTrx () 
	{
		Object oo = get_Value(COLUMNNAME_IsSOTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set M_MatchDownPayment.
		@param M_MatchDownPayment_ID M_MatchDownPayment	  */
	public void setM_MatchDownPayment_ID (int M_MatchDownPayment_ID)
	{
		if (M_MatchDownPayment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_MatchDownPayment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_MatchDownPayment_ID, Integer.valueOf(M_MatchDownPayment_ID));
	}

	/** Get M_MatchDownPayment.
		@return M_MatchDownPayment	  */
	public int getM_MatchDownPayment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MatchDownPayment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_MatchDownPayment_ID()));
    }

	/** Set M_MatchDownPayment_UU.
		@param M_MatchDownPayment_UU M_MatchDownPayment_UU	  */
	public void setM_MatchDownPayment_UU (String M_MatchDownPayment_UU)
	{
		set_Value (COLUMNNAME_M_MatchDownPayment_UU, M_MatchDownPayment_UU);
	}

	/** Get M_MatchDownPayment_UU.
		@return M_MatchDownPayment_UU	  */
	public String getM_MatchDownPayment_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_MatchDownPayment_UU);
	}
}