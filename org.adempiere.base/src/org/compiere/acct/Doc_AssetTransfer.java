package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAssetGroupAcct;
import org.compiere.model.MAssetTransfer;
import org.compiere.model.MDepreciationWorkfile;
import org.compiere.model.MDocType;
import org.compiere.util.Env;

/**
 * Posting for {@link MAssetTransfer} document. DOCBASETYPE_GLJournal.
 * @author Anca Bradau www.arhipac.ro
 */
public class Doc_AssetTransfer extends Doc 
{

	public Doc_AssetTransfer (MAcctSchema as, ResultSet rs, String trxName)
	{
		super(as, MAssetTransfer.class, rs, MDocType.DOCBASETYPE_GLDocument, trxName);
	}

	@Override
	protected String loadDocumentDetails()
	{
		return null;
	}
	
	@Override
	public BigDecimal getBalance() {
    	return Env.ZERO;
	}
	
	/**
	 * Produce posting:
	 * <pre>
	 *	20.., 21..[A_Asset_New_Acct]			=	23..[A_Asset_Acct]		
	 * </pre>
	 */
	@Override
	public ArrayList<Fact> createFacts(MAcctSchema as)
	{
		MAssetTransfer assetTr = getAssetTransfer();
		MDepreciationWorkfile wk = getAssetWorkfile();	
		
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact(this, as, MDepreciationWorkfile.POSTINGTYPE_Actual);
		facts.add(fact);
		//
		MAssetGroupAcct newAssetGroupAcct = MAssetGroupAcct.forA_Asset_Group_ID(getCtx(), assetTr.getNew_Asset_Group_ID(), MAssetGroupAcct.POSTINGTYPE_Actual, as.getC_AcctSchema_ID());
		MAssetGroupAcct oldAssetGroupAcct = MAssetGroupAcct.forA_Asset_Group_ID(getCtx(), assetTr.getA_Asset_Group_ID(), MAssetGroupAcct.POSTINGTYPE_Actual, as.getC_AcctSchema_ID());
		BigDecimal transferAmt = wk.getA_Asset_Cost().subtract(wk.getA_Accumulated_Depr());
		// Change Asset Account
		MAccount dr = MAccount.get(getCtx(), newAssetGroupAcct.getA_Asset_Acct());  
		MAccount cr = MAccount.get(getCtx(), oldAssetGroupAcct.getA_Asset_Acct());
		FactUtil.createSimpleOperation(fact, null, dr, cr, as.getC_Currency_ID(), transferAmt, false);
		//
		return facts;
	}

	/**
	 * @return MAssetTransfer
	 */
	private MAssetTransfer getAssetTransfer()
	{
		return (MAssetTransfer)getPO();
	}
	
	/**
	 * @return MDepreciationWorkfile
	 */
	private MDepreciationWorkfile getAssetWorkfile()
	{
		MAssetTransfer assetTr = getAssetTransfer();
		return MDepreciationWorkfile.get(getCtx(), assetTr.getA_Asset_ID(), MDepreciationWorkfile.POSTINGTYPE_Actual, getTrxName());
	}
	
}
