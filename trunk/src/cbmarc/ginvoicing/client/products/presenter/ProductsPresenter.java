/**
 * 
 */
package cbmarc.ginvoicing.client.products.presenter;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.products.view.ProductsEditView;

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
	private ProductsEditPresenter productsEditPresenter;
	
	public ProductsPresenter(Display display) {
	    this.display = display;
	    
	    productsEditPresenter = new ProductsEditPresenter(new ProductsEditView());
	    
	    bind();
	}
	
	private void bind() {
		// TODO implements view eventbus
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		productsEditPresenter.go(display.getContent());
	    container.add(display.asWidget());
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

}
