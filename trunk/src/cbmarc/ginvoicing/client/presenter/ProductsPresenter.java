/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.ProductsEditView;
import cbmarc.ginvoicing.client.view.ProductsListView;

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
	    
	    productsListPresenter = new ProductsListPresenter(new ProductsListView());
	    productsEditPresenter = new ProductsEditPresenter(new ProductsEditView());
	    
	    bind();
	}
	
	private void bind() {
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void processHistoryToken(String token) {
		if(token != null) {
			Presenter presenter = productsListPresenter;
			
			if(token.startsWith("main/products/edit")) {
				presenter = productsEditPresenter;
			}

			presenter.go(display.getContent());
		}
	}

}
