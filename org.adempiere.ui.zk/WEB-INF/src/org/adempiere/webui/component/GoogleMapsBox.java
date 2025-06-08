package org.adempiere.webui.component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.adempiere.webui.LayoutUtils;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;


public class GoogleMapsBox extends Div {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5570402908948501444L;
	protected PropertyChangeSupport m_propertyChangeListeners = new PropertyChangeSupport(this);
	protected Textbox txt;
	protected A btn;
	
	
	public GoogleMapsBox(){
		initComponents();
	}
	
	public GoogleMapsBox(String GoogleMapsCoordinates)
    {
    	initComponents();
        setGoogleMapsCoordinates(GoogleMapsCoordinates);
    }
	
	 private void initComponents() {
			txt = new Textbox();
			appendChild(txt);
			txt.setHflex("0");
			txt.setSclass("editor-input");

			btn = new A();
			btn.setTarget("_blank");
			btn.setTabindex(-1);
			btn.setSclass("editor-button");
			btn.setZclass("z-button-os");
			btn.setHflex("0");
			appendChild(btn);

			LayoutUtils.addSclass("editor-box", this);
			setTableEditorMode(false);
		}
	 
	 /**
		 * @param imageSrc
		 */
		public void setButtonImage(String imageSrc) {
			btn.setImage(imageSrc);
		}
		
	    /**
		 * @return textbox component
		 */
		public Textbox getTextbox() {
			return txt;
		}

		/**
		 * @param value
		 */
		public void setGoogleMapsCoordinates(String value) {
			txt.setText(value);
			String GoogleMapsCoordinates = null;
			if (value == null) {
				GoogleMapsCoordinates = "about:blank";
			} else {
				GoogleMapsCoordinates = "http://local.google.com/maps?q="+value.trim();
				if (GoogleMapsCoordinates.length() == 0) {
					GoogleMapsCoordinates = "about:blank";
				}
			}
			btn.setHref(GoogleMapsCoordinates);
		}

		/**
		 * @return text
		 */
		public String getText() {
			return txt.getText();
		}
		
		public void setEnabled(boolean enabled) {
	    	txt.setReadonly(!enabled);
	    	if (enabled) {
				LayoutUtils.removeSclass("editor-input-disd", txt);
			} else {
				LayoutUtils.addSclass("editor-input-disd", txt);
			}
		}
		
		/**
		 * @return boolean
		 */
		public boolean isEnabled() {
			return !txt.isReadonly();
		}

		/**
		 * @param evtnm
		 * @param listener
		 */
		public boolean addEventListener(String evtnm, EventListener<?> listener) {
			if (Events.ON_CLICK.equals(evtnm)) {
				return btn.addEventListener(evtnm, listener);
			} else {
				return txt.addEventListener(evtnm, listener);
			}
		}
		
		/**
		 * @param l
		 */
		public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
			m_propertyChangeListeners.addPropertyChangeListener(l);
		}

		/**
		 * @param tooltiptext
		 */
		public void setToolTipText(String tooltiptext) {
			txt.setTooltiptext(tooltiptext);
		}
		
		/**
		 * @return A
		 */
		public A getButton() {
			return btn;
		}
		
		public void setTableEditorMode(boolean flag) {
			if (flag) {
				setHflex("0");
				LayoutUtils.addSclass("grid-editor-input", txt);
				LayoutUtils.addSclass("grid-editor-button", btn);
			} else {
				setHflex("1");
				LayoutUtils.removeSclass("grid-editor-input", txt);
				LayoutUtils.removeSclass("grid-editor-button", btn);
			}
				
		}
}
