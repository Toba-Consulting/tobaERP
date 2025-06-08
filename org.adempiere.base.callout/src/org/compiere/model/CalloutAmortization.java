package org.compiere.model;

import java.util.Properties;

import org.compiere.util.Env;

/**
 * @author Stephan
 * Callout for amortization plan and amortization run
 */
public class CalloutAmortization extends CalloutEngine{

	/**
	 * @param ctx, windowNo, mTab, mField, value
	 * @return empty string
	 */
	public String period(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if(value == null)
			return "";
		
		int C_Period_ID = (int) value;
		MPeriod period = new MPeriod(Env.getCtx(), C_Period_ID, null);
		mTab.setValue("DateAcct", period.getEndDate());
		mTab.setValue("DateDoc", period.getEndDate());
		
		return "";
	}
	
}
