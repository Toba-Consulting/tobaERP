package org.taowi.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.model.MProduct;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

@org.adempiere.base.annotation.Process
public class FA_CancelUpdateRequisition extends SvrProcess{
	
	int C_Order_ID = 0;
	int C_OrderLine_ID = 0;
	int C_Charge_ID = 0;
	int M_Product_ID = 0;
	int M_Requisition_ID = 0;
	int M_RequisitionLine_ID = 0;
	boolean isTrackAsAsset = false;

	
	
	@Override
	protected void prepare() {
		
		
	}

	@Override
	protected String doIt() throws Exception {
	
		M_Requisition_ID = getRecord_ID();
		
		if (M_Requisition_ID > 0){
			
			MRequisition req = new MRequisition(getCtx(), M_Requisition_ID, get_TrxName());
			isTrackAsAsset = req.isTrackAsAsset();
			if(!isTrackAsAsset)
				return "Document not track as asset";
			
			if(isTrackAsAsset){
				req.setIsTrackAsAsset(false);
				req.saveEx();
				
				StringBuilder  sql = new StringBuilder();
				sql.append("SELECT M_Requisition_ID,M_RequisitionLine_ID,C_Order_ID,C_OrderLine_ID");
				sql.append(" FROM M_MatchPR");
				sql.append(" WHERE M_Requisition_ID = ?");
				
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(sql.toString(), null);
					pstmt.setInt(1, M_Requisition_ID);
					rs = pstmt.executeQuery();
					while (rs.next()){
						M_Requisition_ID = rs.getInt(1);
						M_RequisitionLine_ID = rs.getInt(2);
						C_Order_ID = rs.getInt(3);
						C_OrderLine_ID = rs.getInt(4);
						
						MRequisitionLine reqLine = new MRequisitionLine(getCtx(), M_RequisitionLine_ID, get_TrxName());
						C_Charge_ID = reqLine.getC_Charge_ID();
						M_Product_ID = reqLine.getM_Product_ID();
						boolean isTrackAsAssetLine = reqLine.isTrackAsAsset();

						
						if (C_Charge_ID > 0 && M_Product_ID == 0 && isTrackAsAssetLine){
							
							reqLine.setIsTrackAsAsset(false);
							reqLine.saveEx();

							updateOrder(C_Order_ID);
							updateOrderLine(C_OrderLine_ID);
							updateInOut(C_OrderLine_ID);
							
							
						}else if ( M_Product_ID > 0 && C_Charge_ID == 0 && isTrackAsAssetLine) {
							MProduct prod = new MProduct(getCtx(), M_Product_ID, get_TrxName());
							String prodType = prod.getProductType();
							
							if(!prodType.equals("I") && !prodType.equals("R")){
								reqLine.setIsTrackAsAsset(false);
								reqLine.saveEx();
								updateOrder(C_Order_ID);
								updateOrderLine(C_OrderLine_ID);
								updateInOut(C_OrderLine_ID);

								
							}
							
						}
								
				
					}
						 
				} catch (Exception e) {
					return "Error";
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
				
				
			}
			
		}
		
		
		return "";
	}
	
	
	public String updateOrder(int C_Order_ID){
		StringBuilder sqlUpdateOrd = new StringBuilder();
		sqlUpdateOrd.append("UPDATE C_Order SET isTrackAsAsset = 'N' ");
		sqlUpdateOrd.append(" WHERE C_Order_ID = ?");
		sqlUpdateOrd.append(" AND isTrackAsAsset = 'Y' ");

		DB.executeUpdateEx(sqlUpdateOrd.toString(), new Object[]{C_Order_ID}, get_TrxName());
		
		return "";
	}
	
