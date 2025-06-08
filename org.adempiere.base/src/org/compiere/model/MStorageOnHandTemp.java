package org.compiere.model;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author Phie Albert
 */

public class MStorageOnHandTemp {
	private BigDecimal qtyOnHand;
	private Timestamp datematerialpolicy;
	private int M_Product_ID;
	private int M_Locator_ID;
	
	public MStorageOnHandTemp(BigDecimal qtyOnHand, Timestamp datematerialpolicy, int M_Product_ID, int M_Locator_ID)
	{
		this.setQtyOnHand(qtyOnHand);
		this.setDatematerialpolicy(datematerialpolicy);
		this.setM_Locator_ID(M_Locator_ID);
		this.setM_Product_ID(M_Product_ID);
	}
	
	public BigDecimal getQtyOnHand() {
		return qtyOnHand;
	}
	public void setQtyOnHand(BigDecimal qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}
	public Timestamp getDatematerialpolicy() {
		return datematerialpolicy;
	}
	public void setDatematerialpolicy(Timestamp datematerialpolicy) {
		this.datematerialpolicy = datematerialpolicy;
	}
	public int getM_Product_ID() {
		return M_Product_ID;
	}
	public void setM_Product_ID(int m_Product_ID) {
		M_Product_ID = m_Product_ID;
	}
	public int getM_Locator_ID() {
		return M_Locator_ID;
	}
	public void setM_Locator_ID(int m_Locator_ID) {
		M_Locator_ID = m_Locator_ID;
	}
}
