package org.idempiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MFactAcct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author stephan
 * accounting fact details report with first balance
 */
@org.adempiere.base.annotation.Process
public class TaoFactAcctDetails extends SvrProcess{

	private int p_C_AcctSchema_ID = 0;
	//private int p_C_Period_ID = 0;
	private int p_Account_ID = 0;
	private int p_C_BPartner_ID = 0;
	private Timestamp p_DateAcct = null;
	private Timestamp p_DateAcctTo = null;
	
	private BigDecimal firstBalance = Env.ZERO;
	private BigDecimal firstSrcBalance = Env.ZERO;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_AcctSchema_ID"))
				p_C_AcctSchema_ID = para[i].getParameterAsInt();
			//else if (name.equals("C_Period_ID"))
				//p_C_Period_ID = para[i].getParameterAsInt();
			else if (name.equals("Account_ID"))
				p_Account_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = para[i].getParameterAsInt();
			else if (name.equals("DateAcct")){
				p_DateAcct = para[i].getParameterAsTimestamp();
				p_DateAcctTo = para[i].getParameter_ToAsTimestamp();
			}else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		cleanTable();
		createFirstLine();
		createLines();
		
		return null;
	}

	private void createFirstLine(){
		StringBuilder where = new StringBuilder();
		where.append("C_AcctSchema_ID="+p_C_AcctSchema_ID);
		where.append(" AND Account_ID="+p_Account_ID);
		where.append(" AND DateAcct < '"+p_DateAcct+"'");
		
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
				+ ",Account_ID,DateAcct,Balance,BalanceSource");//4
		
		if(p_C_BPartner_ID > 0){
			sql.append(",C_BPartner_ID)");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?)");
			params = new Object[]{getAD_PInstance_ID(),getAD_Client_ID(),Env.getAD_Org_ID(getCtx()),p_C_AcctSchema_ID//4
					,p_Account_ID,p_DateAcct,p_C_BPartner_ID,firstBalance,firstSrcBalance};//5
		}
		else{
			sql.append(")");
			sql.append(" VALUES(?,?,?,?,?,?,?,?)");
			params = new Object[]{getAD_PInstance_ID(),getAD_Client_ID(),Env.getAD_Org_ID(getCtx()),p_C_AcctSchema_ID//4
					,p_Account_ID,p_DateAcct,firstBalance,firstSrcBalance};//4
		}
		
		int no = DB.executeUpdate(sql.toString(), params, true, get_TrxName());
		log.fine("#" + no);
		
	}
	
	private void createLines(){
		StringBuilder where = new StringBuilder();
		where.append("C_AcctSchema_ID="+p_C_AcctSchema_ID);
		where.append(" AND Account_ID="+p_Account_ID);
		where.append(" AND DateAcct BETWEEN '"+p_DateAcct+"'");
		if(p_DateAcctTo == null)
			p_DateAcctTo = new Timestamp(System.currentTimeMillis());
		where.append(" AND '"+p_DateAcctTo+"'");
		
		if(p_C_BPartner_ID > 0)
			where.append(" AND C_BPartner_ID="+p_C_BPartner_ID);
		
		int[] ids = new Query(getCtx(), MFactAcct.Table_Name, where.toString(), get_TrxName())
		.setOnlyActiveRecords(true)
		.setOrderBy("DateAcct,C_BPartner_ID,Created")
		.getIDs();
		
		for (int id : ids) {
			MFactAcct factAcct = new MFactAcct(getCtx(), id, get_TrxName());
			StringBuilder sql = new StringBuilder();
			Object product = factAcct.getM_Product_ID();
			if((int)product == 0)
				product = null;
			Object[] params = null;
			firstBalance = firstBalance.add(factAcct.getAmtAcctDr()).subtract(factAcct.getAmtAcctCr());
			firstSrcBalance = firstSrcBalance.add(factAcct.getAmtSourceDr()).subtract(factAcct.getAmtSourceCr());
			
			sql.append("INSERT INTO T_Facct_Period(AD_PInstance_ID,AD_Client_ID,AD_Org_ID,C_AcctSchema_ID"//4
					+ ",C_BPartner_ID,Account_ID,DateAcct,AmtAcctDr,AmtAcctCr,AmtSourceDr,AmtSourceCr,M_Product_ID"//8
					+ ",Balance,BalanceSource,Description)");//3
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//13
			params = new Object[]{getAD_PInstance_ID(),getAD_Client_ID(),Env.getAD_Org_ID(getCtx()),factAcct.getC_AcctSchema_ID()
					,factAcct.getC_BPartner_ID(),factAcct.getAccount_ID()
					,factAcct.getDateAcct(),factAcct.getAmtAcctDr()
					,factAcct.getAmtAcctCr(),factAcct.getAmtSourceDr()
					,factAcct.getAmtSourceCr(),product
					,firstBalance,firstSrcBalance,factAcct.getDescription()};
			
			int no = DB.executeUpdate(sql.toString(), params, true, get_TrxName());
			log.fine("#" + no);
		}
	}
	
	private void cleanTable(){
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM T_Facct_Period");
		DB.executeUpdate(sql.toString(), get_TrxName());
	}
	
}
