/**
 * 
 */
package cbmarc.ginvoicing.client.event;

import cbmarc.ginvoicing.client.i18n.CustomersConstants;
import cbmarc.ginvoicing.client.rpc.CustomersService;
import cbmarc.ginvoicing.client.rpc.CustomersServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class CustomersEventBus extends HandlerManager {

	private static CustomersServiceAsync service = GWT.create(CustomersService.class);
	private static CustomersConstants constants = GWT.create(CustomersConstants.class);
	
	/**
     * Make the constructor private so it is not possible to create an instance.
     */
	private CustomersEventBus() {
		super(null);
	}
	
	public static CustomersServiceAsync getService() {
		return service;
	}
	
	public static CustomersConstants getConstants() {
		return constants;
	}
	
}
