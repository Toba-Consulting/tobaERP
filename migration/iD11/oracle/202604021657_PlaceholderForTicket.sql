-- 
SELECT register_migration_script('202604021657_PlaceholderForTicket.sql') FROM dual;

SET SQLBLANKLINES ON
SET DEFINE OFF

-- 02/Apr/2026 16:57:10
INSERT INTO AD_Column (AD_Column_ID,Version,Name,AD_Table_ID,ColumnName,DefaultValue,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,FKConstraintType,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'isreversecorrect',270,'isreversecorrect','N',1,'N','N','Y','N','N',0,'N',20,0,0,'Y',TO_TIMESTAMP('2026-04-02 16:57:09','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 16:57:09','YYYY-MM-DD HH24:MI:SS'),100,toRecordId('AD_Element','b450b86a-99dc-442d-8b71-6086075ec959'),'Y','N','U','N','N','N','Y','3d52d2fc-5406-45ed-bbf4-fee9fae9181b','Y',0,'N','N','N','N','N')
;

-- 02/Apr/2026 16:57:48
ALTER TABLE Fact_Acct ADD isreversecorrect CHAR(1) DEFAULT 'N' CHECK (isreversecorrect IN ('Y','N')) NOT NULL
;

-- 02/Apr/2026 16:58:10
ALTER TABLE Fact_Acct MODIFY isreversecorrect CHAR(1) DEFAULT 'N'
;

-- 02/Apr/2026 16:58:14
UPDATE Fact_Acct SET isreversecorrect='N' WHERE isreversecorrect IS NULL
;

