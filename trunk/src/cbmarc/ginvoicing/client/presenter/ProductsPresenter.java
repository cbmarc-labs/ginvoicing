/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.products.ProductsEditViewImpl;
import cbmarc.ginvoicing.client.view.products.ProductsListViewImpl;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class ProductsPresenter implements Presenter {
	
	private HasWidgets container;
	
	private ProductsListPresenter productsListPresenter = 
		new ProductsListPresenter(new ProductsListViewImpl());
	private ProductsEditPresenter productsEditPresenter = 
		new ProductsEditPresenter(new ProductsEditViewImpl());

	@Override
	public void go(final HasWidgets container) {
		this.container = container;
		
		processHistoryToken();
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = productsListPresenter;
			
			if(token.startsWith("main/products/edit")) {
				presenter = productsEditPresenter;
			}

			presenter.go(container);
		}
	}

}
