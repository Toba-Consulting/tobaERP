package org.idempiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MProduction;
import org.compiere.model.MProductionLine;
import org.compiere.model.MTransaction;
import org.compiere.model.Query;
import org.compiere.print.MPrintFormat;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;

/**
 * @author stephan
 * stock card report
 */
@org.adempiere.base.annotation.Process
public class TaoStockCard extends SvrProcess{

	private long m_start = System.currentTimeMillis();
	//parameter
	private Timestamp p_movementDate = null;
	private Timestamp p_movementDateTo = null;
	private int p_M_Locator_ID = 0;
	private int p_M_Product_ID = 0;
	private BigDecimal onhandQty = new BigDecimal(0);
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("MovementDate")){
				p_movementDate = para[i].getParameterAsTimestamp();
				p_movementDateTo = para[i].getParameter_ToAsTimestamp();
			}
			else if (name.equals("M_Locator_ID"))
				p_M_Locator_ID = para[i].getParameterAsInt();
			else if (name.equals("M_Product_ID"))
				p_M_Product_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		cleanTable();
		createFirstLine();
		createTransaction();
		
		String whereClause = "Name = 'T_StockCard'";
		int AD_PrintFormat_ID = new Query(getCtx(), MPrintFormat.Table_Name, whereClause, get_TrxName())
		.setOnlyActiveRecords(true)
		.firstId();
		
		if (AD_PrintFormat_ID > 0) {
			if (Ini.isClient())
				getProcessInfo().setTransientObject (MPrintFormat.get (getCtx(), AD_PrintFormat_ID, false));
			else
				getProcessInfo().setSerializableObject(MPrintFormat.get (getCtx(), AD_PrintFormat_ID, false));
		}

		log.fine((System.currentTimeMillis() - m_start) + " ms");
		
		return "";
	}

	protected void cleanTable(){
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM T_StockCard");
		DB.executeUpdate(sql.toString(), get_TrxName());
	}
	
	protected void createTransaction(){
		
		StringBuilder where = new StringBuilder();
		where.append("M_Product_ID="+p_M_Product_ID+" AND ");
		
		if(p_M_Locator_ID > 0)
			where.append("M_Locator_ID="+p_M_Locator_ID+" AND ");
		
		where.append("MovementDate BETWEEN '" + p_movementDate + "' AND '"+p_movementDateTo+"'");
		int[] transactionIDs = new Query(getCtx(), MTransaction.Table_Name, where.toString(), get_TrxName())
		.setOnlyActiveRecords(true)
		.setClient_ID()
		.setOrderBy("Created")
		.getIDs();
		
		for (int i : transactionIDs) {
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO T_StockCard(AD_PInstance_ID, AD_Client_ID, AD_Org_ID, "
					+ "MovementDate, MovementQty, MovementType, "
					+ "M_InventoryLine_ID, M_MovementLine_ID, M_InoutLine_ID, M_ProductionLine_ID, "
					+ "LastQty, DocumentNo, M_Locator_ID, M_Product_ID) ");
			sb.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			MTransaction transaction = new MTransaction(getCtx(), i, get_TrxName());
			String documentNo = null;
			
			if(transaction.getM_InventoryLine_ID() > 0){
				MInventoryLine invLine = (MInventoryLine) transaction.getM_InventoryLine();
				MInventory inv = invLine.getParent();
				documentNo = inv.getDocumentNo();
			}
			
			else if(transaction.getM_MovementLine_ID() > 0){
				MMovementLine moveLine = (MMovementLine) transaction.getM_MovementLine();
				MMovement mov = moveLine.getParent();
				documentNo = mov.getDocumentNo();
			}
			
			else if(transaction.getM_InOutLine_ID() > 0){
				MInOutLine inoutLine = (MInOutLine) transaction.getM_InOutLine();
				MInOut inout = inoutLine.getParent();
				documentNo = inout.getDocumentNo();
			}
			
			else if(transaction.getM_ProductionLine_ID() > 0){
				MProductionLine prodLine = (MProductionLine) transaction.getM_ProductionLine();
				MProduction prod = new MProduction(getCtx(), prodLine.getM_Production_ID(), get_TrxName());
				documentNo = prod.getDocumentNo();
			}
			
			onhandQty = onhandQty.add(transaction.getMovementQty());
			Object invLine = null, moveLine = null, inoutLine = null, prodLine = null;
			
			if(transaction.getM_InventoryLine_ID() > 0)
				invLine = transaction.getM_InventoryLine_ID();
			else if(transaction.getM_MovementLine_ID() > 0)
				moveLine = transaction.getM_MovementLine_ID();
			else if(transaction.getM_InOutLine_ID() > 0)
				inoutLine = transaction.getM_InOutLine_ID();
			else if(transaction.getM_ProductionLine_ID() > 0)
				prodLine = transaction.getM_ProductionLine_ID();
			
			Object[] params = new Object[]{getAD_PInstance_ID(), getAD_Client_ID(), Env.getAD_Org_ID(getCtx()),
					transaction.getMovementDate(), transaction.getMovementQty(), transaction.getMovementType(),
					invLine, moveLine, inoutLine, prodLine,
					onhandQty, documentNo, transaction.getM_Locator_ID(), p_M_Product_ID};
			
			int no = DB.executeUpdate(sb.toString(), params, true, get_TrxName());
			log.fine("#" + no);
		}
	}
	
	protected void createFirstLine(){
		Object[] params = null;
		onhandQty = getQtyFirst();
		StringBuilder sb = new StringBuilder();
		
		if(p_M_Locator_ID > 0){
			sb.append("INSERT INTO T_StockCard (AD_PInstance_ID, AD_Client_ID, AD_Org_ID ,"
					+ "MovementDate, DocumentNo, M_Product_ID, M_Locator_ID, LastQty) "
					+ "VALUES(?,?,?,'"+p_movementDate+"'");
			sb.append(",'Saldo Awal',?,?,?)");
			params = new Object[]{getAD_PInstance_ID(), getAD_Client_ID(), 
					Env.getAD_Org_ID(getCtx()), p_M_Product_ID, p_M_Locator_ID, onhandQty};
		}
		else{
			sb.append("INSERT INTO T_StockCard (AD_PInstance_ID, AD_Client_ID, AD_Org_ID ,"
					+ "MovementDate, DocumentNo, M_Product_ID, LastQty) "
					+ "VALUES(?,?,?,'"+p_movementDate+"'");
			sb.append(",'Saldo Awal',?,?)");
			params = new Object[]{getAD_PInstance_ID(), getAD_Client_ID(), 
					Env.getAD_Org_ID(getCtx()), p_M_Product_ID,onhandQty};
		}
		
		int no = DB.executeUpdate(sb.toString(), params, true, get_TrxName());
		log.fine("#" + no);
	}

	private BigDecimal getQtyFirst() {
		BigDecimal sumQty = new BigDecimal(0);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COALESCE(SUM(MovementQty),0) FROM M_Transaction "
				+ "WHERE MovementDate < '" + p_movementDate + "' "
				+ "AND M_Product_ID=? ");
		
		if(p_M_Locator_ID > 0){
			sql.append("AND M_Locator_ID=?");
			sumQty = DB.getSQLValueBD(get_TrxName(), sql.toString(), 
					new Object[]{p_M_Product_ID, p_M_Locator_ID});
		}else{
			sumQty = DB.getSQLValueBD(get_TrxName(), sql.toString(), 
					new Object[]{p_M_Product_ID});
		}
		
		if(sumQty == null)
			return Env.ZERO;
		
		return sumQty;
	}
	
}
