package org.idempiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

@org.adempiere.base.annotation.Process
public class ChangeBPCreditLimit extends SvrProcess {

	int p_C_BPartner_ID = 0;
	BigDecimal creditLimit = null;
	String creditStatus = "";
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			} else if ("CreditLimit".equals(name)) {
				creditLimit = para[i].getParameterAsBigDecimal();
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
		
		if (creditLimit == null)
			return "";
		
		else {
			bp.setSO_CreditLimit(creditLimit);
			bp.saveEx();
		}
		
		return "Successfully Updated BP Credit Limit to " + creditLimit;
		
	}

}
