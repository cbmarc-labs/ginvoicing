/**
 * 
 */
package cbmarc.ginvoicing.client;

import cbmarc.ginvoicing.client.categories.presenter.CategoriesPresenter;
import cbmarc.ginvoicing.client.categories.view.CategoriesView;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.MainEvent;
import cbmarc.ginvoicing.client.event.MainHandler;
import cbmarc.ginvoicing.client.home.event.HomeEvent;
import cbmarc.ginvoicing.client.home.event.HomeHandler;
import cbmarc.ginvoicing.client.home.presenter.HomePresenter;
import cbmarc.ginvoicing.client.home.view.HomeView;
import cbmarc.ginvoicing.client.products.presenter.ProductsPresenter;
import cbmarc.ginvoicing.client.products.view.ProductsView;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainPresenter implements Presenter, MainHandler {
	
	public interface Display {
		public HandlerRegistration addHandler(MainHandler handler);
		
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private final Display display;
	private EventBus eventBus = EventBus.getEventBus();
	
	private HomePresenter homePresenter;
	private CategoriesPresenter categoriesPresenter;
	private ProductsPresenter productsPresenter;
	
	public MainPresenter(Display view) {
	    this.display = view;
	    
	    homePresenter = new HomePresenter(new HomeView());
	    categoriesPresenter = new CategoriesPresenter(new CategoriesView());
	    productsPresenter = new ProductsPresenter(new ProductsView());
	    
	    bind();
	}
	
	private void bind() {
		display.addHandler(this);
		
		eventBus.addHandler(MainEvent.getType(), this);
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void categories(MainEvent event) {
		categoriesPresenter.go(display.getContent());
	}

	@Override
	public void products(MainEvent event) {
		productsPresenter.go(display.getContent());
	}

	@Override
	public void home(MainEvent event) {
		homePresenter.go(display.getContent());
	}
	
}
