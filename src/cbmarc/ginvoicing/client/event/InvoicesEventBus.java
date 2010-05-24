/**
 * 
 */
package cbmarc.ginvoicing.client.event;

import cbmarc.ginvoicing.client.i18n.InvoicesConstants;
import cbmarc.ginvoicing.client.rpc.InvoicesService;
import cbmarc.ginvoicing.client.rpc.InvoicesServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class InvoicesEventBus extends HandlerManager {

	private static InvoicesServiceAsync service = 
		GWT.create(InvoicesService.class);
	private static InvoicesConstants constants = 
		GWT.create(InvoicesConstants.class);
	
	/**
     * Make the constructor private so it is not possible to create an instance.
     */
	private InvoicesEventBus() {
		super(null);
	}
	
	public static InvoicesServiceAsync getService() {
		return service;
	}
	
	public static InvoicesConstants getConstants() {
		return constants;
	}
	
}
