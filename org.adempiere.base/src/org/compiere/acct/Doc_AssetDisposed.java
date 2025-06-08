/***********************************************************************
 * This file is part of iDempiere ERP Open Source                      *
 * http://www.idempiere.org                                            *
 *                                                                     *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 **********************************************************************/
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAssetAcct;
import org.compiere.model.MAssetChange;
import org.compiere.model.MAssetDisposed;
import org.compiere.model.MDocType;
import org.compiere.util.Env;
import org.idempiere.fa.model.MFADefaultAccount;

/**
 * Posting for {@link MAssetDisposed} document. DOCBASETYPE_GLDocument.
 * @author Teo_Sarca, SC ARHIPAC SERVICE SRL
 */
public class Doc_AssetDisposed extends Doc
{
	/**
	 * @param as
	 * @param rs
	 * @param trxName
	 */
	public Doc_AssetDisposed (MAcctSchema as, ResultSet rs, String trxName)
	{
		super(as, MAssetDisposed.class, rs, MDocType.DOCBASETYPE_FixedAssetsDisposal, trxName);
	}

	@Override
	protected String loadDocumentDetails()
	{
		return null;
	}
	
	@Override
	public BigDecimal getBalance()
	{
		return Env.ZERO;
	}

	@Override
	public ArrayList<Fact> createFacts(MAcctSchema as)
	{
		MAssetDisposed assetDisp = (MAssetDisposed)getPO();
		
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact(this, as, assetDisp.getPostingType());
		BigDecimal assetAmt = assetDisp.getA_Asset_Cost();
		BigDecimal accumDepAmt = assetDisp.getA_Accumulated_Depr();
		BigDecimal assetNetAmt = assetAmt.subtract(accumDepAmt);

		if (MAssetDisposed.A_DISPOSED_METHOD_Trade.equalsIgnoreCase(assetDisp.getA_Disposed_Method())) {
			BigDecimal amt = assetDisp.getC_InvoiceLine().getLineNetAmt();

			fact.createLine (null,MFADefaultAccount.getAssetRevenueAccount(as),
					as.getC_Currency_ID(), 
					amt, Env.ZERO);

			fact.createLine(null, getAccount(MAssetAcct.COLUMNNAME_A_Accumdepreciation_Acct, as)
					, as.getC_Currency_ID()
					, accumDepAmt, Env.ZERO);

			if (amt.compareTo(assetNetAmt) < 0) {
				BigDecimal loss = assetNetAmt.subtract(amt);
				
				fact.createLine(null, getAccount(MAssetAcct.COLUMNNAME_A_Disposal_Loss_Acct, as)
						, as.getC_Currency_ID()
						, loss, Env.ZERO);
				
			} else if (amt.compareTo(assetNetAmt) > 0) {
				BigDecimal gain = amt.subtract(assetNetAmt);
				
				fact.createLine(null, getAccount(MAssetAcct.COLUMNNAME_A_Disposal_Gain_Acct, as)
						, as.getC_Currency_ID()
						, Env.ZERO, gain);
			}

			fact.createLine(null, getAccount(MAssetAcct.COLUMNNAME_A_Asset_Acct, as)
					, as.getC_Currency_ID()
					, Env.ZERO, assetAmt);

		} else if (MAssetDisposed.A_DISPOSED_METHOD_Simple.equalsIgnoreCase(assetDisp.getA_Disposed_Method())) {

			fact.createLine(null, getAccount(MAssetAcct.COLUMNNAME_A_Asset_Acct, as)
					, as.getC_Currency_ID()
					, Env.ZERO, assetAmt);
			fact.createLine(null, getAccount(MAssetAcct.COLUMNNAME_A_Accumdepreciation_Acct, as)
					, as.getC_Currency_ID()
					, accumDepAmt, Env.ZERO);

			if (assetNetAmt.compareTo(Env.ZERO) > 0) {
				fact.createLine(null, getAccount(MAssetAcct.COLUMNNAME_A_Disposal_Loss_Acct, as)
						, as.getC_Currency_ID()
						, assetNetAmt, Env.ZERO);
			}
		}
		
		facts.add(fact);
		return facts;
	}
	
	/**
	 * @param accountName
	 * @param as
	 * @return MAccount
	 */
	private MAccount getAccount(String accountName, MAcctSchema as)
	{
		MAssetDisposed assetDisp = (MAssetDisposed)getPO();
		MAssetAcct assetAcct = MAssetAcct.forA_Asset_ID(getCtx(), as.get_ID(), assetDisp.getA_Asset_ID(), assetDisp.getPostingType(), assetDisp.getDateAcct(),null);
		int account_id = (Integer)assetAcct.get_Value(accountName);
		return MAccount.get(getCtx(), account_id);
	}

}
