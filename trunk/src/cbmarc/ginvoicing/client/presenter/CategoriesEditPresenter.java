/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.i18n.AppMessages;
import cbmarc.ginvoicing.client.i18n.CategoriesConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.client.view.categories.CategoriesEditView;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.Category;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEditPresenter
		implements Presenter, CategoriesEditView.Presenter {
	
	private final CategoriesEditView view;
	
	private CategoriesServiceAsync service = CategoriesEventBus.getService();
	private CategoriesConstants constants = CategoriesEventBus.getConstants();
	private AppMessages messages = EventBus.getMessages();
	
	private Category category;
	
	public CategoriesEditPresenter(CategoriesEditView view, String id) {
	    this.view = view;
	    view.setPresenter(this);
	    
	    category = new Category();
	    if(id != null) doLoad(id);
	    	else updateDisplayFromData();
	}
	
	/**
	 * @return
	 */
	protected boolean hasValidInput() {
		boolean valid = true;
		StringBuilder sb = new StringBuilder();
		
		if (!FieldVerifier.isValidString(view.getName().getValue())) {
			sb.append(messages.errorField(constants.formName()) + "\n");
			valid = false;
		}
		
		sb.append("   ");
		if(valid == false)
			Window.alert(sb.toString());
		
		return valid;
	}

	/**
	 * @return
	 */
	private void doSave() {
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
			}
			
		});
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		view.focus();
	}
	
	public void updateDataFromDisplay() {
		category.setName(view.getName().getValue());
		category.setDescription(view.getDescription().getValue());
	}
	
	public void updateDisplayFromData() {
		view.reset();
		
		view.getName().setValue(category.getName());
		view.getDescription().setValue(category.getDescription());
	}

	@Override
	public void onCancelButtonClicked() {
		History.newItem("main/categories");
	}

	@Override
	public void onListButtonClicked() {
		History.newItem("main/categories");
	}

	@Override
	public void onSubmitButtonClicked() {
		if(hasValidInput()) doSave();
	}

}
