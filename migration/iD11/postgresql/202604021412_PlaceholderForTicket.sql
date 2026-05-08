-- 
SELECT register_migration_script('202604021412_PlaceholderForTicket.sql') FROM dual;

-- 02/Apr/2026 14:12:16
INSERT INTO AD_Table (AD_Table_ID,Name,TableName,LoadSeq,AccessLevel,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsSecurityEnabled,IsDeleteable,IsHighVolume,IsView,EntityType,ImportTable,IsChangeLog,ReplicationType,CopyColumnsFromTable,IsCentrallyMaintained,AD_Table_UU,Processing,DatabaseViewDrop,CopyComponentsFromView,CreateWindowFromTable,IsShowInDrillOptions,IsPartition) VALUES (nextidfunc(21,'N'),'T_TCSGeneralLedgerView','T_TCSGeneralLedgerView',0,'3',0,0,'Y',TO_TIMESTAMP('2026-04-02 14:12:15','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:12:15','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','N','N','U','N','N','L','N','Y','d68b2c69-b494-42d2-b23f-e2c98a389243','N','N','N','N','Y','N')
;

-- 02/Apr/2026 14:12:16
INSERT INTO AD_Sequence (Name,CurrentNext,IsAudited,StartNewYear,Description,IsActive,IsTableID,AD_Client_ID,AD_Org_ID,Created,CreatedBy,Updated,UpdatedBy,AD_Sequence_ID,IsAutoSequence,StartNo,IncrementNo,CurrentNextSys,AD_Sequence_UU) VALUES ('T_TCSGeneralLedgerView',1000000,'N','N','Table T_TCSGeneralLedgerView','Y','Y',0,0,TO_TIMESTAMP('2026-04-02 14:12:16','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:12:16','YYYY-MM-DD HH24:MI:SS'),100,nextidfunc(16,'N'),'Y',1000000,1,200000,'1690b5b0-aec3-4336-8b6d-347317032f33')
;

-- 02/Apr/2026 14:13:05
DELETE FROM AD_Table WHERE AD_Table_UU='d68b2c69-b494-42d2-b23f-e2c98a389243'
;

-- 02/Apr/2026 14:15:00
INSERT INTO AD_Table (AD_Table_ID,Name,TableName,LoadSeq,AccessLevel,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsSecurityEnabled,IsDeleteable,IsHighVolume,IsView,EntityType,ImportTable,IsChangeLog,ReplicationType,CopyColumnsFromTable,IsCentrallyMaintained,AD_Table_UU,Processing,DatabaseViewDrop,CopyComponentsFromView,CreateWindowFromTable,IsShowInDrillOptions,IsPartition) VALUES (nextidfunc(21,'N'),'M_Periodic_Cost','M_Periodic_Cost',0,'3',0,0,'Y',TO_TIMESTAMP('2026-04-02 14:15:00','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:15:00','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','N','N','U','N','N','L','N','Y','6f05547c-088d-47ed-9b41-bce305b68c2f','N','N','N','N','Y','N')
;

-- 02/Apr/2026 14:15:00
INSERT INTO AD_Sequence (Name,CurrentNext,IsAudited,StartNewYear,Description,IsActive,IsTableID,AD_Client_ID,AD_Org_ID,Created,CreatedBy,Updated,UpdatedBy,AD_Sequence_ID,IsAutoSequence,StartNo,IncrementNo,CurrentNextSys,AD_Sequence_UU) VALUES ('M_Periodic_Cost',1000000,'N','N','Table M_Periodic_Cost','Y','Y',0,0,TO_TIMESTAMP('2026-04-02 14:15:00','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:15:00','YYYY-MM-DD HH24:MI:SS'),100,nextidfunc(16,'N'),'Y',1000000,1,200000,'20c5e3a1-4986-431d-9044-9b4a56160e65')
;

-- 02/Apr/2026 14:16:01
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,AD_Val_Rule_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,FKConstraintType,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Tenant','Tenant for this installation.','A Tenant is a company or a legal entity. You cannot share data between Tenants.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),129,'AD_Client_ID','@#AD_Client_ID@',22,'N','N','Y','N','N',0,'N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:16:00','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:16:00','YYYY-MM-DD HH24:MI:SS'),100,102,'N','N','U','N','N','N','Y','d9d0e504-7603-4395-b8e5-d6f773b21c22','N',0,'N','N','D','N','N')
;

