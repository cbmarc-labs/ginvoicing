/**
 * 
 */
package cbmarc.ginvoicing.client.event;

import cbmarc.ginvoicing.client.i18n.LinesConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class LinesEventBus extends HandlerManager {

	private static LinesConstants constants = 
		GWT.create(LinesConstants.class);
	
	/**
     * Make the constructor private so it is not possible to create an instance.
     */
	private LinesEventBus() {
		super(null);
	}
	
	public static LinesConstants getConstants() {
		return constants;
	}
	
}
