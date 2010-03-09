/**
 * 
 */
package cbmarc.ginvoicing.client.event;

import cbmarc.ginvoicing.client.i18n.LinesConstants;
import cbmarc.ginvoicing.client.rpc.LinesService;
import cbmarc.ginvoicing.client.rpc.LinesServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class LinesEventBus extends HandlerManager {

	private static LinesServiceAsync service = 
		GWT.create(LinesService.class);
	private static LinesConstants constants = 
		GWT.create(LinesConstants.class);
	
	/**
     * Make the constructor private so it is not possible to create an instance.
     */
	private LinesEventBus() {
		super(null);
	}
	
	public static LinesServiceAsync getService() {
		return service;
	}
	
	public static LinesConstants getConstants() {
		return constants;
	}
	
}
