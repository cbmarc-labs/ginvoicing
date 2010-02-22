/**
 * 
 */
package cbmarc.ginvoicing.client.categories.presenter;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.event.CategoriesEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.categories.event.CategoriesHandler;
import cbmarc.ginvoicing.client.categories.view.CategoriesEditView;
import cbmarc.ginvoicing.client.categories.view.CategoriesListView;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesPresenter implements Presenter, CategoriesHandler {
	
	public interface Display {
		public HandlerRegistration addHandler(CategoriesHandler handler);
		
		HasWidgets getContent();
		
		Widget asWidget();
	}
	
	private CategoriesEventBus eventBus = CategoriesEventBus.getInstance();
	protected final Display display;
	
	private CategoriesListView categoriesListView;
	private CategoriesEditView categoriesEditView;
	
	private CategoriesListPresenter categoriesListPresenter;
	private CategoriesEditPresenter categoriesEditPresenter;
	
	public CategoriesPresenter(Display display) {
	    this.display = display;
	    
	    categoriesListView = new CategoriesListView();
	    categoriesEditView = new CategoriesEditView();
	    
	    categoriesListPresenter = new CategoriesListPresenter(categoriesListView);
	    categoriesEditPresenter = new CategoriesEditPresenter(categoriesEditView);
		
	    eventBus.addHandler(CategoriesEvent.getType(), this);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		categoriesListPresenter.go(display.getContent());
	    container.add(display.asWidget());
	}

	@Override
	public void onEdit(CategoriesEvent event) {
		categoriesEditPresenter.go(display.getContent());
	}

	@Override
	public void onList(CategoriesEvent event) {
		categoriesListPresenter.go(display.getContent());
	}

}
