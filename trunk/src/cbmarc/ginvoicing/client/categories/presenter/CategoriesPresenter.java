/**
 * 
 */
package cbmarc.ginvoicing.client.categories.presenter;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.event.CategoriesEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesHandler;
import cbmarc.ginvoicing.client.categories.view.CategoriesEditView;
import cbmarc.ginvoicing.client.categories.view.CategoriesListView;
import cbmarc.ginvoicing.client.categories.view.CategoriesSelectView;
import cbmarc.ginvoicing.client.event.EventBus;

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
	
	private EventBus eventBus = EventBus.getEventBus();
	protected final Display display;
	
	private CategoriesListPresenter categoriesListPresenter;
	private CategoriesEditPresenter categoriesEditPresenter;
	private CategoriesSelectPresenter categoriesSelectPresenter;
	
	public CategoriesPresenter(Display display) {
	    this.display = display;
	    
	    categoriesListPresenter = new CategoriesListPresenter(new CategoriesListView());
	    categoriesEditPresenter = new CategoriesEditPresenter(new CategoriesEditView());
	    categoriesSelectPresenter = new CategoriesSelectPresenter(new CategoriesSelectView());
		
	    bind();
	}
	
	private void bind() {
		display.addHandler(this);
		
		eventBus.addHandler(CategoriesEvent.getType(), this);
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

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
