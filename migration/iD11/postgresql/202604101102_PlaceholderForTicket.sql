-- 
SELECT register_migration_script('202604101102_PlaceholderForTicket.sql') FROM dual;

-- 10/Apr/2026 11:02:59
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Usable Life - Years','Years of the usable life of the asset',53137,'UseLifeYears',22,'N','N','N','N','N',0,'N',22,0,0,'Y',TO_TIMESTAMP('2026-04-10 11:02:59','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-10 11:02:59','YYYY-MM-DD HH24:MI:SS'),100,1947,'Y','N','U','N','N','N','Y','2b5ef135-b8f0-4c1c-b341-9251aa5fab6e','Y',0,'N','N','N','N')
;

-- 10/Apr/2026 11:03:28
ALTER TABLE A_Asset_Addition ADD COLUMN UseLifeYears NUMERIC DEFAULT NULL 
;

-- 10/Apr/2026 11:04:27
INSERT INTO AD_Field (AD_Field_ID,Name,Description,AD_Tab_ID,AD_Column_ID,IsDisplayed,DisplayLength,SeqNo,SortNo,IsSameLine,IsHeading,IsFieldOnly,IsEncrypted,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadOnly,IsCentrallyMaintained,EntityType,AD_Field_UU,IsDisplayedGrid,SeqNoGrid,XPosition,ColumnSpan,NumLines,IsQuickEntry,IsDefaultFocus,IsAdvancedField,IsQuickForm) VALUES (nextidfunc(4,'N'),'Usable Life - Years','Years of the usable life of the asset',53324,toRecordId('AD_Column','2b5ef135-b8f0-4c1c-b341-9251aa5fab6e'),'Y',0,420,0,'N','N','N','N',0,0,'Y',TO_TIMESTAMP('2026-04-10 11:04:27','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-10 11:04:27','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','U','968e1b06-db85-4098-99e5-345c48ac04fa','Y',410,1,1,1,'N','N','N','N')
;

-- 10/Apr/2026 11:05:29
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290, XPosition=4, ColumnSpan=2,Updated=TO_TIMESTAMP('2026-04-10 11:05:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='968e1b06-db85-4098-99e5-345c48ac04fa'
;

-- 10/Apr/2026 11:05:29
UPDATE AD_Field SET SeqNo=300,Updated=TO_TIMESTAMP('2026-04-10 11:05:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59380
;

-- 10/Apr/2026 11:05:29
UPDATE AD_Field SET SeqNo=310,Updated=TO_TIMESTAMP('2026-04-10 11:05:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59381
;

-- 10/Apr/2026 11:05:30
UPDATE AD_Field SET SeqNo=320,Updated=TO_TIMESTAMP('2026-04-10 11:05:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59382
;

-- 10/Apr/2026 11:05:30
UPDATE AD_Field SET SeqNo=330,Updated=TO_TIMESTAMP('2026-04-10 11:05:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59383
;

-- 10/Apr/2026 11:05:30
UPDATE AD_Field SET SeqNo=340,Updated=TO_TIMESTAMP('2026-04-10 11:05:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59390
;

-- 10/Apr/2026 11:05:31
UPDATE AD_Field SET SeqNo=350,Updated=TO_TIMESTAMP('2026-04-10 11:05:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59393
;

-- 10/Apr/2026 11:05:31
UPDATE AD_Field SET SeqNo=360,Updated=TO_TIMESTAMP('2026-04-10 11:05:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59394
;