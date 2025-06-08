package org.taowi.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;

public class MBPCInvAcctByCurrency extends X_C_BP_C_InvAcctByCurrency{

	 public MBPCInvAcctByCurrency (Properties ctx, int C_BP_C_InvAcctByCurrency_ID, String trxName){
		 super (ctx, C_BP_C_InvAcctByCurrency_ID, trxName);
	 }
	 
	 public MBPCInvAcctByCurrency (Properties ctx, ResultSet rs, String trxName){
		 super (ctx, rs, trxName);
	 }
	 
		@Override
	protected boolean beforeSave(boolean newRecord) {
			
			if (isActive()) {
				String sqlWhere="C_AcctSchema_ID="+getC_AcctSchema_ID()+" AND C_Currency_ID="+getC_Currency_ID()+" AND C_BPartner_ID="+getC_BPartner_ID();
				boolean match = new Query(getCtx(), Table_Name, sqlWhere, get_TrxName())
									.setOnlyActiveRecords(true)
									.match();
				if (match) {
					throw new AdempiereException("This Business Partner already has an active record for this currency");
				}
			}
			return true;
		}	 
}
