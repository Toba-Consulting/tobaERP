package org.idempiere.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.X_M_MatchPR;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

@org.adempiere.base.annotation.Process
public class TaoRequisitionCreatePO extends SvrProcess {

	private int p_C_Order_ID = 0;
	private int p_M_Requisition_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			} else if (para[i].getParameterName().equalsIgnoreCase(
					"M_Requisition_ID")) {
				p_M_Requisition_ID = para[i].getParameterAsInt();
			}else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_C_Order_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {

		MOrder order = new MOrder(getCtx(), p_C_Order_ID, get_TrxName());

		MRequisition requisition = new MRequisition(getCtx(), p_M_Requisition_ID, get_TrxName());
		
		MRequisitionLine[] PRLines = requisition.getLines();
		
		if (requisition.hasMatchingRequisitionPO()) {
			return "Requisitionline has been converted";
		}
		
		
		for (MRequisitionLine PRLine : PRLines)
		{
			order.setIsSOTrx(false);
			MOrderLine orderLine = new MOrderLine(order);
			orderLine.setM_Product_ID(PRLine.getM_Product_ID());
			orderLine.setC_Charge_ID(PRLine.getC_Charge_ID());
			orderLine.setC_UOM_ID(PRLine.getC_UOM_ID());
			orderLine.setQty(PRLine.getQty());
			orderLine.saveEx();
			
			X_M_MatchPR match = new X_M_MatchPR(getCtx(), 0, get_TrxName());
			match.setC_Order_ID(orderLine.getC_Order_ID());
			match.setM_Requisition_ID(PRLine.getM_Requisition_ID());
			match.setDateTrx(new Timestamp(System.currentTimeMillis()));
			match.saveEx();
		}
		
		return null;
	}

}
