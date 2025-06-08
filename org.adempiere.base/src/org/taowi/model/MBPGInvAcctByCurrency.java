package org.taowi.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;

public class MBPGInvAcctByCurrency extends X_C_BPG_InvAcctByCurrency{

	public MBPGInvAcctByCurrency (Properties ctx, int C_BPG_InvAcctByCurrency_ID, String trxName)
    {
      super (ctx, C_BPG_InvAcctByCurrency_ID, trxName);
    }
	
	public MBPGInvAcctByCurrency (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		if (isActive()) {
			String sqlWhere="C_AcctSchema_ID="+getC_AcctSchema_ID()+" AND C_Currency_ID="+getC_Currency_ID()+" AND C_BP_Group_ID="+getC_BP_Group_ID();
			boolean match = new Query(getCtx(), Table_Name, sqlWhere, get_TrxName())
								.setOnlyActiveRecords(true)
								.match();
			if (match) {
				throw new AdempiereException("This Business Partner Group already has an active record for this currency");
			}
		}
		return true;
	}
}