	public String updateOrderLine(int C_OrderLine_ID){	
		StringBuilder sqlUpdateOrdLine = new StringBuilder();
		sqlUpdateOrdLine.append("UPDATE C_OrderLine SET isTrackAsAsset = 'N' ");
		sqlUpdateOrdLine.append(" WHERE C_OrderLine_ID = ?");
		sqlUpdateOrdLine.append(" AND isTrackAsAsset = 'Y' ");
		
		DB.executeUpdateEx(sqlUpdateOrdLine.toString(), new Object[]{C_OrderLine_ID}, get_TrxName());
		
		return "";
	}
	
	
	public String updateInOut(int C_OrderLine_ID){
		
		StringBuilder sqlUpdate = new StringBuilder();
		sqlUpdate.append("UPDATE M_InOut SET isTrackAsAsset = 'N' ");
		sqlUpdate.append(" WHERE M_InOut_ID = ?");
		sqlUpdate.append(" AND isTrackAsAsset = 'Y' ");
		
		StringBuilder sqlUpdateLine = new StringBuilder();
		sqlUpdateLine.append("UPDATE M_InOutLine SET isTrackAsAsset = 'N' ");
		sqlUpdateLine.append(" WHERE M_InOutLine_ID = ?");
		sqlUpdateLine.append(" AND isTrackAsAsset = 'Y' ");
		
		StringBuilder sqlMatchPO = new StringBuilder();
		sqlMatchPO.append("SELECT mi.M_InOut_ID, mil.M_InOutLine_ID ");
		sqlMatchPO.append(" FROM M_MatchPO mm ");
		sqlMatchPO.append(" INNER JOIN M_InOutLine mil ON mil.M_InOutLine_ID = mm.M_InOutLine_ID ");
		sqlMatchPO.append(" INNER JOIN M_InOut mi ON mi.M_InOut_ID = mil.M_InOut_ID ");
		sqlMatchPO.append(" WHERE mm.C_OrderLine_ID = ? ");

		PreparedStatement pstmtMatchPO = null;
		ResultSet rsMatchPO = null;
		try {
			pstmtMatchPO = DB.prepareStatement(sqlMatchPO.toString(), null);
			pstmtMatchPO.setInt(1, C_OrderLine_ID);
			rsMatchPO = pstmtMatchPO.executeQuery();
			while (rsMatchPO.next()){
				int M_InOut_ID = rsMatchPO.getInt(1);
				int M_InOutLine_ID = rsMatchPO.getInt(2);
				
				DB.executeUpdateEx(sqlUpdate.toString(), new Object[]{M_InOut_ID}, get_TrxName());
				DB.executeUpdateEx(sqlUpdateLine.toString(), new Object[]{M_InOutLine_ID}, get_TrxName());
				updateInvoice(M_InOutLine_ID);
				
				
			}			 
		} catch (Exception e) {
			return "Error";
		} finally {
			DB.close(rsMatchPO, pstmtMatchPO);
			rsMatchPO = null;
			pstmtMatchPO = null;
		}
		
		return "";
	}
	
	public String updateInvoice(int C_InOutLine_ID){

		StringBuilder sqlUpdate = new StringBuilder();
		sqlUpdate.append("UPDATE C_Invoice SET isTrackAsAsset = 'N' ");
		sqlUpdate.append(" WHERE C_Invoice_ID = ?");
		sqlUpdate.append(" AND isTrackAsAsset = 'Y' ");
		
		StringBuilder sqlUpdateLine = new StringBuilder();
		sqlUpdateLine.append("UPDATE C_InvoiceLine SET isTrackAsAsset = 'N' ");
		sqlUpdateLine.append(" WHERE C_InvoiceLine_ID = ?");
		sqlUpdateLine.append(" AND isTrackAsAsset = 'Y' ");
		
		
		StringBuilder sqlMatchInv = new StringBuilder();
		sqlMatchInv.append("SELECT ci.C_Invoice_ID, cil.C_InvoiceLine_ID ");
		sqlMatchInv.append(" FROM M_MatchInv mm ");
		sqlMatchInv.append(" INNER JOIN C_InvoiceLine cil ON cil.C_InvoiceLine_ID = mm.C_InvoiceLine_ID ");
		sqlMatchInv.append(" INNER JOIN C_Invoice ci ON ci.C_Invoice_ID = cil.C_Invoice_ID ");
		sqlMatchInv.append(" WHERE mm.C_InOutLine_ID = ? ");
		sqlMatchInv.append(" AND ci.IsSoTrx = 'N' ");

		PreparedStatement pstmtMatchInv = null;
		ResultSet rsMatchInv  = null;
		try {
			pstmtMatchInv = DB.prepareStatement(sqlMatchInv.toString(), null);
			pstmtMatchInv.setInt(1, C_InOutLine_ID);
			rsMatchInv = pstmtMatchInv.executeQuery();
			while (rsMatchInv .next()){
				int M_Invoice_ID = rsMatchInv.getInt(1);	
				int M_InvoiceLine_ID = rsMatchInv.getInt(2);
		
				DB.executeUpdateEx(sqlUpdate.toString(), new Object[]{M_Invoice_ID}, get_TrxName());
				DB.executeUpdateEx(sqlUpdateLine.toString(), new Object[]{M_InvoiceLine_ID}, get_TrxName());
					
			}			 
		} catch (Exception e) {
			return "Error";
		} finally {
			DB.close(rsMatchInv , pstmtMatchInv);
			rsMatchInv  = null;
			pstmtMatchInv = null;
		}
		
		return "";
	}

}
