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
	
	private static final String VIEW = MainPresenter.VIEW_CATEGORIES;
	
	private static CategoriesListViewImpl categoriesListView = null;
	private static CategoriesEditViewImpl categoriesEditView = null;
	
	private final CategoriesView view;
	
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
			if (token.startsWith(VIEW + "/edit")) {
				String[] parts = token.split("/");
				String id = null; 
				
				if(categoriesEditView == null)
					categoriesEditView = new CategoriesEditViewImpl();
				
				// is a edit statement?
				if(parts.length > 3) id = parts[parts.length - 1];
				
				new CategoriesEditPresenter(categoriesEditView, id).
					go(view.getContent());
			} else {
				if(categoriesListView == null)
					categoriesListView = new CategoriesListViewImpl();
				
				new CategoriesListPresenter(categoriesListView).
					go(view.getContent());
			}
		}
	}
	
}
