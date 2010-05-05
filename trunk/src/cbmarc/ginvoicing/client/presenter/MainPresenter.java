/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.MainView;
import cbmarc.ginvoicing.client.view.about.AboutViewImpl;
import cbmarc.ginvoicing.client.view.categories.CategoriesViewImpl;
import cbmarc.ginvoicing.client.view.customers.CustomersViewImpl;
import cbmarc.ginvoicing.client.view.invoices.InvoicesViewImpl;
import cbmarc.ginvoicing.client.view.products.ProductsViewImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class MainPresenter implements Presenter, MainView.Presenter {
	
	public static final String VIEW_MAIN = "main";
	public static final String VIEW_ABOUT = VIEW_MAIN + "/about";
	public static final String VIEW_INVOICES = VIEW_MAIN + "/invoices";
	public static final String VIEW_CUSTOMERS = VIEW_MAIN + "/customers";
	public static final String VIEW_PRODUCTS = VIEW_MAIN + "/products";
	public static final String VIEW_CATEGORIES = VIEW_MAIN + "/categories";
	
	private final MainView view;
	
    private InvoicesViewImpl invoicesView = null;
    private CustomersViewImpl customersView = null;
    private ProductsViewImpl productsView = null;
    private CategoriesViewImpl categoriesView = null;
	private AboutViewImpl aboutView = null;
	
	public MainPresenter(MainView view) {
	    this.view = view;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		processHistoryToken();
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {			
			if (token.startsWith(VIEW_ABOUT)) {
				view.setActiveTab(null);
				GWT.runAsync(new RunAsyncCallback() {

					@Override
					public void onFailure(Throwable reason) {
						Window.alert("runAsync ERROR");
					}

					@Override
					public void onSuccess() {
						if(aboutView == null) 
							aboutView = new AboutViewImpl();
						
						new AboutPresenter(aboutView).go(view.getContentPanel());
					}
					
				});
			} else if(token.startsWith(VIEW_CATEGORIES)) {
				view.setActiveTab(3);
				GWT.runAsync(new RunAsyncCallback() {

					@Override
					public void onFailure(Throwable reason) {
						Window.alert("runAsync ERROR");
					}

					@Override
					public void onSuccess() {
						if(categoriesView == null) 
							categoriesView = new CategoriesViewImpl();
						
						new CategoriesPresenter(categoriesView).go(view.getContentPanel());
					}
					
				});
			} else if(token.startsWith(VIEW_CUSTOMERS)) {
				view.setActiveTab(1);
				GWT.runAsync(new RunAsyncCallback() {

					@Override
					public void onFailure(Throwable reason) {
						Window.alert("runAsync ERROR");
					}

					@Override
					public void onSuccess() {
						if(customersView == null) 
							customersView = new CustomersViewImpl();
						
						new CustomersPresenter(customersView).go(view.getContentPanel());
					}
					
				});
			} else if(token.startsWith(VIEW_INVOICES)) {
				view.setActiveTab(0);
				GWT.runAsync(new RunAsyncCallback() {

					@Override
					public void onFailure(Throwable reason) {
						Window.alert("runAsync ERROR");
					}

					@Override
					public void onSuccess() {
						if(invoicesView == null) 
							invoicesView = new InvoicesViewImpl();
						
						new InvoicesPresenter(invoicesView).go(view.getContentPanel());
					}
					
				});
			} else if(token.startsWith(VIEW_PRODUCTS)) {
				view.setActiveTab(2);
				GWT.runAsync(new RunAsyncCallback() {

					@Override
					public void onFailure(Throwable reason) {
						Window.alert("runAsync ERROR");
					}

					@Override
					public void onSuccess() {
						if(productsView == null) 
							productsView = new ProductsViewImpl();
						
						new ProductsPresenter(productsView).go(view.getContentPanel());
					}
					
				});
			} 
		}
	}
	
}
