package org.taowi.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 * @author Stephan
 * process run with scheduler or cron for refresh materialize view
 */

@org.adempiere.base.annotation.Process
public class TaoRefreshMatView extends SvrProcess{

	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		//	add materialized view in string array
		String[] views = 
			{
				"tcs_location_mv", "tcs_org_mv", "tcs_elementvalue_mv", "tcs_warehouse_mv", 
				"tcs_bp_location_mv", "tcs_partner_mv", "tcs_bp_customer_acct_mv", 
				"tcs_bp_vendor_acct_mv", "tcs_bp_employee_acct_mv", "tcs_partnerrelation_mv", 
				"tcs_product_mv", "tcs_charge_mv", "tcs_charge_acct_mv", "tcs_pricelist_productprice_mv", 
				"tcs_project_mv", "tcs_project_acct_mv"
			};
		
		for (String view : views) {
			try{
				StringBuilder sb = new StringBuilder();
				sb.append("REFRESH MATERIALIZED VIEW "+view);
				DB.executeUpdate(sb.toString(), get_TrxName());
			}catch(Exception e){
				log.severe("Error refresh view "+ e.toString());
			}
		}
		
		return null;
	}

}
