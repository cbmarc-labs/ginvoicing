/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.ProductsEditView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ProductsPresenter 
		implements Presenter, ValueChangeHandler<String> {
	
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
		History.addValueChangeHandler(this);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if(token != null) {
			Presenter presenter = null;
			
			if(token.equals("main/products")) {
				presenter = productsEditPresenter;
			} else if(token.equals("main/products/edit")) {
				presenter = productsEditPresenter;
			}

			if(presenter != null) {
				presenter.go(display.getContent());
			}
		}
	}

}
