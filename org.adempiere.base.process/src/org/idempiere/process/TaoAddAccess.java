package org.idempiere.process;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MForm;
import org.compiere.model.MInfoWindow;
import org.compiere.model.MProcess;
import org.compiere.model.MRole;
import org.compiere.model.MWindow;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Form_Access;
import org.compiere.model.X_AD_InfoWindow_Access;
import org.compiere.model.X_AD_Process_Access;
import org.compiere.model.X_AD_Window_Access;
import org.compiere.model.X_AD_Workflow_Access;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.wf.MWorkflow;

@org.adempiere.base.annotation.Process
public class TaoAddAccess extends SvrProcess {

	private String p_AD_Module = "";
	private int p_AD_Role_ID = -1;

	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			/*
			else if (name.equals("AD_Role_ID"))
				p_AD_Role_ID = para[i].getParameterAsInt();
			*/
			else if (name.equals("AD_Module"))
				p_AD_Module = para[i].getParameterAsString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {

		// validasi role
		p_AD_Role_ID=getRecord_ID();
		
		if (p_AD_Role_ID <= 0)
			return "No Role Selected";
		
		if (p_AD_Module != null) {

			List<Object> params = new ArrayList<Object>();
			params.add(p_AD_Module);
			params.add(p_AD_Role_ID);

			MRole role = new MRole(getCtx(), p_AD_Role_ID, get_TrxName());
			StringBuilder windowClause = new StringBuilder(" AD_Module=? AND AD_Window_ID NOT IN ")
			.append("(SELECT AD_Window_ID FROM AD_Window_Access WHERE AD_Role_ID=?)");

			int[] windowIDs = new Query(getCtx(), MWindow.Table_Name,windowClause.toString(), get_TrxName())
			.setParameters(params)
			.setOnlyActiveRecords(true)
			.getIDs();

			for (int windowID : windowIDs) {
				X_AD_Window_Access windowAccess = new X_AD_Window_Access(getCtx(), 0, get_TrxName());
				windowAccess.setAD_Org_ID(role.getAD_Org_ID());
				windowAccess.setAD_Role_ID(p_AD_Role_ID);
				windowAccess.setAD_Window_ID(windowID);
				windowAccess.setIsReadWrite(true);
				windowAccess.saveEx();
			}

			StringBuilder infoWindowClause = new StringBuilder(" AD_Module=? AND AD_InfoWindow_ID NOT IN ")
			.append("(SELECT AD_InfoWindow_ID FROM AD_InfoWindow_Access WHERE AD_Role_ID=?)");

			int[] infoWindowIDs = new Query(getCtx(), MInfoWindow.Table_Name,infoWindowClause.toString(), get_TrxName())
			.setParameters(params)
			.setOnlyActiveRecords(true)
			.getIDs();

			for (int infoWindowID : infoWindowIDs) {
				X_AD_InfoWindow_Access infoWindowAccess = new X_AD_InfoWindow_Access(getCtx(), 0, get_TrxName());
				infoWindowAccess.setAD_Org_ID(role.getAD_Org_ID());
				infoWindowAccess.setAD_Role_ID(p_AD_Role_ID);
				infoWindowAccess.setAD_InfoWindow_ID(infoWindowID);
				infoWindowAccess.saveEx();
			}

			StringBuilder workflowClause = new StringBuilder(" AD_Module=? AND AD_Workflow_ID NOT IN ")
			.append("(SELECT AD_Workflow_ID FROM AD_Workflow_Access WHERE AD_Role_ID=?)");

			int[] workflowIDs = new Query(getCtx(), MWorkflow.Table_Name,workflowClause.toString(), get_TrxName())
			.setParameters(params)
			.setOnlyActiveRecords(true)
			.getIDs();

			for (int workflow : workflowIDs) {

				X_AD_Workflow_Access WorkflowAccess = new X_AD_Workflow_Access(getCtx(), 0, get_TrxName());
				WorkflowAccess.setAD_Org_ID(role.getAD_Org_ID());
				WorkflowAccess.setAD_Role_ID(p_AD_Role_ID);
				WorkflowAccess.setAD_Workflow_ID(workflow);
				WorkflowAccess.setIsReadWrite(true);
				WorkflowAccess.saveEx();
			}

			StringBuilder processClause = new StringBuilder(" AD_Module=? AND AD_Process_ID NOT IN ")
			.append("(SELECT AD_Process_ID FROM AD_Process_Access WHERE AD_Role_ID=?)");

			int[] processIDs = new Query(getCtx(), MProcess.Table_Name,processClause.toString(), get_TrxName())
			.setParameters(params)
			.setOnlyActiveRecords(true)
			.getIDs();

			for (int process : processIDs) {

				X_AD_Process_Access processAccess = new X_AD_Process_Access(getCtx(), 0, get_TrxName());
				processAccess.setAD_Org_ID(role.getAD_Org_ID());
				processAccess.setAD_Role_ID(p_AD_Role_ID);
				processAccess.setAD_Process_ID(process);
				processAccess.setIsReadWrite(true);
				processAccess.saveEx();

			}

			StringBuilder formClause = new StringBuilder(" AD_Module=? AND AD_Form_ID NOT IN ")
			.append("(SELECT AD_Form_ID FROM AD_Form_Access WHERE AD_Role_ID=?)");

			int[] formIDs = new Query(getCtx(), MForm.Table_Name,formClause.toString(), get_TrxName())
			.setParameters(params)
			.setOnlyActiveRecords(true)
			.getIDs();


			for (int form : formIDs) {
				X_AD_Form_Access formAccess = new X_AD_Form_Access(getCtx(), 0,get_TrxName());
				formAccess.setAD_Org_ID(role.getAD_Org_ID());
				formAccess.setAD_Role_ID(p_AD_Role_ID);
				formAccess.setAD_Form_ID(form);
				formAccess.setIsReadWrite(true);
				formAccess.saveEx();
			}
			
			return "Successfully Added Access for Role: " + role.getName();
		}
		return null;
	}

}