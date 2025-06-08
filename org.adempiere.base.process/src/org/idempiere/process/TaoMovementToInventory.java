package org.idempiere.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MDocType;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.X_M_MatchMovement;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Msg;

@org.adempiere.base.annotation.Process
public class TaoMovementToInventory extends SvrProcess {

	private int p_M_Movement_ID = 0;
	private int p_M_LocatorTo_ID = 0;
	private int p_C_DocType_ID = 0;


	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			} else if (para[i].getParameterName().equalsIgnoreCase("M_LocatorTo_ID")) {
				p_M_LocatorTo_ID = para[i].getParameterAsInt();
			} else if (para[i].getParameterName().equalsIgnoreCase("C_DocType_ID")) {
				p_C_DocType_ID = para[i].getParameterAsInt();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub

		p_M_Movement_ID = getRecord_ID();

		if (p_M_Movement_ID==0) 
			return "No Picking selected";


		MMovement move = new MMovement(getCtx(), p_M_Movement_ID, get_TrxName());

		// Validate M_Inventory DocStatus is CO
		// if fail return error msg "Error: DocStatus is not Completed"
		if (!move.getDocStatus().equals(DocAction.STATUS_Completed))
			return "Error: DocStatus is not Completed";

		// Validate M_Inventory has not been converted into M_Movements
		if (move.hasMatchInventoryMovement())
			return "Picking has been converted";

		//Validasi Document Type harus Movement
		if (p_C_DocType_ID==0) 
			return "Error: DocType is Not Selected";
		else {
			MDocType docType = new MDocType(getCtx(), p_C_DocType_ID, get_TrxName());
			if (!docType.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialPhysicalInventory) && !docType.getDocSubTypeInv().equals(MDocType.DOCSUBTYPEINV_InternalUseInventory))
				return "Error: DocType is not Misc Issue";
		}
		//Validasi Locator To harus warehouse yang sama dengan InOut dan merupakan locator storage
		if (p_M_LocatorTo_ID==0) 
			return "Error: Locator To is Not Selected";
		else {
			MLocator locatorTo = new MLocator(getCtx(), p_M_LocatorTo_ID, get_TrxName());
			if (move.getM_Warehouse_ID()!=locatorTo.getM_Warehouse_ID())
				return "Error: Locator To must be in the same warehouse as Picking";
			/*
			X_M_WarehouseZone whZone = new X_M_WarehouseZone(getCtx(), locatorTo.getM_WarehouseZone_ID(), get_TrxName());
			if (!whZone.getM_WarehouseZoneType().equals(X_M_WarehouseZone.M_WAREHOUSEZONETYPE_StorageArea))
				return "Locator To must be in storage area";
			*/
		}

		// Create movement
		MInventory miscIssue = new MInventory(getCtx(), 0, get_TrxName());
		miscIssue.setAD_Org_ID(move.getAD_Org_ID());
		miscIssue.setC_DocType_ID(p_C_DocType_ID);
		miscIssue.setMovementDate(new Timestamp(System.currentTimeMillis()));
		miscIssue.setM_Locator_ID(move.getM_LocatorTo_ID());
		miscIssue.setPosted(false);
		miscIssue.setProcessed(false);
		miscIssue.setIsApproved(false);
		miscIssue.setDocStatus(DocAction.STATUS_Drafted);
		miscIssue.setDocAction(DocAction.ACTION_Complete);
		miscIssue.saveEx();

		MMovementLine[] moveLines = move.getLines(false);

		// Copy lines
		for (MMovementLine moveLine : moveLines) {
			MInventoryLine invLine = new MInventoryLine(getCtx(), 0, get_TrxName());
			invLine.setM_Inventory_ID(miscIssue.get_ID());
			invLine.setAD_Org_ID(miscIssue.getAD_Org_ID());
			invLine.setM_Product_ID(moveLine.getM_Product_ID());
			invLine.setQtyInternalUse(moveLine.getMovementQty());
			invLine.setM_Locator_ID(moveLine.getM_LocatorTo_ID());
			invLine.setC_UOM_ID(moveLine.getC_UOM_ID());
			invLine.setQtyEntered(moveLine.getQtyEntered());
			invLine.saveEx();

			// Create Tao_MatchInOutInventory record
			X_M_MatchMovement matchInv = new X_M_MatchMovement(getCtx(), 0, get_TrxName());
			matchInv.setAD_Org_ID(miscIssue.getAD_Org_ID());
			matchInv.setM_Inventory_ID(miscIssue.getM_Inventory_ID());
			matchInv.setM_InventoryLine_ID(invLine.getM_InventoryLine_ID());
			matchInv.setTrxType(X_M_MatchMovement.TRXTYPE_Movement_GtInventory);
			matchInv.setM_Movement_ID(move.getM_Movement_ID());
			matchInv.setM_MovementLine_ID(moveLine.getM_MovementLine_ID());
			matchInv.setMovementQty(invLine.getQtyInternalUse());
			matchInv.saveEx();
		}

		String message = Msg.parseTranslation(getCtx(), "@GeneratedMiscIssue@ " + miscIssue.getDocumentNo());
		addBufferLog(0, null, null, message, miscIssue.get_Table_ID(), miscIssue.getM_Inventory_ID());

		return "";
	}
}
