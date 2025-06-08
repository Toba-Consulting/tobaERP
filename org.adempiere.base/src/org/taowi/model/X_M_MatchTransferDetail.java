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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_MatchTransferDetail
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_MatchTransferDetail extends PO implements I_M_MatchTransferDetail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160930L;

    /** Standard Constructor */
    public X_M_MatchTransferDetail (Properties ctx, int M_MatchTransferDetail_ID, String trxName)
    {
      super (ctx, M_MatchTransferDetail_ID, trxName);
      /** if (M_MatchTransferDetail_ID == 0)
        {
			setM_MatchTransferDetail_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_MatchTransferDetail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_MatchTransferDetail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BankTransfer getC_BankTransfer() throws RuntimeException
    {
		return (I_C_BankTransfer)MTable.get(getCtx(), I_C_BankTransfer.Table_Name)
			.getPO(getC_BankTransfer_ID(), get_TrxName());	}

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

	/** Set M_MatchTransferDetail.
		@param M_MatchTransferDetail_ID M_MatchTransferDetail	  */
	public void setM_MatchTransferDetail_ID (int M_MatchTransferDetail_ID)
	{
		if (M_MatchTransferDetail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_MatchTransferDetail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_MatchTransferDetail_ID, Integer.valueOf(M_MatchTransferDetail_ID));
	}

	/** Get M_MatchTransferDetail.
		@return M_MatchTransferDetail	  */
	public int getM_MatchTransferDetail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MatchTransferDetail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_MatchTransferDetail_ID()));
    }

	/** Set M_MatchTransferDetail_UU.
		@param M_MatchTransferDetail_UU M_MatchTransferDetail_UU	  */
	public void setM_MatchTransferDetail_UU (String M_MatchTransferDetail_UU)
	{
		set_Value (COLUMNNAME_M_MatchTransferDetail_UU, M_MatchTransferDetail_UU);
	}

	/** Get M_MatchTransferDetail_UU.
		@return M_MatchTransferDetail_UU	  */
	public String getM_MatchTransferDetail_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_MatchTransferDetail_UU);
	}
}