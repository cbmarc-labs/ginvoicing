/**
 * 
 */
package cbmarc.ginvoicing.client.products.event;

import cbmarc.ginvoicing.client.products.ProductsService;
import cbmarc.ginvoicing.client.products.ProductsServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class ProductsEventBus extends HandlerManager {

	private static ProductsServiceAsync service = GWT.create(ProductsService.class);
	//private static ProductsConstants constants = GWT.create(ProductsConstants.class);
	
	private ProductsEventBus() {
		super(null);
	}
	
	public static ProductsServiceAsync getService() {
		return service;
	}
	
}
