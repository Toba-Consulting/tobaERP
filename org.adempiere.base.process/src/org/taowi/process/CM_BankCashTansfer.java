package org.taowi.process;

import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MDocType;
import org.compiere.model.MPayment;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.taowi.model.X_C_BankTransfer;
import org.taowi.model.X_M_MatchTransferDetail;

@org.adempiere.base.annotation.Process
public class CM_BankCashTansfer extends SvrProcess {
	
	//int p_C_BPartner_ID = 0;
	int p_C_DocTypeFrom_ID = 0;
	int p_C_DocTypeTo_ID = 0;
	int p_C_DocTypeAlloc_ID = 0;
	int p_C_Giro_Remittance_ID = 0;
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected String doIt() throws Exception {
		int p_C_BankTransfer_ID = getRecord_ID();

		X_C_BankTransfer bankTransfer = new X_C_BankTransfer(getCtx(),p_C_BankTransfer_ID, get_TrxName());

		if (bankTransfer.getC_TransferFrom_ID() > 0
				|| bankTransfer.getC_TransferTo_ID() > 0
				|| bankTransfer.isProcessed())
			return "Error: Bank Transfer Has Been Processed";


		MPayment paymentFrom = new MPayment(getCtx(), 0, get_TrxName());
		paymentFrom.setAD_Org_ID(bankTransfer.getAD_Org_ID());
		paymentFrom.setC_BankAccount_ID(bankTransfer.getC_BankAccount_ID());
		paymentFrom.setPayAmt(bankTransfer.getAmount());
		paymentFrom.setC_DocType_ID(MDocType.getFirstIDOfDocBaseType(getCtx(),MDocType.DOCBASETYPE_APPayment));
		//paymentFrom.setC_BPartner_ID(p_C_BPartner_ID);
		paymentFrom.setC_Currency_ID(bankTransfer.getC_Currency_ID());
		//paymentFrom.setC_ConversionType_ID(bankTransfer.getC_ConversionType_ID());
		paymentFrom.setIsReceipt(false);
		paymentFrom.setIsAllocated(false);
		paymentFrom.setIsReconciled(false);
		paymentFrom.setDocAction(DocAction.ACTION_Complete);
		paymentFrom.setDocStatus(DocAction.STATUS_Drafted);
		paymentFrom.setDateAcct(bankTransfer.getDateAcct());
		paymentFrom.setDateTrx(bankTransfer.getDateAcct());
		paymentFrom.setDescription("Bank Transfer Out From Trx #"+ bankTransfer.getDocumentNo());
		//paymentFrom.setTrxType(MPayment.TRXTYPE_CreditPayment);
		paymentFrom.setTenderType(MPayment.TENDERTYPE_Check);
		paymentFrom.saveEx();

		MPayment paymentTo = new MPayment(getCtx(), 0, get_TrxName());
		paymentTo.setAD_Org_ID(bankTransfer.getAD_Org_ID());
		paymentTo.setC_BankAccount_ID(bankTransfer.getC_BankAccountTo_ID());
		paymentTo.setPayAmt(bankTransfer.getAmount());
		paymentTo.setC_DocType_ID(MDocType.getFirstIDOfDocBaseType(getCtx(),MDocType.DOCBASETYPE_ARReceipt));
		//paymentTo.setC_BPartner_ID(p_C_BPartner_ID);
		paymentTo.setC_Currency_ID(bankTransfer.getC_Currency_ID());
		paymentTo.setC_ConversionType_ID(bankTransfer.getC_ConversionType_ID());
		paymentTo.setIsReceipt(true);
		paymentTo.setIsAllocated(false);
		paymentTo.setIsReconciled(false);
		paymentTo.setDocAction(DocAction.ACTION_Complete);
		paymentTo.setDocStatus(DocAction.STATUS_Drafted);
		paymentTo.setDateAcct(bankTransfer.getDateAcct());
		paymentTo.setDateTrx(bankTransfer.getDateAcct());
		paymentTo.setDescription("Bank Transfer In From Trx #"+ bankTransfer.getDocumentNo());
		//paymentTo.setTrxType(MPayment.TRXTYPE_CreditPayment);
		paymentTo.setTenderType(MPayment.TENDERTYPE_Account);
		paymentTo.saveEx();

		paymentFrom.processIt(DocAction.ACTION_Complete);
		paymentFrom.saveEx();

		paymentTo.processIt(DocAction.ACTION_Complete);
		paymentTo.saveEx();

		MAllocationHdr alloc = new MAllocationHdr(getCtx(), 0, get_TrxName());
		alloc.setAD_Org_ID(bankTransfer.getAD_Org_ID());
		alloc.setDateTrx(bankTransfer.getDateAcct());
		alloc.setDateAcct(bankTransfer.getDateAcct());
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

		bankTransfer.setC_TransferFrom_ID(paymentFrom.get_ID());
		bankTransfer.setC_TransferTo_ID(paymentTo.get_ID());
		bankTransfer.setProcessed(true);
		bankTransfer.saveEx();
		
		
		X_M_MatchTransferDetail detail = new X_M_MatchTransferDetail(getCtx(), 0, get_TrxName());

		detail.setC_BankTransfer_ID(bankTransfer.getC_BankTransfer_ID());
		detail.setC_TransferFrom_ID(paymentFrom.getC_Payment_ID());
		detail.setC_TransferTo_ID(paymentTo.getC_Payment_ID());
		detail.saveEx();
		
		

		return "Successfully Created Transfer";

	}

}