-- 02/Apr/2026 14:16:32
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,AD_Val_Rule_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,FKConstraintType,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Organization','Organizational entity within tenant','An organization is a unit of your tenant or legal entity - examples are store, department. You can share data between organizations.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),104,'AD_Org_ID','@#AD_Org_ID@',22,'N','N','Y','N','N',0,'N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:16:32','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:16:32','YYYY-MM-DD HH24:MI:SS'),100,113,'N','N','U','N','N','N','Y','2b1f1801-c9db-4902-8525-e061de0d34be','N',0,'N','N','D','N','N')
;

-- 02/Apr/2026 14:17:08
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:17:08','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:17:08','YYYY-MM-DD HH24:MI:SS'),100,'beginningqty','beginningqty','beginningqty','U','d6e2ce36-4d1e-482f-a2e2-c6312f49648a')
;

-- 02/Apr/2026 14:17:40
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'beginningqty',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'beginningqty',131089,'N','N','N','N','N',0,'N',29,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:17:39','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:17:39','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','d6e2ce36-4d1e-482f-a2e2-c6312f49648a'),'Y','N','U','N','N','N','Y','c38b76bb-35a6-4893-9a78-8ed325de135c','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:18:23
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:18:23','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:18:23','YYYY-MM-DD HH24:MI:SS'),100,'beginnningamount','beginnningamount','beginnningamount','U','4c2c2d9a-450f-436a-9732-1241d79908fe')
;

-- 02/Apr/2026 14:19:16
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'beginnningamount',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'beginnningamount',131089,'N','N','N','N','N',0,'N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:19:15','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:19:15','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','4c2c2d9a-450f-436a-9732-1241d79908fe'),'Y','N','U','N','N','N','Y','af760d78-240d-494c-a317-b1f6e96bff02','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:19:44
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:19:44','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:19:44','YYYY-MM-DD HH24:MI:SS'),100,'costprice','costprice','costprice','U','fff0a478-aafe-4660-a8e0-932a5a350027')
;

-- 02/Apr/2026 14:20:14
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'costprice',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'costprice',131089,'N','N','N','N','N',0,'N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:20:14','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:20:14','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','fff0a478-aafe-4660-a8e0-932a5a350027'),'Y','N','U','N','N','N','Y','5d18fb2f-f5a8-45e4-b90b-6714a1d38524','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:20:47
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Period','Period of the Calendar','The Period indicates an exclusive range of dates for a calendar.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'C_Period_ID',22,'N','N','N','N','N',0,'N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:20:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:20:47','YYYY-MM-DD HH24:MI:SS'),100,206,'N','N','U','N','N','N','Y','9c6031a6-71f7-47f7-802b-b6ab3a8dcbfd','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:21:13
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Created','Date this record was created','The Created field indicates the date that this record was created.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'Created','SYSDATE',7,'N','N','Y','N','N',0,'N',16,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:21:13','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:21:13','YYYY-MM-DD HH24:MI:SS'),100,245,'N','N','U','N','N','N','Y','fcfc1b2f-ab03-4ce1-abf3-a41d72069c2c','N',0,'N','N','N','N')
;

-- 02/Apr/2026 14:21:37
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Reference_Value_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,FKConstraintType,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Created By','User who created this records','The Created By field indicates the user who created this record.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'CreatedBy',22,'N','N','Y','N','N',0,'N',30,110,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:21:36','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:21:36','YYYY-MM-DD HH24:MI:SS'),100,246,'N','N','U','N','N','N','Y','c676c5cd-53bc-4009-84d6-04230b6b530a','N',0,'N','N','D','N','N')
;

-- 02/Apr/2026 14:21:51
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:21:51','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:21:51','YYYY-MM-DD HH24:MI:SS'),100,'endingamount','endingamount','endingamount','U','d8eec34a-5f70-491d-af8e-1ee73f70d0ff')
;

