/**
 * 
 */
package cbmarc.ginvoicing.client.products.event;

import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class ProductsEventBus extends HandlerManager {

	private static ProductsEventBus instance = new ProductsEventBus();
	//private static ProductsServiceAsync service = GWT.create(ProductsService.class);
	//private static ProductsConstants constants = GWT.create(ProductsConstants.class);
	
	private ProductsEventBus() {
		super(null);
	}
	
	public static ProductsEventBus getProductsEventBus() {
		return instance;
	}
	
	/*public static CategoriesServiceAsync getService() {
		return service;
	}
	
	public static CategoriesConstants getConstants() {
		return constants;
	}*/
}
