-- 
SELECT register_migration_script('202604061529_PlaceholderForTicket.sql') FROM dual;

-- 06/Apr/2026 15:29:51
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-06 15:29:51','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-06 15:29:51','YYYY-MM-DD HH24:MI:SS'),100,'IsUnitCost','IsUnitCost','IsUnitCost','U','067f8d3f-e987-4a77-8ecc-67663d69d296')
;

-- 06/Apr/2026 15:30:19
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-06 15:30:19','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-06 15:30:19','YYYY-MM-DD HH24:MI:SS'),100,'UnitCost','UnitCost','UnitCost','U','eabf22c2-131b-46fe-aaee-60b2dcfdfbd5')
;

-- 06/Apr/2026 15:30:38
INSERT INTO AD_Element (AD_Element_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,ColumnName,Name,PrintName,EntityType,AD_Element_UU) VALUES (nextidfunc(188,'N'),0,0,'Y',TO_TIMESTAMP('2026-04-06 15:30:37','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-06 15:30:37','YYYY-MM-DD HH24:MI:SS'),100,'UnitCostEntered','UnitCostEntered','UnitCostEntered','U','3f7cb826-c8c0-4ab2-ab91-714f67060ddf')
;

-- 06/Apr/2026 15:31:43
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'IsUnitCost',322,'IsUnitCost','N',3,'N','N','Y','N','N',0,'N',20,0,0,'Y',TO_TIMESTAMP('2026-04-06 15:31:43','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-06 15:31:43','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','067f8d3f-e987-4a77-8ecc-67663d69d296'),'Y','N','U','N','N','N','Y','f2b30bc7-aa97-4c30-b999-2dbef810c41d','Y',0,'N','N','N','N')
;

-- 06/Apr/2026 15:31:51
INSERT INTO t_alter_column values('m_inventoryline','C_UOM_ID','NUMERIC(10)',null,'NULL')
;

-- 06/Apr/2026 15:31:51
ALTER TABLE M_InventoryLine ADD COLUMN IsUnitCost CHAR(1) DEFAULT 'N' CHECK (IsUnitCost IN ('Y','N')) NOT NULL
;

-- 06/Apr/2026 15:32:08
UPDATE AD_Column SET Name='Use Unit Cost', IsMandatory='Y',Updated=TO_TIMESTAMP('2026-04-06 15:32:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_UU='f2b30bc7-aa97-4c30-b999-2dbef810c41d'
;

-- 06/Apr/2026 15:32:21
INSERT INTO t_alter_column values('m_inventoryline','IsUnitCost','CHAR(1)',null,'N')
;

-- 06/Apr/2026 15:32:22
UPDATE M_InventoryLine SET IsUnitCost='N' WHERE IsUnitCost IS NULL
;

-- 06/Apr/2026 15:33:11
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'UnitCost',322,'UnitCost',22,'N','N','N','N','N',0,'N',37,0,0,'Y',TO_TIMESTAMP('2026-04-06 15:33:11','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-06 15:33:11','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','eabf22c2-131b-46fe-aaee-60b2dcfdfbd5'),'Y','N','U','N','N','N','Y','b70f5adb-8655-4f59-bdd1-aa9d16d973a1','N',0,'N','N','N','N')
;

-- 06/Apr/2026 15:33:16
ALTER TABLE M_InventoryLine ADD COLUMN UnitCost NUMERIC DEFAULT NULL 
;

-- 06/Apr/2026 15:33:25
UPDATE AD_Column SET Name='Unit Cost',Updated=TO_TIMESTAMP('2026-04-06 15:33:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_UU='b70f5adb-8655-4f59-bdd1-aa9d16d973a1'
;

-- 06/Apr/2026 15:33:37
INSERT INTO t_alter_column values('m_inventoryline','UnitCost','NUMERIC',null,'NULL')
;

-- 06/Apr/2026 15:34:34
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'UnitCostEntered',322,'UnitCostEntered',22,'N','N','N','N','N',0,'N',37,0,0,'Y',TO_TIMESTAMP('2026-04-06 15:34:34','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-06 15:34:34','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','3f7cb826-c8c0-4ab2-ab91-714f67060ddf'),'Y','N','U','N','N','N','Y','94ef9041-f5aa-4e84-b233-6c9ef7954d2b','N',0,'N','N','N','N')
;

-- 06/Apr/2026 15:34:39
ALTER TABLE M_InventoryLine ADD COLUMN UnitCostEntered NUMERIC DEFAULT NULL 
;

