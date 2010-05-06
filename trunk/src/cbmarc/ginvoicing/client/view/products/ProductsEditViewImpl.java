/**
 * 
 */
package cbmarc.ginvoicing.client.view.products;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.Category;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ProductsEditViewImpl extends Composite 
		implements ProductsEditView {
	
	@UiTemplate("ProductsEditView.ui.xml")
	interface uiBinder extends UiBinder<Widget, ProductsEditViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField Panel loadingPanel;
	@UiField Panel formPanel;
	@UiField TextBox name;
	@UiField HasValue<String> description;
	@UiField ListBox categoryList;
	@UiField HasValue<String> price;
	
	private Presenter presenter = null;
	
	public ProductsEditViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		return this;
	}

	@UiHandler("listButton")
	protected void listClicked(ClickEvent event) {
		if(presenter != null) {
			presenter.onListButtonClicked();
		}
	}

	@UiHandler("categoriesReloadButton")
	protected void categoriesClicked(ClickEvent event) {
		if(presenter != null) {
			presenter.onCategoriesReloadButtonClicked();
		}
	}

	@UiHandler("submitButton")
	protected void submitClicked(ClickEvent event) {
		if(presenter != null) {
			presenter.onSubmitButtonClicked();
		}
	}

	@UiHandler("cancelButton")
	protected void cancelClicked(ClickEvent event) {
		if(presenter != null) {
			presenter.onCancelButtonClicked();
		}
	}

	@UiHandler("resetButton")
	protected void resetClicked(ClickEvent event) {
		reset();
		focus();
	}

	@Override
	public void reset() {
		name.setValue("");
		description.setValue("");
		categoryList.setSelectedIndex(0);
		price.setValue("0.0");
	}

	@Override
	public HasValue<String> getName() {
		return name;
	}

	@Override
	public HasValue<String> getDescription() {
		return description;
	}

	@Override
	public HasValue<String> getPrice() {
		return price;
	}

	@Override
	public void focus() {
		name.setFocus(true);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public String getCategory() {
		String result = null;
		int index = categoryList.getSelectedIndex();
		
		try { result = categoryList.getValue(index); } catch(Exception e) {};
		
		return result;
	}

	@Override
	public void setCategoryList(List<Category> items, String selected) {
		int index = 0;
		
		categoryList.setEnabled(false);
		categoryList.clear();
		for(Category item : items) {
			categoryList.addItem(item.getName(), item.getId());
			
			if(item.getId().equals(selected))
				categoryList.setItemSelected(index, true);
			
			index ++;
		}
		
		if(categoryList.getItemCount() > 0)
			categoryList.setEnabled(true);
	}

	@Override
	public Panel getFormPanel() {
		return formPanel;
	}

	@Override
	public Panel getLoadingPanel() {
		return loadingPanel;
	}
}
