-- adempiere.tcs_periodic_costing_v source
CREATE OR REPLACE VIEW adempiere.tcs_periodic_costing_v
AS SELECT pc.ad_client_id,
    pc.ad_org_id,
    pc.beginningqty,
    pc.beginnningamount,
    pc.c_period_id,
    pc.costprice,
    pc.endingamount,
    pc.endingqty,
    pc.ipv_amount,
    pc.isactive,
    pc.issueamount,
    pc.issueqty,
    pc.landedcostamount,
    pc.m_product_category_id,
    pc.m_product_id,
    pc.receiptamount,
    pc.receiptqty,
    ao.name AS organization,
    ac.name AS client,
    mpc.name AS product_category,
    cp.name AS period,
    mp.name AS product
   FROM m_periodic_cost pc
     JOIN m_product mp ON pc.m_product_id = mp.m_product_id
     JOIN m_product_category mpc ON mpc.m_product_category_id = mp.m_product_category_id
     JOIN c_period cp ON cp.c_period_id = pc.c_period_id
     JOIN ad_org ao ON ao.ad_org_id = pc.ad_org_id
     JOIN ad_client ac ON ac.ad_client_id = pc.ad_client_id;


-- adempiere.t_tcsgeneralledgerview definition
-- Drop table
-- DROP TABLE adempiere.t_tcsgeneralledgerview;
CREATE TABLE adempiere.t_tcsgeneralledgerview (
	ad_client_id numeric(10) NOT NULL,
	ad_org_id numeric(10) NOT NULL,
	org_name bpchar(60) NOT NULL,
	c_acctschema_id numeric(10) NULL,
	acct_schema_name bpchar(60) NULL,
	account_id numeric(10) NULL,
	account_no bpchar(40) NULL,
	account_name bpchar(120) NULL,
	dateacct timestamp NULL,
	c_period_id numeric(10) NULL,
	period_name bpchar(60) NULL,
	postingtype bpchar(1) NULL,
	amtacctdr numeric NULL,
	amtacctcr numeric NULL,
	amtsourcecr numeric NULL,
	amtsourcedr numeric NULL,
	amtacctbalance numeric NULL,
	iso_code bpchar(3) NULL,
	description bpchar(255) NULL,
	ad_pinstance_id numeric(10) NULL,
	"sequence" numeric(10) NULL,
	gl_category_name varchar(60) NULL,
	accountto numeric NULL,
	accountfrom numeric NULL,
	documentno varchar(200) NULL DEFAULT NULL::character varying,
	line numeric(10) NULL DEFAULT NULL::numeric,
	c_bpartner_id numeric(10) NULL DEFAULT NULL::numeric,
	m_product_id numeric(10) NULL DEFAULT NULL::numeric,
	fact_acct_id numeric(10) NULL,
	gl_category_id numeric(10) NULL DEFAULT NULL::numeric,
	c_project_id numeric(10) NULL DEFAULT NULL::numeric,
	c_projectphase_id numeric(10) NULL DEFAULT NULL::numeric,
	c_projecttask_id numeric(10) NULL DEFAULT NULL::numeric,
	c_tax_id numeric(10) NULL DEFAULT NULL::numeric,
	m_locator_id numeric(10) NULL DEFAULT NULL::numeric,
	ad_orgtrx_id numeric(10) NULL DEFAULT NULL::numeric,
	c_campaign_id numeric(10) NULL DEFAULT NULL::numeric,
	c_activity_id numeric(10) NULL DEFAULT NULL::numeric,
	c_salesregion_id numeric(10) NULL DEFAULT NULL::numeric,
	c_locfrom_id numeric(10) NULL DEFAULT NULL::numeric,
	c_locto_id numeric(10) NULL DEFAULT NULL::numeric,
	c_subacct_id numeric(10) NULL DEFAULT NULL::numeric,
	a_asset_id numeric(10) NULL DEFAULT NULL::numeric,
	user1_id numeric(10) NULL DEFAULT NULL::numeric,
	user2_id numeric(10) NULL DEFAULT NULL::numeric,
	accountsequence numeric NULL,
	qty numeric NULL,
	isreversecorrect bpchar(1) NULL DEFAULT 'N'::bpchar
);
-- adempiere.t_tcsgeneralledgerview foreign keys
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT aasset_ttcsgeneralledgerview FOREIGN KEY (a_asset_id) REFERENCES adempiere.a_asset(a_asset_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT adorgtrx_ttcsgeneralledgerview FOREIGN KEY (ad_orgtrx_id) REFERENCES adempiere.ad_org(ad_org_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT cactivity_ttcsgeneralledgervie FOREIGN KEY (c_activity_id) REFERENCES adempiere.c_activity(c_activity_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT cbpartner_ttcsgeneralledgervie FOREIGN KEY (c_bpartner_id) REFERENCES adempiere.c_bpartner(c_bpartner_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT ccampaign_ttcsgeneralledgervie FOREIGN KEY (c_campaign_id) REFERENCES adempiere.c_campaign(c_campaign_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT clocfrom_ttcsgeneralledgerview FOREIGN KEY (c_locfrom_id) REFERENCES adempiere.c_location(c_location_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT clocto_ttcsgeneralledgerview FOREIGN KEY (c_locto_id) REFERENCES adempiere.c_location(c_location_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT cproject_ttcsgeneralledgerview FOREIGN KEY (c_project_id) REFERENCES adempiere.c_project(c_project_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT cprojectphase_ttcsgeneralledge FOREIGN KEY (c_projectphase_id) REFERENCES adempiere.c_projectphase(c_projectphase_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT cprojecttask_ttcsgeneralledger FOREIGN KEY (c_projecttask_id) REFERENCES adempiere.c_projecttask(c_projecttask_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT csalesregion_ttcsgeneralledger FOREIGN KEY (c_salesregion_id) REFERENCES adempiere.c_salesregion(c_salesregion_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT csubacct_ttcsgeneralledgerview FOREIGN KEY (c_subacct_id) REFERENCES adempiere.c_subacct(c_subacct_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT ctax_ttcsgeneralledgerview FOREIGN KEY (c_tax_id) REFERENCES adempiere.c_tax(c_tax_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT glcategory_ttcsgeneralledgervi FOREIGN KEY (gl_category_id) REFERENCES adempiere.gl_category(gl_category_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT mlocator_ttcsgeneralledgerview FOREIGN KEY (m_locator_id) REFERENCES adempiere.m_locator(m_locator_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT mproduct_ttcsgeneralledgerview FOREIGN KEY (m_product_id) REFERENCES adempiere.m_product(m_product_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT user1_ttcsgeneralledgerview FOREIGN KEY (user1_id) REFERENCES adempiere.c_elementvalue(c_elementvalue_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE adempiere.t_tcsgeneralledgerview ADD CONSTRAINT user2_ttcsgeneralledgerview FOREIGN KEY (user2_id) REFERENCES adempiere.c_elementvalue(c_elementvalue_id) DEFERRABLE INITIALLY DEFERRED;