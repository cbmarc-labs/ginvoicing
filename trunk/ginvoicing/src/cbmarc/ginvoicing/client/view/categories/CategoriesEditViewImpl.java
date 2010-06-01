/**
 * 
 */
package cbmarc.ginvoicing.client.view.categories;

import cbmarc.ginvoicing.client.ui.LoadingPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEditViewImpl extends Composite
		implements CategoriesEditView {
	
	@UiTemplate("CategoriesEditView.ui.xml")
	interface uiBinder extends UiBinder<Widget, CategoriesEditViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField LoadingPanel loadingPanel;
	@UiField Panel formPanel;
	@UiField TextBox name;
	@UiField HasValue<String> description;
	
	private Presenter presenter = null;
	
	public CategoriesEditViewImpl() {
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
	}

	@Override
	public void focus() {
		name.setFocus(true);
	}

	@Override
	public HasValue<String> getDescription() {
		return description;
	}

	@Override
	public HasValue<String> getName() {
		return name;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Panel getFormPanel() {
		return formPanel;
	}

	@Override
	public LoadingPanel getLoadingPanel() {
		return loadingPanel;
	}
	
}
