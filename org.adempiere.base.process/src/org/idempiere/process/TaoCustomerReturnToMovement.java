package org.idempiere.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.X_M_MatchMovement;
import org.compiere.model.X_M_WarehouseZone;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Msg;

@org.adempiere.base.annotation.Process
public class TaoCustomerReturnToMovement extends SvrProcess {

	private int p_M_InOut_ID = 0;
	private int p_M_LocatorTo_ID = 0;
	private int p_C_DocType_ID = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			} else if (para[i].getParameterName().equalsIgnoreCase(
					"M_InOut_ID")) {
				p_M_InOut_ID = para[i].getParameterAsInt();
			} else if (para[i].getParameterName().equalsIgnoreCase(
					"M_LocatorTo_ID")) {
				p_M_LocatorTo_ID = para[i].getParameterAsInt();
			} else if (para[i].getParameterName().equalsIgnoreCase(
					"C_DocType_ID")) {
				p_C_DocType_ID = para[i].getParameterAsInt();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		if (p_M_InOut_ID==0) 
			return "No Customer RMA is Selected";

		MInOut retur = new MInOut(getCtx(), p_M_InOut_ID, get_TrxName());

		// Validate M_InOut DocStatus is CO
//		if (!retur.getDocStatus().equals(DocAction.STATUS_Completed))
//			return "Error: DocStatus is not Completed";

		// Validate M_InOut has not been converted into M_MOvement
		if (retur.hasMatchInOutMovement())
			return "Customer RMA has been converted";

		//Validasi Document Type harus Movement
		if (p_C_DocType_ID==0) 
			return "Error: DocType is Not Selected";
		else {
			MDocType docType = new MDocType(getCtx(), p_C_DocType_ID, get_TrxName());
			if (!docType.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialMovement))
				return "Error: DocType is not Material Movement";
		}
		
		//Validasi Locator To harus warehouse yang sama dengan InOut dan merupakan locator storage
		if (p_M_LocatorTo_ID==0) 
			return "Error: Locator To is Not Selected";
		else {
			MLocator locatorTo = new MLocator(getCtx(), p_M_LocatorTo_ID, get_TrxName());
			if (retur.getM_Warehouse_ID()!=locatorTo.getM_Warehouse_ID())
				return "Error: Locator To must be in the same warehouse as Customer RMA";
			X_M_WarehouseZone whZone = new X_M_WarehouseZone(getCtx(), locatorTo.getM_WarehouseZone_ID(), get_TrxName());
			if (!whZone.getM_WarehouseZoneType().equals(X_M_WarehouseZone.M_WAREHOUSEZONETYPE_StorageArea))
				return "Locator To must be in storage area";
		}

//		if (rma.getM_Locator_ID()==0)
//			return "Error: Locator on Material Receipt / Customer Return is missing";

		MInOutLine[] returLines = retur.getLines();
		boolean hasProduct = false;

		for (MInOutLine returLine : returLines) {
			if (returLine.getM_Product_ID()!=0)
				hasProduct = true;
		}

		if (!hasProduct)
			return "Customer RMA does not have product lines";

		// Create movement
		MMovement movement = new MMovement(getCtx(), 0, get_TrxName());
		movement.setMovementDate(new Timestamp(System.currentTimeMillis()));
		movement.setMoveType(MMovement.MOVETYPE_Intra_Warehouse);
		movement.setM_Locator_ID(retur.getM_Locator_ID());
		movement.setM_LocatorTo_ID(p_M_LocatorTo_ID);
		movement.setAD_Org_ID(retur.getAD_Org_ID());
		movement.setC_BPartner_ID(retur.getC_BPartner_ID());
		movement.setC_DocType_ID(p_C_DocType_ID);
		movement.saveEx();

		for (MInOutLine returLine : returLines) {
			if (hasProduct) {
				MMovementLine moveLine = new MMovementLine(movement);
				moveLine.setAD_Org_ID(retur.getAD_Org_ID());
				moveLine.setM_Product_ID(returLine.getM_Product_ID());
				moveLine.setQtyEntered(returLine.getQtyEntered());
				moveLine.setC_UOM_ID(returLine.getC_UOM_ID());
				moveLine.setMovementQty(returLine.getMovementQty());
				moveLine.setM_Locator_ID(returLine.getM_Locator_ID());
				moveLine.setM_LocatorTo_ID(p_M_LocatorTo_ID);
				moveLine.saveEx();

				// Create Tao_MatchInOutMovement record
				X_M_MatchMovement matchInout = new X_M_MatchMovement(getCtx(), 0, get_TrxName());
				matchInout.setAD_Org_ID(returLine.getAD_Org_ID());
				matchInout.setM_InOut_ID(returLine.getM_InOut_ID());
				matchInout.setM_InOutLine_ID(returLine.getM_InOutLine_ID());
				matchInout.setM_Movement_ID(movement.getM_Movement_ID());
				matchInout.setQtyEntered(returLine.getQtyEntered());
				matchInout.setMovementQty(returLine.getQtyEntered());
				matchInout.setC_UOM_ID(returLine.getC_UOM_ID());
				matchInout.setTrxType("IOM");
				matchInout.saveEx();
			}
		}

		String message = Msg.parseTranslation(getCtx(), "@GeneratedMovement@ " + movement.getDocumentNo());
		addBufferLog(0, null, null, message, movement.get_Table_ID(), movement.getM_Movement_ID());

		return "";
	}

}
