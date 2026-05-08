-- 
SELECT register_migration_script('202604021439_PlaceholderForTicket.sql') FROM dual;

SET SQLBLANKLINES ON
SET DEFINE OFF

-- 02/Apr/2026 14:39:17
INSERT INTO AD_Table (AD_Table_ID,Name,TableName,LoadSeq,AccessLevel,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsSecurityEnabled,IsDeleteable,IsHighVolume,IsView,EntityType,ImportTable,IsChangeLog,ReplicationType,CopyColumnsFromTable,IsCentrallyMaintained,AD_Table_UU,Processing,DatabaseViewDrop,CopyComponentsFromView,CreateWindowFromTable,IsShowInDrillOptions,IsPartition) VALUES (nextidfunc(21,'N'),'T_TCSGeneralLedgerView','T_TCSGeneralLedgerView',0,'3',0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:17','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:17','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','N','N','U','N','N','L','N','Y','8d40360b-3ac1-43ab-9696-c17eeb0802aa','N','N','N','N','Y','N')
;

-- 02/Apr/2026 14:39:33
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,AD_Val_Rule_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Tenant','Tenant for this installation.','A Tenant is a company or a legal entity. You cannot share data between Tenants.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),129,'AD_Client_ID','@#AD_Client_ID@',10,'N','N','Y','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:32','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:32','YYYY-MM-DD HH24:MI:SS'),100,102,'N','N','U','N','5e096779-9b4e-4145-9ce4-737861899d42','N','D')
;

-- 02/Apr/2026 14:39:33
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,AD_Val_Rule_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Organization','Organizational entity within tenant','An organization is a unit of your tenant or legal entity - examples are store, department. You can share data between organizations.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),104,'AD_Org_ID','@#AD_Org_ID@',10,'N','N','Y','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:33','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:33','YYYY-MM-DD HH24:MI:SS'),100,113,'N','N','U','N','22d55acf-3b29-4201-ada8-59b9770e57bd','N','D')
;

-- 02/Apr/2026 14:39:33
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:33','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:33','YYYY-MM-DD HH24:MI:SS'),100,'org_name','org_name','org_name','U','5c1e020e-6f2c-4954-bcec-bcf4d0d1ce13')
;

-- 02/Apr/2026 14:39:34
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'org_name',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'org_name',60,'N','N','Y','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:33','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:33','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','5c1e020e-6f2c-4954-bcec-bcf4d0d1ce13'),'Y','N','U','N','8599fedf-5b3f-46b5-a6b6-5661fd9a059b','N')
;

-- 02/Apr/2026 14:39:34
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Accounting Schema','Rules for accounting','An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_AcctSchema_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:34','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:34','YYYY-MM-DD HH24:MI:SS'),100,181,'N','N','U','N','48d09e7f-2479-493b-9084-89f72a81b6d7','N','C')
;

-- 02/Apr/2026 14:39:34
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:34','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:34','YYYY-MM-DD HH24:MI:SS'),100,'acct_schema_name','acct_schema_name','acct_schema_name','U','b4b3d49d-2dac-4f31-b947-cc1f3311db5f')
;

-- 02/Apr/2026 14:39:35
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'acct_schema_name',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'acct_schema_name',60,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:34','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:34','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','b4b3d49d-2dac-4f31-b947-cc1f3311db5f'),'Y','N','U','N','d194ebd0-930d-4484-9ad9-acd8ce2e748c','N')
;

-- 02/Apr/2026 14:39:35
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Reference_Value_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Account','Account used','The (natural) account used',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'Account_ID',10,'N','N','N','N','N','N',30,132,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:35','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:35','YYYY-MM-DD HH24:MI:SS'),100,148,'N','N','U','N','1e45fa9b-5a4b-45c6-9573-dff472b97462','N')
;

-- 02/Apr/2026 14:39:35
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:35','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:35','YYYY-MM-DD HH24:MI:SS'),100,'account_no','account_no','account_no','U','ac128877-7568-49ca-af1c-f1da72bb5a51')
;

-- 02/Apr/2026 14:39:36
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'account_no',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'account_no',40,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:35','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:35','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','ac128877-7568-49ca-af1c-f1da72bb5a51'),'Y','N','U','N','71f3bec7-61dc-4104-bf00-d909c3e4a84b','N')
;

-- 02/Apr/2026 14:39:36
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,SeqNoSelection,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Account Name',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'Account_Name',120,'N','N','N','N','N','N',14,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:36','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:36','YYYY-MM-DD HH24:MI:SS'),100,202278,'N','Y','U','N','f4a71f6a-abe5-4235-995c-eb4eec955b81',10,'N')
;

-- 02/Apr/2026 14:39:36
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Account Date','Accounting Date','The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'DateAcct',29,'N','N','N','N','N','N',15,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:36','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:36','YYYY-MM-DD HH24:MI:SS'),100,263,'N','N','U','N','b8c79f70-f3c6-4f29-b3db-fddcfd3cef75','N')
;

-- 02/Apr/2026 14:39:37
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Period','Period of the Calendar','The Period indicates an exclusive range of dates for a calendar.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_Period_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:36','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:36','YYYY-MM-DD HH24:MI:SS'),100,206,'N','N','U','N','ebd45698-531f-406d-aee7-e1dee3bc79b5','N')
;

-- 02/Apr/2026 14:39:37
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:37','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:37','YYYY-MM-DD HH24:MI:SS'),100,'period_name','period_name','period_name','U','2c06ad41-8f28-40f1-9719-875769e0b884')
;

-- 02/Apr/2026 14:39:37
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'period_name',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'period_name',60,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:37','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:37','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','2c06ad41-8f28-40f1-9719-875769e0b884'),'Y','N','U','N','32c30120-ec03-48f0-87f7-bfc47b273598','N')
;

-- 02/Apr/2026 14:39:38
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Reference_Value_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Posting Type','The type of posted amount for the transaction','The Posting Type indicates the type of amount (Actual, Budget, Reservation, Commitment, Statistical) the transaction.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'PostingType',1,'N','N','N','N','N','N',17,125,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:37','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:37','YYYY-MM-DD HH24:MI:SS'),100,514,'Y','N','U','N','b16dc545-5675-43ae-ba91-85b81b0abec7','N')
;

-- 02/Apr/2026 14:39:38
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Accounted Debit','Accounted Debit Amount','The Account Debit Amount indicates the transaction amount converted to this organization''s accounting currency',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'AmtAcctDr',14,'N','N','N','N','N','N',12,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:38','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:38','YYYY-MM-DD HH24:MI:SS'),100,162,'N','N','U','N','7b8df129-6771-42c2-813e-9fe13cb56b0b','N')
;

