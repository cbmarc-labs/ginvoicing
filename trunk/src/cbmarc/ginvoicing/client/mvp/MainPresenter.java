/**
 * 
 */
package cbmarc.ginvoicing.client.mvp;


import cbmarc.ginvoicing.client.mvp.categories.CategoriesPresenter;
import cbmarc.ginvoicing.client.mvp.categories.CategoriesView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainPresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private final HandlerManager eventBus;
	private final Display display;
	
	private CategoriesPresenter categoriesPresenter;
	
	public MainPresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    
	    categoriesPresenter = new CategoriesPresenter(
	    		eventBus, new CategoriesView());
	    
	    bind();
	}
	
	public void bind() {
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		categoriesPresenter.go(display.getContent());
		
	    container.add(display.asWidget());
	}
}