-- 06/Apr/2026 15:38:55
INSERT INTO AD_Field (AD_Field_ID,Name,AD_Tab_ID,AD_Column_ID,IsDisplayed,DisplayLength,SeqNo,SortNo,IsSameLine,IsHeading,IsFieldOnly,IsEncrypted,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadOnly,IsCentrallyMaintained,EntityType,AD_Field_UU,IsDisplayedGrid,SeqNoGrid,XPosition,ColumnSpan,NumLines,IsQuickEntry,IsDefaultFocus,IsAdvancedField,IsQuickForm) VALUES (nextidfunc(4,'N'),'IsUnitCost',toRecordId('AD_Tab','3f9e9412-5bfa-474e-afa0-629b4126db06'),toRecordId('AD_Column','f2b30bc7-aa97-4c30-b999-2dbef810c41d'),'Y',0,130,0,'N','N','N','N',0,0,'Y',TO_TIMESTAMP('2026-04-06 15:38:55','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-06 15:38:55','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','U','89e598ed-0e19-479f-9eda-01b269b26d91','Y',140,1,1,1,'N','N','N','N')
;

-- 06/Apr/2026 15:39:06
UPDATE AD_Field SET Name='Use Unit Cost',Updated=TO_TIMESTAMP('2026-04-06 15:39:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='89e598ed-0e19-479f-9eda-01b269b26d91'
;

-- 06/Apr/2026 15:39:20
INSERT INTO AD_Field (AD_Field_ID,Name,AD_Tab_ID,AD_Column_ID,IsDisplayed,DisplayLength,SeqNo,SortNo,IsSameLine,IsHeading,IsFieldOnly,IsEncrypted,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadOnly,IsCentrallyMaintained,EntityType,AD_Field_UU,IsDisplayedGrid,SeqNoGrid,XPosition,ColumnSpan,NumLines,IsQuickEntry,IsDefaultFocus,IsAdvancedField,IsQuickForm) VALUES (nextidfunc(4,'N'),'UnitCostEntered',toRecordId('AD_Tab','3f9e9412-5bfa-474e-afa0-629b4126db06'),toRecordId('AD_Column','94ef9041-f5aa-4e84-b233-6c9ef7954d2b'),'Y',0,140,0,'N','N','N','N',0,0,'Y',TO_TIMESTAMP('2026-04-06 15:39:20','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-06 15:39:20','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','U','c221a278-4ca5-4b8b-b681-0bab47832d6b','Y',150,1,1,1,'N','N','N','N')
;

-- 06/Apr/2026 15:39:27
UPDATE AD_Field SET Name='Unit Cost Entered',Updated=TO_TIMESTAMP('2026-04-06 15:39:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='c221a278-4ca5-4b8b-b681-0bab47832d6b'
;

-- 06/Apr/2026 15:42:19
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=40, XPosition=5,Updated=TO_TIMESTAMP('2026-04-06 15:42:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='89e598ed-0e19-479f-9eda-01b269b26d91'
;

-- 06/Apr/2026 15:42:20
UPDATE AD_Field SET SeqNo=50,Updated=TO_TIMESTAMP('2026-04-06 15:42:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='ccc1a008-14dc-493e-bc95-2c7923851864'
;

-- 06/Apr/2026 15:42:20
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=60, XPosition=4, ColumnSpan=2,Updated=TO_TIMESTAMP('2026-04-06 15:42:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='c221a278-4ca5-4b8b-b681-0bab47832d6b'
;

-- 06/Apr/2026 15:42:20
UPDATE AD_Field SET SeqNo=70,Updated=TO_TIMESTAMP('2026-04-06 15:42:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='99f191f0-3663-4e68-9860-bc0fecd92091'
;

-- 06/Apr/2026 15:42:21
UPDATE AD_Field SET SeqNo=80,Updated=TO_TIMESTAMP('2026-04-06 15:42:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='517c5abe-3759-48c7-a395-cc6d494945c2'
;

-- 06/Apr/2026 15:42:21
UPDATE AD_Field SET SeqNo=90,Updated=TO_TIMESTAMP('2026-04-06 15:42:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='a3d64dd4-fd5d-40fb-ae01-555708e0140e'
;

-- 06/Apr/2026 15:42:21
UPDATE AD_Field SET SeqNo=100,Updated=TO_TIMESTAMP('2026-04-06 15:42:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='88bab8bf-01b9-4743-b5c4-68c5e5a82dfe'
;

-- 06/Apr/2026 15:42:22
UPDATE AD_Field SET SeqNo=110,Updated=TO_TIMESTAMP('2026-04-06 15:42:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='4e4d275b-c2da-4379-9ba7-84828c661b56'
;

-- 06/Apr/2026 15:42:22
UPDATE AD_Field SET SeqNo=120,Updated=TO_TIMESTAMP('2026-04-06 15:42:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='8d2426cf-4c5d-4c31-b4c6-07921dc6f3f1'
;

-- 06/Apr/2026 15:42:22
UPDATE AD_Field SET SeqNo=130,Updated=TO_TIMESTAMP('2026-04-06 15:42:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='5eeccaf3-b84c-44af-9c69-eb7a4b81bfd5'
;

-- 06/Apr/2026 15:42:23
UPDATE AD_Field SET SeqNo=140,Updated=TO_TIMESTAMP('2026-04-06 15:42:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='338fe7d2-8697-4c58-8998-8c740a3f20c8'
;

-- 06/Apr/2026 15:42:39
UPDATE AD_Field SET DisplayLogic='@IsUnitCost@=Y',Updated=TO_TIMESTAMP('2026-04-06 15:42:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='c221a278-4ca5-4b8b-b681-0bab47832d6b'
;

