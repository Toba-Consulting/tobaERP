package org.idempiere.process;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MClient;
import org.compiere.model.MClientInfo;
import org.compiere.model.MStorageProvider;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 *	@author stephan
 *	save all attachment from database into file storage (storage provider)
 *	this process just for migration from database to storage provider
 */
@org.adempiere.base.annotation.Process
public class TaoAttachmentMigration extends SvrProcess{

	private int p_AD_StorageProvider_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) {
				;
			} else if (para[i].getParameterName().equalsIgnoreCase(
					"AD_StorageProvider_ID")) {
				p_AD_StorageProvider_ID = para[i].getParameterAsInt();
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		if(p_AD_StorageProvider_ID == 0)
			return "Please select storage provider";
		
		MStorageProvider prov = new MStorageProvider(getCtx(), p_AD_StorageProvider_ID, get_TrxName());
		if(prov.getFolder().isEmpty())
			return "Please fill folder in storage provider";
		
		//	location storage provider
		String locationFolder = prov.getFolder();
		
		//	get all attachment
		String sql = "SELECT AD_Attachment_ID FROM AD_Attachment WHERE AD_Client_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Client_ID());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//	generate xml for change binary data
				StringBuilder xml = new StringBuilder();
				xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				xml.append("<attachments>");
				
				MAttachment att = new MAttachment(getCtx(), rs.getInt(1), get_TrxName());
				String pattern = att.getAD_Client_ID()+"/"+att.getAD_Org_ID()+"/"+att.getAD_Table_ID()+"/"+att.getRecord_ID()+"/";
				
				MAttachmentEntry[] entrys = att.getEntries();
				for (MAttachmentEntry entry : entrys) {
					if (entry != null && entry.getData() != null)
					{
						FileOutputStream fos = null;
						try
						{
							String destination = locationFolder+pattern;
							
							//	generate folder compatible with pattern storage provider
							File file = new File(destination + entry.getName());
							file.getParentFile().mkdirs();
							
							//	write into file
							fos = new FileOutputStream(file);
							fos.write(entry.getData());
							
							xml.append("<entry file=\"%ATTACHMENT_FOLDER%");
							xml.append(pattern + entry.getName()+"\" ");
							xml.append("name=\"" + entry.getName()+"\"/>");
							
						}
						catch (Exception e)
						{
							return "Error :"+e;
						}
						finally{
							fos.close();
						}
					}
				}
				
				xml.append("</attachments>");
				
				try{
					StringBuilder up = new StringBuilder();
					up.append("UPDATE AD_Attachment SET BinaryData='"+xml.toString()
							+"',Title = 'xml' WHERE AD_Attachment_ID="+att.getAD_Attachment_ID());
					DB.executeUpdateEx(up.toString(), get_TrxName());
				}catch(Exception e){
					return "Error :"+e;
				}
				
			}
		}catch(Exception e){
			return "Error :"+e;
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		//	set attachment storage in client info
		MClient client = new MClient(getCtx(), getAD_Client_ID(), get_TrxName());
		MClientInfo clientInfo = client.getInfo();
		clientInfo.setAD_StorageProvider_ID(p_AD_StorageProvider_ID);
		clientInfo.saveEx();
		
		return "Complete migration into "+prov.getName();
	}

}
