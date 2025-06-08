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
import org.compiere.model.X_M_WarehouseZone;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Msg;

@org.adempiere.base.annotation.Process
public class TaoInventoryToMovement extends SvrProcess {
	private int p_M_Inventory_ID = 0;
	private int p_M_LocatorTo_ID = 0;
	private int p_M_WarehouseZoneTo_ID = 0;

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
			} else if (para[i].getParameterName().equalsIgnoreCase("M_WarehouseZoneTo_ID")) {
				p_M_WarehouseZoneTo_ID = para[i].getParameterAsInt();

			} else if (para[i].getParameterName().equalsIgnoreCase("C_DocType_ID")) {
				p_C_DocType_ID = para[i].getParameterAsInt();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {

		p_M_Inventory_ID = getRecord_ID();

		if (p_M_Inventory_ID==0) 
			return "No Misc Receipt selected";

		MInventory inv = new MInventory(getCtx(), p_M_Inventory_ID, get_TrxName());

		if (inv.getM_Warehouse_ID() <= 0) 
			return "Missing Warehouse Info in Misc Receipt";
		
		if (inv.getM_WarehouseZone_ID() <= 0)
			return "Missing Warehouse Zone Info in Misc Receipt";
		
		// Validate M_Inventory DocStatus is CO
		// if fail return error msg "Error: DocStatus is not Completed"
		if (!inv.getDocStatus().equals(DocAction.STATUS_Completed))
			return "Error: DocStatus is not Completed";

		// Validate M_Inventory has not been converted into M_Movements
		if (inv.hasMatchMovementInventory())
			return "M_Inventory has been converted";

		//Validasi Document Type harus Movement
		if (p_C_DocType_ID==0) 
			return "Error: DocType is Not Selected";
		else {
			MDocType docType = new MDocType(getCtx(), p_C_DocType_ID, get_TrxName());
			if (!docType.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialMovement))
				return "Error: DocType is not Material Movement";
		}
		//Validasi Locator To harus warehouse yang sama dengan InOut dan merupakan locator storage
		
		MLocator locatorTo = null;
		
		if (p_M_LocatorTo_ID==0) 
			return "Error: Locator To is Not Selected";
		else {
			locatorTo = new MLocator(getCtx(), p_M_LocatorTo_ID, get_TrxName());
			if (inv.getM_Warehouse_ID()!=locatorTo.getM_Warehouse_ID())
				return "Error: Locator To must be in the same warehouse as Material Receipt / Customer Return";
			X_M_WarehouseZone whZone = new X_M_WarehouseZone(getCtx(), p_M_WarehouseZoneTo_ID, get_TrxName());
			if (!whZone.getM_WarehouseZoneType().equals(X_M_WarehouseZone.M_WAREHOUSEZONETYPE_StorageArea))
				return "Warehouse Zone must be in storage area";
		}

		// Create movement
		MMovement movement = new MMovement(getCtx(), 0, get_TrxName());
		movement.setMovementDate(new Timestamp(System.currentTimeMillis()));
		movement.setAD_Org_ID(inv.getAD_Org_ID());
		movement.setMoveType(MMovement.MOVETYPE_Intra_Warehouse);
		movement.setM_Warehouse_ID(inv.getM_Warehouse_ID());
		movement.setM_WarehouseZone_ID(inv.getM_WarehouseZone_ID());		
		movement.setM_WarehouseTo_ID(locatorTo.getM_Warehouse_ID());
		movement.setM_WarehouseZoneTo_ID(p_M_WarehouseZoneTo_ID);
		movement.setM_Locator_ID(inv.getM_Locator_ID());
		movement.setM_LocatorTo_ID(p_M_LocatorTo_ID);
		movement.setC_DocType_ID(p_C_DocType_ID);
		if (inv.get_ValueAsInt("AD_OrgTrx_ID") > 0){
			movement.set_ValueOfColumn("AD_OrgTrx_ID", inv.get_ValueAsInt("AD_OrgTrx_ID"));
		}
		if (inv.get_ValueAsInt("C_Project_ID") > 0){
			movement.set_ValueOfColumn("C_Project_ID", inv.get_ValueAsInt("C_Project_ID"));
		}
		movement.saveEx();

		MInventoryLine[] invLines = inv.getLines(false);

		// Copy lines
		for (MInventoryLine invLine : invLines) {
			MMovementLine moveLine = new MMovementLine(movement);
			moveLine.setM_Product_ID(invLine.getM_Product_ID());
			moveLine.setQtyEntered(invLine.getQtyMiscReceipt());
			moveLine.setMovementQty(invLine.getQtyMiscReceipt());
			//moveLine.setM_Locator_ID(movement.getM_Locator_ID());
			moveLine.setM_Locator_ID(invLine.getM_Locator_ID());
			moveLine.setM_LocatorTo_ID(p_M_LocatorTo_ID);
			moveLine.setC_UOM_ID(invLine.getC_UOM_ID());
			moveLine.saveEx();

			// Create Tao_MatchInOutInventory record
			X_M_MatchMovement matchInv = new X_M_MatchMovement(getCtx(), 0, get_TrxName());
			matchInv.setAD_Org_ID(movement.getAD_Org_ID());
			matchInv.setM_Inventory_ID(inv.getM_Inventory_ID());
			matchInv.setM_InventoryLine_ID(invLine.getM_InventoryLine_ID());
			matchInv.setTrxType(X_M_MatchMovement.TRXTYPE_Inventory_GtMovement);
			matchInv.setM_Movement_ID(movement.getM_Movement_ID());
			matchInv.setM_MovementLine_ID(moveLine.getM_MovementLine_ID());
			matchInv.setMovementQty(invLine.getQtyMiscReceipt());
			matchInv.setQtyEntered(invLine.getQtyMiscReceipt());
			matchInv.saveEx();
		}

		String message = Msg.parseTranslation(getCtx(), "@GeneratedMovement@ " + movement.getDocumentNo());
		addBufferLog(0, null, null, message, movement.get_Table_ID(), movement.getM_Movement_ID());

		return "";
	}

	
}
