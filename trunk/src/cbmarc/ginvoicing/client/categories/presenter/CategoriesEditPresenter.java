/**
 * 
 */
package cbmarc.ginvoicing.client.categories.presenter;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.event.CategoriesEditEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesEditHandler;
import cbmarc.ginvoicing.client.categories.event.CategoriesEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.categories.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEditPresenter 
		implements Presenter, CategoriesEditHandler {
	
	public interface Display {
		TextBox getName();
		
		void reset();
		public HandlerRegistration addHandler(CategoriesEditHandler handler);
		Widget asWidget();
	}
	
	private CategoriesEventBus eventBus = CategoriesEventBus.getInstance();
	private CategoriesServiceAsync service = CategoriesEventBus.getService();
	private final Display display;
	
	private Categories bean = new Categories();
	
	public CategoriesEditPresenter(Display view) {
	    this.display = view;
		
		display.addHandler(this);
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
	private void updateDataFromDisplay() {
		this.bean.setName(display.getName().getValue());
	}
	
	public boolean validateInput() {
		boolean valid = true;
		
		// First, we validate the input.
		if (!FieldVerifier.isValidName(display.getName().getValue())) {
			Window.alert("Please enter at least four characters on name field.");
			
			return false;
		}
		
		return valid;
	}

	public boolean doSave() {
		if(!validateInput()) return false;
		
		updateDataFromDisplay();
		
		service.save(bean, new AsyncCallback<Categories>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save failed: " + caught.toString());
			}

			@Override
			public void onSuccess(Categories result) {
				History.newItem("categorieslist");
			}
			
		});
		
		return true;
	}
	
	/**
	 * 
	 */
	private void updateDisplayFromData() {
		display.getName().setValue(bean.getName());
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();

		display.reset();
		if(bean.getId() != null) updateDisplayFromData();
		
	    container.add(display.asWidget());
	    display.getName().setFocus(true);
	}

	@Override
	public void onCancel(CategoriesEditEvent event) {
		eventBus.fireEvent(CategoriesEvent.listPanel());
	}

	@Override
	public void onList(CategoriesEditEvent event) {
		eventBus.fireEvent(CategoriesEvent.listPanel());
	}

	@Override
	public void onSubmit(CategoriesEditEvent event) {
		Window.alert("onSubmit");
	}

}