-- 02/Apr/2026 17:00:24
INSERT INTO AD_Field (AD_Field_ID,Name,AD_Tab_ID,AD_Column_ID,IsDisplayed,DisplayLength,SeqNo,SortNo,IsSameLine,IsHeading,IsFieldOnly,IsEncrypted,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadOnly,IsCentrallyMaintained,EntityType,AD_Field_UU,IsDisplayedGrid,SeqNoGrid,XPosition,ColumnSpan,NumLines,IsQuickEntry,IsDefaultFocus,IsAdvancedField,IsQuickForm) VALUES (nextidfunc(4,'N'),'isreversecorrect',242,toRecordId('AD_Column','3d52d2fc-5406-45ed-bbf4-fee9fae9181b'),'Y',0,370,0,'N','N','N','N',0,0,'Y',TO_TIMESTAMP('2026-04-02 17:00:24','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 17:00:24','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y','U','aa58e3dc-1904-499b-a105-5a29949d57c3','Y',370,1,1,1,'N','N','N','N')
;

-- 02/Apr/2026 17:00:35
UPDATE AD_Field SET Name='Reverse Correct',Updated=TO_TIMESTAMP('2026-04-02 17:00:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='aa58e3dc-1904-499b-a105-5a29949d57c3'
;

-- 02/Apr/2026 17:01:16
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140, XPosition=5,Updated=TO_TIMESTAMP('2026-04-02 17:01:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_UU='aa58e3dc-1904-499b-a105-5a29949d57c3'
;

-- 02/Apr/2026 17:01:16
UPDATE AD_Field SET SeqNo=150,Updated=TO_TIMESTAMP('2026-04-02 17:01:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2363
;

-- 02/Apr/2026 17:01:16
UPDATE AD_Field SET SeqNo=160,Updated=TO_TIMESTAMP('2026-04-02 17:01:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2356
;

-- 02/Apr/2026 17:01:17
UPDATE AD_Field SET SeqNo=170,Updated=TO_TIMESTAMP('2026-04-02 17:01:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2357
;

-- 02/Apr/2026 17:01:17
UPDATE AD_Field SET SeqNo=180,Updated=TO_TIMESTAMP('2026-04-02 17:01:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2366
;

-- 02/Apr/2026 17:01:17
UPDATE AD_Field SET SeqNo=190,Updated=TO_TIMESTAMP('2026-04-02 17:01:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2344
;

-- 02/Apr/2026 17:01:18
UPDATE AD_Field SET SeqNo=200,Updated=TO_TIMESTAMP('2026-04-02 17:01:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2353
;

-- 02/Apr/2026 17:01:18
UPDATE AD_Field SET SeqNo=210,Updated=TO_TIMESTAMP('2026-04-02 17:01:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2354
;

-- 02/Apr/2026 17:01:18
UPDATE AD_Field SET SeqNo=220,Updated=TO_TIMESTAMP('2026-04-02 17:01:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=3886
;

-- 02/Apr/2026 17:01:18
UPDATE AD_Field SET SeqNo=230,Updated=TO_TIMESTAMP('2026-04-02 17:01:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2367
;

-- 02/Apr/2026 17:01:19
UPDATE AD_Field SET SeqNo=240,Updated=TO_TIMESTAMP('2026-04-02 17:01:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2368
;

-- 02/Apr/2026 17:01:19
UPDATE AD_Field SET SeqNo=250,Updated=TO_TIMESTAMP('2026-04-02 17:01:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12607
;

-- 02/Apr/2026 17:01:19
UPDATE AD_Field SET SeqNo=260,Updated=TO_TIMESTAMP('2026-04-02 17:01:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12608
;

-- 02/Apr/2026 17:01:20
UPDATE AD_Field SET SeqNo=270,Updated=TO_TIMESTAMP('2026-04-02 17:01:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2361
;

-- 02/Apr/2026 17:01:20
UPDATE AD_Field SET SeqNo=280,Updated=TO_TIMESTAMP('2026-04-02 17:01:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2360
;

-- 02/Apr/2026 17:01:20
UPDATE AD_Field SET SeqNo=290,Updated=TO_TIMESTAMP('2026-04-02 17:01:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=3891
;

-- 02/Apr/2026 17:01:20
UPDATE AD_Field SET SeqNo=300,Updated=TO_TIMESTAMP('2026-04-02 17:01:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=5141
;

-- 02/Apr/2026 17:01:21
UPDATE AD_Field SET SeqNo=310,Updated=TO_TIMESTAMP('2026-04-02 17:01:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2352
;

-- 02/Apr/2026 17:01:21
UPDATE AD_Field SET SeqNo=320,Updated=TO_TIMESTAMP('2026-04-02 17:01:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2350
;

-- 02/Apr/2026 17:01:21
UPDATE AD_Field SET SeqNo=330,Updated=TO_TIMESTAMP('2026-04-02 17:01:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2349
;

-- 02/Apr/2026 17:01:22
UPDATE AD_Field SET SeqNo=340,Updated=TO_TIMESTAMP('2026-04-02 17:01:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2348
;

-- 02/Apr/2026 17:01:22
UPDATE AD_Field SET SeqNo=350,Updated=TO_TIMESTAMP('2026-04-02 17:01:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2347
;

-- 02/Apr/2026 17:01:22
UPDATE AD_Field SET SeqNo=360,Updated=TO_TIMESTAMP('2026-04-02 17:01:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2358
;

-- 02/Apr/2026 17:01:22
UPDATE AD_Field SET SeqNo=370,Updated=TO_TIMESTAMP('2026-04-02 17:01:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2365
;

-- 02/Apr/2026 17:01:23
UPDATE AD_Field SET SeqNo=0,Updated=TO_TIMESTAMP('2026-04-02 17:01:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=204836
;

-- 02/Apr/2026 17:25:17
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Document No','Document sequence number of the document','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).',270,'DocumentNo',30,'N','N','N','N','N',0,'N',10,0,0,'Y',TO_TIMESTAMP('2026-04-02 17:25:17','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 17:25:17','YYYY-MM-DD HH24:MI:SS'),100,290,'N','Y','U','N','N','N','Y','caae4013-8218-46ed-90cc-6858fbacb0ce','Y',10,'N','N','N','N')
;

-- 02/Apr/2026 17:25:42
ALTER TABLE Fact_Acct ADD DocumentNo VARCHAR2(30 CHAR) DEFAULT NULL 
;

-- 02/Apr/2026 17:26:10
INSERT INTO AD_Column (AD_Column_ID,Version,Name,Description,Help,AD_Table_ID,ColumnName,FieldLength,IsKey,IsParent,IsMandatory,IsTranslated,IsIdentifier,SeqNo,IsEncrypted,AD_Reference_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_Element_ID,IsUpdateable,IsSelectionColumn,EntityType,IsSyncDatabase,IsAlwaysUpdateable,IsAutocomplete,IsAllowLogging,AD_Column_UU,IsAllowCopy,SeqNoSelection,IsToolbarButton,IsSecure,IsHtml,IsPartitionKey) VALUES (nextidfunc(3,'N'),0,'Line No','Unique line for this document','Indicates the unique line for a document.  It will also control the display order of the lines within a document.',270,'Line',22,'N','N','N','N','N',0,'N',11,0,0,'Y',TO_TIMESTAMP('2026-04-02 17:26:10','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-04-02 17:26:10','YYYY-MM-DD HH24:MI:SS'),100,439,'N','N','U','N','N','N','Y','6be1aa3b-914a-42ea-bb8c-d1699db3640f','Y',0,'N','N','N','N')
;

-- 02/Apr/2026 17:26:20
ALTER TABLE Fact_Acct ADD Line NUMBER(10) DEFAULT NULL 
;

