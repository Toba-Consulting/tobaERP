package org.taowi.process;

import java.util.logging.Level;

import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRMA;
import org.compiere.model.MRMALine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Msg;

/**
 * TAOWI-1068
 */
@org.adempiere.base.annotation.Process
public class CreateSOReplacement extends SvrProcess{

	private int p_RMA_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			}
			else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}		
		
		p_RMA_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		if (p_RMA_ID == 0){
			return "No RMA to Convert";
		}
		
		int M_InOut_ID = 0;
		int C_Order_ID_From = 0;

		MRMA rma = new MRMA(getCtx(), p_RMA_ID, get_TrxName());
		MDocType doctype = new MDocType(getCtx(), rma.getC_DocType_ID(),
				get_TrxName());
		String DocStatus = rma.getDocStatus();
		int C_Order_ID = 0;
		M_InOut_ID = rma.getInOut_ID();
		C_Order_ID = rma.getC_Order_ID();

		if (M_InOut_ID == 0) {
			return "RMA Must have Shipment Document";
		}

		if (!DocStatus.equalsIgnoreCase("CO")) {
			return "Only Completed RMA Can Be Processed";
		}

		String sql = "SELECT M_InOut_ID " + " FROM M_InOut "
				+ " WHERE M_RMA_ID = ? " + " AND DocStatus = 'CO' "
				+ " AND Movementtype = 'C+' ";

		int return_ID = DB.getSQLValue(null, sql, rma.getM_RMA_ID());

		if (return_ID <= 0) {
			return "RMA has No Completed Customer Return ";
		}

		if (C_Order_ID > 0) {
			return "This RMA has been Converted";
		}
		
		if (!doctype.get_ValueAsBoolean("IsForReplacement"))
			return "RMA is Not for Replacement";

		MInOut inOut = new MInOut(getCtx(), M_InOut_ID, get_TrxName());

		C_Order_ID_From = inOut.getC_Order_ID();

		if (C_Order_ID_From == 0) {
			return "No Sales Order Source";
		}

		MOrder orderFrom = new MOrder(getCtx(), C_Order_ID_From, get_TrxName());
		MOrder orderTo = new MOrder(getCtx(), 0, get_TrxName());

		orderTo.setClientOrg(orderFrom.getAD_Client_ID(),
				orderFrom.getAD_Org_ID());
		orderTo.setC_BPartner_ID(orderFrom.getC_BPartner_ID());
		orderTo.setC_BPartner_Location_ID(orderFrom.getC_BPartner_Location_ID());
		orderTo.setM_Warehouse_ID(orderFrom.getM_Warehouse_ID());
		orderTo.setSalesRep_ID(orderFrom.getSalesRep_ID());

		orderTo.setDescription(orderFrom.getDescription());
		orderTo.setC_Project_ID(orderFrom.getC_Project_ID());
		orderTo.setDeliveryViaRule(orderFrom.getDeliveryViaRule());
		orderTo.setDeliveryRule(orderFrom.getDeliveryRule());
		orderTo.setFreightCostRule(orderFrom.getFreightCostRule());
		orderTo.setM_PriceList_ID(orderFrom.getM_PriceList_ID());
		orderTo.setPaymentRule(orderFrom.getPaymentRule());
		orderTo.setC_PaymentTerm_ID(orderFrom.getC_PaymentTerm_ID());
		orderTo.setC_Currency_ID(orderFrom.getC_Currency_ID());
		orderTo.setC_Tax_ID(orderFrom.getC_Tax_ID());
		orderTo.setDocStatus("DR");
		orderTo.setC_DocType_ID(orderFrom.getC_DocType_ID());
		orderTo.setC_DocTypeTarget_ID(orderFrom.getC_DocTypeTarget_ID());
		orderTo.saveEx();

		MRMALine RMALines[] = rma.getLines();

		if (RMALines.length == 0) {
			return "No RMA Line";
		}

		for (MRMALine RMALine : RMALines) {
			MRMALine line = new MRMALine(getCtx(), RMALine.getM_RMALine_ID(),
					get_TrxName());
			MInOutLine inOutLine = new MInOutLine(getCtx(),
					line.getM_InOutLine_ID(), get_TrxName());
			MOrderLine orderLineFrom = new MOrderLine(getCtx(),
					inOutLine.getC_OrderLine_ID(), get_TrxName());
			MOrderLine orderLineTo = new MOrderLine(getCtx(), 0, get_TrxName());

			orderLineTo.setC_Order_ID(orderTo.getC_Order_ID());
			orderLineTo.setAD_Org_ID(orderTo.getAD_Org_ID());
			orderLineTo.setM_Product_ID(line.getM_Product_ID());
			orderLineTo.setQtyEntered(line.getQty());
			orderLineTo.setC_Campaign_ID(orderLineFrom.getC_Campaign_ID());
			orderLineTo.setM_Promotion_ID(orderLineFrom.getM_Promotion_ID());
			orderLineTo.setC_UOM_ID(orderLineFrom.getC_UOM_ID());
			orderLineTo.setPriceActual(orderLineFrom.getPriceActual());
			orderLineTo.setPriceEntered(orderLineFrom.getPriceEntered());
			orderLineTo.setPriceList(orderLineFrom.getPriceList());
			orderLineTo.setPriceLimit(orderLineFrom.getPriceLimit());
			orderLineTo.setC_Tax_ID(orderLineFrom.getC_Tax_ID());
			orderLineTo.setQtyReserved(line.getQty());
			orderLineTo.saveEx();
		}

		rma.setC_Order_ID(orderTo.getC_Order_ID());
		rma.saveEx();

		String message = Msg.parseTranslation(getCtx(),"@GeneratedSOReplacement@" + orderTo.getDocumentNo());
		addBufferLog(0, null, null, message, orderTo.get_Table_ID(),orderTo.getC_Order_ID());

		return "";
	}

}
