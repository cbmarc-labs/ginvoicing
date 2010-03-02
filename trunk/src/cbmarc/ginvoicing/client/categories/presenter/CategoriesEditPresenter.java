/**
 * 
 */
package cbmarc.ginvoicing.client.categories.presenter;

import cbmarc.ginvoicing.client.AppAsyncCallback;
import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.CategoriesServiceAsync;
import cbmarc.ginvoicing.client.categories.event.CategoriesEditEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesEditHandler;
import cbmarc.ginvoicing.client.categories.event.CategoriesEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.Category;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEditPresenter 
		implements Presenter, CategoriesEditHandler {
	
	public interface Display {
		String getName();
		void setName(String value);
		
		String getDescription();
		void setDescription(String value);
		
		public void focus();
		public void reset();
		
		public HandlerRegistration addHandler(CategoriesEditHandler handler);
		Widget asWidget();
	}
	
	private EventBus eventBus = EventBus.getEventBus();
	private CategoriesServiceAsync service = CategoriesEventBus.getService();
	private final Display display;
	
	private Category category = new Category();
	
	public CategoriesEditPresenter(Display view) {
	    this.display = view;
		
		bind();
	}
	
	private void bind() {
		display.addHandler(this);
	}
	
	/**
	 * @return
	 */
	public Category getCategories() {
		return category;
	}

	/**
	 * @param bean
	 */
	public void setCategories(Category category) {
		this.category = category;
	}
	
	/**
	 * @return
	 */
	protected boolean hasValidInput() {
		boolean valid = true;
		
		// TODO validate all fields
		// First, we validate the input.
		if (!FieldVerifier.isValidName(display.getName())) {
			Window.alert("Please enter at least four characters on name field.");
			
			return false;
		}
		
		return valid;
	}

	/**
	 * @return
	 */
	public void doSave() {
		updateDataFromDisplay();
		
		service.save(category, new AppAsyncCallback<Category>() {

			@Override
			public void onSuccess(Category result) {
				eventBus.fireEvent(CategoriesEditEvent.submit());
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());

		updateDisplayFromData();
		display.focus();
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		category.setName(display.getName());
		category.setDescription(display.getDescription());
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		display.setName(category.getName());
		display.setDescription(category.getDescription());
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
		if(hasValidInput()) doSave();
	}

}
