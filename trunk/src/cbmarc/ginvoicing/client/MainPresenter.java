/**
 * 
 */
package cbmarc.ginvoicing.client;

import cbmarc.ginvoicing.client.categories.presenter.CategoriesPresenter;
import cbmarc.ginvoicing.client.categories.view.CategoriesView;
import cbmarc.ginvoicing.client.customers.presenter.CustomersPresenter;
import cbmarc.ginvoicing.client.customers.view.CustomersView;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.MainEvent;
import cbmarc.ginvoicing.client.event.MainHandler;
import cbmarc.ginvoicing.client.home.presenter.HomePresenter;
import cbmarc.ginvoicing.client.home.view.HomeView;
import cbmarc.ginvoicing.client.invoices.presenter.InvoicesPresenter;
import cbmarc.ginvoicing.client.invoices.view.InvoicesView;
import cbmarc.ginvoicing.client.products.presenter.ProductsPresenter;
import cbmarc.ginvoicing.client.products.view.ProductsView;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainPresenter implements Presenter, MainHandler {
	
	public interface Display {
		public HandlerRegistration addHandler(MainHandler handler);
		
		public void setTabLinkActive(int link);
		
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private final Display display;
	private EventBus eventBus = EventBus.getEventBus();
	
	private HomePresenter homePresenter;
	private CategoriesPresenter categoriesPresenter;
	private ProductsPresenter productsPresenter;
	private CustomersPresenter customersPresenter;
	private InvoicesPresenter invoicesPresenter;
	
	public MainPresenter(Display view) {
	    this.display = view;
	    
	    homePresenter = new HomePresenter(new HomeView());
	    categoriesPresenter = new CategoriesPresenter(new CategoriesView());
	    productsPresenter = new ProductsPresenter(new ProductsView());
	    customersPresenter = new CustomersPresenter(new CustomersView());
	    invoicesPresenter = new InvoicesPresenter(new InvoicesView());
	    
	    bind();
	}
	
	private void bind() {
		display.addHandler(this);
		
		eventBus.addHandler(MainEvent.getType(), this);
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		
		homePresenter.go(display.getContent());
	}

	@Override
	public void categories(MainEvent event) {
		display.setTabLinkActive(1);
		categoriesPresenter.go(display.getContent());
	}

	@Override
	public void products(MainEvent event) {
		display.setTabLinkActive(2);
		productsPresenter.go(display.getContent());
	}

	@Override
	public void home(MainEvent event) {
		display.setTabLinkActive(0);
		homePresenter.go(display.getContent());
	}

	@Override
	public void customers(MainEvent event) {
		display.setTabLinkActive(3);
		customersPresenter.go(display.getContent());
	}

	@Override
	public void invoices(MainEvent event) {
		display.setTabLinkActive(4);
		invoicesPresenter.go(display.getContent());
	}
	
}
