package org.taowi.process;

import org.adempiere.exceptions.AdempiereException;
import org.taowi.model.MBPGInvAcctByCurrency;
import org.taowi.model.MBPCInvAcctByCurrency;
import org.taowi.model.MBPVInvAcctByCurrency;
import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

@org.adempiere.base.annotation.Process
public class TCS_BPGroupUpdateARAPAcctByCurrency extends SvrProcess{
	
	private int p_C_BPG_InvAcctByCurrency_ID=0;
	private int p_C_BPartner_ID=0;
	private boolean p_UpdateAllAssignedBP=false;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if(name.equals("C_BPartner_ID")){
				p_C_BPartner_ID = para[i].getParameterAsInt();
			}
			else if(name.equals("UpdateAllAssignedBP")){
				p_UpdateAllAssignedBP = "Y".equals(para[i].getParameter());
			}
		}
		p_C_BPG_InvAcctByCurrency_ID=getRecord_ID();		
	}
	
	@Override
	protected String doIt() throws Exception {
		
		if (!p_UpdateAllAssignedBP && p_C_BPartner_ID==0) {
			throw new AdempiereException("If not update all Business Partner, Business Partner is mandatory");
		}
		
		MBPGInvAcctByCurrency bpgInvAcct= new MBPGInvAcctByCurrency(getCtx(), p_C_BPG_InvAcctByCurrency_ID, get_TrxName());
		int []C_BPartner_IDs;
		if (p_UpdateAllAssignedBP) {
			String sql="AD_Client_ID="+Env.getAD_Client_ID(getCtx())+" AND C_BP_Group_ID="+bpgInvAcct.getC_BP_Group_ID();
			C_BPartner_IDs = new Query(getCtx(), MBPartner.Table_Name, sql, get_TrxName()).getIDs();
		}
		else C_BPartner_IDs=new int[]{p_C_BPartner_ID};
		
		for (int C_BPartner_ID : C_BPartner_IDs) {
			MBPCInvAcctByCurrency CustomerAcct=GetBPCustomerAcctByCurrency(bpgInvAcct.getAD_Client_ID(), C_BPartner_ID, bpgInvAcct.getC_Currency_ID());
			CustomerAcct.setC_AcctSchema_ID(bpgInvAcct.getC_AcctSchema_ID());
			CustomerAcct.setC_Receivable_Acct(bpgInvAcct.getC_Receivable_Acct());
			CustomerAcct.setUnEarnedRevenue_Acct(bpgInvAcct.getUnEarnedRevenue_Acct());
			CustomerAcct.setC_Prepayment_Acct(bpgInvAcct.getC_Prepayment_Acct());
			CustomerAcct.saveEx();
			
			MBPVInvAcctByCurrency VendorAcct=GetBPVendorAcctByCurrency(bpgInvAcct.getAD_Client_ID(), C_BPartner_ID, bpgInvAcct.getC_Currency_ID());
			VendorAcct.setC_AcctSchema_ID(bpgInvAcct.getC_AcctSchema_ID());
			VendorAcct.setV_Liability_Acct(bpgInvAcct.getV_Liability_Acct());
			VendorAcct.setNotInvoicedReceipts_Acct(bpgInvAcct.getNotInvoicedReceipts_Acct());
			VendorAcct.setV_Prepayment_Acct(bpgInvAcct.getV_Prepayment_Acct());
			VendorAcct.saveEx();
		}
		return "Success";
	}
	
	private MBPCInvAcctByCurrency GetBPCustomerAcctByCurrency(int AD_Client_ID, int C_BPartner_ID, int C_Currency_ID){
		
		String sql=	"SELECT C_BP_C_InvAcctByCurrency_ID "+
				"FROM C_BP_C_InvAcctByCurrency "+
				"WHERE AD_Client_ID="+AD_Client_ID+" AND C_BPartner_ID="+C_BPartner_ID+" AND C_Currency_ID="+C_Currency_ID+
				" ORDER BY Created DESC";
		
		int C_BP_C_InvAcctByCurrency_ID=DB.getSQLValue(get_TrxName(), sql);
		//if record not found set value to 0 to create new record
		if(C_BP_C_InvAcctByCurrency_ID<=0)C_BP_C_InvAcctByCurrency_ID=0;
		MBPCInvAcctByCurrency ret=new MBPCInvAcctByCurrency(getCtx(), C_BP_C_InvAcctByCurrency_ID, get_TrxName());
		//if is new record set C_BPartner_ID
		if(ret.getC_BPartner_ID()==0){
			ret.setC_BPartner_ID(C_BPartner_ID);
			ret.setC_Currency_ID(C_Currency_ID);
		}
		return ret;
	}
	
	private MBPVInvAcctByCurrency GetBPVendorAcctByCurrency(int AD_Client_ID, int C_BPartner_ID, int C_Currency_ID){
		
		String sql=	"SELECT C_BP_V_InvAcctByCurrency_ID "+
				"FROM C_BP_V_InvAcctByCurrency "+
				"WHERE AD_Client_ID="+AD_Client_ID+" AND C_BPartner_ID="+C_BPartner_ID+" AND C_Currency_ID="+C_Currency_ID+
				" ORDER BY Created DESC";
		
		int C_BP_V_InvAcctByCurrency_ID=DB.getSQLValue(get_TrxName(), sql);
		//if record not found set value to 0 to create new record
		if(C_BP_V_InvAcctByCurrency_ID<=0)C_BP_V_InvAcctByCurrency_ID=0;
		MBPVInvAcctByCurrency ret=new MBPVInvAcctByCurrency(getCtx(), C_BP_V_InvAcctByCurrency_ID, get_TrxName());
		//if is new record set C_BPartner_ID and C_Currency_ID
		if(ret.getC_BPartner_ID()==0){
			ret.setC_BPartner_ID(C_BPartner_ID);
			ret.setC_Currency_ID(C_Currency_ID);
		}
		return ret;
	}
}
