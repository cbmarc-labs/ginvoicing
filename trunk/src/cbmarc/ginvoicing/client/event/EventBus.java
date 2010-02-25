/**
 * 
 */
package cbmarc.ginvoicing.client.event;

import cbmarc.ginvoicing.client.i18n.AppConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;

/**
 * @author MCOSTA
 *
 */
public class EventBus extends HandlerManager {

	private static EventBus instance = new EventBus();
	private static Constants constants = GWT.create(AppConstants.class);
	
	private EventBus() {
		super(null);
	}
	
	public static EventBus getEventBus() {
		return instance;
	}
	
	public static Constants getConstants() {
		return constants;
	}
}
