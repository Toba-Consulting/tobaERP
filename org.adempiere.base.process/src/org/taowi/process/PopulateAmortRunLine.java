package org.taowi.process;


import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPeriod;
import org.compiere.model.MTCSAmortizationLine;
import org.compiere.model.MTCSAmortizationRun;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.CLogger;
/**
 * @author Stephan
 * copy from amortization line to amortization run
 */
@org.adempiere.base.annotation.Process
public class PopulateAmortRunLine extends SvrProcess{

	private CLogger log = CLogger.getCLogger(PopulateAmortRunLine.class);
	
	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		
		int AmortizationRun_ID = getRecord_ID();
		MTCSAmortizationRun amortizationRun = new MTCSAmortizationRun(getCtx(), AmortizationRun_ID, get_TrxName());
		
		int C_Period_ID = amortizationRun.getC_Period_ID();
		
		if(C_Period_ID <= 0)
			throw new AdempiereException("Please Select Period");
		
		//reset existing link from amortization run header to existing amortization run line before repopulate
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "+MTCSAmortizationLine.Table_Name+" SET "+MTCSAmortizationLine.COLUMNNAME_TCS_AmortizationRun_ID+" = NULL WHERE "+
					MTCSAmortizationLine.COLUMNNAME_TCS_AmortizationRun_ID +" = "+AmortizationRun_ID);
		int no = DB.executeUpdate(sb.toString(), get_TrxName());
		log.info("Updated Amortization Line#"+no);
		
		//populate amortization run line 
		String where = MPeriod.COLUMNNAME_C_Period_ID+"=? AND "+MTCSAmortizationLine.COLUMNNAME_Processed+"='N'";
		int[] line_IDs = new Query(getCtx(), MTCSAmortizationLine.Table_Name, where, get_TrxName())
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.setParameters(C_Period_ID)
			.getIDs();
		
		for (int line_ID : line_IDs) {
			MTCSAmortizationLine line = new MTCSAmortizationLine(getCtx(), line_ID, get_TrxName());
			line.setTCS_AmortizationRun_ID(AmortizationRun_ID);
			line.saveEx();
		}
		return "";
	}
	
	public void updateAmortizationLine(int AmortizationRun_ID){

	}

}
