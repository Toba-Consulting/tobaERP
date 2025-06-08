/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.util.Properties;

/**
 *	Warehouse Callouts
 *	
 *  @author Edwin Ang
 *  @version $Id: CalloutWarehouse.java,v 1.0 2015/05/26 00:51:02 edwinang Exp $
 */
public class CalloutWarehouse extends CalloutEngine
{
	/**
	 *  In Transit Warehouse
	 *  when warehouse is transit warehouse, it will not have zoning
	 *	@param ctx context
	 *	@param WindowNo window no
	 *	@param mTab tab
	 *	@param mField field
	 *	@param value value
	 *	@return null or error message
	 */
	public String inTransit (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())		//	assuming it is resetting value
			return "";

		boolean isUseWHZoning = mTab.getValueAsBoolean(MWarehouse.COLUMNNAME_IsUseWHZoning);
		boolean isTransit = mTab.getValueAsBoolean(MWarehouse.COLUMNNAME_IsInTransit);
		
		if (mField.getColumnName().equals(MWarehouse.COLUMNNAME_IsInTransit) ||
				mField.getColumnName().equals(MWarehouse.COLUMNNAME_IsUseWHZoning)) {

			if (isTransit && isUseWHZoning) {
				mTab.setValue(MWarehouse.COLUMNNAME_IsUseWHZoning, false);
			}
		}
		return "";
		
	}	//	Warehouse - in Transit

}	//	CalloutWarehouse
