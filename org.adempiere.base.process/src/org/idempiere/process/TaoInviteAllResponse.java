package org.idempiere.process;

import java.util.logging.Level;

import org.compiere.model.MMailText;
import org.compiere.model.MRfQ;
import org.compiere.model.MRfQResponse;
import org.compiere.model.MRfQTopic;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

@org.adempiere.base.annotation.Process
public class TaoInviteAllResponse extends SvrProcess {
	
	/**	RfQ Response				*/
	private int		p_C_RfQ_ID = 0;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_C_RfQ_ID = getRecord_ID();
	}	//	prepare


	@Override
	protected String doIt() throws Exception {
		
		int sent = 0;
		int notSent = 0;
		StringBuilder retValue = new StringBuilder();
		
		MRfQ rfq = new MRfQ(getCtx(), p_C_RfQ_ID, get_TrxName());
		MRfQResponse[] responses = rfq.getResponses(true, false);
		
		for (MRfQResponse response: responses) {
			MRfQTopic rfQTopic = new MRfQTopic(getCtx(), response.getC_RfQ().getC_RfQ_Topic_ID() , get_TrxName());
			if (rfQTopic.get_ValueAsInt("R_MailText_ID") > 0) {
				MMailText mailText = new MMailText(getCtx(),rfQTopic.get_ValueAsInt("R_MailText_ID"), get_TrxName());
				if (response.sendRfQ(mailText))
					sent++;
				else
					notSent++;
			}
		}	
		retValue.append("@Sent@=").append(sent).append(" - @Error@=").append(notSent);
		
		return null;
	}

}