-- 02/Apr/2026 14:39:38
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Accounted Credit','Accounted Credit Amount','The Account Credit Amount indicates the transaction amount converted to this organization''s accounting currency',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'AmtAcctCr',14,'N','N','N','N','N','N',12,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:38','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:38','YYYY-MM-DD HH24:MI:SS'),100,161,'N','N','U','N','f3fb351a-5c7d-4dd0-813f-9177f81dfb39','N')
;

-- 02/Apr/2026 14:39:38
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Source Credit','Source Credit Amount','The Source Credit Amount indicates the credit amount for this line in the source currency.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'AmtSourceCr',14,'N','N','N','N','N','N',12,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:38','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:38','YYYY-MM-DD HH24:MI:SS'),100,164,'N','N','U','N','0c40bde7-b0b1-4bc7-9548-05ffa821d210','N')
;

-- 02/Apr/2026 14:39:39
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Source Debit','Source Debit Amount','The Source Debit Amount indicates the credit amount for this line in the source currency.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'AmtSourceDr',14,'N','N','N','N','N','N',12,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:39','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:39','YYYY-MM-DD HH24:MI:SS'),100,165,'N','N','U','N','b6e1032d-f21e-4c20-beaa-08feaf096846','N')
;

-- 02/Apr/2026 14:39:39
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Accounted Balance','Accounted Balance Amount','The Account Balance Amount indicates the transaction amount converted to this organization''s accounting currency',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'AmtAcctBalance',14,'N','N','N','N','N','N',12,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:39','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:39','YYYY-MM-DD HH24:MI:SS'),100,2649,'N','N','U','N','ac3d96c8-aa18-40c9-8d14-4917e78e9883','N')
;

-- 02/Apr/2026 14:39:39
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'ISO Currency Code','Three letter ISO 4217 Code of the Currency','For details - http://www.unece.org/trade/rec/rec09en.htm',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'ISO_Code',3,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:39','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:39','YYYY-MM-DD HH24:MI:SS'),100,328,'Y','N','U','N','15ba4308-6fbc-4d77-adfa-f445fab5648f','N')
;

-- 02/Apr/2026 14:39:40
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,SeqNoSelection,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Description','Optional short description of the record','A description is limited to 255 characters.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'Description',255,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:40','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:40','YYYY-MM-DD HH24:MI:SS'),100,275,'Y','Y','U','N','f318b3b0-3471-4f53-b9d5-852698271113',20,'N')
;

-- 02/Apr/2026 14:39:40
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Process Instance','Instance of the process',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'AD_PInstance_ID',10,'N','N','N','N','N','N',30,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:40','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:40','YYYY-MM-DD HH24:MI:SS'),100,114,'N','N','U','N','e434ec66-acdd-4b88-8aac-4eaac0039c79','N','C')
;

-- 02/Apr/2026 14:39:40
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Sequence',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'Sequence',10,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:40','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:40','YYYY-MM-DD HH24:MI:SS'),100,52016,'Y','N','U','N','7e5af9e7-4e5c-4559-9146-35b18ad35ac6','N','N')
;

-- 02/Apr/2026 14:39:41
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:41','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:41','YYYY-MM-DD HH24:MI:SS'),100,'gl_category_name','gl_category_name','gl_category_name','U','46f48a27-90ae-4585-a5cd-f58ceee65303')
;

-- 02/Apr/2026 14:39:41
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'gl_category_name',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'gl_category_name',60,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:40','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:40','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','46f48a27-90ae-4585-a5cd-f58ceee65303'),'Y','N','U','N','0b73a419-1c99-4bde-81bc-420f44f0a54f','N')
;

-- 02/Apr/2026 14:39:41
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:41','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:41','YYYY-MM-DD HH24:MI:SS'),100,'accountto','accountto','accountto','U','1b9d7245-aafd-4405-bc47-85771573c0b9')
;

-- 02/Apr/2026 14:39:41
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'accountto',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'accountto',14,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:41','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:41','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','1b9d7245-aafd-4405-bc47-85771573c0b9'),'Y','N','U','N','6acadb6c-98f4-489d-ad33-7f8d8b0c9672','N')
;

-- 02/Apr/2026 14:39:42
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:42','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:42','YYYY-MM-DD HH24:MI:SS'),100,'accountfrom','accountfrom','accountfrom','U','5824b0c6-38ff-4bea-b3dd-a6be65b014e7')
;

-- 02/Apr/2026 14:39:42
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'accountfrom',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'accountfrom',14,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:42','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:42','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','5824b0c6-38ff-4bea-b3dd-a6be65b014e7'),'Y','N','U','N','e063b6a1-e6d3-4363-92e7-418b72565164','N')
;

-- 02/Apr/2026 14:39:42
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,SeqNoSelection,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Document No','Document sequence number of the document','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'DocumentNo',200,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:42','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:42','YYYY-MM-DD HH24:MI:SS'),100,290,'N','Y','U','N','6d39515b-f406-46a9-94b4-4b1036991611',30,'N')
;

-- 02/Apr/2026 14:39:43
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Line No','Unique line for this document','Indicates the unique line for a document.  It will also control the display order of the lines within a document.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'Line',10,'N','N','N','N','N','N',11,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:42','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:42','YYYY-MM-DD HH24:MI:SS'),100,439,'N','N','U','N','1223f08c-2ce4-4a51-a28f-b498f84a8fe6','N')
;

-- 02/Apr/2026 14:39:43
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Business Partner','Identifies a Business Partner','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_BPartner_ID',10,'N','N','N','N','N','N',30,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:43','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:43','YYYY-MM-DD HH24:MI:SS'),100,187,'N','N','U','N','4aee2b42-efb5-4d3e-9cb7-38a64f0621c7','N')
;

-- 02/Apr/2026 14:39:43
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,AD_Val_Rule_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Product','Product, Service, Item','Identifies an item which is either purchased or sold in this organization.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),231,'M_Product_ID',10,'N','N','N','N','N','N',30,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:43','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:43','YYYY-MM-DD HH24:MI:SS'),100,454,'Y','N','U','N','d3ff35c9-e306-43e6-a034-6377dfeafd0e','N','N')
;

-- 02/Apr/2026 14:39:43
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Accounting Fact',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'Fact_Acct_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:43','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:43','YYYY-MM-DD HH24:MI:SS'),100,885,'N','N','U','N','8ad005a0-6f48-4471-8199-d8831e7876eb','N')
;

-- 02/Apr/2026 14:39:44
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'GL Category','General Ledger Category','The General Ledger Category is an optional, user defined method of grouping journal lines.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'GL_Category_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:44','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:44','YYYY-MM-DD HH24:MI:SS'),100,309,'N','N','U','N','408bbd30-99f1-482c-9d03-27c07187e3f1','N')
;

