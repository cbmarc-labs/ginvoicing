/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.categories.CategoriesEditViewImpl;
import cbmarc.ginvoicing.client.view.categories.CategoriesListViewImpl;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesPresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	protected final Display display;
	
	private CategoriesListPresenter categoriesListPresenter;
	private CategoriesEditPresenter categoriesEditPresenter;
	
	public CategoriesPresenter(Display display) {
	    this.display = display;
	    
	    categoriesListPresenter = new CategoriesListPresenter(
	    		new CategoriesListViewImpl());
	    categoriesEditPresenter = new CategoriesEditPresenter(
	    		new CategoriesEditViewImpl());
		
	    bind();
	}
	
	private void bind() {}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		processHistoryToken();
	    container.add(display.asWidget());
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = categoriesListPresenter;
			
			if(token.startsWith("main/categories/edit")) {
				presenter = categoriesEditPresenter;
			}

			presenter.go(display.getContent());
		}
	}

}
