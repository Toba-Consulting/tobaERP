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
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.base.Core;
import org.adempiere.base.IProductPricing;
import org.adempiere.model.GridTabWrapper;
import org.compiere.util.Env;

/**
 *	Requisition Callouts
 *  @author Jorg Janke
 *  @version $Id: CalloutRequisition.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class CalloutRequisition extends CalloutEngine
{
	/**
	 *	Requisition Line - Product.
	 *		- PriceStd
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_Product_ID = (Integer)value;
		if (M_Product_ID == null || M_Product_ID.intValue() == 0)
			return "";
		final I_M_Requisition req = GridTabWrapper.create(mTab.getParentTab(), I_M_Requisition.class);
		final I_M_RequisitionLine line = GridTabWrapper.create(mTab, I_M_RequisitionLine.class);
		setPrice(ctx, WindowNo, req, line);
		MProduct product = MProduct.get(ctx, M_Product_ID);
		line.setC_UOM_ID(product.getC_UOM_ID());

		return "";
	}	//	product

	/**
	 * Requisition line - Qty
	 * 	- Price, LineNetAmt
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String amt (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		/*
		if (isCalloutActive() || value == null)
			return "";
		
		final I_M_Requisition req = GridTabWrapper.create(mTab.getParentTab(), I_M_Requisition.class);
		final I_M_RequisitionLine line = GridTabWrapper.create(mTab, I_M_RequisitionLine.class);
		//	Qty changed - recalc price
		if (mField.getColumnName().equals(I_M_RequisitionLine.COLUMNNAME_Qty) 
			&& "Y".equals(Env.getContext(ctx, WindowNo, "DiscountSchema")))
		{
			setPrice(ctx, WindowNo, req, line);
		}

		int StdPrecision = Env.getContextAsInt(ctx, WindowNo, "StdPrecision");
		BigDecimal Qty = line.getQty();
		BigDecimal PriceActual = line.getPriceActual();
		if (log.isLoggable(Level.FINE)) log.fine("amt - Qty=" + Qty + ", Price=" + PriceActual + ", Precision=" + StdPrecision);

		//	Multiply
		BigDecimal LineNetAmt = Qty.multiply(PriceActual);
		if (LineNetAmt.scale() > StdPrecision)
			LineNetAmt = LineNetAmt.setScale(StdPrecision, RoundingMode.HALF_UP);
		line.setLineNetAmt(LineNetAmt);
		if (log.isLoggable(Level.INFO)) log.info("amt - LineNetAmt=" + LineNetAmt);
		//
		 * 
		 */
		return "";
	}	//	amt

	private void setPrice(Properties ctx, int WindowNo, I_M_Requisition req, I_M_RequisitionLine line)
	{
		/* @win price list not mandatory
		int C_BPartner_ID = line.getC_BPartner_ID();
		BigDecimal Qty = line.getQty();
		boolean isSOTrx = false;
		IProductPricing pp = Core.getProductPricing();
		pp.setInitialValues(line.getM_Product_ID(), C_BPartner_ID, Qty, isSOTrx, null);
		//
		int M_PriceList_ID = req.getM_PriceList_ID();
		pp.setM_PriceList_ID(M_PriceList_ID);
		int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_Version_ID");
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		Timestamp orderDate = req.getDateRequired();
		pp.setPriceDate(orderDate);
		//
		line.setPriceActual(pp.getPriceStd());
		Env.setContext(ctx, WindowNo, "EnforcePriceLimit", pp.isEnforcePriceLimit() ? "Y" : "N");	//	not used
		Env.setContext(ctx, WindowNo, "DiscountSchema", pp.isDiscountSchema() ? "Y" : "N");
		*/
	}
	
	public String priceList(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) 
	{ 
		if (isCalloutActive() || value == null) 
			return ""; 
		 
		if (mField.getColumnName().equals(MRequisition.COLUMNNAME_M_PriceList_ID)) 
		{ 
			int priceListID = (Integer) mTab.getValue(MRequisition.COLUMNNAME_M_PriceList_ID); 
			if (priceListID > 0) { 
				MPriceList pl = MPriceList.get(Env.getCtx(), priceListID, null); 
				mTab.setValue(MRequisition.COLUMNNAME_C_Currency_ID, pl.getC_Currency_ID()); 
			} 
		} 
		return ""; 
	} 
	 
	/** 
	 *	Requisition Line - Quantity. 
	 *		- called from C_UOM_ID, Qty, M_Product_ID 
	 *		- for Multi UOM 
	 *  @author edwinang 
	 *  @param ctx context 
	 *  @param WindowNo current Window No 
	 *  @param mTab Grid Tab 
	 *  @param mField Grid Field 
	 *  @param value New Value 
	 *  @return null or error message 
	 */ 
	public String qty (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) 
	{ 
		if (isCalloutActive() || value == null) 
			return ""; 
		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "M_Product_ID"); 
		BigDecimal qtyRequired = Env.ZERO; 
		BigDecimal qty; 
 
		//	No Product 
		if (M_Product_ID == 0) 
		{ 
			qty = (BigDecimal)mTab.getValue("Qty"); 
			qtyRequired = qty; 
			mTab.setValue(MRequisitionLine.COLUMNNAME_QtyRequired, qtyRequired); 
		} 
		//	UOM Changed - convert from Entered -> Product 
		else if (mField.getColumnName().equals("C_UOM_ID")) 
		{ 
			//qty conversion 
			int C_UOM_To_ID = ((Integer)value).intValue(); 
			qty = (BigDecimal)mTab.getValue("Qty"); 
			BigDecimal qty1 = qty.setScale(MUOM.getPrecision(ctx, C_UOM_To_ID), RoundingMode.HALF_UP); 
			if (qty.compareTo(qty1) != 0) 
			{ 
				if (log.isLoggable(Level.FINE)) log.fine("Corrected Qty Scale UOM=" + C_UOM_To_ID 
					+ "; Qty=" + qty + "->" + qty1); 
				qty = qty1; 
				mTab.setValue(MRequisitionLine.COLUMNNAME_Qty, qty); 
			} 
			qtyRequired = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
				C_UOM_To_ID, qty); 
			if (qtyRequired == null) 
				qtyRequired = qty; 
			 
			mTab.setValue(MRequisitionLine.COLUMNNAME_QtyRequired, qtyRequired); 
			 
			//set conversion context variable 
			boolean conversion = qty.compareTo(qtyRequired) != 0; 
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N"); 
			 
			/*//TODOL @win pending price conversion on requisitionline 
			//price conversion 
			PriceActual = (BigDecimal)mTab.getValue("PriceActual"); 
			PriceEntered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
				C_UOM_To_ID, PriceActual); 
			if (PriceEntered == null) 
				PriceEntered = PriceActual; 
			if (log.isLoggable(Level.FINE)) log.fine("UOM=" + C_UOM_To_ID 
				+ ", QtyEntered/PriceActual=" + qty + "/" + PriceActual 
				+ " -> " + conversion 
				+ " QtyOrdered/PriceEntered=" + qtyRequired + "/" + PriceEntered); 
			 
			mTab.setValue("PriceEntered", PriceEntered); 
			*/ //end @win pending price conversion on requisitionline 
 
		} 
		//	QtyEntered changed - calculate QtyOrdered 
		else if (mField.getColumnName().equals("Qty")) 
		{ 
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "C_UOM_ID"); 
			qty = (BigDecimal)value; 
			BigDecimal qty1 = qty.setScale(MUOM.getPrecision(ctx, C_UOM_To_ID), RoundingMode.HALF_UP); 
			if (qty.compareTo(qty1) != 0) 
			{ 
				if (log.isLoggable(Level.FINE)) log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID 
					+ "; QtyEntered=" + qty + "->" + qty1); 
				qty = qty1; 
				mTab.setValue(MRequisitionLine.COLUMNNAME_Qty, qty); 
			} 
			qtyRequired = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
				C_UOM_To_ID, qty); 
			if (qtyRequired == null) 
				qtyRequired = qty; 
			boolean conversion = qty.compareTo(qtyRequired) != 0; 
			if (log.isLoggable(Level.FINE)) log.fine("UOM=" + C_UOM_To_ID 
				+ ", QtyEntered=" + qty 
				+ " -> " + conversion 
				+ " QtyOrdered=" + qtyRequired); 
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N"); 
			mTab.setValue(MRequisitionLine.COLUMNNAME_QtyRequired, qtyRequired); 
		} 
		//	QtyOrdered changed - calculate QtyEntered (should not happen) 
		else if (mField.getColumnName().equals("QtyRequired")) 
		{ 
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "C_UOM_ID"); 
			qtyRequired = (BigDecimal)value; 
			int precision = MProduct.get(ctx, M_Product_ID).getUOMPrecision(); 
			BigDecimal qtyRequired1 = qtyRequired.setScale(precision, RoundingMode.HALF_UP); 
			if (qtyRequired.compareTo(qtyRequired1) != 0) 
			{ 
				if (log.isLoggable(Level.FINE)) log.fine("Corrected QtyRequired Scale " 
					+ qtyRequired + "->" + qtyRequired1); 
				qtyRequired = qtyRequired1; 
				mTab.setValue(MRequisitionLine.COLUMNNAME_QtyRequired, qtyRequired); 
			} 
			qty = MUOMConversion.convertProductTo (ctx, M_Product_ID, 
				C_UOM_To_ID, qtyRequired); 
			if (qty == null) 
				qty = qtyRequired; 
			boolean conversion = qtyRequired.compareTo(qty) != 0; 
			if (log.isLoggable(Level.FINE)) log.fine("UOM=" + C_UOM_To_ID 
				+ ", QtyRequired=" + qtyRequired 
				+ " -> " + conversion 
				+ " QtyRequired=" + qty); 
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N"); 
			mTab.setValue(MRequisitionLine.COLUMNNAME_Qty, qty); 
		} 
		else 
		{ 
		//	QtyEntered = (BigDecimal)mTab.getValue("QtyEntered"); 
			qtyRequired = (BigDecimal)mTab.getValue("Qty"); 
		} 
 
		// 
		return ""; 
	}	//	qty 
}	//	CalloutRequisition
