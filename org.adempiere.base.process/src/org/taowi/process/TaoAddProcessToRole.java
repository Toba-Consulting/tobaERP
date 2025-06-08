package org.taowi.process;

import java.util.logging.Level;

import org.compiere.model.MProcess;
import org.compiere.model.MRole;
import org.compiere.model.MTab;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Process_Access;
import org.compiere.model.X_AD_Tab;
import org.compiere.model.X_AD_ToolBarButton;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 * @author Febrian
 * TAOWI-1065
 * add all process based on window
 */
@org.adempiere.base.annotation.Process
public class TaoAddProcessToRole extends SvrProcess {

	private int p_AD_Window_ID = 0;
	private boolean p_IsListProcessOnly = false;
	private boolean p_IsReportOnly = false;
	private int p_AD_Role_ID = -1;

	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Window_ID"))
				p_AD_Window_ID = para[i].getParameterAsInt();
			else if (name.equals("IsListProcessOnly"))
				p_IsListProcessOnly = para[i].getParameterAsBoolean();
			else if (name.equals("IsReportOnly"))
				p_IsReportOnly = para[i].getParameterAsBoolean();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {

		// role validation
		p_AD_Role_ID=getRecord_ID();
		
		if (p_AD_Role_ID <= 0)
			return "No Role Selected";

		MRole role = new MRole(getCtx(), p_AD_Role_ID, get_TrxName());
		
		if (p_AD_Window_ID >0) {
			
			int[] tabIDs = new Query(getCtx(), X_AD_Tab.Table_Name, " AD_Window_ID = ? ",get_TrxName())
			.setParameters(new Object[]{p_AD_Window_ID})
			.setOnlyActiveRecords(true)
			.getIDs();

			for (int tabID : tabIDs) {
				
				MTab tab = new MTab(getCtx(),tabID,get_TrxName());
				int tabProcessID = tab.getAD_Process_ID();
				if(tabProcessID>0)
				{
					//check access
					int processID = DB.getSQLValue(get_TrxName(), "SELECT AD_Process_ID FROM AD_Process_Access "
							+ "WHERE AD_Role_ID=? AND AD_Process_ID=?",
							new Object[]{p_AD_Role_ID,tabProcessID});
					if(processID<0)
					{
						MProcess process = new MProcess(getCtx(),tabProcessID,get_TrxName());
						if((p_IsListProcessOnly && process.get_ValueAsBoolean("IsReport")==false)
								|| (p_IsReportOnly && process.get_ValueAsBoolean("IsReport")==true)
								|| (p_IsListProcessOnly && p_IsReportOnly))
						{
							X_AD_Process_Access processAccess = new X_AD_Process_Access(getCtx(), 0, get_TrxName());
							processAccess.setAD_Org_ID(role.getAD_Org_ID());
							processAccess.setAD_Role_ID(p_AD_Role_ID);
							processAccess.setAD_Process_ID(process.getAD_Process_ID());
							processAccess.setIsReadWrite(true);
							processAccess.saveEx();
						}
					}
				}
				
				StringBuilder processClause = new StringBuilder(" AD_Tab_ID=? AND AD_Process_ID NOT IN ")
				.append("(SELECT AD_Process_ID FROM AD_Process_Access WHERE AD_Role_ID=?)");
				
				int[] toolbarIDs = new Query(getCtx(),X_AD_ToolBarButton.Table_Name,processClause.toString(),get_TrxName())
				.setParameters(new Object[]{tabID,p_AD_Role_ID})
				.setOnlyActiveRecords(true)
				.getIDs();
				
				for (int toolbarID : toolbarIDs) {
					X_AD_ToolBarButton toolbar = new X_AD_ToolBarButton(getCtx(),toolbarID,get_TrxName());
					MProcess process = new MProcess(getCtx(),toolbar.getAD_Process_ID(),get_TrxName());
					
					if((p_IsListProcessOnly && process.get_ValueAsBoolean("IsReport")==false)
						|| (p_IsReportOnly && process.get_ValueAsBoolean("IsReport")==true)
						|| (p_IsListProcessOnly && p_IsReportOnly))
					{
						X_AD_Process_Access processAccess = new X_AD_Process_Access(getCtx(), 0, get_TrxName());
						processAccess.setAD_Org_ID(role.getAD_Org_ID());
						processAccess.setAD_Role_ID(p_AD_Role_ID);
						processAccess.setAD_Process_ID(process.getAD_Process_ID());
						processAccess.setIsReadWrite(true);
						processAccess.saveEx();
					}
				}
			}
			
			return "Successfully Added Access for Role: " + role.getName();
		}
		return null;
	}

}