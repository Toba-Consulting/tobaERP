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

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for M_WarehouseZone
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */

@org.adempiere.base.Model(table="M_WarehouseZone")
public class X_M_WarehouseZone extends PO implements I_M_WarehouseZone, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20151129L;

    /** Standard Constructor */
    public X_M_WarehouseZone (Properties ctx, int M_WarehouseZone_ID, String trxName)
    {
      super (ctx, M_WarehouseZone_ID, trxName);
      /** if (M_WarehouseZone_ID == 0)
        {
			setM_WarehouseZone_ID (0);
			setM_WarehouseZoneType (null);
// S
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_M_WarehouseZone (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_WarehouseZone[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warehouse Zone.
		@param M_WarehouseZone_ID Warehouse Zone	  */
	public void setM_WarehouseZone_ID (int M_WarehouseZone_ID)
	{
		if (M_WarehouseZone_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_WarehouseZone_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_WarehouseZone_ID, Integer.valueOf(M_WarehouseZone_ID));
	}

	/** Get Warehouse Zone.
		@return Warehouse Zone	  */
	public int getM_WarehouseZone_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseZone_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** M_WarehouseZoneType AD_Reference_ID=300006 */
	public static final int M_WAREHOUSEZONETYPE_AD_Reference_ID=300006;
	/** Delivery Area = D */
	public static final String M_WAREHOUSEZONETYPE_DeliveryArea = "D";
	/** Receiving Area = R */
	public static final String M_WAREHOUSEZONETYPE_ReceivingArea = "R";
	/** Storage Area = S */
	public static final String M_WAREHOUSEZONETYPE_StorageArea = "S";
	/** In-Transit = I */
	public static final String M_WAREHOUSEZONETYPE_In_Transit = "I";
	/** Booking Area = B */
	public static final String M_WAREHOUSEZONETYPE_BookingArea = "B";
	/** Set Zone Type.
		@param M_WarehouseZoneType Zone Type	  */
	public void setM_WarehouseZoneType (String M_WarehouseZoneType)
	{

		set_Value (COLUMNNAME_M_WarehouseZoneType, M_WarehouseZoneType);
	}

	/** Get Zone Type.
		@return Zone Type	  */
	public String getM_WarehouseZoneType () 
	{
		return (String)get_Value(COLUMNNAME_M_WarehouseZoneType);
	}

	/** Set M_WarehouseZone_UU.
		@param M_WarehouseZone_UU M_WarehouseZone_UU	  */
	public void setM_WarehouseZone_UU (String M_WarehouseZone_UU)
	{
		set_Value (COLUMNNAME_M_WarehouseZone_UU, M_WarehouseZone_UU);
	}

	/** Get M_WarehouseZone_UU.
		@return M_WarehouseZone_UU	  */
	public String getM_WarehouseZone_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_WarehouseZone_UU);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}
}