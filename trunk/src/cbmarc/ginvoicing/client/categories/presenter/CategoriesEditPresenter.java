/**
 * 
 */
package cbmarc.ginvoicing.client.categories.presenter;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.CategoriesServiceAsync;
import cbmarc.ginvoicing.client.categories.event.CategoriesEditEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesEditHandler;
import cbmarc.ginvoicing.client.categories.event.CategoriesEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesEventBus;
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
	
	private Categories categories = new Categories();
	
	public CategoriesEditPresenter(Display view) {
	    this.display = view;
		
		display.addHandler(this);
	}
	
	/**
	 * @return
	 */
	public Categories getCategories() {
		return categories;
	}

	/**
	 * @param bean
	 */
	public void setCategories(Categories categories) {
		this.categories = categories;
	}
	
	/**
	 * @return
	 */
	protected boolean hasValidInput() {
		boolean valid = true;
		
		// First, we validate the input.
		if (!FieldVerifier.isValidName(display.getName().getValue())) {
			Window.alert("Please enter at least four characters on name field.");
			
			return false;
		}
		
		return valid;
	}

	/**
	 * @return
	 */
	public boolean doSave() {
		if(!hasValidInput()) return false;
		
		updateDataFromDisplay();
		
		service.save(categories, new AsyncCallback<Categories>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save failed: " + caught.toString());
			}

			@Override
			public void onSuccess(Categories result) {
				eventBus.fireEvent(CategoriesEditEvent.submit());
			}
			
		});
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();

		display.reset();
		if(categories.getId() != null)
			updateDisplayFromData();
		
	    container.add(display.asWidget());
	    display.getName().setFocus(true);
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		categories.setName(display.getName().getValue());
		// TODO
		// bean.setName(display.getName());
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.getName().setValue(categories.getName());
		// TODO
		// display.setName(bean.getName());
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
