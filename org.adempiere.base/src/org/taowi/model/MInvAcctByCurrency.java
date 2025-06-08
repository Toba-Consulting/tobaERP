package org.taowi.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;

public class MInvAcctByCurrency extends X_C_InvAcctByCurrency{

	public MInvAcctByCurrency(Properties ctx, int C_InvAcctByCurrency_ID,String trxName) {
		super(ctx, C_InvAcctByCurrency_ID, trxName);
	}

	public MInvAcctByCurrency (Properties ctx, ResultSet rs, String trxName){
      super (ctx, rs, trxName);
    }
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		if (isActive()) {
			String sqlWhere="C_AcctSchema_ID="+getC_AcctSchema_ID()+" AND C_Currency_ID="+getC_Currency_ID();
			boolean match = new Query(getCtx(), Table_Name, sqlWhere, get_TrxName())
								.setOnlyActiveRecords(true)
								.match();
			if (match) {
				throw new AdempiereException("This Accounting Schema already has an active record for this currency");
			}
		}
		return true;
	}
}
