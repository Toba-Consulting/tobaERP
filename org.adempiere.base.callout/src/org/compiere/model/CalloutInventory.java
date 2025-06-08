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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Physical Inventory Callouts
 *	
 *  @author Jorg Janke
 *  @version $Id: CalloutInventory.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 */
public class CalloutInventory extends CalloutEngine
{
	/**
	 *  Product/Locator/ASI modified.
	 * 		Set Attribute Set Instance
	 *
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";

		// set docSubTypeInv
		int doctypeid = Env.getContextAsInt(ctx, WindowNo, "C_DocType_ID");
		String docSubTypeInv = null;
		if (doctypeid > 0) {
			MDocType dt = MDocType.get(ctx, doctypeid);
			docSubTypeInv = dt.getDocSubTypeInv();
		}

		if ("M_Product_ID".equals(mField.getColumnName())) {
			// product changed - remove old ASI
			mTab.setValue("M_AttributeSetInstance_ID", 0);
		}

		//	Get Book Value
		int M_Product_ID = 0;
		Integer Product = (Integer)mTab.getValue("M_Product_ID");
		if (Product != null)
			M_Product_ID = Product.intValue();
		if (M_Product_ID == 0)
			return "";
		int M_Locator_ID = 0;
		Integer Locator = (Integer)mTab.getValue("M_Locator_ID");
		if (Locator != null)
			M_Locator_ID = Locator.intValue();
		if (M_Locator_ID == 0)
			return "";
		
		//	Set Attribute
		int M_AttributeSetInstance_ID = 0; 
		Integer ASI = (Integer)mTab.getValue("M_AttributeSetInstance_ID");
		if (ASI != null)
			M_AttributeSetInstance_ID = ASI.intValue();
		//	Product Selection
		if (MInventoryLine.COLUMNNAME_M_Product_ID.equals(mField.getColumnName()))
		{
			if (Env.getContextAsInt(ctx, WindowNo, Env.TAB_INFO, "M_Product_ID") == M_Product_ID)
			{
				M_AttributeSetInstance_ID = Env.getContextAsInt(ctx, WindowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID");
			}
			else
			{
				M_AttributeSetInstance_ID = 0;
			}
			if (M_AttributeSetInstance_ID != 0)
				mTab.setValue(MInventoryLine.COLUMNNAME_M_AttributeSetInstance_ID, M_AttributeSetInstance_ID);
			else
				mTab.setValue(MInventoryLine.COLUMNNAME_M_AttributeSetInstance_ID, 0);
		}
			
		// Set QtyBook from first storage location
		// kviiksaar: Call's now the extracted function
		BigDecimal bd = null;
		if (MDocType.DOCSUBTYPEINV_PhysicalInventory.equals(docSubTypeInv)) {
			try {
				bd = setQtyBook(M_AttributeSetInstance_ID, M_Product_ID, M_Locator_ID);
				mTab.setValue("QtyBook", bd);
			} catch (Exception e) {
				return e.getLocalizedMessage();
			}
		}
		
//		@win add support multiUOM
		MProduct product = MProduct.get(ctx, M_Product_ID);
		mTab.setValue("C_UOM_ID", Integer.valueOf(product.getC_UOM_ID()));
		BigDecimal QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
		mTab.setValue("MovementQty", QtyEntered);
		// end @win add support multiUOM
		
		//
		if (log.isLoggable(Level.INFO)) log.info("M_Product_ID=" + M_Product_ID 
			+ ", M_Locator_ID=" + M_Locator_ID
			+ ", M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID
			+ " - QtyBook=" + bd);
		return "";
	}   //  product
	
	
	/**
	 * kviiksaar
	 * 
	 * Returns the current Book Qty for given parameters or 0
	 * 
	 * @param M_AttributeSetInstance_ID
	 * @param M_Product_ID
	 * @param M_Locator_ID
	 * @return
	 * @throws Exception
	 */
	private BigDecimal setQtyBook (int M_AttributeSetInstance_ID, int M_Product_ID, int M_Locator_ID) throws Exception {
		// Set QtyBook from first storage location
		BigDecimal bd = null;
		String sql = "SELECT SUM(QtyOnHand) FROM M_StorageOnHand "
			+ "WHERE M_Product_ID=?"	//	1
			+ " AND M_Locator_ID=?"		//	2
			+ " AND M_AttributeSetInstance_ID=?"; //3
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_Product_ID);
			pstmt.setInt(2, M_Locator_ID);
			pstmt.setInt(3, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				bd = rs.getBigDecimal(1);
				if (bd != null)
					return bd;
			} else {
				// gwu: 1719401: clear Booked Quantity to zero first in case the query returns no rows, 
				// for example when the locator has never stored a particular product.
				return Env.ZERO;
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new Exception(e.getLocalizedMessage());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return Env.ZERO;
	}
	
