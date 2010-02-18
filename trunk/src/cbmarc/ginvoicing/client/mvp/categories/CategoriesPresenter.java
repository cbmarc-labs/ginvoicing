/**
 * 
 */
package cbmarc.ginvoicing.client.mvp.categories;

import cbmarc.ginvoicing.client.i18n.AppConstants;
import cbmarc.ginvoicing.client.mvp.Presenter;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesAddEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesAddEventHandler;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesCreatedEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesCreatedEventHandler;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesDeleteEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesDeleteEventHandler;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesEditCancelledEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesEditCancelledEventHandler;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesEditEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesEditEventHandler;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesSaveEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesSaveEventHandler;
import cbmarc.ginvoicing.client.mvp.categories.presenter.CategoriesEditPresenter;
import cbmarc.ginvoicing.client.mvp.categories.presenter.CategoriesListPresenter;
import cbmarc.ginvoicing.client.mvp.categories.rpc.CategoriesService;
import cbmarc.ginvoicing.client.mvp.categories.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.client.mvp.categories.view.CategoriesEditView;
import cbmarc.ginvoicing.client.mvp.categories.view.CategoriesListView;
import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	
	final private AppConstants appConstants;
	
	private final CategoriesServiceAsync rpcService;
	private final HandlerManager eventBus;
	protected final Display display;
	
	protected final CategoriesListPresenter list;
	protected final CategoriesEditPresenter edit;
	
	/**
	 * @param eventBus
	 * @param view
	 */
	public CategoriesPresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    this.rpcService = GWT.create(CategoriesService.class);
	    
	    appConstants = GWT.create(AppConstants.class);
		list = new CategoriesListPresenter(
				rpcService, eventBus, new CategoriesListView());
		edit = new CategoriesEditPresenter(
				rpcService, eventBus, new CategoriesEditView());
		
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {		
		eventBus.addHandler(CategoriesAddEvent.TYPE,
				new CategoriesAddEventHandler() {
			public void onAdd(CategoriesAddEvent event) {
				doAdd();
			}
	    });
		
		eventBus.addHandler(CategoriesEditCancelledEvent.TYPE,
				new CategoriesEditCancelledEventHandler() {

			@Override
			public void onEditCancelled(CategoriesEditCancelledEvent event) {
				list.go(display.getContent());
			}
			
	    });
		
		eventBus.addHandler(CategoriesCreatedEvent.TYPE, 
				new CategoriesCreatedEventHandler() {

			@Override
			public void onCreated(CategoriesCreatedEvent event) {
				list.go(display.getContent());
			}
			
		});
		
		eventBus.addHandler(CategoriesEditEvent.TYPE, 
				new CategoriesEditEventHandler() {

			@Override
			public void onEdit(CategoriesEditEvent event) {
				doEdit(event.getKey());
			}
			
		});
		
		eventBus.addHandler(CategoriesDeleteEvent.TYPE, 
				new CategoriesDeleteEventHandler() {

			@Override
			public void onDelete(CategoriesDeleteEvent event) {
				list.deleteSelected();
			}
			
		});
		
		eventBus.addHandler(CategoriesSaveEvent.TYPE, 
				new CategoriesSaveEventHandler() {

			@Override
			public void onSave(CategoriesSaveEvent event) {
				edit.doSave();
			}
			
		});
	}
	
	/**
	 * 
	 */
	protected void doAdd() {
		edit.setBean(new Categories());
		edit.go(display.getContent());
	}
	
	/**
	 * @param id
	 */
	private void doEdit(String id) {
		rpcService.selectById(id, new AsyncCallback<Categories>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(appConstants.errorFeching());
			}

			@Override
			public void onSuccess(Categories bean) {
				if(bean != null) {
					edit.setBean(bean);
					edit.go(display.getContent());
				} else {
					Window.alert(appConstants.errorFeching());
				}
			}
			
		});
	}

	/**
	 * @return the list
	 */
	public CategoriesListPresenter getList() {
		return list;
	}

	/**
	 * @return the edit
	 */
	public CategoriesEditPresenter getEdit() {
		return edit;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		list.go(display.getContent());
	    container.add(display.asWidget());
	}

}
