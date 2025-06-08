package org.idempiere.process;

import java.util.logging.Level;

import org.compiere.model.MRole;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

@org.adempiere.base.annotation.Process
public class TaoDeleteAccess extends SvrProcess {

	private String p_AD_Module = "";
	private int p_AD_Role_ID = -1;
	private boolean p_Delete_All = false;
	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Role_ID"))
				p_AD_Role_ID = para[i].getParameterAsInt();
			else if (name.equals("AD_Module"))
				p_AD_Module = para[i].getParameterAsString();
			else if (name.equals("DeleteAll"))
				p_Delete_All = para[i].getParameterAsBoolean();

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {

		p_AD_Role_ID = getRecord_ID();
		
		if (p_AD_Role_ID<=0)
			return "Error: No Role is Selected";

		StringBuilder whereClause = new StringBuilder();
		whereClause.append(" WHERE AD_Role_ID =" + p_AD_Role_ID);

		if (p_Delete_All) {
			int winDel = DB.executeUpdateEx("DELETE FROM AD_Window_Access" + whereClause.toString(),get_TrxName());
			int procDel = DB.executeUpdateEx("DELETE FROM AD_Process_Access" + whereClause.toString(),get_TrxName());
			int formDel = DB.executeUpdateEx("DELETE FROM AD_Form_Access" + whereClause.toString(),get_TrxName());
			int wfDel = DB.executeUpdateEx("DELETE FROM AD_WorkFlow_Access" + whereClause.toString(),get_TrxName());
			int infoDel = DB.executeUpdateEx("DELETE FROM AD_InfoWindow_Access" + whereClause.toString(),get_TrxName());
			int orgDel = DB.executeUpdateEx("DELETE FROM AD_Role_OrgAccess" + whereClause.toString(),get_TrxName());
			int taskDel = DB.executeUpdateEx("DELETE FROM AD_Task_Access" + whereClause.toString(),get_TrxName());
			int docActionDel = DB.executeUpdateEx("DELETE FROM AD_Document_Action_Access" + whereClause.toString(),get_TrxName());
			int toolbarDel = DB.executeUpdateEx("DELETE FROM AD_ToolBarButtonRestrict" + whereClause.toString(),get_TrxName());
			int whDel = DB.executeUpdateEx("DELETE FROM AD_Role_WHAccess" + whereClause.toString(),get_TrxName());

			
			if (log.isLoggable(Level.FINE))
				log.fine("AD_Window_Access=" + winDel 
						+ ", AD_Process_Access="+ procDel 
						+ ", AD_Form_Access=" + formDel
						+ ", AD_Workflow_Access=" + wfDel
						+ ", AD_InfoWindow_Access=" + infoDel
						+ ", AD_Role_OrgAccess=" + orgDel
						+ ", AD_Task_Access=" + taskDel
						+ ", AD_Document_Action_Access=" + docActionDel
						+ ", AD_ToolBarButtonRestrict=" + toolbarDel
						+ ", AD_Role_WHAccess=" + whDel);

			MRole role = new MRole(getCtx(), p_AD_Role_ID, get_TrxName());
			return "Successfully Deleted All Access for Role " + role.getName();

		} else if (p_AD_Module != null) {
			int winDel = DB.executeUpdateEx("DELETE FROM AD_Window_Access"
					+ whereClause.toString()
					+ " AND AD_Window_ID IN (SELECT AD_Window_ID FROM AD_Window WHERE AD_Module ="
					+ "'" + p_AD_Module + "'" + ")",
					get_TrxName());

			int procDel = DB.executeUpdateEx("DELETE FROM AD_Process_Access"
					+ whereClause.toString()
					+ " AND AD_Process_ID IN (SELECT AD_Process_ID FROM AD_Process WHERE AD_Module ="
					+ "'" + p_AD_Module + "'" + ")",
					get_TrxName());

			int formDel = DB.executeUpdateEx("DELETE FROM AD_Form_Access"
					+ whereClause.toString()
					+ " AND AD_Form_ID IN (SELECT AD_Form_ID from AD_Form WHERE AD_Module ="
					+ "'" + p_AD_Module + "'" + ")",
					get_TrxName());

			int wfDel = DB.executeUpdateEx("DELETE FROM AD_WorkFlow_Access"
					+ whereClause.toString()
					+ " AND AD_WorkFlow_ID IN (SELECT AD_WorkFlow_ID FROM AD_WorkFlow WHERE AD_Module ="
					+ "'" + p_AD_Module + "'" + ")",
					get_TrxName());

			int infoDel = DB.executeUpdateEx("DELETE FROM AD_InfoWindow_Access"
					+ whereClause.toString()
					+ " AND AD_InfoWindow_ID IN (SELECT AD_InfoWindow_ID from AD_InfoWindow WHERE AD_Module ="
					+ "'" + p_AD_Module + "'" + ")",
					get_TrxName());


			if (log.isLoggable(Level.FINE))
				log.fine("AD_Window_Access=" + winDel + ", AD_Process_Access="
						+ procDel + ", AD_Form_Access=" + formDel
						+ ", AD_Workflow_Access=" + wfDel
						+ ", AD_InfoWindow_Access=" + infoDel);
		}
		MRole role = new MRole(getCtx(), p_AD_Role_ID, get_TrxName());
		return "Successfully Deleted Access for Role " + role.getName();

	}
}
