/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.CategoriesEvent;
import cbmarc.ginvoicing.client.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.event.CategoriesHandler;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.client.view.CategoriesEditView;
import cbmarc.ginvoicing.client.view.CategoriesListView;
import cbmarc.ginvoicing.shared.entity.Category;

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
	
	protected final Display display;
	
	private EventBus eventBus = EventBus.getEventBus();
	private CategoriesServiceAsync service = CategoriesEventBus.getService();
	
	private CategoriesListPresenter categoriesListPresenter;
	private CategoriesEditPresenter categoriesEditPresenter;
	
	public CategoriesPresenter(Display display) {
	    this.display = display;
	    
	    categoriesListPresenter = new CategoriesListPresenter(new CategoriesListView());
	    categoriesEditPresenter = new CategoriesEditPresenter(new CategoriesEditView());
		
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
	    container.add(display.asWidget());
		
		categoriesListPresenter.go(display.getContent());
	}

	@Override
	public void onEdit(CategoriesEvent event, String id) {
		if(id == null) {
			categoriesEditPresenter.setCategories(new Category());
			categoriesEditPresenter.go(display.getContent());
		} else {
			service.selectById(id, new AppAsyncCallback<Category>() {

				@Override
				public void onSuccess(Category result) {
					categoriesEditPresenter.setCategories(result);
					categoriesEditPresenter.go(display.getContent());
				}
				
			});
		}
	}

	@Override
	public void onList(CategoriesEvent event) {
		categoriesListPresenter.go(display.getContent());
	}

}
