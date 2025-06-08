package org.idempiere.process;

import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

@org.adempiere.base.annotation.Process
public class ChangeBPCreditStatus extends SvrProcess {

	int p_C_BPartner_ID = 0;
	String creditStatus = "";
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			} else if ("SOCreditStatus".equals(name)) {
				creditStatus = para[i].getParameterAsString();
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}
	}
	@Override
	protected String doIt() throws Exception {
		
		p_C_BPartner_ID = getRecord_ID();
		
		if (p_C_BPartner_ID <=0)
			return "Error";
		
		MBPartner bp = new MBPartner(getCtx(), p_C_BPartner_ID, get_TrxName());
		
		if (creditStatus.equalsIgnoreCase(bp.getSOCreditStatus()))
			return "";
		
		else {
			bp.setSOCreditStatus(creditStatus);
			bp.saveEx();
		}
		
		return "Successfully Updated BP Credit Status to " + creditStatus;
		
	}

}
