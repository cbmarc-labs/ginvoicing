/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.products.ProductsEditViewImpl;
import cbmarc.ginvoicing.client.view.products.ProductsListViewImpl;
import cbmarc.ginvoicing.client.view.products.ProductsView;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class ProductsPresenter implements Presenter, ProductsView.Presenter {
	
	private final ProductsView view;
	
	private ProductsListPresenter productsListPresenter = new ProductsListPresenter(new ProductsListViewImpl());
	private ProductsEditPresenter productsEditPresenter = new ProductsEditPresenter(new ProductsEditViewImpl());

	/**
	 * @param view
	 */
	public ProductsPresenter(ProductsView view) {
		this.view = view;
	}

	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		processHistoryToken();
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = productsListPresenter;
			
			if(token.startsWith("main/products/edit")) {
				presenter = productsEditPresenter;
			}

			presenter.go(view.getContent());
		}
	}

}
