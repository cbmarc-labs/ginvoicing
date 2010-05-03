/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.categories.CategoriesEditViewImpl;
import cbmarc.ginvoicing.client.view.categories.CategoriesListViewImpl;
import cbmarc.ginvoicing.client.view.categories.CategoriesView;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class CategoriesPresenter 
		implements Presenter, CategoriesView.Presenter {
	
	private final CategoriesView view;
	
	private CategoriesListPresenter categoriesListPresenter = new CategoriesListPresenter(new CategoriesListViewImpl());
	private CategoriesEditPresenter categoriesEditPresenter = new CategoriesEditPresenter(new CategoriesEditViewImpl());
	
	/**
	 * @param view
	 */
	public CategoriesPresenter(CategoriesView view) {
		this.view = view;
	}

	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		processHistoryToken();
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = categoriesListPresenter;
			
			if(token.startsWith("main/categories/edit")) {
				presenter = categoriesEditPresenter;
			}

			presenter.go(view.getContent());
		}
	}

}
