package org.idempiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MInventory;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

@org.adempiere.base.annotation.Process
public class TaoCreateCostRecord extends SvrProcess {
	

	/**	Organization to be imported to	*/
	private int				p_AD_Org_ID = 0;
	/**	Default Date					*/
	private Timestamp		p_MovementDate = null;
	/**	Accounting Schema in which costing to be updated	*/
	private int				p_C_AcctSchema_ID = 0;
	MAcctSchema acctSchema 	= null;
	/**	Cost Type for which costing to be updated		*/
	private int				p_M_CostType_ID = 0;
	/**	Cost Element for which costing to be updated	*/
	private int				p_M_CostElement_ID = 0;

	private MInventory 		costingDoc = null;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("C_AcctSchema_ID"))
				p_C_AcctSchema_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("M_CostElement_ID"))
				p_M_CostElement_ID = ((BigDecimal)para[i].getParameter()).intValue();

			else
				log.log(Level.WARNING, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		
		if (p_AD_Org_ID < 0) {
			throw new IllegalArgumentException("Org required!");
		}
		
		if (p_C_AcctSchema_ID <= 0) {
			throw new IllegalArgumentException("Accounting Schema required!");
		}

		if (p_M_CostElement_ID <= 0 ) {
			throw new IllegalArgumentException("Cost Element required!");
		}
		
		acctSchema = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID, get_TrxName());
		
		String whereClause = "m_product_id not in (select distinct m_product_id from m_cost mc where m_costelement_id=? "
				+ "and ad_client_id=? and c_acctschema_id=? and m_attributesetinstance_id=0)";
		
		int[] products = new Query(getCtx(),MProduct.Table_Name, whereClause, get_TrxName())
							.setParameters(new Object[]{p_M_CostElement_ID, getAD_Client_ID(), p_C_AcctSchema_ID})
							.setOnlyActiveRecords(true)
							.getIDs();
		
		for (int productID : products ) { 
			MProduct product = MProduct.get(getCtx(), productID);
			
			MCost cost = MCost.get (product, 0
					, acctSchema, 0, p_M_CostElement_ID, get_TrxName());
			if (cost.is_new())
				cost.saveEx();
		}
		return null;
	}

}