-- 02/Apr/2026 14:22:20
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'endingamount',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'endingamount',131089,'N','N','N','N','N',0,'N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:22:19','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:22:19','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','d8eec34a-5f70-491d-af8e-1ee73f70d0ff'),'Y','N','U','N','N','N','Y','c7007b22-d524-4ec0-b8f2-848409688ad7','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:22:41
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:22:41','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:22:41','YYYY-MM-DD HH24:MI:SS'),100,'endingqty','endingqty','endingqty','U','5c83d90c-785c-4467-953d-c77ed31ba34a')
;

-- 02/Apr/2026 14:23:17
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'endingqty',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'endingqty',131089,'N','N','N','N','N',0,'N',29,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:23:17','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:23:17','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','5c83d90c-785c-4467-953d-c77ed31ba34a'),'Y','N','U','N','N','N','Y','d3d59d44-ca7f-4b9c-b3ca-bab668a2cd34','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:23:38
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:23:38','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:23:38','YYYY-MM-DD HH24:MI:SS'),100,'ipv_amount','ipv_amount','ipv_amount','U','51ec301c-df25-4d1e-89f9-767bcda9df86')
;

-- 02/Apr/2026 14:24:02
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'ipv_amount',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'ipv_amount',131089,'N','N','N','N','N',0,'N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:24:02','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:24:02','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','51ec301c-df25-4d1e-89f9-767bcda9df86'),'Y','N','U','N','N','N','Y','a58187db-ae5b-4c3d-bc1f-c3aae80a2b7f','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:24:27
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Active','The record is active in the system','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'IsActive','Y',1,'N','N','Y','N','N',0,'N',20,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:24:27','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:24:27','YYYY-MM-DD HH24:MI:SS'),100,348,'Y','N','U','N','N','N','Y','4e1e0b25-161e-4cd6-afa7-0c1babc9f6e9','N',0,'N','N','N','N')
;

-- 02/Apr/2026 14:24:48
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:24:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:24:48','YYYY-MM-DD HH24:MI:SS'),100,'issueamount','issueamount','issueamount','U','058e7e2b-136c-4f68-98ff-7a5b71aa9450')
;

-- 02/Apr/2026 14:25:11
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'issueamount',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'issueamount',131089,'N','N','N','N','N',0,'N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:25:11','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:25:11','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','058e7e2b-136c-4f68-98ff-7a5b71aa9450'),'Y','N','U','N','N','N','Y','c9febe61-bc14-4349-b8ae-5ad50eeb5fb8','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:25:50
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:25:50','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:25:50','YYYY-MM-DD HH24:MI:SS'),100,'issueqty','issueqty','issueqty','U','94850329-d526-456a-8faf-72dc6eb173a4')
;

-- 02/Apr/2026 14:26:19
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'issueqty',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'issueqty',131089,'N','N','N','N','N',0,'N',29,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:26:19','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:26:19','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','94850329-d526-456a-8faf-72dc6eb173a4'),'Y','N','U','N','N','N','Y','b502aeff-9f33-49e8-86f8-a52cc8dbd8db','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:26:34
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:26:34','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:26:34','YYYY-MM-DD HH24:MI:SS'),100,'landedcostamount','landedcostamount','landedcostamount','U','6912d8a0-bf6d-457d-a4b1-0aa7b35907bf')
;

-- 02/Apr/2026 14:27:03
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'landedcostamount',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'landedcostamount',131089,'N','N','N','N','N',0,'N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:27:03','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:27:03','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','6912d8a0-bf6d-457d-a4b1-0aa7b35907bf'),'Y','N','U','N','N','N','Y','34733294-d878-4540-936b-32643366a0e6','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:27:28
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,FKConstraintType,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Attribute Set Instance','Product Attribute Set Instance','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'M_AttributeSetInstance_ID',22,'N','N','N','N','N',0,'N',35,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:27:28','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:27:28','YYYY-MM-DD HH24:MI:SS'),100,2019,'Y','N','U','N','N','N','Y','555ab6d1-8076-4db1-9f0b-88197281044b','Y',0,'N','N','N','N','N')
;

-- 02/Apr/2026 14:27:45
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:27:45','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:27:45','YYYY-MM-DD HH24:MI:SS'),100,'M_Periodic_Cost_ID','M_Periodic_Cost_ID','M_Periodic_Cost_ID','U','258494e5-e0ea-4ecf-924e-62c5cf4dae3b')
;