-- 02/Apr/2026 14:39:44
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Project','Financial Project','A Project allows you to track and control internal or external activities.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_Project_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:44','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:44','YYYY-MM-DD HH24:MI:SS'),100,208,'N','N','U','N','8312ae88-686b-4fe2-94be-64703cc21247','N')
;

-- 02/Apr/2026 14:39:44
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Project Phase','Phase of a Project',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_ProjectPhase_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:44','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:44','YYYY-MM-DD HH24:MI:SS'),100,2073,'N','N','U','N','45f306df-a1c5-4a4e-abb6-f3d2805dd58d','N')
;

-- 02/Apr/2026 14:39:45
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Project Task','Actual Project Task in a Phase','A Project Task in a Project Phase represents the actual work.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_ProjectTask_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:44','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:44','YYYY-MM-DD HH24:MI:SS'),100,2074,'N','N','U','N','0b18c125-177f-426c-a9a0-6f218363de05','N')
;

-- 02/Apr/2026 14:39:45
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Tax','Tax identifier','The Tax indicates the type of tax used in document line.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_Tax_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:45','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:45','YYYY-MM-DD HH24:MI:SS'),100,213,'N','N','U','N','32578776-633b-490a-876d-fd1c798c0c50','N')
;

-- 02/Apr/2026 14:39:45
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Locator','Warehouse Locator','The Locator indicates where in a Warehouse a product is located.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'M_Locator_ID',10,'N','N','N','N','N','N',31,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:45','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:45','YYYY-MM-DD HH24:MI:SS'),100,448,'N','N','U','N','67286081-3590-40e7-a003-e1a295724c11','N')
;

-- 02/Apr/2026 14:39:46
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Reference_Value_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Trx Organization','Performing or initiating organization','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'AD_OrgTrx_ID',10,'N','N','N','N','N','N',18,130,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:45','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:45','YYYY-MM-DD HH24:MI:SS'),100,112,'Y','N','U','N','5366f0d8-5e60-436a-912e-f55030b4eeba','N','N')
;

-- 02/Apr/2026 14:39:46
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Campaign','Marketing Campaign','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_Campaign_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:46','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:46','YYYY-MM-DD HH24:MI:SS'),100,550,'Y','N','U','N','8a8ad36e-b6ce-459d-b31f-7368e5787e3e','N','N')
;

-- 02/Apr/2026 14:39:46
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Activity','Business Activity','Activities indicate tasks that are performed and used to utilize Activity based Costing',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_Activity_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:46','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:46','YYYY-MM-DD HH24:MI:SS'),100,1005,'Y','N','U','N','0e920399-246a-4d89-a952-4a0ffbd00a28','N','N')
;

-- 02/Apr/2026 14:39:46
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Sales Region','Sales coverage region','The Sales Region indicates a specific area of sales coverage.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_SalesRegion_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:46','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:46','YYYY-MM-DD HH24:MI:SS'),100,210,'Y','N','U','N','6a338f16-a6d2-4bec-89d2-f627c82f7e3f','N','N')
;

-- 02/Apr/2026 14:39:47
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Reference_Value_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Location From','Location that inventory was moved from','The Location From indicates the location that a product was moved from.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_LocFrom_ID',10,'N','N','N','N','N','N',18,133,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:47','YYYY-MM-DD HH24:MI:SS'),100,200,'N','N','U','N','b7871322-8ab9-46dc-b4a3-ee467c7f5796','N')
;

-- 02/Apr/2026 14:39:47
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Reference_Value_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Location To','Location that inventory was moved to','The Location To indicates the location that a product was moved to.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_LocTo_ID',10,'N','N','N','N','N','N',18,133,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:47','YYYY-MM-DD HH24:MI:SS'),100,201,'N','N','U','N','90f6bba8-d286-4f9c-8641-71b8dd5da6d4','N')
;

-- 02/Apr/2026 14:39:47
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Sub Account','Sub account for Element Value','The Element Value (e.g. Account) may have optional sub accounts for further detail. The sub account is dependent on the value of the account, so a further specification. If the sub-accounts are more or less the same, consider using another accounting dimension.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'C_SubAcct_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:47','YYYY-MM-DD HH24:MI:SS'),100,2876,'N','N','U','N','275db468-5dea-444f-afcc-25412a7eb432','N')
;

-- 02/Apr/2026 14:39:48
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Asset','Asset used internally or by customers','An asset is either created by purchasing or by delivering a product.  An asset can be used internally or be a customer asset.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'A_Asset_ID',10,'N','N','N','N','N','N',30,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:47','YYYY-MM-DD HH24:MI:SS'),100,1884,'N','N','U','N','0c2ee972-7368-44ea-a300-0ec613b20a23','N')
;

-- 02/Apr/2026 14:39:48
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Reference_Value_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'User Element List 1','User defined list element #1','The user defined element displays the optional elements that have been defined for this account combination.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'User1_ID',10,'N','N','N','N','N','N',30,134,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:48','YYYY-MM-DD HH24:MI:SS'),100,613,'Y','N','U','N','ca258f56-71ff-4235-98c5-3433b757beba','N','N')
;

-- 02/Apr/2026 14:39:48
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Reference_Value_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'User Element List 2','User defined list element #2','The user defined element displays the optional elements that have been defined for this account combination.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'User2_ID',10,'N','N','N','N','N','N',30,137,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:48','YYYY-MM-DD HH24:MI:SS'),100,614,'Y','N','U','N','7f7a285f-588e-4c1b-8613-20c2d105b211','N','N')
;

-- 02/Apr/2026 14:39:49
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:48','YYYY-MM-DD HH24:MI:SS'),100,'accountsequence','accountsequence','accountsequence','U','23c1df9a-494e-45bc-9051-b9ec8c2c11de')
;

-- 02/Apr/2026 14:39:49
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'accountsequence',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'accountsequence',14,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:48','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','23c1df9a-494e-45bc-9051-b9ec8c2c11de'),'Y','N','U','N','1a58ddc6-0e95-437c-af9d-4fa7b3db5286','N')
;

-- 02/Apr/2026 14:39:49
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Quantity','Quantity','The Quantity indicates the number of a specific product or item for this document.',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'Qty',14,'N','N','N','N','N','N',29,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:49','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:49','YYYY-MM-DD HH24:MI:SS'),100,526,'Y','N','U','N','ccba3113-5bdf-45ce-a11e-09399f31e62e','N')
;

-- 02/Apr/2026 14:39:49
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:49','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:49','YYYY-MM-DD HH24:MI:SS'),100,'isreversecorrect','isreversecorrect','isreversecorrect','U','b450b86a-99dc-442d-8b71-6086075ec959')
;

