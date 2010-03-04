/**
 * 
 */
package cbmarc.ginvoicing.client.event;

import cbmarc.ginvoicing.client.i18n.CategoriesConstants;
import cbmarc.ginvoicing.client.rpc.CategoriesService;
import cbmarc.ginvoicing.client.rpc.CategoriesServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEventBus extends HandlerManager {

	private static CategoriesServiceAsync service = 
		GWT.create(CategoriesService.class);
	private static CategoriesConstants constants = 
		GWT.create(CategoriesConstants.class);
	
	/**
     * Make the constructor private so it is not possible to create an instance.
     */
	private CategoriesEventBus() {
		super(null);
	}
	
	public static CategoriesServiceAsync getService() {
		return service;
	}
	
	public static CategoriesConstants getConstants() {
		return constants;
	}
	
}
