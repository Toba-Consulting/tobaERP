package org.taowi.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.Env;

/*
 * @David
 */

public class MAgingDaily extends X_T_AgingDaily{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4065860698882032509L;

	
	/** Number of items 		*/
	private int		m_noItems = 0;
	/** Sum of Due Days			*/
	private int		m_daysDueSum = 0;

	public MAgingDaily(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MAgingDaily (Properties ctx, int AD_PInstance_ID, Timestamp StatementDate, 
			int C_BPartner_ID, int C_Currency_ID, 
			int C_Invoice_ID, int C_InvoicePaySchedule_ID,
			int C_BP_Group_ID, Timestamp DueDate, boolean IsSOTrx, String trxName)
		{
			this (ctx, 0, trxName);
			setAD_PInstance_ID (AD_PInstance_ID);
			setStatementDate(StatementDate);
			//
			setC_BPartner_ID (C_BPartner_ID);
			setC_Currency_ID (C_Currency_ID);
			setC_BP_Group_ID (C_BP_Group_ID);
			setIsSOTrx (IsSOTrx);

			//	Optional
		//	setC_Invoice_ID (C_Invoice_ID);		// may be zero
			set_ValueNoCheck ("C_Invoice_ID", new Integer(C_Invoice_ID));
		//	setC_InvoicePaySchedule_ID(C_InvoicePaySchedule_ID);	//	may be zero
			set_Value ("C_InvoicePaySchedule_ID", new Integer(C_InvoicePaySchedule_ID));
			setIsListInvoices(C_Invoice_ID != 0);
			//
			setDueDate(DueDate);		//	only sensible if List invoices
		}	//	MAging

	public MAgingDaily (Properties ctx, int AD_PInstance_ID, Timestamp StatementDate, 
			int C_BPartner_ID, int C_Currency_ID, 
			int C_Invoice_ID, int C_InvoicePaySchedule_ID,
			int C_BP_Group_ID, int AD_Org_ID, Timestamp DueDate, boolean IsSOTrx, String trxName)
		{
			this (ctx, 0, trxName);
			setAD_PInstance_ID (AD_PInstance_ID);
			setStatementDate(StatementDate);
			//
			setC_BPartner_ID (C_BPartner_ID);
			setC_Currency_ID (C_Currency_ID);
			setC_BP_Group_ID (C_BP_Group_ID);
			setAD_Org_ID(AD_Org_ID);
			setIsSOTrx (IsSOTrx);

			//	Optional
		//	setC_Invoice_ID (C_Invoice_ID);		// may be zero
			set_ValueNoCheck ("C_Invoice_ID", new Integer(C_Invoice_ID));
		//	setC_InvoicePaySchedule_ID(C_InvoicePaySchedule_ID);	//	may be zero
			set_Value ("C_InvoicePaySchedule_ID", new Integer(C_InvoicePaySchedule_ID));
			setIsListInvoices(C_Invoice_ID != 0);
			//
			setDueDate(DueDate);		//	only sensible if List invoices
		}	//	MAging

	public MAgingDaily(Properties ctx, int T_AgingDaily_ID, String trxName) {
		super(ctx, T_AgingDaily_ID, trxName);
		// TODO Auto-generated constructor stub
		if (T_AgingDaily_ID == 0)
		{
		//	setAD_PInstance_ID (0);
		//	setC_BP_Group_ID (0);
		//	setC_BPartner_ID (0);
		//	setC_Currency_ID (0);
			//
			setDue0 (Env.ZERO);
			setDueIn1 (Env.ZERO);
			setDueIn2 (Env.ZERO);
			setDueIn3 (Env.ZERO);
			setDueIn4 (Env.ZERO);
			setDueIn4 (Env.ZERO);
			setDueIn5 (Env.ZERO);
			setDueIn6 (Env.ZERO);
		
			setPastDueAmt (Env.ZERO);
			//
			setOpenAmt(Env.ZERO);
			setInvoicedAmt(Env.ZERO);
			//
			setIsListInvoices (false);
			setIsSOTrx (false);
		}
	}


	
	public void add(Timestamp DueDate, int daysDue, BigDecimal invoicedAmt,
			BigDecimal openAmt) {
		if (invoicedAmt == null)
			invoicedAmt = Env.ZERO;
		setInvoicedAmt(getInvoicedAmt().add(invoicedAmt));
		if (openAmt == null)
			openAmt = Env.ZERO;
		setOpenAmt(getOpenAmt().add(openAmt));
		//	Days Due
		m_noItems++;
		m_daysDueSum += daysDue;
		setDaysDue(m_daysDueSum/m_noItems);
		//	Due Date
		if (getDueDate().after(DueDate))
			setDueDate(DueDate);		//	earliest
		//
		BigDecimal amt = openAmt;
		
		//	Not due - negative
		
		//Already past due
		if (daysDue > 0)	setPastDueAmt(getPastDueAmt().add(amt));
		//Due today
		else if(daysDue==0)	setDue0(getDue0().add(amt));
		
		//Not Due Yet
		//Due in x Days
		else if(daysDue==(-1)) setDueIn1(getDueIn1().add(amt));
		else if(daysDue==(-2)) setDueIn2(getDueIn2().add(amt));
		else if(daysDue==(-3)) setDueIn3(getDueIn3().add(amt));
		else if(daysDue==(-4)) setDueIn4(getDueIn4().add(amt));
		else if(daysDue==(-5)) setDueIn5(getDueIn5().add(amt));
		else if(daysDue==(-6)) setDueIn6(getDueIn6().add(amt));

		
	}	//	add

	public String toString() {
		StringBuilder sb = new StringBuilder("MAgingDaily[");
		sb.append("AD_PInstance_ID=").append(getAD_PInstance_ID())
			.append(",C_BPartner_ID=").append(getC_BPartner_ID())
			.append(",C_Currency_ID=").append(getC_Currency_ID())
			.append(",C_Invoice_ID=").append(getC_Invoice_ID());
		sb.append("]");
		return sb.toString();
	}
}