-- 02/Apr/2026 14:39:50
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'isreversecorrect',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'isreversecorrect','N',1,'N','N','Y','N','N','N',20,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:39:49','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:39:49','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','b450b86a-99dc-442d-8b71-6086075ec959'),'Y','N','U','N','699bfd85-f8b4-4ad4-93f1-d5b20305fb87','N')
;
-- 02/Apr/2026 14:54:47
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,AD_Val_Rule_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Tenant','Tenant for this installation.','A Tenant is a company or a legal entity. You cannot share data between Tenants.',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),129,'AD_Client_ID','@#AD_Client_ID@',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:47','YYYY-MM-DD HH24:MI:SS'),100,102,'N','N','U','N','53591d0b-3b38-472f-bc70-d1d0422f2aeb','N','D')
;

-- 02/Apr/2026 14:54:48
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,AD_Val_Rule_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Organization','Organizational entity within tenant','An organization is a unit of your tenant or legal entity - examples are store, department. You can share data between organizations.',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),104,'AD_Org_ID','@#AD_Org_ID@',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:47','YYYY-MM-DD HH24:MI:SS'),100,113,'N','N','U','N','edf9e87e-2b71-4b35-86a5-b308d82c9937','N','D')
;

-- 02/Apr/2026 14:54:48
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'beginningqty',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'beginningqty',14,'N','N','N','N','N','N',29,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:48','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','d6e2ce36-4d1e-482f-a2e2-c6312f49648a'),'Y','N','U','N','dce85314-3e1e-4da6-854e-d27111f27a72','N','N')
;

-- 02/Apr/2026 14:54:48
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'beginnningamount',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'beginnningamount',14,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:48','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','4c2c2d9a-450f-436a-9732-1241d79908fe'),'Y','N','U','N','e2a3941f-7c3c-4f00-a6a5-7bbdc7ce02c2','N','N')
;

-- 02/Apr/2026 14:54:49
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Period','Period of the Calendar','The Period indicates an exclusive range of dates for a calendar.',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'C_Period_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:48','YYYY-MM-DD HH24:MI:SS'),100,206,'N','N','U','N','a4a6a49b-6a21-4e03-b91a-cfaec98c14e2','N')
;

-- 02/Apr/2026 14:54:49
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'costprice',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'costprice',14,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:49','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:49','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','fff0a478-aafe-4660-a8e0-932a5a350027'),'Y','N','U','N','aac81b2d-7ef4-402e-b95e-46a89b5cd28a','N','N')
;

-- 02/Apr/2026 14:54:49
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'endingamount',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'endingamount',14,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:49','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:49','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','d8eec34a-5f70-491d-af8e-1ee73f70d0ff'),'Y','N','U','N','4f54b015-f160-4326-b653-6a7b0fda82e1','N','N')
;

-- 02/Apr/2026 14:54:49
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'endingqty',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'endingqty',14,'N','N','N','N','N','N',29,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:49','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:49','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','5c83d90c-785c-4467-953d-c77ed31ba34a'),'Y','N','U','N','6a6d17e4-31d0-4dd1-8325-215029bdd168','N','N')
;

-- 02/Apr/2026 14:54:50
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'ipv_amount',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'ipv_amount',14,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:50','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:50','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','51ec301c-df25-4d1e-89f9-767bcda9df86'),'Y','N','U','N','d576e64e-5c88-4fb9-a0d8-3d46959e83d3','N','N')
;

-- 02/Apr/2026 14:54:50
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Active','The record is active in the system','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'IsActive','Y',1,'N','N','Y','N','N','N',20,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:50','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:50','YYYY-MM-DD HH24:MI:SS'),100,348,'Y','N','U','N','145e8e7c-687c-4452-90ef-c52ff706ff33','N')
;

-- 02/Apr/2026 14:54:50
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'issueamount',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'issueamount',14,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:50','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:50','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','058e7e2b-136c-4f68-98ff-7a5b71aa9450'),'Y','N','U','N','714954c3-ae51-41dd-9662-f28e94175c21','N','N')
;

-- 02/Apr/2026 14:54:51
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'issueqty',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'issueqty',14,'N','N','N','N','N','N',29,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:50','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:50','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','94850329-d526-456a-8faf-72dc6eb173a4'),'Y','N','U','N','125c4a11-b0af-4f13-946f-51ffe4980ce1','N','N')
;

-- 02/Apr/2026 14:54:51
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'landedcostamount',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'landedcostamount',14,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:51','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:51','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','6912d8a0-bf6d-457d-a4b1-0aa7b35907bf'),'Y','N','U','N','7d565384-0480-4880-a91d-ef9fcaff3afe','N','N')
;

-- 02/Apr/2026 14:54:51
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Product Category','Category of a Product','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'M_Product_Category_ID',10,'N','N','N','N','N','N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:51','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:51','YYYY-MM-DD HH24:MI:SS'),100,453,'N','N','U','N','7598f82f-a2c9-48de-9439-bfbb8b37eae6','N')
;

-- 02/Apr/2026 14:54:52
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,AD_Val_Rule_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'Product','Product, Service, Item','Identifies an item which is either purchased or sold in this organization.',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),231,'M_Product_ID',10,'N','N','N','N','N','N',30,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:51','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:51','YYYY-MM-DD HH24:MI:SS'),100,454,'Y','N','U','N','ced0bebb-be78-42a7-9007-ec66ed03186a','N','N')
;

-- 02/Apr/2026 14:54:52
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'receiptamount',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'receiptamount',14,'N','N','N','N','N','N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:52','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:52','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','94f2ff92-baaf-4a20-8831-471fcaeeadd1'),'Y','N','U','N','b991797c-b65b-43c4-8690-f87089076a0b','N','N')
;

