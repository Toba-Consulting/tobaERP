package org.taowi.process;

import java.sql.Timestamp;

import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MDocType;
import org.compiere.model.MPayment;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.taowi.model.X_C_BankTransfer;
import org.taowi.model.X_M_MatchTransferDetail;

@org.adempiere.base.annotation.Process
public class CM_CancelBankTransfer extends SvrProcess{

	//int p_C_BPartner_ID = 0;
	Timestamp p_DateAcct = null;
	Timestamp p_DateTrx = null;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] parameters = getParameter();
		for (ProcessInfoParameter para : parameters) {
			 if (para.getParameterName().equals("DateTrx")) {
				  p_DateTrx = para.getParameterAsTimestamp(); 
			 }else if (para.getParameterName().equals("DateAcct")) {
				  p_DateAcct = para.getParameterAsTimestamp();
			} 
			  
		}
	}

	@Override
	protected String doIt() throws Exception {
	
		int p_C_BankTransfer_ID = getRecord_ID();
		
		X_C_BankTransfer bankTransfer = new X_C_BankTransfer(getCtx(),p_C_BankTransfer_ID, get_TrxName());

		if (bankTransfer.getC_CancelTransferFrom_ID() > 0 || bankTransfer.getC_CancelTransferTo_ID() > 0
				|| !bankTransfer.isProcessed())
			return "Error: Bank Transfer Has Been Canceled";
		

		
		MPayment paymentFrom = new MPayment(getCtx(), 0, get_TrxName());
		paymentFrom.setAD_Org_ID(bankTransfer.getAD_Org_ID());
		paymentFrom.setC_BankAccount_ID(bankTransfer.getC_BankAccount_ID());
		paymentFrom.setPayAmt(bankTransfer.getAmount());
		paymentFrom.setC_DocType_ID(MDocType.getFirstIDOfDocBaseType(getCtx(),MDocType.DOCBASETYPE_ARReceipt));
		paymentFrom.setC_Currency_ID(bankTransfer.getC_Currency_ID());
		paymentFrom.setC_ConversionType_ID(bankTransfer.getC_ConversionType_ID());
		paymentFrom.setIsReceipt(true);
		paymentFrom.setIsAllocated(false);
		paymentFrom.setIsReconciled(false);
		paymentFrom.setDocAction(DocAction.ACTION_Complete);
		paymentFrom.setDocStatus(DocAction.STATUS_Drafted);
		paymentFrom.setDateAcct(p_DateAcct);
		paymentFrom.setDateTrx(p_DateTrx);
		paymentFrom.setDescription("Bank Transfer Out From Trx #" + bankTransfer.getDocumentNo());
		//paymentFrom.setTrxType(MPayment.TRXTYPE_CreditPayment);
		paymentFrom.setTenderType(MPayment.TENDERTYPE_Account);
		paymentFrom.saveEx();

		MPayment paymentTo = new MPayment(getCtx(), 0, get_TrxName());
		paymentTo.setAD_Org_ID(bankTransfer.getAD_Org_ID());
		paymentTo.setC_BankAccount_ID(bankTransfer.getC_BankAccountTo_ID());
		paymentTo.setPayAmt(bankTransfer.getAmount());
		paymentTo.setC_DocType_ID(MDocType.getFirstIDOfDocBaseType(getCtx(),MDocType.DOCBASETYPE_APPayment));
		//paymentTo.setC_BPartner_ID(p_C_BPartner_ID);
		paymentTo.setC_Currency_ID(bankTransfer.getC_Currency_ID());
		paymentTo.setC_ConversionType_ID(bankTransfer.getC_ConversionType_ID());
		paymentTo.setIsReceipt(false);
		paymentTo.setIsAllocated(false);
		paymentTo.setIsReconciled(false);
		paymentTo.setDocAction(DocAction.ACTION_Complete);
		paymentTo.setDocStatus(DocAction.STATUS_Drafted);
		paymentTo.setDateAcct(p_DateAcct);
		paymentTo.setDateTrx(p_DateTrx);
		paymentTo.setDescription("Bank Transfer In From Trx #" + bankTransfer.getDocumentNo());
		//paymentTo.setTrxType(MPayment.TRXTYPE_CreditPayment);
		paymentTo.setTenderType(MPayment.TENDERTYPE_Account);
		paymentTo.saveEx();

		paymentFrom.processIt(DocAction.ACTION_Complete);
		paymentFrom.saveEx();

		paymentTo.processIt(DocAction.ACTION_Complete);
		paymentTo.saveEx();

		MAllocationHdr alloc = new MAllocationHdr(getCtx(), 0, get_TrxName());
		alloc.setAD_Org_ID(bankTransfer.getAD_Org_ID());
		alloc.setDateTrx(p_DateTrx);
		alloc.setDateAcct(p_DateAcct);
		alloc.setC_Currency_ID(bankTransfer.getC_Currency_ID());
		alloc.setC_DocType_ID(MDocType.getFirstIDOfDocBaseType(getCtx(),MDocType.DOCBASETYPE_PaymentAllocation));
		alloc.setDocAction(DocAction.ACTION_Complete);
		alloc.setDocStatus(DocAction.STATUS_Drafted);
		alloc.saveEx();

		MAllocationLine allocLine = new MAllocationLine(alloc);
		allocLine.setC_Payment_ID(paymentFrom.get_ID());
		//allocLine.setC_BPartner_ID(p_C_BPartner_ID);
		allocLine.setAmount(bankTransfer.getAmount().negate());
		allocLine.saveEx();

		MAllocationLine allocLine2 = new MAllocationLine(alloc);
		allocLine2.setC_Payment_ID(paymentTo.get_ID());
		//allocLine2.setC_BPartner_ID(p_C_BPartner_ID);
		allocLine2.setAmount(bankTransfer.getAmount());
		allocLine2.saveEx();

		alloc.processIt(DocAction.ACTION_Complete);
		alloc.saveEx();

		bankTransfer.setC_CancelTransferFrom_ID(paymentFrom.get_ID());
		bankTransfer.setC_CancelTransferTo_ID(paymentTo.get_ID());
		bankTransfer.setProcessed(true);
		
		bankTransfer.saveEx();
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  M_MatchTransferDetail_ID FROM "
					+ " M_MatchTransferDetail WHERE C_BankTransfer_ID = " +bankTransfer.getC_BankTransfer_ID());
	
		int  M_MatchTransferDetail_ID = DB.getSQLValueEx(get_TrxName(), sql.toString());
		
		X_M_MatchTransferDetail detail = new X_M_MatchTransferDetail(getCtx(), M_MatchTransferDetail_ID, get_TrxName());

		detail.setC_CancelTransferFrom_ID(paymentFrom.getC_Payment_ID());
		detail.setC_CancelTransferTo_ID(paymentTo.getC_Payment_ID());
		detail.saveEx();
				
		
		return "Successfully Created Transfer";

	}

}
