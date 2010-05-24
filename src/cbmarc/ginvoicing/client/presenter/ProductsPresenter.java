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
	
	private static final String VIEW = MainPresenter.VIEW_PRODUCTS;
	
	private static ProductsListViewImpl productsListView = null;
	private static ProductsEditViewImpl productsEditView = null;
	
	private final ProductsView view;

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
			if (token.startsWith(VIEW + "/edit")) {
				String[] parts = token.split("/");
				String id = null; 
				
				if(productsEditView == null)
					productsEditView = new ProductsEditViewImpl();
				
				// is a edit statement?
				if(parts.length > 3) id = parts[parts.length - 1];
				
				new ProductsEditPresenter(productsEditView, id).
					go(view.getContent());
			} else {
				if(productsListView == null)
					productsListView = new ProductsListViewImpl();
				
				new ProductsListPresenter(productsListView).
					go(view.getContent());
			}
		}
	}

}
