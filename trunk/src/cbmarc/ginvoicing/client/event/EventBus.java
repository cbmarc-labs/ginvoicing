/**
 * 
 */
package cbmarc.ginvoicing.client.event;

import cbmarc.ginvoicing.client.i18n.AppConstants;
import cbmarc.ginvoicing.client.i18n.AppMessages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class EventBus extends HandlerManager {

	private static EventBus instance = new EventBus();
	private static AppConstants constants = GWT.create(AppConstants.class);
	private static AppMessages messages = GWT.create(AppMessages.class);
	
	private EventBus() {
		super(null);
	}
	
	public static EventBus getEventBus() {
		return instance;
	}
	
	public static AppConstants getConstants() {
		return constants;
	}
	
	public static AppMessages getMessages() {
		return messages;
	}
}
