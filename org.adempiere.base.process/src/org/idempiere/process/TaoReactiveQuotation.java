package org.idempiere.process;

import java.util.logging.Level;

import org.compiere.model.MQuotation;
import org.compiere.model.X_M_MatchQuotation;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

@org.adempiere.base.annotation.Process
public class TaoReactiveQuotation extends SvrProcess{

	private int p_C_Quotation_ID = 0;

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

		p_C_Quotation_ID = getRecord_ID();

	}


	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
		
		if (p_C_Quotation_ID == 0)
			return "No Quotation has been selected";
		
		MQuotation quotation = new MQuotation(getCtx(),
				p_C_Quotation_ID, get_TrxName());
		
		X_M_MatchQuotation matchquote[] = quotation.getMatchQuotation();
		
		if (matchquote.length == 0){
			quotation.setProcessed(false);
			quotation.setDocAction(DocAction.ACTION_None);
			quotation.setDocStatus(MQuotation.DOCSTATUS_Drafted);
			quotation.saveEx();
		}
		
		return null;
	}

}
