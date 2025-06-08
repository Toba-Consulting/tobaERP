/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.taowi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_BankTransfer
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_C_BankTransfer 
{

    /** TableName=C_BankTransfer */
    public static final String Table_Name = "C_BankTransfer";

    /** AD_Table_ID=300152 */
    public static final int Table_ID = 300152;

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name C_BankAccount_ID */
    public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";

	/** Set Bank Account.
	  * Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID);

	/** Get Bank Account.
	  * Account at the Bank
	  */
	public int getC_BankAccount_ID();

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException;

    /** Column name C_BankAccountTo_ID */
    public static final String COLUMNNAME_C_BankAccountTo_ID = "C_BankAccountTo_ID";

	/** Set Bank Account To.
	  * Bank Account selected as fund transfer destination 
	  */
	public void setC_BankAccountTo_ID (int C_BankAccountTo_ID);

	/** Get Bank Account To.
	  * Bank Account selected as fund transfer destination 
	  */
	public int getC_BankAccountTo_ID();

	public org.compiere.model.I_C_BankAccount getC_BankAccountTo() throws RuntimeException;

    /** Column name C_BankTransfer_ID */
    public static final String COLUMNNAME_C_BankTransfer_ID = "C_BankTransfer_ID";

	/** Set C_BankTransfer	  */
	public void setC_BankTransfer_ID (int C_BankTransfer_ID);

	/** Get C_BankTransfer	  */
	public int getC_BankTransfer_ID();

    /** Column name C_BankTransfer_UU */
    public static final String COLUMNNAME_C_BankTransfer_UU = "C_BankTransfer_UU";

	/** Set C_BankTransfer_UU	  */
	public void setC_BankTransfer_UU (String C_BankTransfer_UU);

	/** Get C_BankTransfer_UU	  */
	public String getC_BankTransfer_UU();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_CancelTransferFrom_ID */
    public static final String COLUMNNAME_C_CancelTransferFrom_ID = "C_CancelTransferFrom_ID";

	/** Set Cancel Transfer From.
	  * Cancel Giro Transaction and create payment to restore giro transaction that already being remitted (Cancel Transfer From based on Transfer To)
	  */
	public void setC_CancelTransferFrom_ID (int C_CancelTransferFrom_ID);

	/** Get Cancel Transfer From.
	  * Cancel Giro Transaction and create payment to restore giro transaction that already being remitted (Cancel Transfer From based on Transfer To)
	  */
	public int getC_CancelTransferFrom_ID();

	public org.compiere.model.I_C_Payment getC_CancelTransferFrom() throws RuntimeException;

    /** Column name C_CancelTransferTo_ID */
    public static final String COLUMNNAME_C_CancelTransferTo_ID = "C_CancelTransferTo_ID";

	/** Set Cancel Transfer To	  */
	public void setC_CancelTransferTo_ID (int C_CancelTransferTo_ID);

	/** Get Cancel Transfer To	  */
	public int getC_CancelTransferTo_ID();

	public org.compiere.model.I_C_Payment getC_CancelTransferTo() throws RuntimeException;

    /** Column name C_ConversionType_ID */
    public static final String COLUMNNAME_C_ConversionType_ID = "C_ConversionType_ID";

	/** Set Currency Type.
	  * Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID);

	/** Get Currency Type.
	  * Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID();

	public org.compiere.model.I_C_ConversionType getC_ConversionType() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name C_CurrencyTo_ID */
    public static final String COLUMNNAME_C_CurrencyTo_ID = "C_CurrencyTo_ID";

	/** Set C_CurrencyTo_ID	  */
	public void setC_CurrencyTo_ID (int C_CurrencyTo_ID);

	/** Get C_CurrencyTo_ID	  */
	public int getC_CurrencyTo_ID();

	public org.compiere.model.I_C_Currency getC_CurrencyTo() throws RuntimeException;

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name C_TransferFrom_ID */
    public static final String COLUMNNAME_C_TransferFrom_ID = "C_TransferFrom_ID";

	/** Set Bank Transfer From	  */
	public void setC_TransferFrom_ID (int C_TransferFrom_ID);

	/** Get Bank Transfer From	  */
	public int getC_TransferFrom_ID();

	public org.compiere.model.I_C_Payment getC_TransferFrom() throws RuntimeException;

    /** Column name C_TransferTo_ID */
    public static final String COLUMNNAME_C_TransferTo_ID = "C_TransferTo_ID";

	/** Set Bank Transfer To	  */
	public void setC_TransferTo_ID (int C_TransferTo_ID);

	/** Get Bank Transfer To	  */
	public int getC_TransferTo_ID();

	public org.compiere.model.I_C_Payment getC_TransferTo() throws RuntimeException;

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name GiroNo */
    public static final String COLUMNNAME_GiroNo = "GiroNo";

	/** Set Giro No	  */
	public void setGiroNo (String GiroNo);

	/** Get Giro No	  */
	public String getGiroNo();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsMultiCurrency */
    public static final String COLUMNNAME_IsMultiCurrency = "IsMultiCurrency";

	/** Set MultiCurrency.
	  * for Multi Currency Transaction
	  */
	public void setIsMultiCurrency (boolean IsMultiCurrency);

	/** Get MultiCurrency.
	  * for Multi Currency Transaction
	  */
	public boolean isMultiCurrency();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name StatementDate */
    public static final String COLUMNNAME_StatementDate = "StatementDate";

	/** Set Statement date.
	  * Date of the statement
	  */
	public void setStatementDate (Timestamp StatementDate);

	/** Get Statement date.
	  * Date of the statement
	  */
	public Timestamp getStatementDate();

    /** Column name TransferStatus */
    public static final String COLUMNNAME_TransferStatus = "TransferStatus";

	/** Set Transfer Status	  */
	public void setTransferStatus (String TransferStatus);

	/** Get Transfer Status	  */
	public String getTransferStatus();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name UserRate */
    public static final String COLUMNNAME_UserRate = "UserRate";

	/** Set User Rate	  */
	public void setUserRate (BigDecimal UserRate);

	/** Get User Rate	  */
	public BigDecimal getUserRate();
}
