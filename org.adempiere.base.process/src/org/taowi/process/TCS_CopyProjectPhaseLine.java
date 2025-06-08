package org.taowi.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProjectLine;
import org.compiere.model.MProjectPhase;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

@org.adempiere.base.annotation.Process
public class TCS_CopyProjectPhaseLine extends SvrProcess{

	private int p_C_ProjectPhase_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals(MProjectPhase.COLUMNNAME_C_ProjectPhase_ID))
				p_C_ProjectPhase_ID = para[i].getParameterAsInt();
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		int C_ProjectPhase_ID = getRecord_ID();
		
		if(p_C_ProjectPhase_ID <= 0)
			throw new AdempiereException("Project Phase is Mandatory");
		
		MProjectPhase currentProjectPhase = new MProjectPhase(getCtx(), p_C_ProjectPhase_ID, get_TrxName());
		MProjectPhase thisProjectPhase = new MProjectPhase(getCtx(), C_ProjectPhase_ID, get_TrxName());
		
		int count = 0;
		
		for (MProjectLine currentLine : currentProjectPhase.getLines()) {
			MProjectLine thisLine = new MProjectLine(getCtx(), 0, get_TrxName());
			thisLine.setAD_Org_ID(currentLine.getAD_Org_ID());
			thisLine.setC_Project_ID(thisProjectPhase.getC_Project_ID());
			thisLine.setC_ProjectPhase_ID(thisProjectPhase.getC_ProjectPhase_ID());
			thisLine.setLine(currentLine.getLine());
			thisLine.setDescription(currentLine.getDescription());
			thisLine.setPlannedQty(currentLine.getPlannedQty());
			thisLine.setPlannedPrice(currentLine.getPlannedPrice());
			thisLine.setPlannedAmt(currentLine.getPlannedAmt());
			thisLine.setPlannedMarginAmt(currentLine.getPlannedMarginAmt());
			thisLine.setCommittedAmt(currentLine.getCommittedAmt());
			thisLine.setM_Product_ID(currentLine.getM_Product_ID());
			thisLine.setM_Product_Category_ID(currentLine.getM_Product_Category_ID());
			thisLine.setInvoicedQty(currentLine.getInvoicedQty());
			thisLine.setCommittedQty(currentLine.getCommittedQty());
			thisLine.setInvoicedAmt(currentLine.getInvoicedAmt());
			thisLine.setC_Order_ID(currentLine.getC_Order_ID());
			thisLine.saveEx();
			
			count+=1;
		}
		
		return "Created"+count;
	}

}
