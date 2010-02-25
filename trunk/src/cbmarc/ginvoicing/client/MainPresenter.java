/**
 * 
 */
package cbmarc.ginvoicing.client;

import cbmarc.ginvoicing.client.categories.presenter.CategoriesPresenter;
import cbmarc.ginvoicing.client.categories.view.CategoriesView;
import cbmarc.ginvoicing.client.products.presenter.ProductsPresenter;
import cbmarc.ginvoicing.client.products.view.ProductsView;

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
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private HasWidgets container;
	private final Display display;
	
	private CategoriesPresenter categoriesPresenter;
	private ProductsPresenter productsPresenter;
	
	public MainPresenter(Display view) {
	    this.display = view;
	    
	    categoriesPresenter = new CategoriesPresenter(new CategoriesView());
	    productsPresenter = new ProductsPresenter(new ProductsView());
	    
	    bind();
	}
	
	private void bind() {
		History.addValueChangeHandler(this);
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if(token != null) {
			Presenter presenter = null;
			
			if(token.equals("categories")) {
				presenter = categoriesPresenter;
			} else if (token.equals("products")) {
				presenter = productsPresenter;
			}

			if(presenter != null) {
				presenter.go(display.getContent());
				
				container.add(display.asWidget());
			}
		}
	}
}
