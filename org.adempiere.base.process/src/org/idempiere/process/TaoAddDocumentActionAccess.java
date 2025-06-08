package org.idempiere.process;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MRole;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Document_Action_Access;
import org.compiere.model.X_AD_Ref_List;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

@org.adempiere.base.annotation.Process
public class TaoAddDocumentActionAccess extends SvrProcess {

	private int p_C_DocType_ID = -1;
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
			else if (name.equals("C_DocType_ID"))
				p_C_DocType_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {

		// validasi role
		p_AD_Role_ID = getRecord_ID();
		
		if (p_AD_Role_ID <= 0)
			return "No Role Selected";

		if (p_C_DocType_ID <= 0) {
			return "Document Type Not Selected";
		}

		List<Object> params = new ArrayList<Object>();
		params.add(p_C_DocType_ID);
		params.add(p_AD_Role_ID);

		MRole role = new MRole(getCtx(), p_AD_Role_ID, get_TrxName());

		String sql = "AD_Reference_ID=135 AND AD_Ref_List_ID NOT IN " +
				"(SELECT AD_Ref_List_ID FROM AD_Document_Action_Access WHERE C_DocType_ID=? AND AD_Role_ID=?)";
		
		int[] refLists = new Query(getCtx(), X_AD_Ref_List.Table_Name, sql, get_TrxName())
						.setParameters(params)
						.setOnlyActiveRecords(true)
						.getIDs();
		
		for (int refList: refLists) {
			X_AD_Document_Action_Access docAccess = new X_AD_Document_Action_Access(getCtx(), 0, get_TrxName());
			docAccess.setAD_Org_ID(0);
			docAccess.setC_DocType_ID(p_C_DocType_ID);
			docAccess.setAD_Role_ID(p_AD_Role_ID);
			docAccess.setAD_Ref_List_ID(refList);
			docAccess.saveEx();
		}
		
		role.loadAccess(true);
		return "Successfully Added Access for Role: " + role.getName();
	}
}
