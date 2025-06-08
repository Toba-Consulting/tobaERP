package org.idempiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MRMA;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_M_MatchMovement;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

@org.adempiere.base.annotation.Process
public class TaoPickingFromOrder extends SvrProcess {

	int p_C_Order_ID = 0;
	int p_M_RMA_ID = 0;
	int p_M_Locator_Storage_ID = 0;
	int p_C_DocType_ID = 0;
	int p_M_LocatorTo_ID = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			} else if (para[i].getParameterName().equalsIgnoreCase("C_DocType_ID")) {
				p_C_DocType_ID = para[i].getParameterAsInt();
			} else if (para[i].getParameterName().equalsIgnoreCase("M_Locator_ID")) {
				p_M_Locator_Storage_ID = para[i].getParameterAsInt();
			} else if (para[i].getParameterName().equalsIgnoreCase("M_LocatorTo_ID")) {
				p_M_LocatorTo_ID = para[i].getParameterAsInt();
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
			p_C_Order_ID = getRecord_ID();
			order = new MOrder(getCtx(), getRecord_ID(), get_TrxName());
			if (!order.getDocStatus().equals(DocAction.ACTION_Complete)) {
				return "Error : DocStatus is not Completed";
			}
			sqlWhere.append("C_Order_ID=? AND TrxType ='ORP'");

		} else {
			p_M_RMA_ID = getRecord_ID();
			rma = new MRMA(getCtx(), getRecord_ID(), get_TrxName());
			if (!rma.getDocStatus().equals(DocAction.ACTION_Complete)) {
				return "Error : DocStatus is not Completed";
			}
			sqlWhere.append("M_RMA_ID=? AND TrxType ='ORP'");
		}

		boolean match = new Query(getCtx(), X_M_MatchMovement.Table_Name,
				sqlWhere.toString(), get_TrxName()).setOnlyActiveRecords(true)
				.setParameters(getRecord_ID()).match();

		if (match) {
			return "Error: Order / RMA has been converted";
		}

		MMovement movement = new MMovement(getCtx(), 0, get_TrxName());

		// Create movement header
		if (p_C_Order_ID > 0) {
			movement.setAD_Org_ID(order.getAD_Org_ID());
		} else {
			movement.setAD_Org_ID(rma.getAD_Org_ID());
		}

		movement.setMovementDate(new Timestamp(System.currentTimeMillis()));
		movement.setMoveType(MMovement.MOVETYPE_Intra_Warehouse);

		MLocator locator = MLocator.get(getCtx(), p_M_Locator_Storage_ID);
		MLocator locatorTo = MLocator.get(getCtx(), p_M_LocatorTo_ID);

		if (isOrder) {
			movement.setAD_Org_ID(order.getAD_Org_ID());
			movement.setM_Warehouse_ID(order.getM_Warehouse_ID());
			if (order.getC_Project_ID() > 0) {
				movement.setC_Project_ID(order.getC_Project_ID());
			}
			if (order.get_ValueAsInt("AD_OrgTrx_ID") > 0){
				movement.set_ValueOfColumn("AD_OrgTrx_ID", order.get_ValueAsInt("AD_OrgTrx_ID"));
			}
			if (order.getAD_OrgTrx_ID() > 0){
				movement.setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
			}
			if (order.getAD_OrgTrx_ID() > 0){
				movement.setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
			}

		} else {
			movement.setAD_Org_ID(rma.getAD_Org_ID());
			movement.setM_Warehouse_ID(locator.getM_Warehouse_ID());
			if (rma.get_ValueAsInt("C_Project_ID") > 0) {
				movement.setC_Project_ID(rma.get_ValueAsInt("C_Project_ID"));
			}
			if (rma.get_ValueAsInt("AD_OrgTrx_ID") > 0){
				movement.set_ValueOfColumn("AD_OrgTrx_ID", rma.get_ValueAsInt("AD_OrgTrx_ID"));
			}
			if (rma.get_ValueAsInt("AD_OrgTrx_ID") > 0) {
				movement.setC_Project_ID(rma.get_ValueAsInt("AD_OrgTrx_ID"));
			}
			if (rma.get_ValueAsInt("AD_OrgTrx_ID") > 0) {
				movement.setC_Project_ID(rma.get_ValueAsInt("AD_OrgTrx_ID"));
			}
		}	
		
		
		movement.setM_WarehouseZone_ID(locator.getM_WarehouseZone_ID());
		movement.setM_Locator_ID(p_M_Locator_Storage_ID);
		movement.setM_WarehouseTo_ID(locatorTo.getM_Warehouse_ID());
		movement.setM_WarehouseZoneTo_ID(locatorTo.getM_WarehouseZone_ID());
		movement.setM_LocatorTo_ID(p_M_LocatorTo_ID);
		movement.saveEx();

		if (isOrder) {
			// @win code start here
			HashMap<Integer, BigDecimal> productOrderQty = new HashMap<Integer, BigDecimal>();

			if (isOrder) {
				String sql = "SELECT M_Product_ID, SUM(QtyOrdered) "
						+ "FROM C_OrderLine " 
						+ "WHERE C_Order_ID=? "
						+ "GROUP BY M_Product_ID";

				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(sql, null);
					pstmt.setInt(1, p_C_Order_ID);
					rs = pstmt.executeQuery();
					while (rs.next())
						productOrderQty.put(rs.getInt(1), rs.getBigDecimal(2));
				} catch (Exception e) {
					return "Error";
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			}

			HashMap<Integer, BigDecimal> productBookingQty = new HashMap<Integer, BigDecimal>();
			String sql1 = "SELECT mov.M_Product_ID, SUM(mov.MovementQty) "
					+ "FROM M_MatchMovement mmatch "
					+ "JOIN M_MovementLine mov ON mmatch.M_MovementLine_ID=mov.M_MovementLine_ID "
					+ "WHERE mmatch.C_Order_ID=? "
					+ "AND mmatch.trxType='ORB' " 
					+ "GROUP BY mov.M_Product_ID";

			PreparedStatement pstmt1 = null;
			ResultSet rs1 = null;

			try {
				pstmt1 = DB.prepareStatement(sql1, null);
				pstmt1.setInt(1, p_C_Order_ID);
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					productBookingQty
							.put(rs1.getInt(1), rs1.getBigDecimal(2));
				} else {
					productBookingQty.isEmpty();
				}

			} catch (Exception e) {
				return "Error";
			} finally {
				DB.close(rs1, pstmt1);
				pstmt1 = null;
				rs1 = null;
			}

			if (!productOrderQty.isEmpty()) {
				for (Map.Entry<Integer, BigDecimal> entry : productOrderQty.entrySet()) {
					BigDecimal unbookedQty = Env.ZERO;
					MMovementLine moveLine = new MMovementLine(movement);
					moveLine.setM_Product_ID(entry.getKey());

					if (productBookingQty.isEmpty()) {
						unbookedQty = entry.getValue();
						moveLine.setQtyEntered(unbookedQty);
						moveLine.setMovementQty(unbookedQty);
						moveLine.setM_Locator_ID(p_M_Locator_Storage_ID);
						moveLine.setM_LocatorTo_ID(p_M_LocatorTo_ID);
						moveLine.saveEx();

						X_M_MatchMovement matchMovement = new X_M_MatchMovement(getCtx(), 0, get_TrxName());
						matchMovement.setC_Order_ID(p_C_Order_ID);
						matchMovement.setQtyEntered(moveLine.getQtyEntered());
						matchMovement.setC_UOM_ID(moveLine.getC_UOM_ID());
						matchMovement.setM_Movement_ID(movement.getM_Movement_ID());
						matchMovement.setM_MovementLine_ID(moveLine.getM_MovementLine_ID());
						matchMovement.setMovementQty(moveLine.getMovementQty());
						matchMovement.setTrxType("ORP");
						matchMovement.setM_Locator_ID(moveLine.getM_Locator_ID());
						matchMovement.setM_Locator_ID(moveLine.getM_LocatorTo_ID());
						matchMovement.saveEx();

					} else if (!productBookingQty.isEmpty()) {

						String sql2 = "SELECT mov.M_Product_ID, SUM(mov.MovementQty) "
								+ "FROM M_MatchMovement mmatch "
								+ "JOIN M_MovementLine mov ON mmatch.M_MovementLine_ID=mov.M_MovementLine_ID "
								+ "WHERE mmatch.C_Order_ID=? "
								+ "AND mmatch.trxType='ORB' "
								+ "AND mov.M_Product_ID=? "
								+ "GROUP BY mov.M_Product_ID";

						PreparedStatement pstmt2 = null;
						ResultSet rs2 = null;

						try {
							pstmt2 = DB.prepareStatement(sql2, null);
							pstmt2.setInt(1, p_C_Order_ID);
							pstmt2.setInt(2, entry.getKey());
							rs2 = pstmt2.executeQuery();
							if (rs2.next()) {

								productBookingQty.put(rs2.getInt(1),rs2.getBigDecimal(2));
								BigDecimal booking = rs2.getBigDecimal(2);

								unbookedQty = entry.getValue().subtract(booking);
								moveLine.setQtyEntered(booking);
								moveLine.setMovementQty(booking);
								moveLine.setM_Locator_ID(p_M_Locator_Storage_ID);
								moveLine.setM_LocatorTo_ID(p_M_LocatorTo_ID);
								moveLine.saveEx();

								X_M_MatchMovement matchMovement = new X_M_MatchMovement(getCtx(), 0, get_TrxName());
								matchMovement.setC_Order_ID(p_C_Order_ID);
								matchMovement.setQtyEntered(moveLine.getQtyEntered());
								matchMovement.setC_UOM_ID(moveLine.getC_UOM_ID());
								matchMovement.setM_Movement_ID(movement.getM_Movement_ID());
								matchMovement.setM_MovementLine_ID(moveLine.getM_MovementLine_ID());
								matchMovement.setMovementQty(moveLine.getMovementQty());
								matchMovement.setTrxType("ORP");
								matchMovement.setM_Locator_ID(moveLine.getM_Locator_ID());
								matchMovement.setM_Locator_ID(moveLine.getM_LocatorTo_ID());
								matchMovement.saveEx();

								if (unbookedQty.compareTo(Env.ZERO) > 0) {
									String sql3 = "SELECT mov.M_Locator_ID, SUM(mov.MovementQty), mov.C_UOM_ID "
											+ "FROM M_MatchMovement mmatch "
											+ "JOIN M_MovementLine mov ON mmatch.M_MovementLine_ID=mov.M_MovementLine_ID "
											+ "AND mmatch.C_Order_ID=? "
											+ "AND mov.M_Product_ID=? "
											+ "AND mmatch.TrxType='ORB' "
											+ "GROUP BY mov.m_locator_id,mov.C_UOM_ID;";

									PreparedStatement pstmt3 = null;
									ResultSet rs3 = null;

									try {
										pstmt3 = DB.prepareStatement(sql3, null);
										pstmt3.setInt(1, p_C_Order_ID);
										pstmt3.setInt(2, entry.getKey());
										rs3 = pstmt3.executeQuery();
										if (rs3.next()) {
											unbookedQty = entry.getValue().subtract(rs3.getBigDecimal(2));
											MMovementLine moveLine2 = new MMovementLine(movement);
											moveLine2.setM_Product_ID(entry.getKey());
											moveLine2.setQtyEntered(unbookedQty);
											moveLine2.setMovementQty(unbookedQty);
											moveLine2.setM_Locator_ID(rs3.getInt(1));
											moveLine2.setM_LocatorTo_ID(p_M_LocatorTo_ID);
											moveLine2.setC_UOM_ID(rs3.getInt(3));
											moveLine2.saveEx();

											X_M_MatchMovement matchMovement2 = new X_M_MatchMovement(getCtx(), 0, get_TrxName());
											matchMovement2.setC_Order_ID(p_C_Order_ID);
											matchMovement2.setQtyEntered(moveLine2.getQtyEntered());
											matchMovement2.setC_UOM_ID(moveLine2.getC_UOM_ID());
											matchMovement2.setM_Movement_ID(movement.getM_Movement_ID());
											matchMovement2.setM_MovementLine_ID(moveLine2.getM_MovementLine_ID());
											matchMovement2.setMovementQty(moveLine2.getMovementQty());
											matchMovement2.setTrxType("ORP");
											matchMovement2.setM_Locator_ID(moveLine2.getM_Locator_ID());
											matchMovement2.setM_Locator_ID(moveLine2.getM_LocatorTo_ID());
											matchMovement2.saveEx();

										}
									} catch (Exception e) {
										return "Error";
									} finally {
										DB.close(rs3, pstmt3);
										pstmt3 = null;
										rs3 = null;

									}

								}

							}

						} catch (Exception e) {
							return "Error";
						} finally {
							DB.close(rs2, pstmt2);
							pstmt2 = null;
							rs2 = null;
						}

					}

				}

			}
		} else {
			HashMap<Integer, BigDecimal> productOrderQty = new HashMap<Integer, BigDecimal>();
			String sql = "SELECT M_Product_ID, SUM(Qty) " 
					+ "FROM M_RMALine "
					+ "WHERE M_RMA_ID=? " 
					+ "GROUP BY M_Product_ID";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, p_M_RMA_ID);
				rs = pstmt.executeQuery();
				while (rs.next())
					productOrderQty.put(rs.getInt(1), rs.getBigDecimal(2));
			} catch (Exception e) {
				return "Error";
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}

			HashMap<Integer, BigDecimal> productBookingQty2 = new HashMap<Integer, BigDecimal>();

			String sql1 = "SELECT mov.M_Product_ID, SUM(mov.MovementQty) "
					+ "FROM M_MatchMovement mmatch "
					+ "JOIN M_MovementLine mov ON mmatch.M_MovementLine_ID=mov.M_MovementLine_ID "
					+ "AND mmatch.M_RMA_ID=? " 
					+ "AND mmatch.TrXType='ORB' "
					+ "GROUP BY mov.m_product_id";

			PreparedStatement pstmt1 = null;
			ResultSet rs1 = null;

			try {
				pstmt1 = DB.prepareStatement(sql1, null);
				pstmt1.setInt(1, p_M_RMA_ID);
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					productBookingQty2.put(rs1.getInt(1), rs1.getBigDecimal(2));
				} else {
					productBookingQty2.isEmpty();
				}

			} catch (Exception e) {
				return "Error";
			} finally {
				DB.close(rs1, pstmt1);
				pstmt1 = null;
				rs1 = null;
			}

			if (!productOrderQty.isEmpty()) {
				for (Map.Entry<Integer, BigDecimal> entry : productOrderQty.entrySet()) {
					BigDecimal unbookedQty = Env.ZERO;
					MMovementLine moveLine = new MMovementLine(movement);
					moveLine.setM_Product_ID(entry.getKey());

					if (productBookingQty2.isEmpty()) {
						unbookedQty = entry.getValue();
						moveLine.setQtyEntered(unbookedQty);
						moveLine.setMovementQty(unbookedQty);
						moveLine.setM_Locator_ID(p_M_Locator_Storage_ID);
						moveLine.setM_LocatorTo_ID(p_M_LocatorTo_ID);
						moveLine.saveEx();

						X_M_MatchMovement matchMovement = new X_M_MatchMovement(getCtx(), 0, get_TrxName());
						matchMovement.setC_Order_ID(p_C_Order_ID);
						matchMovement.setQtyEntered(moveLine.getQtyEntered());
						matchMovement.setC_UOM_ID(moveLine.getC_UOM_ID());
						matchMovement.setM_Movement_ID(movement.getM_Movement_ID());
						matchMovement.setM_MovementLine_ID(moveLine.getM_MovementLine_ID());
						matchMovement.setQtyEntered(moveLine.getMovementQty());
						matchMovement.setMovementQty(moveLine.getMovementQty());
						matchMovement.setTrxType("ORP");
						matchMovement.setM_Locator_ID(moveLine.getM_Locator_ID());
						matchMovement.setM_Locator_ID(moveLine.getM_LocatorTo_ID());
						matchMovement.saveEx();

					} else if (!productBookingQty2.isEmpty()) {

						String sql2 = "SELECT mov.M_Product_ID, SUM(mov.MovementQty) "
								+ "FROM M_MatchMovement mmatch "
								+ "JOIN M_MovementLine mov ON mmatch.M_MovementLine_ID=mov.M_MovementLine_ID "
								+ "AND mmatch.M_RMA_ID=? "
								+ "AND mmatch.TrXType='ORB' "
								+ "AND mov.M_Product_ID=? "
								+ "GROUP BY mov.m_product_id";

						PreparedStatement pstmt2 = null;
						ResultSet rs2 = null;

						try {
							pstmt2 = DB.prepareStatement(sql2, null);
							pstmt2.setInt(1, p_M_RMA_ID);
							pstmt2.setInt(2, entry.getKey());
							rs2 = pstmt2.executeQuery();
							if (rs2.next()) {
								productBookingQty2.put(rs2.getInt(1),rs2.getBigDecimal(2));
								BigDecimal booking = rs2.getBigDecimal(2);

								unbookedQty = entry.getValue().subtract(booking);
								moveLine.setQtyEntered(booking);
								moveLine.setMovementQty(booking);
								moveLine.setM_Locator_ID(p_M_Locator_Storage_ID);
								moveLine.setM_LocatorTo_ID(p_M_LocatorTo_ID);
								moveLine.saveEx();

								X_M_MatchMovement matchMovement = new X_M_MatchMovement(getCtx(), 0, get_TrxName());
								matchMovement.setC_Order_ID(p_C_Order_ID);
								matchMovement.setC_UOM_ID(moveLine.getC_UOM_ID());
								matchMovement.setM_Movement_ID(movement.getM_Movement_ID());
								matchMovement.setM_MovementLine_ID(moveLine.getM_MovementLine_ID());
								matchMovement.setQtyEntered(moveLine.getQtyEntered());
								matchMovement.setMovementQty(moveLine.getMovementQty());
								matchMovement.setTrxType("ORP");
								matchMovement.setM_Locator_ID(moveLine.getM_Locator_ID());
								matchMovement.setM_Locator_ID(moveLine.getM_LocatorTo_ID());
								matchMovement.saveEx();

								if (unbookedQty.compareTo(Env.ZERO) > 0) {
									String sql3 = "SELECT mov.M_Locator_ID, SUM(mov.MovementQty), mov.C_UOM_ID "
											+ "FROM M_MatchMovement mmatch "
											+ "JOIN M_MovementLine mov ON mmatch.M_MovementLine_ID=mov.M_MovementLine_ID "
											+ "AND mmatch.M_RMA_ID=? "
											+ "AND mov.M_Product_ID=? "
											+ "AND mmatch.TrXType='ORB' "
											+ "GROUP BY mov.m_locator_id, mov.C_UOM_ID";

									PreparedStatement pstmt3 = null;
									ResultSet rs3 = null;

									try {
										pstmt3 = DB.prepareStatement(sql3, null);
										pstmt3.setInt(1, p_M_RMA_ID);
										pstmt3.setInt(2, entry.getKey());
										rs3 = pstmt3.executeQuery();
										if (rs3.next()) {
											unbookedQty = entry.getValue().subtract(rs3.getBigDecimal(2));
											MMovementLine moveLine2 = new MMovementLine(movement);
											moveLine2.setM_Product_ID(entry.getKey());
											moveLine2.setQtyEntered(unbookedQty);
											moveLine2.setMovementQty(unbookedQty);
											moveLine2.setM_Locator_ID(rs3.getInt(1));
											moveLine2.setM_LocatorTo_ID(p_M_LocatorTo_ID);
											moveLine2.saveEx();

											X_M_MatchMovement matchMovement2 = new X_M_MatchMovement(getCtx(), 0, get_TrxName());
											matchMovement2.setM_RMA_ID(p_M_RMA_ID);
											matchMovement2.setQtyEntered(moveLine2.getQtyEntered());
											matchMovement2.setC_UOM_ID(moveLine2.getC_UOM_ID());
											matchMovement2.setM_Movement_ID(movement.getM_Movement_ID());
											matchMovement2.setM_MovementLine_ID(moveLine2.getM_MovementLine_ID());
											matchMovement2.setMovementQty(moveLine2.getMovementQty());
											matchMovement2.setTrxType("ORP");
											matchMovement2.setM_Locator_ID(moveLine2.getM_Locator_ID());
											matchMovement2.setM_Locator_ID(moveLine2.getM_LocatorTo_ID());
											matchMovement2.saveEx();

										}
									} catch (Exception e) {
										return "Error";
									} finally {
										DB.close(rs3, pstmt3);
										pstmt3 = null;
										rs3 = null;

									}
								}
							}

						} catch (Exception e) {
							return "Error";
						} finally {
							DB.close(rs2, pstmt2);
							pstmt2 = null;
							rs2 = null;
						}

					}
				}
			}
		}
		String message = Msg.parseTranslation(getCtx(), "@GeneratedPicking@"+ movement.getDocumentNo());
		addBufferLog(0, null, null, message, movement.get_Table_ID(),movement.getM_Movement_ID());

		return "";
	}

}
