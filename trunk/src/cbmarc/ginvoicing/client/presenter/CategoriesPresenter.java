/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.categories.CategoriesEditViewImpl;
import cbmarc.ginvoicing.client.view.categories.CategoriesListViewImpl;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class CategoriesPresenter implements Presenter {
	
	private HasWidgets container;
	
	private CategoriesListPresenter categoriesListPresenter = 
		new CategoriesListPresenter(new CategoriesListViewImpl());
	private CategoriesEditPresenter categoriesEditPresenter =
		new CategoriesEditPresenter(new CategoriesEditViewImpl());
		
	@Override
	public void go(final HasWidgets container) {
		this.container = container;

		processHistoryToken();
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = categoriesListPresenter;
			
			if(token.startsWith("main/categories/edit")) {
				presenter = categoriesEditPresenter;
			}

			presenter.go(container);
		}
	}

}
