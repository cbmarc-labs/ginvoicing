/**
 * 
 */
package cbmarc.ginvoicing.client.categories.event;

import cbmarc.ginvoicing.client.categories.CategoriesConstants;
import cbmarc.ginvoicing.client.categories.CategoriesService;
import cbmarc.ginvoicing.client.categories.CategoriesServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEventBus extends HandlerManager {

	private static CategoriesEventBus instance = new CategoriesEventBus();
	private static CategoriesServiceAsync service = GWT.create(CategoriesService.class);
	private static CategoriesConstants constants = GWT.create(CategoriesConstants.class);
	
	private CategoriesEventBus() {
		super(null);
	}
	
	public static CategoriesEventBus getEventBus() {
		return instance;
	}
	
	public static CategoriesServiceAsync getService() {
		return service;
	}
	
	public static CategoriesConstants getConstants() {
		return constants;
	}
}
