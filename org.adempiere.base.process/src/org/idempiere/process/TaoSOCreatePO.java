package org.idempiere.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

@org.adempiere.base.annotation.Process
public class TaoSOCreatePO extends SvrProcess {

	private int p_C_Order_ID = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			} else if (para[i].getParameterName()
					.equalsIgnoreCase("C_Order_ID")) {
				p_C_Order_ID = para[i].getParameterAsInt();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		// Validate : Parameter value for Order can't be 0
		if (p_C_Order_ID == 0)
			return "No Sales Order has Been Selected";

		MOrder order = new MOrder(getCtx(), p_C_Order_ID, get_TrxName());
		MOrderLine[] orderLines = order.getLines();
		
		//validate: Purchase Order
		if(!order.isSOTrx())
			return "Document type not sales order";

		// Validate: Purchase Order has lines
		if (orderLines.length == 0) {
			return "No Order Line ";
		}

		
		//Validate: All quotation lines must have M_Product_ID
		boolean checkProductID = true;
		
		for (MOrderLine oLine : orderLines) {
			if (oLine.getM_Product_ID() == 0)
				checkProductID = false;
			}
		
		if (!checkProductID)
			return "Only Sales Order have product can be converted to purchase order";
		
		//Create Order
		
		MOrder newOrder = new MOrder(getCtx(), 0, get_TrxName());
		newOrder.setAD_Org_ID(order.getAD_Org_ID());
		newOrder.setC_BPartner_ID(order.getC_BPartner_ID());
		newOrder.setC_BPartner_Location_ID(order.getC_BPartner_Location_ID());
		newOrder.setC_Currency_ID(order.getC_Currency_ID());
		newOrder.setIsActive(true);
		newOrder.setM_Warehouse_ID(order.getM_Warehouse_ID());
		newOrder.setC_DocType_ID(order.getC_DocType_ID());
		newOrder.setC_DocTypeTarget_ID(order.getC_DocTypeTarget_ID());
		newOrder.setC_PaymentTerm_ID(order.getC_PaymentTerm_ID());
		newOrder.setM_PriceList_ID(order.getM_PriceList_ID());
		newOrder.setIsSOTrx(false);
		newOrder.setDatePromised(new Timestamp(System.currentTimeMillis()));
		newOrder.setDateAcct(new Timestamp(System.currentTimeMillis()));
		newOrder.saveEx();
			for (MOrderLine oLine:orderLines){
				
				MOrderLine newOrderLine = new MOrderLine(getCtx(), newOrder.getC_Order_ID(), get_TrxName());
				
				newOrderLine.setC_Tax_ID(oLine.getC_Tax_ID());
				newOrderLine.setM_AttributeSetInstance_ID(oLine.getM_AttributeSetInstance_ID());
				newOrderLine.setC_Currency_ID(oLine.getC_Currency_ID());
				newOrderLine.setC_BPartner_ID(oLine.getC_BPartner_ID());
				newOrderLine.setM_Product_ID(oLine.getM_Product_ID());
				newOrderLine.setC_Charge_ID(oLine.getC_Charge_ID());
				newOrderLine.setC_BPartner_Location_ID(oLine.getC_BPartner_Location_ID());
				newOrderLine.setAD_Org_ID(oLine.getAD_Org_ID());
				newOrderLine.setM_Warehouse_ID(oLine.getM_Warehouse_ID());
				newOrderLine.setC_UOM_ID(oLine.getC_UOM_ID());
				newOrderLine.setC_Order_ID(newOrder.getC_Order_ID());
				newOrderLine.setLine(oLine.getLine());
				newOrderLine.setDateOrdered(oLine.getDateOrdered());
				newOrderLine.setDatePromised(oLine.getDatePromised());
				newOrderLine.setQtyReserved(oLine.getQtyReserved());
				newOrderLine.setQtyEntered(oLine.getQtyEntered());
				newOrderLine.setQtyDelivered(oLine.getQtyDelivered());
				newOrderLine.setQtyInvoiced(oLine.getQtyInvoiced());
				newOrderLine.setQtyOrdered(oLine.getQtyOrdered());
				newOrderLine.setQty(oLine.getQtyEntered());
				newOrderLine.setM_Shipper_ID(oLine.getM_Shipper_ID());
				newOrderLine.setPriceList(oLine.getPriceList());
				newOrderLine.setPriceActual(oLine.getPriceActual());
				newOrderLine.setPriceLimit(oLine.getPriceLimit());
				newOrderLine.setLineNetAmt(oLine.getLineNetAmt());
				newOrderLine.setDiscount(oLine.getDiscount());
				newOrderLine.setFreightAmt(oLine.getFreightAmt());
				newOrderLine.setS_ResourceAssignment_ID(oLine.getS_ResourceAssignment_ID());
				newOrderLine.setRef_OrderLine_ID(oLine.getRef_OrderLine_ID());
				newOrderLine.setM_AttributeSetInstance_ID(oLine.getM_AttributeSetInstance_ID());;
				newOrderLine.setIsDescription(oLine.isDescription());
				newOrderLine.setProcessed(oLine.isProcessed());
				newOrderLine.setPriceEntered(oLine.getPriceEntered());
				newOrderLine.setC_Project_ID(oLine.getC_Project_ID());
				newOrderLine.setPriceCost(oLine.getPriceCost());
				newOrderLine.setQtyLostSales(oLine.getQtyLostSales());
				newOrderLine.setC_ProjectPhase_ID(oLine.getC_ProjectPhase_ID());
				newOrderLine.setC_ProjectTask_ID(oLine.getC_ProjectTask_ID());
				newOrderLine.setRRAmt(oLine.getRRAmt());
				newOrderLine.setCreateShipment(oLine.getCreateShipment());
				newOrderLine.setCreateProduction(oLine.getCreateProduction());
				newOrderLine.saveEx();
			
			}
		
		return "Succesed Generated";
	}

}
