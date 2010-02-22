/**
 * 
 */
package cbmarc.ginvoicing.client.categories.event;

import cbmarc.ginvoicing.client.categories.rpc.CategoriesService;
import cbmarc.ginvoicing.client.categories.rpc.CategoriesServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEventBus extends HandlerManager {

	private static CategoriesEventBus instance = new CategoriesEventBus();
	private static CategoriesServiceAsync service = 
		GWT.create(CategoriesService.class);
	
	private CategoriesEventBus() {
		super(null);
	}
	
	public static CategoriesEventBus getInstance() {
		return instance;
	}
	
	public static CategoriesServiceAsync getService() {
		return service;
	}
}
