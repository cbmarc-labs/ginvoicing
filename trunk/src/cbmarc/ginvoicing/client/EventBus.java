/**
 * 
 */
package cbmarc.ginvoicing.client;

import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class EventBus extends HandlerManager {

	private static EventBus instance = new EventBus();
	
	private EventBus() {
		super(null);
	}
	
	public static EventBus getInstance() {
		return instance;
	}
}