	/**
	 *
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param GridTab     Model Tab
	 *  @param GridField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String qty(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		if (isCalloutActive() || value == null)
			return "";

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "M_Product_ID");
		
		//	@win add support multiUOM
		int C_DocType_ID = Env.getContextAsInt(ctx, WindowNo, "C_DocType_ID");
		boolean isInternalUse = false;
		MDocType docType = MDocType.get(ctx, C_DocType_ID);
		
		if (docType.getDocSubTypeInv().equals(MDocType.DOCSUBTYPEINV_InternalUseInventory)) {
			isInternalUse = true;
		}
		
		BigDecimal MovementQty = Env.ZERO;
		BigDecimal QtyEntered = Env.ZERO;

		//	No Product
		if (M_Product_ID == 0)
		{
			QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
			mTab.setValue(MInventoryLine.COLUMNNAME_QtyInternalUse, QtyEntered);
		}
		
		//	UOM Changed - convert from Entered -> Product
		else if (mField.getColumnName().equals("C_UOM_ID") && isInternalUse)
		{
			int C_UOM_To_ID = ((Integer)value).intValue();
			QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
			BigDecimal QtyEntered1 = QtyEntered.setScale(MUOM.getPrecision(ctx, C_UOM_To_ID), RoundingMode.HALF_UP);
			if (QtyEntered.compareTo(QtyEntered1) != 0)
			{
				if (log.isLoggable(Level.FINE)) log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID
					+ "; QtyEntered=" + QtyEntered + "->" + QtyEntered1);
				QtyEntered = QtyEntered1;
				mTab.setValue("QtyEntered", QtyEntered);
			}
			
			MovementQty = MUOMConversion.convertProductFrom (ctx, M_Product_ID,
				C_UOM_To_ID, QtyEntered);
			if (MovementQty == null)
				MovementQty = QtyEntered;
			
			boolean conversion = QtyEntered.compareTo(MovementQty) != 0;
			if (log.isLoggable(Level.FINE)) log.fine("UOM=" + C_UOM_To_ID
				+ ", QtyEntered=" + QtyEntered
				+ " -> " + conversion
				+ " QtyInternalUse=" + MovementQty);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue(MInventoryLine.COLUMNNAME_QtyInternalUse, MovementQty);
		}
		//	No UOM defined
		else if (Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "C_UOM_ID") == 0)
		{
			QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
			mTab.setValue(MInventoryLine.COLUMNNAME_QtyInternalUse, QtyEntered);
		}
		//	QtyEntered changed - calculate MovementQty
		else if (mField.getColumnName().equals("QtyEntered"))
		{
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "C_UOM_ID");
			QtyEntered = (BigDecimal)value;
			BigDecimal QtyEntered1 = QtyEntered.setScale(MUOM.getPrecision(ctx, C_UOM_To_ID), RoundingMode.HALF_UP);
			if (QtyEntered.compareTo(QtyEntered1) != 0)
			{
				if (log.isLoggable(Level.FINE)) log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID
					+ "; QtyEntered=" + QtyEntered + "->" + QtyEntered1);
				QtyEntered = QtyEntered1;
				mTab.setValue("QtyEntered", QtyEntered);
			}
			MovementQty = MUOMConversion.convertProductFrom (ctx, M_Product_ID,
				C_UOM_To_ID, QtyEntered);
			if (MovementQty == null)
				MovementQty = QtyEntered;
			boolean conversion = QtyEntered.compareTo(MovementQty) != 0;
			if (log.isLoggable(Level.FINE)) log.fine("UOM=" + C_UOM_To_ID
				+ ", QtyEntered=" + QtyEntered
				+ " -> " + conversion
				+ " QtyInternalUse=" + MovementQty);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue(MInventoryLine.COLUMNNAME_QtyInternalUse, MovementQty);
		}
		
		//	end	@win add support multiUOM
		//mTab.setValue("QtyMiscReceipt", QtyEntered.negate());
		
		return "";
	} //  qty

	public String qtyMiscReceipt(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		if (isCalloutActive() || value == null)
			return "";

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "M_Product_ID");
		
		//	@win add support multiUOM
		BigDecimal MovementQty = Env.ZERO;
		BigDecimal QtyMiscReceipt = Env.ZERO;
		
		int C_DocType_ID = Env.getContextAsInt(ctx, WindowNo, "C_DocType_ID");
		boolean isMiscReceipt = false;
		MDocType docType = MDocType.get(ctx, C_DocType_ID);
		
		if (docType.getDocSubTypeInv().equals(MDocType.DOCSUBTYPEINV_MiscReceipt)) {
			isMiscReceipt = true;
		}
		
		//	No Product
		if (M_Product_ID == 0)
		{
			QtyMiscReceipt = (BigDecimal)mTab.getValue("QtyMiscReceipt");
			mTab.setValue(MInventoryLine.COLUMNNAME_QtyInternalUse, QtyMiscReceipt.negate());
		}
		
		//	UOM Changed - convert from Entered -> Product
		else if (mField.getColumnName().equals("C_UOM_ID") && isMiscReceipt)
		{
			int C_UOM_To_ID = ((Integer)value).intValue();
			QtyMiscReceipt = (BigDecimal)mTab.getValue("QtyMiscReceipt");
			BigDecimal QtyMiscReceipt1 = QtyMiscReceipt.setScale(MUOM.getPrecision(ctx, C_UOM_To_ID), RoundingMode.HALF_UP);
			if (QtyMiscReceipt.compareTo(QtyMiscReceipt1) != 0)
			{
				if (log.isLoggable(Level.FINE)) log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID
					+ "; QtyEntered=" + QtyMiscReceipt + "->" + QtyMiscReceipt1);
				QtyMiscReceipt = QtyMiscReceipt1;
				mTab.setValue("QtyMiscReceipt", QtyMiscReceipt);
			}
			
			MovementQty = MUOMConversion.convertProductFrom (ctx, M_Product_ID,
				C_UOM_To_ID, QtyMiscReceipt);
			if (MovementQty == null)
				MovementQty = QtyMiscReceipt;
			
			boolean conversion = QtyMiscReceipt.compareTo(MovementQty) != 0;
			if (log.isLoggable(Level.FINE)) log.fine("UOM=" + C_UOM_To_ID
				+ ", QtyEntered=" + QtyMiscReceipt
				+ " -> " + conversion
				+ " QtyInternalUse=" + MovementQty);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue(MInventoryLine.COLUMNNAME_QtyInternalUse, MovementQty.negate());
		}
		//	No UOM defined
		else if (Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "C_UOM_ID") == 0)
		{
			QtyMiscReceipt = (BigDecimal)mTab.getValue("QtyMiscReceipt");
			mTab.setValue(MInventoryLine.COLUMNNAME_QtyInternalUse, QtyMiscReceipt.negate());
		}
		//	QtyEntered changed - calculate MovementQty
		else if (mField.getColumnName().equals("QtyMiscReceipt"))
		{
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "C_UOM_ID");
			QtyMiscReceipt = (BigDecimal)value;
			BigDecimal QtyMiscReceipt1 = QtyMiscReceipt.setScale(MUOM.getPrecision(ctx, C_UOM_To_ID), RoundingMode.HALF_UP);
			if (QtyMiscReceipt.compareTo(QtyMiscReceipt1) != 0)
			{
				if (log.isLoggable(Level.FINE)) log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID
					+ "; QtyEntered=" + QtyMiscReceipt + "->" + QtyMiscReceipt1);
				QtyMiscReceipt = QtyMiscReceipt1;
				mTab.setValue("QtyMiscReceipt", QtyMiscReceipt);
			}
			MovementQty = MUOMConversion.convertProductFrom (ctx, M_Product_ID,
				C_UOM_To_ID, QtyMiscReceipt);
			if (MovementQty == null)
				MovementQty = QtyMiscReceipt;
			boolean conversion = QtyMiscReceipt.compareTo(MovementQty) != 0;
			if (log.isLoggable(Level.FINE)) log.fine("UOM=" + C_UOM_To_ID
				+ ", QtyEntered=" + QtyMiscReceipt
				+ " -> " + conversion
				+ " QtyInternalUse=" + MovementQty);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue(MInventoryLine.COLUMNNAME_QtyInternalUse, MovementQty.negate());
		}
		//	end	@win add support multiUOM
	//	mTab.setValue(MInventoryLine.COLUMNNAME_QtyEntered, QtyMiscReceipt.negate());
		return "";
	}

}	//	CalloutInventory
