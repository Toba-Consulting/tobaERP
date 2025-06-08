package org.taowi.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MRole;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Process;
import org.compiere.model.X_AD_Process_Access;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

@org.adempiere.base.annotation.Process
public class taowi_AddProcessWorkflowToRole extends SvrProcess {

	private int p_AD_Role_ID = 0;

	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		p_AD_Role_ID = getRecord_ID();
		int[] AD_Process_IDs = null;
		int AD_Workflow_ID = 0;
		if (p_AD_Role_ID == 0)
			return "";

		MRole role = new MRole(getCtx(), p_AD_Role_ID, get_TrxName());
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT AD_WorkFlow_ID FROM AD_WorkFlow_Access WHERE AD_Role_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, p_AD_Role_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AD_Workflow_ID = rs.getInt(1);

				AD_Process_IDs = new Query(getCtx(), X_AD_Process.Table_Name,
						" AD_WorkFlow_ID = ? ", get_TrxName())
						.setParameters(new Object[] { AD_Workflow_ID })
						.setOnlyActiveRecords(true).getIDs();

				for (int AD_Process_ID : AD_Process_IDs) {

					X_AD_Process_Access processAccess = new X_AD_Process_Access(
							getCtx(), 0, get_TrxName());
					processAccess.setAD_Role_ID(p_AD_Role_ID);
					processAccess.setAD_Org_ID(role.getAD_Org_ID());
					processAccess.setAD_Process_ID(AD_Process_ID);
					processAccess.setIsReadWrite(true);
					processAccess.saveEx();

				}

			}

		} catch (Exception e) {
			return "Error";
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return "Successfully Added Process Access tied";
	}

}
