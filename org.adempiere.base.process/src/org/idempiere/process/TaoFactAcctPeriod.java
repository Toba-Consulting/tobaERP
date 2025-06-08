package org.idempiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MFactAcct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author stephan
 */
@org.adempiere.base.annotation.Process
public class TaoFactAcctPeriod extends SvrProcess{

	private int p_C_AcctSchema_ID = 0;
	private int p_C_Period_ID = 0;
	private int p_Account_ID = 0;
	private int p_C_BPartner_ID = 0;
	//private Timestamp p_DateAcct = null;
	//private Timestamp p_DateAcctTo = null;
	
	private BigDecimal firstBalance = Env.ZERO;
	private BigDecimal firstSrcBalance = Env.ZERO;
	
	private String errMsg = "";
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_AcctSchema_ID"))
				p_C_AcctSchema_ID = para[i].getParameterAsInt();
			else if (name.equals("C_Period_ID"))
				p_C_Period_ID = para[i].getParameterAsInt();
			else if (name.equals("Account_ID"))
				p_Account_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = para[i].getParameterAsInt();
//			else if (name.equals("DateAcct")){
//				p_DateAcct = para[i].getParameterAsTimestamp();
//				p_DateAcctTo = para[i].getParameter_ToAsTimestamp();
//			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		cleanTable();
		createFirstLine();
		createSummary();
		
		return errMsg;
	}

	private void createSummary(){
		StringBuilder sb = new StringBuilder();
		/*
		sb.append("INSERT INTO T_Facct_Period(AD_PInstance_ID,AD_Client_ID,AD_Org_ID,C_AcctSchema_ID"//4
				+ ",C_BPartner_ID,Account_ID,DateAcct,AmtAcctDr,AmtAcctCr,AmtSourceDr,AmtSourceCr,M_Product_ID)"//8
				+ ",Balance,BalanceSource)");//2
		*/
		sb.append("SELECT "+getAD_PInstance_ID()+",fact_acct.ad_client_id,"//1..2
				+ "fact_acct.ad_org_id,fact_acct.c_acctschema_id, fact_acct.C_BPartner_ID,"//3..5
				+ "fact_acct.account_id,fact_acct.c_period_id,"//6..7
				+ "sum(fact_acct.amtacctdr) AS AmtAcctDr, "//8
				+ "sum(fact_acct.amtacctcr) AS AmtAcctCr, "//9
				+ "sum(fact_acct.amtsourcedr) AS AmtSourceDr, "//10
				+ "sum(fact_acct.amtsourcecr) AS AmtSourceCr, "//11
				+ "sum(fact_acct.amtacctdr - fact_acct.amtacctcr) AS AmtAcct, "//12
				+ "sum(fact_acct.amtsourcedr - fact_acct.amtsourcecr) AS AmtSource, "//13
				+ "fact_acct.m_product_id, "//14
				+ "fact_acct.c_bpartner_id "//15
				+ "FROM fact_acct "
				+ "WHERE AD_Client_ID=? AND C_AcctSchema_ID=? AND Account_ID=? "
				+ "AND C_Period_ID=?");
		if(p_C_BPartner_ID > 0)
			sb.append(" AND C_BPartner_ID=?");
		sb.append(" GROUP BY fact_acct.ad_client_id,fact_acct.ad_org_id,fact_acct.c_acctschema_id, "
				+ "fact_acct.account_id,fact_acct.m_product_id,fact_acct.c_bpartner_id,fact_acct.c_period_id");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sb.toString(), get_TrxName());
			pstmt.setInt(1, getAD_Client_ID());
			pstmt.setInt(2, p_C_AcctSchema_ID);
			pstmt.setInt(3, p_Account_ID);
			pstmt.setInt(4, p_C_Period_ID);
			if(p_C_BPartner_ID > 0)
				pstmt.setInt(5, p_C_BPartner_ID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				firstBalance = firstBalance.add(rs.getBigDecimal(12));
				firstSrcBalance = firstSrcBalance.add(rs.getBigDecimal(13));
				Object[] params = null;
				Object product = rs.getInt(14);
				if((int)product == 0)
					product = null;
				
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO T_Facct_Period(AD_PInstance_ID,AD_Client_ID,AD_Org_ID,C_AcctSchema_ID"//4
						+ ",C_BPartner_ID,Account_ID,C_Period_ID,AmtAcctDr,AmtAcctCr,AmtSourceDr,AmtSourceCr,M_Product_ID"//8
						+ ",Balance,BalanceSource) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				params = new Object[]{rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)
						,rs.getInt(7),rs.getBigDecimal(8),rs.getBigDecimal(9),rs.getBigDecimal(10)
						,rs.getBigDecimal(11),product,firstBalance,firstSrcBalance};
				
				int no = DB.executeUpdate(sql.toString(), params, true, get_TrxName());
				log.fine("#" + no);
			}
		} catch (Exception e) {
			errMsg = e.toString();
		} finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}
	
	private void createFirstLine(){
		StringBuilder where = new StringBuilder();
		where.append("C_AcctSchema_ID="+p_C_AcctSchema_ID);
		where.append(" AND Account_ID="+p_Account_ID);
		where.append(" AND C_Period_ID < "+p_C_Period_ID);
		
		if(p_C_BPartner_ID > 0)
			where.append(" AND C_BPartner_ID="+p_C_BPartner_ID);
		
		int[] ids = new Query(getCtx(), MFactAcct.Table_Name, where.toString(), get_TrxName())
			.setOnlyActiveRecords(true)
			.getIDs();
		
		BigDecimal balance = Env.ZERO;
		BigDecimal balanceSrc = Env.ZERO;
		
		for (int id : ids) {
			MFactAcct factAcct = new MFactAcct(getCtx(), id, get_TrxName());
			balance = balance.add(factAcct.getAmtAcctDr().subtract(factAcct.getAmtAcctCr()));
			balanceSrc = balanceSrc.add(factAcct.getAmtSourceDr().subtract(factAcct.getAmtSourceCr()));
		}
		
		firstBalance = balance;
		firstSrcBalance = balanceSrc;
		Object[] params = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO T_Facct_Period(AD_PInstance_ID,AD_Client_ID,AD_Org_ID,C_AcctSchema_ID"//4
				+ ",Account_ID,C_Period_ID,Balance,BalanceSource");//4
		
		if(p_C_BPartner_ID > 0){
			sql.append(",C_BPartner_ID)");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?)");
			params = new Object[]{getAD_PInstance_ID(),getAD_Client_ID(),Env.getAD_Org_ID(getCtx()),p_C_AcctSchema_ID//4
					,p_Account_ID,p_C_Period_ID,firstBalance,firstSrcBalance,p_C_BPartner_ID};//5
		}
		else{
			sql.append(")");
			sql.append(" VALUES(?,?,?,?,?,?,?,?)");
			params = new Object[]{getAD_PInstance_ID(),getAD_Client_ID(),Env.getAD_Org_ID(getCtx()),p_C_AcctSchema_ID//4
					,p_Account_ID,p_C_Period_ID,firstBalance,firstSrcBalance};//4
		}
		
		int no = DB.executeUpdate(sql.toString(), params, true, get_TrxName());
		log.fine("#" + no);
		
	}
	
	private void cleanTable(){
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM T_Facct_Period");
		DB.executeUpdate(sql.toString(), get_TrxName());
	}
	
}