-- 02/Apr/2026 14:54:52
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton,FKConstraintType) VALUES (nextidfunc(3,'N'),0.0,'receiptqty',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'receiptqty',14,'N','N','N','N','N','N',29,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:52','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:52','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','e311d051-abc0-458c-888c-5031147db538'),'Y','N','U','N','9d41bb65-e5f1-45aa-83cf-81e7940c8aca','N','N')
;

-- 02/Apr/2026 14:54:52
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:52','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:52','YYYY-MM-DD HH24:MI:SS'),100,'organization','organization','organization','U','6687e25d-dc57-46f5-a578-3ebe1af84122')
;

-- 02/Apr/2026 14:54:53
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'organization',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'organization',60,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:52','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:52','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','6687e25d-dc57-46f5-a578-3ebe1af84122'),'Y','N','U','N','73d9a9ac-66d5-402c-9832-fe69039c3ede','N')
;

-- 02/Apr/2026 14:54:53
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:53','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:53','YYYY-MM-DD HH24:MI:SS'),100,'client','client','client','U','c9ccb928-fc54-418d-9746-0f0ffc023669')
;

-- 02/Apr/2026 14:54:53
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'client',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'client',60,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:53','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:53','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','c9ccb928-fc54-418d-9746-0f0ffc023669'),'Y','N','U','N','6f680c1c-0484-4836-8d74-5193f74df467','N')
;

-- 02/Apr/2026 14:54:53
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:53','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:53','YYYY-MM-DD HH24:MI:SS'),100,'product_category','product_category','product_category','U','56eaf0a8-3e01-4f24-a68f-56e796f750a3')
;

-- 02/Apr/2026 14:54:54
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'product_category',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'product_category',60,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:53','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:53','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','56eaf0a8-3e01-4f24-a68f-56e796f750a3'),'Y','N','U','N','63c8660e-1b7e-4a1f-8077-b23ad49b6ffb','N')
;

-- 02/Apr/2026 14:54:54
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:54','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:54','YYYY-MM-DD HH24:MI:SS'),100,'period','period','period','U','4d9f31f5-7f8f-4129-b22f-5cfc3457965d')
;

-- 02/Apr/2026 14:54:54
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'period',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'period',60,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:54','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:54','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','4d9f31f5-7f8f-4129-b22f-5cfc3457965d'),'Y','N','U','N','94f98335-87d2-4118-94b4-edb56c13c617','N')
;

-- 02/Apr/2026 14:54:55
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsAlwaysUpdateable,AD_Column_UU,IsToolbarButton) VALUES (nextidfunc(3,'N'),0.0,'Product',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'Product',255,'N','N','N','N','N','N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:54:54','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:54:54','YYYY-MM-DD HH24:MI:SS'),100,1417,'Y','N','U','N','80733898-f2d0-4337-8c33-6b555e5c3465','N')
;

