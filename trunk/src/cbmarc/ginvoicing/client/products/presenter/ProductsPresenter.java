/**
 * 
 */
package cbmarc.ginvoicing.client.products.presenter;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectHandler;
import cbmarc.ginvoicing.client.categories.presenter.CategoriesSelectPresenter;
import cbmarc.ginvoicing.client.categories.view.CategoriesSelectView;
import cbmarc.ginvoicing.client.products.event.ProductsEventBus;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ProductsPresenter implements Presenter, CategoriesSelectHandler {
	
	public interface Display {
		HasWidgets getContent();
		
		Widget asWidget();
	}
	
	protected final Display display;
	private ProductsEventBus eventBus = ProductsEventBus.getProductsEventBus();
	private CategoriesSelectView categoriesSelectView;
	private CategoriesSelectPresenter categoriesSelectPresenter;
	
	public ProductsPresenter(Display display) {
	    this.display = display;
	    
	    categoriesSelectView = new CategoriesSelectView();
	    categoriesSelectPresenter = new CategoriesSelectPresenter(
	    		eventBus, categoriesSelectView);
		
	    eventBus.addHandler(CategoriesSelectEvent.getType(), this);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		//categoriesListPresenter.go(display.getContent());
		categoriesSelectPresenter.go(display.getContent());
	    container.add(display.asWidget());
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void onCancel(CategoriesSelectEvent event) {
		Window.alert("CANCELADO DESDE PRODUCTS PRESENTER");
	}

	@Override
	public void onReload(CategoriesSelectEvent event) {
		Window.alert("onReload DESDE PRODUCTS PRESENTER");
	}

	@Override
	public void onTableClicked(CategoriesSelectEvent event, int row) {
		// TODO Auto-generated method stub
		
	}

}
