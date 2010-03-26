/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.Category;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEditPresenter 
		implements Presenter, SubmitCancelHandler {
	
	public interface Display {
		String getName();
		void setName(String value);
		
		String getDescription();
		void setDescription(String value);
		
		public void focus();
		public void reset();
		
		public HandlerRegistration addSubmitCancelHandler(
				SubmitCancelHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private CategoriesServiceAsync service = CategoriesEventBus.getService();
	private Category category = new Category();
	
	public CategoriesEditPresenter(Display view) {
	    this.display = view;
		
		bind();
	}
	
	private void bind() {
		display.addSubmitCancelHandler(this);
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
		
		service.save(category, new AppAsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				History.newItem("main/categories");
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void doLoad(String id) {
		service.selectById(id, new AppAsyncCallback<Category>() {

			@Override
			public void onSuccess(Category result) {
				category = result;
				updateDisplayFromData();
				display.focus();
			}
			
		});
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		category = new Category();
	    
	    String[] parts = History.getToken().split("/");
	    if(parts.length > 3)
	    	doLoad(parts[parts.length - 1]);

		updateDisplayFromData();
		container.add(display.asWidget());
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

	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.event.SubmitCancelHandler#onCancel(cbmarc.ginvoicing.client.event.SubmitCancelEvent)
	 */
	@Override
	public void onCancel(SubmitCancelEvent event) {
		History.newItem("main/categories");
	}

	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.event.SubmitCancelHandler#onSubmit(cbmarc.ginvoicing.client.event.SubmitCancelEvent)
	 */
	@Override
	public void onSubmit(SubmitCancelEvent event) {
		if(hasValidInput())
			doSave();
	}

	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.presenter.Presenter#processHistoryToken(java.lang.String)
	 */
	@Override
	public void processHistoryToken(String token) {
		// Nothing to do.
	}

}