-- 02/Apr/2026 14:57:11
INSERT INTO AD_ReportView (AD_ReportView_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,AD_Table_ID,EntityType,AD_ReportView_UU) VALUES (nextidfunc(298,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:57:11','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:57:11','YYYY-MM-DD HH24:MI:SS'),100,'TCS_Periodic_Costing_V',toRecordId('AD_Table','754e48b4-f5a0-4386-bdd8-499230ce74d1'),'U','688aa035-e149-444b-810b-52f806db8ddb')
;

-- 02/Apr/2026 14:59:09
INSERT INTO AD_Process (AD_Process_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Help,IsReport,Value,IsDirectPrint,AccessLevel,EntityType,Statistic_Count,Statistic_Seconds,IsBetaFunctionality,ShowHelp,CopyFromProcess,AD_Process_UU,AllowMultipleExecution,BXS_MBIsOpenNewTab) VALUES (nextidfunc(199,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:59:09','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:59:09','YYYY-MM-DD HH24:MI:SS'),100,'Periodic Cost','Report untuk melihat hasil perhitungan dari process Calculate Periodic Cost.','Y','Periodic Cost','N','3','U',0,0,'N','Y','N','3aaa3727-f9ba-442a-938c-7723b5540534','P','N')
;

-- 02/Apr/2026 14:59:56
UPDATE AD_Process SET AD_ReportView_ID=toRecordId('AD_ReportView','688aa035-e149-444b-810b-52f806db8ddb'),Updated=TO_TIMESTAMP('2026-04-02 14:59:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_UU='3aaa3727-f9ba-442a-938c-7723b5540534'
;

-- 02/Apr/2026 15:00:44
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:00:44','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:00:44','YYYY-MM-DD HH24:MI:SS'),100,'Client','Client/Tenant for this installation.','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',toRecordId('AD_Process','3aaa3727-f9ba-442a-938c-7723b5540534'),10,19,'N',0,'Y','AD_Client_ID','N','U','6c8ed994-66ca-4fc4-a519-704dfee4cf25','N','N','D','N')
;

-- 02/Apr/2026 15:01:30
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:01:30','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:01:30','YYYY-MM-DD HH24:MI:SS'),100,'Period','Period of the Calendar','The Period indicates an exclusive range of dates for a calendar.',toRecordId('AD_Process','3aaa3727-f9ba-442a-938c-7723b5540534'),20,19,'N',0,'N','C_Period_ID','N','U','4bbbe8a2-4b5b-45e2-adfe-0095e0dc7816','N','N','D','N')
;

-- 02/Apr/2026 15:02:05
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:02:05','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:02:05','YYYY-MM-DD HH24:MI:SS'),100,'Product','Product, Service, Item','Identifies an item which is either purchased or sold in this organization.',toRecordId('AD_Process','3aaa3727-f9ba-442a-938c-7723b5540534'),30,30,'N',0,'N','M_Product_ID','Y','U',454,'3002dbb3-d61c-425c-9fa9-c883b1393439','N','N','D','N')
;

-- 02/Apr/2026 15:02:58
INSERT INTO AD_Process (AD_Process_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,IsReport,Value,IsDirectPrint,AccessLevel,EntityType,Statistic_Count,Statistic_Seconds,IsBetaFunctionality,ShowHelp,CopyFromProcess,AD_Process_UU,AllowMultipleExecution,BXS_MBIsOpenNewTab) VALUES (nextidfunc(199,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:02:58','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:02:58','YYYY-MM-DD HH24:MI:SS'),100,'Calculate Periodic Cost','N','Calculate Periodic Cost','N','3','U',0,0,'N','Y','N','af6024c5-b321-4d29-8a3d-37885626dda5','Y','N')
;

-- 02/Apr/2026 15:03:55
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,DefaultValue,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:03:55','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:03:55','YYYY-MM-DD HH24:MI:SS'),100,'Tenant','Tenant for this installation.','A Tenant is a company or a legal entity. You cannot share data between Tenants.',toRecordId('AD_Process','af6024c5-b321-4d29-8a3d-37885626dda5'),10,19,'N',0,'Y','@#AD_Client_ID@','AD_Client_ID','Y','U',102,'4abfb262-bf6d-42af-9243-f66eefdf598a','N','N','D','N')
;

-- 02/Apr/2026 15:04:12
UPDATE AD_Process_Para SET Name='Client',Updated=TO_TIMESTAMP('2026-04-02 15:04:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_UU='4abfb262-bf6d-42af-9243-f66eefdf598a'
;

-- 02/Apr/2026 15:04:41
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:04:41','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:04:41','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Schema','Rules for accounting','An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar',toRecordId('AD_Process','af6024c5-b321-4d29-8a3d-37885626dda5'),20,19,'N',0,'Y','C_AcctSchema_ID','Y','U',181,'3bed4e3e-9698-4564-a689-b92239d71b1f','N','N','D','N')
;

-- 02/Apr/2026 15:05:17
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,DefaultValue,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:05:17','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:05:17','YYYY-MM-DD HH24:MI:SS'),100,'Organization','Organizational entity within tenant','An organization is a unit of your tenant or legal entity - examples are store, department. You can share data between organizations.',toRecordId('AD_Process','af6024c5-b321-4d29-8a3d-37885626dda5'),30,19,'N',0,'N','@#AD_Org_ID@','AD_Org_ID','Y','U',113,'6c9189b3-4289-47c2-87da-ed75a25d3b2d','N','N','D','N')
;

-- 02/Apr/2026 15:05:45
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,AD_Reference_Value_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:05:45','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:05:45','YYYY-MM-DD HH24:MI:SS'),100,'Period','Period of the Calendar','The Period indicates an exclusive range of dates for a calendar.',toRecordId('AD_Process','af6024c5-b321-4d29-8a3d-37885626dda5'),40,200162,275,'N',0,'Y','C_Period_ID','Y','U',206,'639f8dbf-a440-4049-9ae3-865d66818d9d','N','N','D','Y')
;

-- 02/Apr/2026 15:05:57
UPDATE AD_Process_Para SET AD_Reference_ID=19, AD_Reference_Value_ID=NULL,Updated=TO_TIMESTAMP('2026-04-02 15:05:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_UU='639f8dbf-a440-4049-9ae3-865d66818d9d'
;

-- 02/Apr/2026 15:06:41
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,DisplayLogic,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:06:41','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:06:41','YYYY-MM-DD HH24:MI:SS'),100,'Product','Product, Service, Item','Identifies an item which is either purchased or sold in this organization.',toRecordId('AD_Process','af6024c5-b321-4d29-8a3d-37885626dda5'),50,30,'N',0,'N','M_Product_ID','Y','U',454,'@M_Product_Category_ID@=0 & @M_InOut_ID@=0 & @M_Inventory_ID@=0','1bdd275c-33c6-427f-a2a5-999acf963730','N','N','D','N')
;

-- 02/Apr/2026 15:07:18
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,DisplayLogic,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:07:17','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:07:17','YYYY-MM-DD HH24:MI:SS'),100,'Product Category','Category of a Product','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.',toRecordId('AD_Process','af6024c5-b321-4d29-8a3d-37885626dda5'),60,19,'N',0,'N','M_Product_Category_ID','Y','U',453,'@M_Product_ID@=0 & @M_InOut_ID@=0 & @M_Inventory_ID@=0','89da7a70-abfc-4bff-8f83-0066783974db','N','N','D','N')
;

-- 02/Apr/2026 15:07:44
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,DisplayLogic,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:07:44','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:07:44','YYYY-MM-DD HH24:MI:SS'),100,'Shipment/Receipt','Material Shipment Document','The Material Shipment / Receipt ',toRecordId('AD_Process','af6024c5-b321-4d29-8a3d-37885626dda5'),70,30,'N',0,'N','M_InOut_ID','Y','U',1025,'@M_Product_ID@=0 & @M_Product_Category_ID@=0 & @M_Inventory_ID@=0','e3d57e31-068f-450f-b254-131982d4e2fa','N','N','D','N')
;

-- 02/Apr/2026 15:08:14
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,DisplayLogic,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:08:14','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:08:14','YYYY-MM-DD HH24:MI:SS'),100,'Phys.Inventory','Parameters for a Physical Inventory','The Physical Inventory indicates a unique parameters for a physical inventory.',toRecordId('AD_Process','af6024c5-b321-4d29-8a3d-37885626dda5'),80,30,'N',22,'N','M_Inventory_ID','Y','U',1027,'@M_Product_ID@=0 & @M_InOut_ID@=0 & @M_Product_Category_ID@=0','af252a22-1582-43e1-ac0a-2e54bbaf7336','N','N','D','N')
;

-- 02/Apr/2026 15:08:26
UPDATE AD_Process_Para SET Name='Inventory',Updated=TO_TIMESTAMP('2026-04-02 15:08:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_UU='af252a22-1582-43e1-ac0a-2e54bbaf7336'
;

-- 02/Apr/2026 15:08:56
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,DefaultValue,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:08:55','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:08:55','YYYY-MM-DD HH24:MI:SS'),100,'Recalculate',toRecordId('AD_Process','af6024c5-b321-4d29-8a3d-37885626dda5'),90,20,'N',0,'N','N','isRecalculate','Y','U',toRecordId('AD_Element','7148cdc8-b91e-4726-b107-c5e0b4e8b123'),'f08409b3-e5d7-404f-9068-908cb6cab313','N','N','D','N')
;

-- 02/Apr/2026 15:09:04
UPDATE AD_Process_Para SET Name='Recalculate?',Updated=TO_TIMESTAMP('2026-04-02 15:09:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_UU='f08409b3-e5d7-404f-9068-908cb6cab313'
;

-- 02/Apr/2026 15:09:24
UPDATE AD_Process_Para SET FieldLength=0,Updated=TO_TIMESTAMP('2026-04-02 15:09:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_UU='af252a22-1582-43e1-ac0a-2e54bbaf7336'
;

-- 02/Apr/2026 15:09:53
UPDATE AD_Process SET Classname='id.mitraabadi.process.MAB_CalculateCostingPeriodic',Updated=TO_TIMESTAMP('2026-04-02 15:09:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_UU='af6024c5-b321-4d29-8a3d-37885626dda5'
;

-- 02/Apr/2026 15:10:57
INSERT INTO AD_Process (AD_Process_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Help,IsReport,Value,IsDirectPrint,Classname,AccessLevel,EntityType,Statistic_Count,Statistic_Seconds,IsBetaFunctionality,ShowHelp,CopyFromProcess,AD_Process_UU,AllowMultipleExecution,BXS_MBIsOpenNewTab) VALUES (nextidfunc(199,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:10:57','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:10:57','YYYY-MM-DD HH24:MI:SS'),100,'Update Cost Detail','Jalankan proses ini HANYA JIKA ingin melakukan update terhadap nilai jurnal.','N','Update Cost Detail','N','id.mitraabadi.process.MAB_UpdateCostDetail','3','U',0,0,'N','Y','N','ae25a448-51cd-443b-be70-d2713a8c1755','Y','N')
;

-- 02/Apr/2026 15:11:43
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:11:42','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:11:42','YYYY-MM-DD HH24:MI:SS'),100,'Tenant','Tenant for this installation.','A Tenant is a company or a legal entity. You cannot share data between Tenants.',toRecordId('AD_Process','ae25a448-51cd-443b-be70-d2713a8c1755'),10,19,'N',0,'Y','AD_Client_ID','Y','U',102,'1f8f6ca5-a785-48b9-9c1b-dac5f4bb5354','N','N','D','N')
;

-- 02/Apr/2026 15:11:50
UPDATE AD_Process_Para SET Name='Client',Updated=TO_TIMESTAMP('2026-04-02 15:11:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_UU='1f8f6ca5-a785-48b9-9c1b-dac5f4bb5354'
;

-- 02/Apr/2026 15:13:06
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:13:05','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:13:05','YYYY-MM-DD HH24:MI:SS'),100,'Period','Period of the Calendar','The Period indicates an exclusive range of dates for a calendar.',toRecordId('AD_Process','ae25a448-51cd-443b-be70-d2713a8c1755'),20,19,'N',0,'Y','C_Period_ID','Y','U',206,'923d3767-b2be-41ce-bfad-c91a9d936acb','N','N','D','N')
;

-- 02/Apr/2026 15:13:30
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:13:30','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:13:30','YYYY-MM-DD HH24:MI:SS'),100,'Product','Product, Service, Item','Identifies an item which is either purchased or sold in this organization.',toRecordId('AD_Process','ae25a448-51cd-443b-be70-d2713a8c1755'),30,30,'N',0,'N','M_Product_ID','Y','U',454,'b58dab51-b263-43a4-a3a7-4ed360d947db','N','N','D','N')
;

-- 02/Apr/2026 15:15:19
INSERT INTO AD_Menu (AD_Menu_ID,Name,Action,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsSummary,AD_Process_ID,IsSOTrx,IsReadOnly,EntityType,IsCentrallyMaintained,AD_Menu_UU) VALUES (nextidfunc(7,'N'),'Update Cost Detail','P',0,0,'Y',TO_TIMESTAMP('2026-04-02 15:15:19','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:15:19','YYYY-MM-DD HH24:MI:SS'),100,'N',toRecordId('AD_Process','ae25a448-51cd-443b-be70-d2713a8c1755'),'N','N','U','Y','4c057074-a46e-464c-acb7-ea2efb1612b8')
;

-- 02/Apr/2026 15:15:19
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo, AD_TreeNodeMM_UU) SELECT t.AD_Client_ID, 0, 'Y', getDate(), 100, getDate(), 100,t.AD_Tree_ID, toRecordId('AD_Menu','4c057074-a46e-464c-acb7-ea2efb1612b8'), 0, 999, Generate_UUID() FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=toRecordId('AD_Menu','4c057074-a46e-464c-acb7-ea2efb1612b8'))
;

-- 02/Apr/2026 15:15:50
INSERT INTO AD_Menu (AD_Menu_ID,Name,Action,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsSummary,AD_Process_ID,IsSOTrx,IsReadOnly,EntityType,IsCentrallyMaintained,AD_Menu_UU) VALUES (nextidfunc(7,'N'),'Calculate Periodic Cost','P',0,0,'Y',TO_TIMESTAMP('2026-04-02 15:15:50','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:15:50','YYYY-MM-DD HH24:MI:SS'),100,'N',toRecordId('AD_Process','af6024c5-b321-4d29-8a3d-37885626dda5'),'Y','N','U','Y','b18cda0b-4feb-4d0f-ae66-7eb5d052a0cb')
;

-- 02/Apr/2026 15:15:50
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo, AD_TreeNodeMM_UU) SELECT t.AD_Client_ID, 0, 'Y', getDate(), 100, getDate(), 100,t.AD_Tree_ID, toRecordId('AD_Menu','b18cda0b-4feb-4d0f-ae66-7eb5d052a0cb'), 0, 999, Generate_UUID() FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=toRecordId('AD_Menu','b18cda0b-4feb-4d0f-ae66-7eb5d052a0cb'))
;

-- 02/Apr/2026 15:16:02
UPDATE AD_Menu SET IsSOTrx='N',Updated=TO_TIMESTAMP('2026-04-02 15:16:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_UU='b18cda0b-4feb-4d0f-ae66-7eb5d052a0cb'
;

-- 02/Apr/2026 15:16:36
INSERT INTO AD_Menu (AD_Menu_ID,Name,Action,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsSummary,AD_Process_ID,IsSOTrx,IsReadOnly,EntityType,IsCentrallyMaintained,AD_Menu_UU) VALUES (nextidfunc(7,'N'),'Periodic Cost','R',0,0,'Y',TO_TIMESTAMP('2026-04-02 15:16:35','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:16:35','YYYY-MM-DD HH24:MI:SS'),100,'N',toRecordId('AD_Process','3aaa3727-f9ba-442a-938c-7723b5540534'),'N','N','U','Y','719d615e-39cc-4c1a-996d-dcb1946d3d3f')
;

-- 02/Apr/2026 15:16:36
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo, AD_TreeNodeMM_UU) SELECT t.AD_Client_ID, 0, 'Y', getDate(), 100, getDate(), 100,t.AD_Tree_ID, toRecordId('AD_Menu','719d615e-39cc-4c1a-996d-dcb1946d3d3f'), 0, 999, Generate_UUID() FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=toRecordId('AD_Menu','719d615e-39cc-4c1a-996d-dcb1946d3d3f'))
;

-- 02/Apr/2026 15:18:28
INSERT INTO AD_ReportView (AD_ReportView_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,AD_Table_ID,EntityType,AD_ReportView_UU) VALUES (nextidfunc(298,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:18:28','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:18:28','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger View',toRecordId('AD_Table','8d40360b-3ac1-43ab-9696-c17eeb0802aa'),'U','8a17b275-dd09-4df5-9bdd-ea4c68d40480')
;

-- 02/Apr/2026 15:19:41
INSERT INTO AD_Process (AD_Process_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,IsReport,Value,IsDirectPrint,AD_ReportView_ID,Classname,AccessLevel,EntityType,Statistic_Count,Statistic_Seconds,IsBetaFunctionality,ShowHelp,CopyFromProcess,AD_Process_UU,AllowMultipleExecution,BXS_MBIsOpenNewTab) VALUES (nextidfunc(199,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:19:41','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:19:41','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger','Y','General Ledger','N',toRecordId('AD_ReportView','8a17b275-dd09-4df5-9bdd-ea4c68d40480'),'id.mitraabadi.process.TCSGeneralLedgerView','3','U',0,0,'N','Y','N','84b9526c-3dfc-45f0-bc52-766d69157ff3','P','N')
;

-- 02/Apr/2026 15:20:58
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,AD_Reference_Value_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:20:58','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:20:58','YYYY-MM-DD HH24:MI:SS'),100,'Account','Account used','The (natural) account used',toRecordId('AD_Process','84b9526c-3dfc-45f0-bc52-766d69157ff3'),10,30,331,'N',0,'Y','Account_ID','Y','U',148,'c5578dc8-397f-4f31-8fe4-b7b6ccfe02f1','N','N','D','N')
;

-- 02/Apr/2026 15:21:12
UPDATE AD_Process_Para SET Name='AccountFrom', ColumnName='AccountFrom',Updated=TO_TIMESTAMP('2026-04-02 15:21:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_UU='c5578dc8-397f-4f31-8fe4-b7b6ccfe02f1'
;

-- 02/Apr/2026 15:21:23
UPDATE AD_Process_Para SET Name='Account From',Updated=TO_TIMESTAMP('2026-04-02 15:21:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_UU='c5578dc8-397f-4f31-8fe4-b7b6ccfe02f1'
;

-- 02/Apr/2026 15:21:53
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,AD_Reference_Value_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:21:53','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:21:53','YYYY-MM-DD HH24:MI:SS'),100,'Account','Account used','The (natural) account used',toRecordId('AD_Process','84b9526c-3dfc-45f0-bc52-766d69157ff3'),20,30,331,'N',0,'Y','Account_ID','Y','U',148,'4204b4d2-6bc9-4f40-8515-d0449d302180','N','N','D','N')
;

-- 02/Apr/2026 15:22:04
UPDATE AD_Process_Para SET Name='Account To', IsMandatory='N', ColumnName='AccountTo',Updated=TO_TIMESTAMP('2026-04-02 15:22:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_UU='4204b4d2-6bc9-4f40-8515-d0449d302180'
;

-- 02/Apr/2026 15:24:12
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,Placeholder,Placeholder2,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:24:12','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:24:12','YYYY-MM-DD HH24:MI:SS'),100,'Account Date','Accounting Date','The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.',toRecordId('AD_Process','84b9526c-3dfc-45f0-bc52-766d69157ff3'),30,15,'Y',0,'Y','DateAcct','Y','U',263,'9fea1464-174f-493e-883c-5ea49cd0116d','N','From','To','N','D','N')
;

-- 02/Apr/2026 15:26:54
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,AD_Process_ID,SeqNo,AD_Reference_ID,AD_Reference_Value_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:26:54','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:26:54','YYYY-MM-DD HH24:MI:SS'),100,'isreversecorrect',toRecordId('AD_Process','84b9526c-3dfc-45f0-bc52-766d69157ff3'),40,17,319,'N',1,'N','isreversecorrect','Y','U',toRecordId('AD_Element','b450b86a-99dc-442d-8b71-6086075ec959'),'f812239d-9bd6-4d15-9a44-a0e3bcf010a1','N','N','D','N')
;

-- 02/Apr/2026 15:27:05
UPDATE AD_Process_Para SET Name='Reverse Correct?',Updated=TO_TIMESTAMP('2026-04-02 15:27:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_UU='f812239d-9bd6-4d15-9a44-a0e3bcf010a1'
;

-- 02/Apr/2026 15:29:18
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,AD_Reference_Value_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:29:18','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:29:18','YYYY-MM-DD HH24:MI:SS'),100,'Organization','Organizational entity within tenant','An organization is a unit of your tenant or legal entity - examples are store, department. You can share data between organizations.',toRecordId('AD_Process','84b9526c-3dfc-45f0-bc52-766d69157ff3'),50,18,276,'N',0,'Y','AD_Org_ID','Y','U',113,'888ccccd-7b53-4aa3-be6d-921b4272a1bd','N','N','D','N')
;

-- 02/Apr/2026 15:29:43
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:29:43','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:29:43','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner','Identifies a Business Partner','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson',toRecordId('AD_Process','84b9526c-3dfc-45f0-bc52-766d69157ff3'),60,30,'N',0,'N','C_BPartner_ID','Y','U',187,'932d00b5-42a3-43c3-b0c9-57ec76f6be23','N','N','D','N')
;

-- 02/Apr/2026 15:30:01
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:30:00','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:30:00','YYYY-MM-DD HH24:MI:SS'),100,'Campaign','Marketing Campaign','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.',toRecordId('AD_Process','84b9526c-3dfc-45f0-bc52-766d69157ff3'),70,19,'N',0,'N','C_Campaign_ID','Y','U',550,'e93b6c88-a22a-4289-8cfe-9849370159ee','N','N','D','N')
;

-- 02/Apr/2026 15:31:07
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,Description,Help,AD_Process_ID,SeqNo,AD_Reference_ID,IsRange,FieldLength,IsMandatory,DefaultValue,ColumnName,IsCentrallyMaintained,EntityType,AD_Element_ID,AD_Process_Para_UU,IsEncrypted,IsAutocomplete,DateRangeOption,IsShowNegateButton) VALUES (nextidfunc(200,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 15:31:07','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:31:07','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Schema','Rules for accounting','An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar',toRecordId('AD_Process','84b9526c-3dfc-45f0-bc52-766d69157ff3'),80,19,'N',0,'Y','1000000','C_AcctSchema_ID','Y','U',181,'4bd20248-b344-4147-b6ae-066e893c672a','N','N','D','N')
;

-- 02/Apr/2026 15:31:47
INSERT INTO AD_Menu (AD_Menu_ID,Name,Action,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsSummary,AD_Process_ID,IsSOTrx,IsReadOnly,EntityType,IsCentrallyMaintained,AD_Menu_UU) VALUES (nextidfunc(7,'N'),'General Ledger','R',0,0,'Y',TO_TIMESTAMP('2026-04-02 15:31:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 15:31:47','YYYY-MM-DD HH24:MI:SS'),100,'N',toRecordId('AD_Process','84b9526c-3dfc-45f0-bc52-766d69157ff3'),'Y','N','U','Y','cb9a656d-8f43-4864-ae73-2e3a6c4b5f3a')
;

-- 02/Apr/2026 15:31:47
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo, AD_TreeNodeMM_UU) SELECT t.AD_Client_ID, 0, 'Y', getDate(), 100, getDate(), 100,t.AD_Tree_ID, toRecordId('AD_Menu','cb9a656d-8f43-4864-ae73-2e3a6c4b5f3a'), 0, 999, Generate_UUID() FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=toRecordId('AD_Menu','cb9a656d-8f43-4864-ae73-2e3a6c4b5f3a'))
;

-- 02/Apr/2026 15:32:09
UPDATE AD_Menu SET IsSOTrx='N',Updated=TO_TIMESTAMP('2026-04-02 15:32:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_UU='cb9a656d-8f43-4864-ae73-2e3a6c4b5f3a'
;

