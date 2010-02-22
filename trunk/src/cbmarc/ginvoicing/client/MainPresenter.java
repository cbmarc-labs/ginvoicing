/**
 * 
 */
package cbmarc.ginvoicing.client;

import cbmarc.ginvoicing.client.categories.presenter.CategoriesPresenter;
import cbmarc.ginvoicing.client.categories.view.CategoriesView;

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
	
	private final Display display;
	private CategoriesPresenter categoriesPresenter;
	
	public MainPresenter(Display view) {
	    this.display = view;
	    
	    categoriesPresenter = new CategoriesPresenter(new CategoriesView());
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		categoriesPresenter.go(display.getContent());
	    container.add(display.asWidget());
	}
}
