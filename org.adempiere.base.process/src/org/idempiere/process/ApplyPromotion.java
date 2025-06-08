/**********************************************************************
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
*                                                                     *
* Contributors:                                                       *
* - Thomas Bayen                                                      *
* - Carlos Ruiz                                                       *
**********************************************************************/
package org.idempiere.process;

import org.adempiere.model.PromotionRule;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 *	
 *
 * 	@author 	Carlos Ruiz - globalqss
 */
@org.adempiere.base.annotation.Process
public class ApplyPromotion extends SvrProcess
{

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
	
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws Exception
	{
		int orderID = getRecord_ID();
		
		MOrder order = new MOrder(getCtx(), orderID, get_TrxName());
		PromotionRule.applyPromotions(order);
		order.getLines(true, null);
		order.calculateTaxTotal();
		order.saveEx();
		increasePromotionCounter(order);
		
		return "@OK@";
	}	//	doIt

	private void increasePromotionCounter(MOrder order) {
		MOrderLine[] lines = order.getLines(false, null);
		String promotionCode = (String)order.get_Value("PromotionCode");
		for (MOrderLine ol : lines) {
			if (ol.getC_Charge_ID() > 0) {
				Integer promotionID = (Integer) ol.get_Value("M_Promotion_ID");
				if (promotionID != null && promotionID.intValue() > 0) {

					int M_PromotionPreCondition_ID = findPromotionPreConditionId(
							order, promotionCode, promotionID);
					if (M_PromotionPreCondition_ID > 0) {
						String update = "UPDATE M_PromotionPreCondition SET PromotionCounter = PromotionCounter + 1 WHERE M_PromotionPreCondition_ID = ?";
						DB.executeUpdate(update, M_PromotionPreCondition_ID, order.get_TrxName());
					}
				}
			}
		}
	}

	private int findPromotionPreConditionId(MOrder order, String promotionCode,
			Integer promotionID) {
		String bpFilter = "M_PromotionPreCondition.C_BPartner_ID = ? OR M_PromotionPreCondition.C_BP_Group_ID = ? OR (M_PromotionPreCondition.C_BPartner_ID IS NULL AND M_PromotionPreCondition.C_BP_Group_ID IS NULL)";
		String priceListFilter = "M_PromotionPreCondition.M_PriceList_ID IS NULL OR M_PromotionPreCondition.M_PriceList_ID = ?";
		String warehouseFilter = "M_PromotionPreCondition.M_Warehouse_ID IS NULL OR M_PromotionPreCondition.M_Warehouse_ID = ?";
		String dateFilter = "M_PromotionPreCondition.StartDate <= ? AND (M_PromotionPreCondition.EndDate >= ? OR M_PromotionPreCondition.EndDate IS NULL)";

		StringBuilder select = new StringBuilder();
		select.append(" SELECT M_PromotionPreCondition.M_PromotionPreCondition_ID FROM M_PromotionPreCondition ")
			.append(" WHERE")
			.append(" (" + bpFilter + ")")
			.append(" AND (").append(priceListFilter).append(")")
			.append(" AND (").append(warehouseFilter).append(")")
			.append(" AND (").append(dateFilter).append(")")
			.append(" AND (M_PromotionPreCondition.M_Promotion_ID = ?)")
			.append(" AND (M_PromotionPreCondition.IsActive = 'Y')");
		if (promotionCode != null && promotionCode.trim().length() > 0) {
			select.append(" AND (M_PromotionPreCondition.PromotionCode = ?)");
		} else {
			select.append(" AND (M_PromotionPreCondition.PromotionCode IS NULL)");
		}
		select.append(" ORDER BY M_PromotionPreCondition.C_BPartner_ID Desc, M_PromotionPreCondition.C_BP_Group_ID Desc, M_PromotionPreCondition.M_PriceList_ID Desc, M_PromotionPreCondition.M_Warehouse_ID Desc, M_PromotionPreCondition.StartDate Desc");
		int M_PromotionPreCondition_ID = 0;
		int C_BP_Group_ID = 0;
		try {
			C_BP_Group_ID = order.getC_BPartner().getC_BP_Group_ID();
		} catch (Exception e) {
		}
		if (promotionCode != null && promotionCode.trim().length() > 0) {
			M_PromotionPreCondition_ID = DB.getSQLValue(order.get_TrxName(), select.toString(), order.getC_BPartner_ID(),
					C_BP_Group_ID, order.getM_PriceList_ID(), order.getM_Warehouse_ID(), order.getDateOrdered(),
					order.getDateOrdered(), promotionID, promotionCode);
		} else {
			M_PromotionPreCondition_ID = DB.getSQLValue(order.get_TrxName(), select.toString(), order.getC_BPartner_ID(),
					C_BP_Group_ID, order.getM_PriceList_ID(), order.getM_Warehouse_ID(), order.getDateOrdered(),
					order.getDateOrdered(), promotionID);
		}
		return M_PromotionPreCondition_ID;
	}
	
}	//	ApplyPromotion
