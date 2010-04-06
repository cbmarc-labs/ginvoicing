/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.CategoriesEditView;
import cbmarc.ginvoicing.client.view.CategoriesListView;

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
	    
	    categoriesListPresenter = new CategoriesListPresenter(new CategoriesListView());
	    categoriesEditPresenter = new CategoriesEditPresenter(new CategoriesEditView());
		
	    bind();
	}
	
	private void bind() {}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}

	@Override
	public void processHistoryToken(String token) {
		if(token != null) {
			Presenter presenter = categoriesListPresenter;
			
			if(token.startsWith("main/categories/edit")) {
				presenter = categoriesEditPresenter;
			}

			presenter.go(display.getContent());
		}
	}

}