-- 02/Apr/2026 14:28:07
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'M_Periodic_Cost_ID',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'M_Periodic_Cost_ID',22,'Y','N','N','N','N',0,'N',13,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:28:06','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:28:06','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','258494e5-e0ea-4ecf-924e-62c5cf4dae3b'),'N','N','U','N','N','N','Y','998b8f9c-0def-482d-b682-0db8308d9b7a','N',0,'N','N','N','N')
;

-- 02/Apr/2026 14:28:21
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:28:21','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:28:21','YYYY-MM-DD HH24:MI:SS'),100,'M_Periodic_Cost_UU','M_Periodic_Cost_UU','M_Periodic_Cost_UU','U','10c8f5ce-b105-40aa-b7fb-827fce1f09c1')
;

-- 02/Apr/2026 14:28:50
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'M_Periodic_Cost_UU',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'M_Periodic_Cost_UU',36,'N','N','N','N','N',0,'N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:28:49','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:28:49','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','10c8f5ce-b105-40aa-b7fb-827fce1f09c1'),'N','N','U','N','N','N','Y','2e06afe9-a7a1-4339-8dbf-186ea56a6485','N',0,'N','N','N','N')
;

-- 02/Apr/2026 14:29:05
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Product Category','Category of a Product','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'M_Product_Category_ID',22,'N','N','N','N','N',0,'N',19,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:29:05','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:29:05','YYYY-MM-DD HH24:MI:SS'),100,453,'N','N','U','N','N','N','Y','20159cce-66d1-4220-a19c-21c8a7b1d89a','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:29:44
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,AD_Val_Rule_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,FKConstraintType,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Product','Product, Service, Item','Identifies an item which is either purchased or sold in this organization.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),231,'M_Product_ID',22,'N','N','N','N','N',0,'N',30,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:29:44','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:29:44','YYYY-MM-DD HH24:MI:SS'),100,454,'Y','N','U','N','N','N','Y','0e9343ce-1647-43a3-ac72-ff8c93ce0579','Y',0,'N','N','N','N','N')
;

-- 02/Apr/2026 14:29:55
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:29:55','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:29:55','YYYY-MM-DD HH24:MI:SS'),100,'receiptamount','receiptamount','receiptamount','U','94f2ff92-baaf-4a20-8831-471fcaeeadd1')
;

-- 02/Apr/2026 14:30:22
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'receiptamount',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'receiptamount',131089,'N','N','N','N','N',0,'N',22,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:30:22','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:30:22','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','94f2ff92-baaf-4a20-8831-471fcaeeadd1'),'Y','N','U','N','N','N','Y','66a05f3c-d940-4c25-8e2e-d5f619c533c7','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:30:37
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-02 14:30:36','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:30:36','YYYY-MM-DD HH24:MI:SS'),100,'receiptqty','receiptqty','receiptqty','U','e311d051-abc0-458c-888c-5031147db538')
;

-- 02/Apr/2026 14:31:01
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'receiptqty',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'receiptqty',131089,'N','N','N','N','N',0,'N',29,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:31:01','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:31:01','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','e311d051-abc0-458c-888c-5031147db538'),'Y','N','U','N','N','N','Y','8b04364a-5200-4c08-ba3e-ebbeeea9f736','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 14:31:21
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Updated','Date this record was updated','The Updated field indicates the date that this record was updated.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'Updated','SYSDATE',7,'N','N','N','N','N',0,'N',16,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:31:21','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:31:21','YYYY-MM-DD HH24:MI:SS'),100,607,'N','N','U','N','N','N','Y','89c56c6b-bf26-40a4-91fd-12dd68924f93','N',0,'N','N','N','N')
;

-- 02/Apr/2026 14:31:47
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Reference_Value_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,FKConstraintType,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Updated By','User who updated this records','The Updated By field indicates the user who updated this record.',toRecordId('AD_Table','6f05547c-088d-47ed-9b41-bce305b68c2f'),'UpdatedBy',22,'N','N','Y','N','N',0,'N',18,110,0,0,'Y',TO_TIMESTAMP('2026-04-02 14:31:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 14:31:47','YYYY-MM-DD HH24:MI:SS'),100,608,'N','N','U','N','N','N','Y','4ad22f89-64f7-492e-8983-02206bb2c366','N',0,'N','N','D','N','N')
;

