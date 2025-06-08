package org.taowi.process;

import java.math.BigDecimal;
import java.util.List;

import org.compiere.model.MPayment;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.taowi.model.MTCSAllocateCharge;

@org.adempiere.base.annotation.Process
public class TaoCalculatePayment extends SvrProcess{

	int C_Payment_ID=0;
	
	@Override
	protected void prepare() {
		
		C_Payment_ID=getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		if(C_Payment_ID == 0){
			return "No Selected Payment";
		}
		
		BigDecimal sums = Env.ZERO;
		
		MPayment payment = new MPayment(Env.getCtx(),C_Payment_ID,null);
		MTCSAllocateCharge[] allocharges = payment.getAllocLines();
		
		if(allocharges == null || allocharges.length <= 0)
			return "No Allocation Charge Line";
		
		for(MTCSAllocateCharge allocharge:allocharges){
			//sums+=allocharges.getAmount().intValue();
			sums = sums.add(allocharge.getAmount());
		}
		
		payment.setPayAmt(sums);
		payment.saveEx();
		
		return "";
	}
	

}
