/**
 * 
 */
package cbmarc.ginvoicing.client.event;

import cbmarc.ginvoicing.client.i18n.ProductsConstants;
import cbmarc.ginvoicing.client.rpc.ProductsService;
import cbmarc.ginvoicing.client.rpc.ProductsServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class ProductsEventBus extends HandlerManager {

	private static ProductsServiceAsync service = 
		GWT.create(ProductsService.class);
	private static ProductsConstants constants = 
		GWT.create(ProductsConstants.class);
	
	/**
     * Make the constructor private so it is not possible to create an instance.
     */
	private ProductsEventBus() {
		super(null);
	}
	
	public static ProductsServiceAsync getService() {
		return service;
	}
	
	public static ProductsConstants getConstants() {
		return constants;
	}
	
}
