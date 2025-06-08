package org.taowi.process;

import org.adempiere.exceptions.AdempiereException;
import org.taowi.model.MBPGInvAcctByCurrency;
import org.compiere.model.MBPGroup;
import org.taowi.model.MBPCInvAcctByCurrency;
import org.taowi.model.MBPVInvAcctByCurrency;
import org.compiere.model.MBPartner;
import org.taowi.model.MInvAcctByCurrency;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

@org.adempiere.base.annotation.Process
public class TCS_AcctSchemaUpdateARAPAcctByCurrency extends SvrProcess{

	private int p_C_InvAcctByCurrency_ID=0;
	private int p_C_BP_Group_ID=0;
	private boolean p_UpdateAllBPGroup=false;	
	private boolean p_UpdateAllAssignedBP=false;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if(name.equals("C_BP_Group_ID")){
				p_C_BP_Group_ID = para[i].getParameterAsInt();
			}
			else if(name.equals("UpdateAllBPGroup")){
				p_UpdateAllBPGroup = "Y".equals(para[i].getParameter());
			}
			else if(name.equals("UpdateAllAssignedBP")){
				p_UpdateAllAssignedBP = "Y".equals(para[i].getParameter());
			}
		}
		p_C_InvAcctByCurrency_ID=getRecord_ID();		
	}

	@Override
	protected String doIt() throws Exception {
		
		if (!p_UpdateAllBPGroup && p_C_BP_Group_ID==0) {
			throw new AdempiereException("If not update all BP Group, BP Group is mandatory");
		}
		
		
		MInvAcctByCurrency invAcct = new MInvAcctByCurrency(getCtx(), p_C_InvAcctByCurrency_ID, get_TrxName());
		int [] C_BP_Group_IDs = null;
		if (p_UpdateAllBPGroup) {
			String sql="AD_Client_ID="+Env.getAD_Client_ID(getCtx());
			C_BP_Group_IDs = new Query(getCtx(), MBPGroup.Table_Name, sql, get_TrxName()).getIDs();
		}
		else C_BP_Group_IDs= new int[]{p_C_BP_Group_ID};
		
		for (int C_BP_Group_ID : C_BP_Group_IDs) {
			int C_BPG_InvAcctByCurrency_ID=GetBPGroupAcctByCurrency(invAcct.getAD_Client_ID(),C_BP_Group_ID,invAcct.getC_Currency_ID());
			MBPGInvAcctByCurrency bpgInvAcct = new MBPGInvAcctByCurrency(getCtx(), C_BPG_InvAcctByCurrency_ID, get_TrxName());
			bpgInvAcct.setC_AcctSchema_ID(invAcct.getC_AcctSchema_ID());
			bpgInvAcct.setC_Currency_ID(invAcct.getC_Currency_ID());
			bpgInvAcct.setC_Receivable_Acct(invAcct.getC_Receivable_Acct());
			bpgInvAcct.setV_Liability_Acct(invAcct.getV_Liability_Acct());
			bpgInvAcct.setUnEarnedRevenue_Acct(invAcct.getUnEarnedRevenue_Acct());
			bpgInvAcct.setNotInvoicedReceipts_Acct(invAcct.getNotInvoicedReceipts_Acct());
			bpgInvAcct.setC_Prepayment_Acct(invAcct.getC_Prepayment_Acct());
			bpgInvAcct.setV_Prepayment_Acct(invAcct.getV_Prepayment_Acct());
			
			if(bpgInvAcct.getC_BP_Group_ID()==0)bpgInvAcct.setC_BP_Group_ID(C_BP_Group_ID);
			
			bpgInvAcct.saveEx();
			
			if (p_UpdateAllAssignedBP) {
				String sql="C_BP_Group_ID="+C_BP_Group_ID;
				int [] AssignedBPs=new Query(getCtx(), MBPartner.Table_Name, sql, get_TrxName()).getIDs();
				for (int AssignedBP : AssignedBPs) {
					MBPCInvAcctByCurrency CustomerAcct=GetBPCustomerAcctByCurrency(invAcct.getAD_Client_ID(), AssignedBP, invAcct.getC_Currency_ID());
					CustomerAcct.setC_AcctSchema_ID(invAcct.getC_AcctSchema_ID());
					CustomerAcct.setC_Receivable_Acct(invAcct.getC_Receivable_Acct());
					CustomerAcct.setUnEarnedRevenue_Acct(invAcct.getUnEarnedRevenue_Acct());
					CustomerAcct.setC_Prepayment_Acct(invAcct.getC_Prepayment_Acct());
					CustomerAcct.saveEx();
					
					MBPVInvAcctByCurrency VendorAcct=GetBPVendorAcctByCurrency(invAcct.getAD_Client_ID(), AssignedBP, invAcct.getC_Currency_ID());
					VendorAcct.setC_AcctSchema_ID(invAcct.getC_AcctSchema_ID());
					VendorAcct.setV_Liability_Acct(invAcct.getV_Liability_Acct());
					VendorAcct.setNotInvoicedReceipts_Acct(invAcct.getNotInvoicedReceipts_Acct());
					VendorAcct.setV_Prepayment_Acct(invAcct.getV_Prepayment_Acct());
					VendorAcct.saveEx();
				}
			}
		}
		
		return "Success";
	}

	private int GetBPGroupAcctByCurrency(int AD_Client_ID, int C_BP_Group_ID, int C_Currency_ID){

		String sql=	"SELECT C_BPG_InvAcctByCurrency_ID "+
					"FROM C_BPG_InvAcctByCurrency "+
					"WHERE AD_Client_ID="+AD_Client_ID+" AND C_BP_Group_ID="+C_BP_Group_ID+" AND C_Currency_ID="+C_Currency_ID+
					" ORDER BY Created DESC";
		
		int C_BPG_InvAcctByCurrency_ID=DB.getSQLValue(get_TrxName(), sql);
		
		if(C_BPG_InvAcctByCurrency_ID<0)
			return 0;
		else 
			return C_BPG_InvAcctByCurrency_ID;
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
