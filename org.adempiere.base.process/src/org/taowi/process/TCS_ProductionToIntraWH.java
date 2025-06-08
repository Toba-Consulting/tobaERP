package org.taowi.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MProduction;
import org.compiere.model.MProductionLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Msg;

@org.adempiere.base.annotation.Process
public class TCS_ProductionToIntraWH extends SvrProcess{

	private int p_C_DocType_ID = 0;
	private String p_MoveType = "";
	private int p_M_LocatorTo_ID = 0;
	private boolean p_IsMoveNotGood = false;
	private int p_M_LocatorNotGood_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals(MMovement.COLUMNNAME_C_DocType_ID))
				p_C_DocType_ID = para[i].getParameterAsInt();
			else if (name.equals(MMovement.COLUMNNAME_MoveType))
				p_MoveType = para[i].getParameterAsString();
			else if (name.equals(MMovement.COLUMNNAME_M_Locator_ID))
				p_M_LocatorTo_ID = para[i].getParameterAsInt();
			else if (name.equals("IsMoveNotGood"))
				p_IsMoveNotGood = para[i].getParameterAsBoolean();
			else if (name.equals("M_LocatorNotGood_ID"))
				p_M_LocatorNotGood_ID = para[i].getParameterAsInt();
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		int M_Production_ID = getRecord_ID();
		MProduction production = new MProduction(getCtx(), M_Production_ID, get_TrxName());
		
		if(p_IsMoveNotGood){
			MMovement movement = createMovement();
			MMovement notGoodMovement = createNotGoodMovement();
			
			String message = Msg.parseTranslation(getCtx(), "@GeneratedMovement@ "+movement.getDocumentNo()
					+", "+notGoodMovement.getDocumentNo());
			addBufferLog(0, null, null, message, 0, 0);
		}
		else{
			/*
			Timestamp currentDate = new Timestamp(System.currentTimeMillis());
			
			MMovement movement = new MMovement(getCtx(), 0, get_TrxName());
			movement.setAD_Org_ID(production.getAD_Org_ID());
			movement.setMovementDate(currentDate);
			movement.setC_DocType_ID(p_C_DocType_ID);
			movement.setM_Warehouse_ID(production.get_ValueAsInt("M_Warehouse_ID"));
			movement.setM_Locator_ID(production.getM_Locator_ID());
			movement.setM_LocatorTo_ID(p_M_LocatorTo_ID);
			movement.setMoveType(p_MoveType);
			movement.saveEx();
			
			for (MProductionLine productionLine : getLines(M_Production_ID)) {
				MMovementLine movementLine = new MMovementLine(movement);
				movementLine.setM_Locator_ID(movement.getM_Locator_ID());
				movementLine.setM_LocatorTo_ID(movement.getM_LocatorTo_ID());
				movementLine.setM_Product_ID(productionLine.getM_Product_ID());
				movementLine.setQtyEntered(productionLine.getMovementQty());
				movementLine.setMovementQty(productionLine.getMovementQty());
				movementLine.setLine(productionLine.getLine());
				movementLine.saveEx();
			}*/
			
			MMovement movement = createMovement();
			
			String message = Msg.parseTranslation(getCtx(), "@GeneratedMovement@ "+movement.getDocumentNo());
			addBufferLog(0, null, null, message, movement.get_Table_ID(), movement.get_ID());
			
		}
		
		return "";
	}

	private MMovement createMovement(){
		int M_Production_ID = getRecord_ID();
		MProduction production = new MProduction(getCtx(), M_Production_ID, get_TrxName());
		
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		
		MMovement movement = new MMovement(getCtx(), 0, get_TrxName());
		movement.setAD_Org_ID(production.getAD_Org_ID());
		movement.setMovementDate(currentDate);
		movement.setC_DocType_ID(p_C_DocType_ID);
		movement.setM_Warehouse_ID(production.get_ValueAsInt("M_Warehouse_ID"));
		movement.setM_Locator_ID(production.getM_Locator_ID());
		movement.setM_LocatorTo_ID(p_M_LocatorTo_ID);
		movement.setMoveType(p_MoveType);
		movement.saveEx();
		
		for (MProductionLine productionLine : getLines(M_Production_ID)) {
			MMovementLine movementLine = new MMovementLine(movement);
			movementLine.setM_Locator_ID(movement.getM_Locator_ID());
			movementLine.setM_LocatorTo_ID(movement.getM_LocatorTo_ID());
			movementLine.setM_Product_ID(productionLine.getM_Product_ID());
			BigDecimal qtyNotGood = (BigDecimal) productionLine.get_Value("QtyNotGood");
			movementLine.setQtyEntered(productionLine.getMovementQty().subtract(qtyNotGood));
			movementLine.setMovementQty(productionLine.getMovementQty().subtract(qtyNotGood));
			movementLine.setLine(productionLine.getLine());
			movementLine.saveEx();
		}
		
		return movement;
	}
	
	private MMovement createNotGoodMovement(){
		int M_Production_ID = getRecord_ID();
		MProduction production = new MProduction(getCtx(), M_Production_ID, get_TrxName());
		
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		
		MMovement movement = new MMovement(getCtx(), 0, get_TrxName());
		movement.setAD_Org_ID(production.getAD_Org_ID());
		movement.setMovementDate(currentDate);
		movement.setC_DocType_ID(p_C_DocType_ID);
		movement.setM_Warehouse_ID(production.get_ValueAsInt("M_Warehouse_ID"));
		movement.setM_Locator_ID(production.getM_Locator_ID());
		movement.setM_LocatorTo_ID(p_M_LocatorNotGood_ID);
		movement.setMoveType(p_MoveType);
		movement.saveEx();
		
		for (MProductionLine productionLine : getLines(M_Production_ID)) {
			MMovementLine movementLine = new MMovementLine(movement);
			movementLine.setM_Locator_ID(movement.getM_Locator_ID());
			movementLine.setM_LocatorTo_ID(movement.getM_LocatorTo_ID());
			movementLine.setM_Product_ID(productionLine.getM_Product_ID());
			BigDecimal qtyNotGood = (BigDecimal) productionLine.get_Value("QtyNotGood");
			movementLine.setQtyEntered(qtyNotGood);
			movementLine.setMovementQty(qtyNotGood);
			movementLine.setLine(productionLine.getLine());
			movementLine.saveEx();
		}
		
		return movement;
	}
	
	private MProductionLine[] getLines(int M_Production_ID) {
		ArrayList<MProductionLine> list = new ArrayList<MProductionLine>();
		
		String sql = "SELECT pl.M_ProductionLine_ID "
			+ "FROM M_ProductionLine pl "
			+ "WHERE pl.M_Production_ID = ? "
			+ "AND pl.IsEndProduct = 'Y' ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, M_Production_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add( new MProductionLine( getCtx(), rs.getInt(1), get_TrxName() ) );	
		}
		catch (SQLException ex)
		{
			throw new AdempiereException("Unable to load production lines", ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		MProductionLine[] retValue = new MProductionLine[list.size()];
		list.toArray(retValue);
		return retValue;
	}
	
}
