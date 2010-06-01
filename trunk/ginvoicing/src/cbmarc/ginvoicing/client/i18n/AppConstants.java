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
	
	String menuCategories();
	String menuInvoices();
	String menuCustomers();
	String menuProducts();
	String menuSuppliers();

	String loading();

	String reloadButton();
	String addButton();
	String delButton();
	String listButton();
	String filterButton();
	String submitButton();
	String cancelButton();
	String resetButton();
	
	String errorServer();
	String limitExceeded();
	String areYouSure();
	
	String warningDeleteItems();
	
	String noItemsSelected();
	String noData();
	
	String itemsLabel();
	
	String about();
}
