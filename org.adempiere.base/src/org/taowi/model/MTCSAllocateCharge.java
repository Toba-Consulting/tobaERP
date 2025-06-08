package org.taowi.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MPayment;
import org.compiere.model.MPaymentAllocate;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MTCSAllocateCharge extends X_TCS_AllocateCharge {

	/**
	 *  
	 */
	private static final long serialVersionUID = -673240099429938296L;

	public MTCSAllocateCharge(Properties ctx, int TCS_AllocateCharge_ID, String trxName) {
		super(ctx, TCS_AllocateCharge_ID, trxName);
	}

	public MTCSAllocateCharge(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean afterSave (boolean newRecord, boolean success){

		if (success) {
			MPayment payment = new MPayment(getCtx(),getC_Payment_ID(),get_TrxName());

			if (newRecord && payment.getC_Invoice_ID() > 0)
				payment.setC_Invoice_ID(0);
			if (newRecord && payment.getC_Charge_ID() > 0)
				payment.setC_Charge_ID(0);
			if (newRecord && payment.getC_Order_ID() > 0)
				payment.setC_Order_ID(0);

			//@win: set payment amt as sum total amount on allocate charge
			if (newRecord || is_ValueChanged(COLUMNNAME_Amount)) {
				BigDecimal totalAmt = new Query(getCtx(), MTCSAllocateCharge.Table_Name,"C_Payment_ID=?", get_TrxName())
				.setParameters(payment.get_ID())
				.setOnlyActiveRecords(true)
				.sum(COLUMNNAME_Amount);
				payment.setPayAmt(totalAmt);
			}

			payment.saveEx();
		}
		return true;
	}

}
