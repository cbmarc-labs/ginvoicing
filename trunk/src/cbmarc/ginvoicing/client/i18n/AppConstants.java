/**
 * 
 */
package cbmarc.ginvoicing.client.i18n;

import com.google.gwt.i18n.client.Constants;

/**
 * @author MCOSTA
 *
 */
public interface AppConstants extends Constants {
	String appTitle(); // application title
	String appVersion();
	String appCopyright();
	
	String menuHome();
	String menuCategories();
	String menuProducts();
	String menuCustomers();
	String menuInvoices();

	String loading();

	String reloadButton();
	String addButton();
	String delButton();
	String listButton();
	String submitButton();
	String cancelButton();
	
	String errorServer();
	
	String areYouSure();
	
	String noItemsSelected();
	String noData();
}
