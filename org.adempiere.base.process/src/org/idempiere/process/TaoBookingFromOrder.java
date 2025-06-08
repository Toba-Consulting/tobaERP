package org.idempiere.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRMA;
import org.compiere.model.MRMALine;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_M_MatchMovement;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Msg;

@org.adempiere.base.annotation.Process
public class TaoBookingFromOrder extends SvrProcess {

	int M_Locator_ID = 0;
	int M_LocatorTo_ID = 0;
	int C_DocType_ID = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			} else if (para[i].getParameterName().equalsIgnoreCase(
					"M_Locator_ID")) {
				M_Locator_ID = para[i].getParameterAsInt();
			} else if (para[i].getParameterName().equalsIgnoreCase(
					"M_LocatorTo_ID")) {
				M_LocatorTo_ID = para[i].getParameterAsInt();
			} else if (para[i].getParameterName().equalsIgnoreCase(
					"C_DocType_ID")) {
				C_DocType_ID = para[i].getParameterAsInt();
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}
	}

	@Override
	protected String doIt() throws Exception {

		boolean isOrder = false;
		MOrder order = null;
		MRMA rma = null;
		StringBuilder sqlWhere = new StringBuilder();

		MTable table = new MTable(getCtx(), getTable_ID(), get_TrxName());
		if (table.getTableName().equals(MOrder.Table_Name))
			isOrder = true;

		if (isOrder) {
			order = new MOrder(getCtx(), getRecord_ID(), get_TrxName());
			if (!order.getDocStatus().equals(DocAction.ACTION_Complete)) {
				return "Error : DocStatus is not Completed";
			}
			sqlWhere.append("C_Order_ID=? AND TrxType='ORB'");

		} else {
			rma = new MRMA(getCtx(), getRecord_ID(), get_TrxName());
			if (!rma.getDocStatus().equals(DocAction.ACTION_Complete)) {
				return "Error : DocStatus is not Completed";
			}
			sqlWhere.append("M_RMA_ID=? AND TrxType='ORB'");
		}

		boolean match = new Query(getCtx(), X_M_MatchMovement.Table_Name,
				sqlWhere.toString(), get_TrxName())
		// .setOnlyActiveRecords(true)
		.setParameters(getRecord_ID()).match();

		if (match) {
			return "Error: Order / RMA has been converted";
		}

		if (M_Locator_ID == M_LocatorTo_ID) {
			return "Locator and LocatorTo can't be the same";
		}
		// locator harus didalam warehouse yg sama dengan yang ada di order
		MMovement movement = new MMovement(getCtx(), 0, get_TrxName());
		movement.setMovementDate(new Timestamp(System.currentTimeMillis()));
		movement.setMoveType(MMovement.MOVETYPE_Intra_Warehouse);

		movement.setM_Locator_ID(M_Locator_ID);
		movement.setM_LocatorTo_ID(M_LocatorTo_ID);

		MLocator locator = MLocator.get(getCtx(), M_Locator_ID);
		MLocator locatorTo = MLocator.get(getCtx(), M_LocatorTo_ID);

		if (isOrder) {
			movement.setAD_Org_ID(order.getAD_Org_ID());
			movement.setM_Warehouse_ID(order.getM_Warehouse_ID());
			if (order.getC_Project_ID() > 0) {
				movement.setC_Project_ID(order.getC_Project_ID());
			}

		} else {
			movement.setAD_Org_ID(rma.getAD_Org_ID());
			movement.setM_Warehouse_ID(locator.getM_Warehouse_ID());
			if (rma.get_ValueAsInt("C_Project_ID") > 0) {
				movement.setC_Project_ID(rma.get_ValueAsInt("C_Project_ID"));
			}
		}
		
		if (order.get_ValueAsInt("AD_OrgTrx_ID") > 0){
			movement.set_ValueOfColumn("AD_OrgTrx_ID", order.get_ValueAsInt("AD_OrgTrx_ID"));
		}
		movement.setM_WarehouseZone_ID(locator.getM_WarehouseZone_ID());
		movement.setM_WarehouseTo_ID(locatorTo.getM_Warehouse_ID());
		movement.setM_WarehouseZoneTo_ID(locatorTo.getM_WarehouseZone_ID());
		movement.saveEx();

		if (isOrder) {
			MOrderLine[] orderLines = order.getLines();
			for (MOrderLine orderLine : orderLines) {
				MMovementLine moveLine = new MMovementLine(movement);
				moveLine.setLine(orderLine.getLine());
				moveLine.setDescription(orderLine.getDescription());
				moveLine.setM_Product_ID(orderLine.getM_Product_ID());
				moveLine.setQtyEntered(orderLine.getQtyEntered());
				moveLine.setC_UOM_ID(orderLine.getC_UOM_ID());
				moveLine.setQtyEntered(orderLine.getQtyEntered());
				moveLine.setMovementQty(orderLine.getQtyEntered());
				moveLine.setM_Locator_ID(M_Locator_ID);
				moveLine.setM_LocatorTo_ID(M_LocatorTo_ID);
				moveLine.saveEx();

				X_M_MatchMovement matchMovement = new X_M_MatchMovement(
						getCtx(), 0, get_TrxName());
				matchMovement.setC_Order_ID(orderLine.getC_Order_ID());
				matchMovement.setC_OrderLine_ID(orderLine
						.getC_OrderLine_ID());
				matchMovement.setM_Movement_ID(movement.getM_Movement_ID());
				matchMovement.setM_MovementLine_ID(moveLine
						.getM_MovementLine_ID());
				matchMovement.setQtyEntered(moveLine.getQtyEntered());
				matchMovement.setC_UOM_ID(orderLine.getC_UOM_ID());
				matchMovement.setTrxType("ORB");
				matchMovement.setMovementQty(moveLine.getMovementQty());
				matchMovement.saveEx();
			}

		} else {
			MRMALine[] rmaLines = rma.getLines(true);
			for (MRMALine rmaLine : rmaLines) {
				MMovementLine moveLine = new MMovementLine(movement);
				moveLine.setAD_Org_ID(rmaLine.getAD_Org_ID());
				moveLine.setLine(rmaLine.getLine());
				moveLine.setDescription(rmaLine.getDescription());
				moveLine.setM_Product_ID(rmaLine.getM_Product_ID());
				moveLine.setQtyEntered(rmaLine.getQty());
				moveLine.setC_UOM_ID(rmaLine.getC_UOM_ID());
				moveLine.setQtyEntered(rmaLine.getQty());
				moveLine.setMovementQty(rmaLine.getQty());
				moveLine.setM_Locator_ID(M_Locator_ID);
				moveLine.setM_LocatorTo_ID(M_LocatorTo_ID);
				moveLine.saveEx();

				X_M_MatchMovement matchMovement = new X_M_MatchMovement(
						getCtx(), 0, get_TrxName());
				matchMovement.setM_RMA_ID(rmaLine.getM_RMA_ID());
				matchMovement.setM_RMALine_ID(rmaLine.getM_RMALine_ID());
				matchMovement.setM_Movement_ID(movement.getM_Movement_ID());
				matchMovement.setM_MovementLine_ID(moveLine
						.getM_MovementLine_ID());
				matchMovement.setQtyEntered(rmaLine.getQty());
				matchMovement.setC_UOM_ID(rmaLine.getC_UOM_ID());
				matchMovement.setTrxType("ORB");
				matchMovement.setQtyEntered(moveLine.getQtyEntered());
				matchMovement.setMovementQty(moveLine.getMovementQty());
				matchMovement.saveEx();
			}
		}


		String message = Msg.parseTranslation(getCtx(),
				"@GeneratedMovement@ " + movement.getDocumentNo());
		addBufferLog(0, null, null, message, movement.get_Table_ID(),
				movement.getM_Movement_ID());

		return "";
	}
}