-- 02/Apr/2026 14:31:56
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2026-04-02 14:31:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_UU='89c56c6b-bf26-40a4-91fd-12dd68924f93'
;

-- 02/Apr/2026 14:32:10
UPDATE AD_Column SET FKConstraintName='CPeriod_MPeriodicCost', FKConstraintType='N',Updated=TO_TIMESTAMP('2026-04-02 14:32:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_UU='9c6031a6-71f7-47f7-802b-b6ab3a8dcbfd'
;

-- 02/Apr/2026 14:32:11
UPDATE AD_Column SET FKConstraintName='MAttributeSetInstance_MPeriodicCost', FKConstraintType='N',Updated=TO_TIMESTAMP('2026-04-02 14:32:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_UU='555ab6d1-8076-4db1-9f0b-88197281044b'
;

-- 02/Apr/2026 14:32:11
UPDATE AD_Column SET FKConstraintName='MProductCategory_MPeriodicCost', FKConstraintType='N',Updated=TO_TIMESTAMP('2026-04-02 14:32:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_UU='20159cce-66d1-4220-a19c-21c8a7b1d89a'
;

-- 02/Apr/2026 14:32:12
UPDATE AD_Column SET FKConstraintName='MProduct_MPeriodicCost', FKConstraintType='N',Updated=TO_TIMESTAMP('2026-04-02 14:32:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_UU='0e9343ce-1647-43a3-ac72-ff8c93ce0579'
;

-- 02/Apr/2026 14:32:12
CREATE TABLE M_Periodic_Cost (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, C_Period_ID NUMERIC(10) DEFAULT NULL , Created TIMESTAMP DEFAULT statement_timestamp() NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, M_AttributeSetInstance_ID NUMERIC(10) DEFAULT NULL , M_Periodic_Cost_ID NUMERIC(10) DEFAULT NULL , M_Periodic_Cost_UU VARCHAR(36) DEFAULT NULL , M_Product_Category_ID NUMERIC(10) DEFAULT NULL , M_Product_ID NUMERIC(10) DEFAULT NULL , Updated TIMESTAMP DEFAULT statement_timestamp() NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, beginningqty NUMERIC DEFAULT NULL , beginnningamount NUMERIC DEFAULT NULL , costprice NUMERIC DEFAULT NULL , endingamount NUMERIC DEFAULT NULL , endingqty NUMERIC DEFAULT NULL , ipv_amount NUMERIC DEFAULT NULL , issueamount NUMERIC DEFAULT NULL , issueqty NUMERIC DEFAULT NULL , landedcostamount NUMERIC DEFAULT NULL , receiptamount NUMERIC DEFAULT NULL , receiptqty NUMERIC DEFAULT NULL , CONSTRAINT M_Periodic_Cost_Key PRIMARY KEY (M_Periodic_Cost_ID), CONSTRAINT M_Periodic_Cost_UU_idx UNIQUE (M_Periodic_Cost_UU))
;

-- 02/Apr/2026 14:32:12
ALTER TABLE M_Periodic_Cost ADD CONSTRAINT CPeriod_MPeriodicCost FOREIGN KEY (C_Period_ID) REFERENCES c_period(c_period_id) DEFERRABLE INITIALLY DEFERRED
;

-- 02/Apr/2026 14:32:12
ALTER TABLE M_Periodic_Cost ADD CONSTRAINT MAttributeSetInstance_MPeriodicCost FOREIGN KEY (M_AttributeSetInstance_ID) REFERENCES m_attributesetinstance(m_attributesetinstance_id) DEFERRABLE INITIALLY DEFERRED
;

-- 02/Apr/2026 14:32:12
ALTER TABLE M_Periodic_Cost ADD CONSTRAINT MProductCategory_MPeriodicCost FOREIGN KEY (M_Product_Category_ID) REFERENCES m_product_category(m_product_category_id) DEFERRABLE INITIALLY DEFERRED
;

-- 02/Apr/2026 14:32:12
ALTER TABLE M_Periodic_Cost ADD CONSTRAINT MProduct_MPeriodicCost FOREIGN KEY (M_Product_ID) REFERENCES m_product(m_product_id) DEFERRABLE INITIALLY DEFERRED
;

