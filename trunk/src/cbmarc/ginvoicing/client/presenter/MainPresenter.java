/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.CategoriesView;
import cbmarc.ginvoicing.client.view.CustomersView;
import cbmarc.ginvoicing.client.view.HomeView;
import cbmarc.ginvoicing.client.view.InvoicesView;
import cbmarc.ginvoicing.client.view.ProductsView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainPresenter implements Presenter, ValueChangeHandler<String> {
	
	public interface Display {
		public void setTabLinkActive(int link);
		
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private final Display display;
	
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
		History.addValueChangeHandler(this);
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if(token != null) {
			Presenter presenter = null;
			
			if(token.startsWith("main/home")) {
				display.setTabLinkActive(0);
				presenter = homePresenter;
			} else if(token.startsWith("main/categories")) {
				display.setTabLinkActive(1);
				presenter = categoriesPresenter;
			} else if(token.startsWith("main/products")) {
				display.setTabLinkActive(2);
				presenter = productsPresenter;
			} else if(token.startsWith("main/customers")) {
				display.setTabLinkActive(3);
				presenter = customersPresenter;
			} else if(token.startsWith("main/invoices")) {
				display.setTabLinkActive(4);
				presenter = invoicesPresenter;
			}

			if(presenter != null) {
				presenter.go(display.getContent());
			}
		}
	}
	
}
