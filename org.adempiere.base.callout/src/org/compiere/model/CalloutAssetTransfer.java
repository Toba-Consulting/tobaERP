package org.compiere.model;

import java.util.Properties;

import org.adempiere.model.GridTabWrapper;
import org.compiere.util.Env;

public class CalloutAssetTransfer extends CalloutEngine{

	public String asset (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		I_A_AssetTransfer assetTransfer = GridTabWrapper.create(mTab, I_A_AssetTransfer.class);
		MAsset asset = new MAsset(Env.getCtx(), assetTransfer.getA_Asset_ID(), null);
		assetTransfer.setA_Asset_Group_ID(asset.getA_Asset_Group_ID());
		assetTransfer.setAD_Org_ID(asset.getAD_Org_ID());
		assetTransfer.setNew_Asset_Group_ID(asset.getA_Asset_Group_ID());
		return null;
	}
}
