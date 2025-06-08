package org.idempiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.I_M_MatchDownPayment;
import org.compiere.model.MInvoice;
import org.compiere.model.Query;
import org.compiere.model.X_M_MatchDownPayment;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

@org.adempiere.base.annotation.Process
public class TaoApplyDPInvoice extends SvrProcess {

	private int p_C_Invoice_ID = 0;
	private int p_DP_Invoice_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			} else if (para[i].getParameterName().equalsIgnoreCase(
					"C_Invoice_ID")) {
				p_DP_Invoice_ID = para[i].getParameterAsInt();
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}

	}

	@Override
	protected String doIt() throws Exception {
		
		p_C_Invoice_ID = getRecord_ID();
		
		MInvoice invoiceOri = new MInvoice(getCtx(), p_C_Invoice_ID, get_TrxName());
		
		if (invoiceOri.isDownPaymentInvoice())
			return "Error: Target Invoice is down payment invoice";
		
		if (p_DP_Invoice_ID == 0)
			return "Invoice DP not selected";
		
		MInvoice invoiceDP = new MInvoice(getCtx(), p_DP_Invoice_ID, get_TrxName());
		
		boolean match = new Query(getCtx(), I_M_MatchDownPayment.Table_Name, "DP_Invoice_ID=? AND C_Invoice_ID IS NOT NULL",get_TrxName())
						.setParameters(new Object[]{p_DP_Invoice_ID})
						.match();
		
		if (match) {
			return "Invoice DP has been applied before";
		}
	
		BigDecimal totalLines = Env.ZERO;
		BigDecimal GrandTotals = Env.ZERO;
		
		/* @stephan TAOWI-1019
		List<X_M_MatchDownPayment> matchDPs = new Query(getCtx(), I_M_MatchDownPayment.Table_Name, "C_Invoice_ID=?",get_TrxName())
		.setParameters(new Object[]{p_C_Invoice_ID})
		.list();
		
		if (!matchDPs.isEmpty()) {
			for (X_M_MatchDownPayment dp : matchDPs) {
				MInvoice inv = new MInvoice(getCtx(), dp.getDP_Invoice_ID(), get_TrxName());
				totalLines= totalLines.add(inv.getTotalLines());
				GrandTotals= GrandTotals.add(inv.getGrandTotal());
				
			}
		}  end
		*/
		
		X_M_MatchDownPayment matchDP = new X_M_MatchDownPayment(getCtx(), 0, get_TrxName());
		matchDP.setC_Invoice_ID(p_C_Invoice_ID);
		matchDP.setDP_Invoice_ID(p_DP_Invoice_ID);
		matchDP.setAD_Org_ID(invoiceOri.getAD_Org_ID());
		matchDP.setDownPaymentAmt(invoiceDP.getTotalLines());
		matchDP.setIsSOTrx(invoiceDP.isSOTrx());
		matchDP.saveEx();
	
		invoiceDP.setIsInvoiceDPApplied(true);
		invoiceDP.saveEx();
		
		totalLines = totalLines.add(invoiceDP.getTotalLines());
		GrandTotals = GrandTotals.add(invoiceDP.getGrandTotal());
		
		invoiceOri.setTotalLines(invoiceOri.getTotalLines().subtract(totalLines));
		invoiceOri.setGrandTotal(invoiceOri.getGrandTotal().subtract(GrandTotals));
		invoiceOri.saveEx();
	
		repost(invoiceOri.getC_Invoice_ID(), invoiceDP.getC_Invoice_ID());
		
		String message = Msg.parseTranslation(getCtx(),
				"@InvoiceDPApplied@ ");
		addBufferLog(0, null, null, message, 0,
				0);
		
		return null;
	}

	/**
	 * TAOWI-2266
	 * this method will delete previous fact acct or journal
	 * and repost immediately
	 * @param C_InvoiceOri_ID, C_InvoiceDP_ID
	 */
	protected void repost(int C_InvoiceOri_ID, int C_InvoiceDP_ID){
		//  delete record from fact acct
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM Fact_Acct WHERE AD_Table_ID = "+MInvoice.Table_ID)
		.append(" AND Record_ID=?");
		
		int no = DB.executeUpdate(sb.toString(), C_InvoiceOri_ID, get_TrxName());
		no += DB.executeUpdate(sb.toString(), C_InvoiceDP_ID, get_TrxName());
		
		log.info("DELETED Fact_Acct RECORD#"+no);
		
		//  update posted invoice to not posted
		sb = new StringBuilder();
		sb.append("UPDATE C_Invoice SET Posted='N' WHERE C_Invoice_ID=?");
		
		no = DB.executeUpdate(sb.toString(), C_InvoiceOri_ID, get_TrxName());
		no += DB.executeUpdate(sb.toString(), C_InvoiceDP_ID, get_TrxName());
		
		//  post immediately
		MInvoice invoiceOri = new MInvoice(getCtx(), C_InvoiceOri_ID, get_TrxName());
		invoiceOri.processIt(DocAction.ACTION_Post);
		invoiceOri.saveEx();
		
		MInvoice invoiceDP = new MInvoice(getCtx(), C_InvoiceDP_ID, get_TrxName());
		invoiceDP.processIt(DocAction.ACTION_Post);
		invoiceDP.saveEx();
	}
	
}
