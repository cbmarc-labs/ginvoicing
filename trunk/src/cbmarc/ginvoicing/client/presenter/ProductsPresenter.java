/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.products.ProductsEditViewImpl;
import cbmarc.ginvoicing.client.view.products.ProductsListViewImpl;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ProductsPresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	protected final Display display;
	
	private ProductsListPresenter productsListPresenter;
	private ProductsEditPresenter productsEditPresenter;
	
	public ProductsPresenter(Display display) {
	    this.display = display;
	    
	    productsListPresenter = new ProductsListPresenter(new ProductsListViewImpl());
	    productsEditPresenter = new ProductsEditPresenter(new ProductsEditViewImpl());
	    
	    bind();
	}
	
	private void bind() {
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		processHistoryToken();
	    container.add(display.asWidget());
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = productsListPresenter;
			
			if(token.startsWith("main/products/edit")) {
				presenter = productsEditPresenter;
			}

			presenter.go(display.getContent());
		}
	}

}
