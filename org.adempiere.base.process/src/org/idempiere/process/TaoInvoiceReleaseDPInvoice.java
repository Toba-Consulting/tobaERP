package org.idempiere.process;

import java.util.logging.Level;

import org.compiere.model.MInvoice;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

@org.adempiere.base.annotation.Process
public class TaoInvoiceReleaseDPInvoice extends SvrProcess {
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
		
		if (p_DP_Invoice_ID == 0)
			return "Invoice DP not selected";
		
		MInvoice invoiceDP = new MInvoice(getCtx(), p_DP_Invoice_ID, get_TrxName());
		
		DB.executeUpdateEx("DELETE FROM M_MatchDownPayment WHERE DP_Invoice_ID=?", new Object[] {p_DP_Invoice_ID}, get_TrxName());
		invoiceDP.setIsInvoiceDPApplied(false);
		invoiceDP.saveEx();
		
		invoiceOri.setTotalLines(invoiceOri.getTotalLines().add(invoiceDP.getTotalLines()));
		invoiceOri.setGrandTotal(invoiceOri.getGrandTotal().add(invoiceDP.getGrandTotal()));
		invoiceOri.saveEx();
	
		return "";
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

