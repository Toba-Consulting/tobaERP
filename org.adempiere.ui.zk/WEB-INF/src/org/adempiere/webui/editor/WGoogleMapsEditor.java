package org.adempiere.webui.editor;

import org.adempiere.webui.ValuePreference;
import org.adempiere.webui.component.GoogleMapsBox;
import org.adempiere.webui.event.ContextMenuEvent;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.window.WFieldRecordInfo;
import org.compiere.model.GridField;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

public class WGoogleMapsEditor  extends WEditor implements ContextMenuListener {

	private static final String[] LISTENER_EVENTS = {Events.ON_CHANGE, Events.ON_OK};
	private String oldValue;

	

	public WGoogleMapsEditor( GridField gridField) {
		super(new GoogleMapsBox(), gridField);
		getComponent().setButtonImage(ThemeManager.getThemeResource("images/GM.png"));
		
		popupMenu = new WEditorPopupMenu(false, false, isShowPreference());
		popupMenu.addMenuListener(this);
		addChangeLogMenu(popupMenu);
	}

	@Override
	public void onEvent(Event event) throws Exception {
		if (Events.ON_CHANGE.equals(event.getName()) || Events.ON_OK.equals(event.getName()))
		{
			String newValue = getComponent().getText();
			if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
	    	    return;
	    	}
	        if (oldValue == null && newValue == null) {
	        	return;
	        }
			ValueChangeEvent changeEvent = new ValueChangeEvent(this, this.getColumnName(), oldValue, newValue);
			fireValueChange(changeEvent);
			oldValue = newValue;
		}
		
	}

	@Override
	public void onMenu(ContextMenuEvent evt) {
		if (WEditorPopupMenu.CHANGE_LOG_EVENT.equals(evt.getContextEvent()))
		{
			WFieldRecordInfo.start(gridField);
		}
		else if (WEditorPopupMenu.PREFERENCE_EVENT.equals(evt.getContextEvent()))
		{
			if (isShowPreference())
				ValuePreference.start(getComponent(), getGridField(), getValue());
		}
		
	}

	@Override
	public void setReadWrite(boolean readWrite) {
		getComponent().setEnabled(readWrite);
	}

	@Override
	public boolean isReadWrite() {
		return getComponent().isEnabled();
	}
	
	@Override
	public GoogleMapsBox getComponent() {
		return (GoogleMapsBox) component;
	}
	

	@Override
	public void setValue(Object value) {
		 if (value == "")
	        {
	        	oldValue = "";
	            getComponent().setGoogleMapsCoordinates("");
	        }
	        else
	        {
	        	oldValue = String.valueOf(value);
	            getComponent().setGoogleMapsCoordinates(oldValue);
	        }
	}

	@Override
	public Object getValue() {
		return getComponent().getText();
	}

	@Override
	public String getDisplay() {
		
		return getComponent().getText();
	}
	
	public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }
	
	@Override
	public void setTableEditor(boolean b) {
		super.setTableEditor(b);
		getComponent().setTableEditorMode(b);
	}	
}
