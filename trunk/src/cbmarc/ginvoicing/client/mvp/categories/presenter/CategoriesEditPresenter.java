/**
 * 
 */
package cbmarc.ginvoicing.client.mvp.categories.presenter;

import cbmarc.ginvoicing.client.mvp.Presenter;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesCreatedEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesEditCancelledEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesSaveEvent;
import cbmarc.ginvoicing.client.mvp.categories.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEditPresenter implements Presenter {
	
	public interface Display {
		HasClickHandlers getListButton();
		
		TextBox getName();
		
		HasClickHandlers getSubmitButton();
		HasClickHandlers getCancelButton();
		
		void reset();
		
		Widget asWidget();
	}
	
	private final CategoriesServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private Categories bean;
	
	private final HandlerManager localEventBus;
	
	/**
	 * @param rpcService
	 * @param eventBus
	 * @param view
	 */
	public CategoriesEditPresenter(CategoriesServiceAsync rpcService, 
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	    
	    bean = new Categories();
	    localEventBus = new HandlerManager(null);
	    
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {
		display.getListButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CategoriesEditCancelledEvent());
			}
	    	
	    });
		
		display.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CategoriesEditCancelledEvent());
			}
			
		});
		
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CategoriesSaveEvent());
			}
			
		});
	}
	
	/**
	 * @return
	 */
	public Categories getBean() {
		return bean;
	}

	/**
	 * @param bean
	 */
	public void setBean(Categories bean) {
		this.bean = bean;
	}
	
	/**
	 * 
	 */
	private void fillBean() {
		this.bean.setName(display.getName().getValue());
	}

	public boolean doSave() {
		fillBean();
		
		rpcService.save(bean, new AsyncCallback<Categories>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save failed: " + caught.toString());
			}

			@Override
			public void onSuccess(Categories result) {
				eventBus.fireEvent(new CategoriesCreatedEvent());
			}
			
		});
		
		return true;
	}
	
	/**
	 * 
	 */
	private void fillDisplay() {
		display.getName().setValue(bean.getName());
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();

		display.reset();
		if(bean.getId() != null) fillDisplay();
		
	    container.add(display.asWidget());
	    display.getName().setFocus(true);
	}

}
