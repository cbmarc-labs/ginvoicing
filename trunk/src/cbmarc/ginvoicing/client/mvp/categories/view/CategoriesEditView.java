/**
 * 
 */
package cbmarc.ginvoicing.client.mvp.categories.view;


import cbmarc.ginvoicing.client.mvp.categories.presenter.CategoriesEditPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEditView extends Composite 
		implements CategoriesEditPresenter.Display {
	interface uiBinder extends UiBinder<Widget, CategoriesEditView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField HasClickHandlers listButton;
	
	@UiField TextBox name;
	@UiField HasValue<String> description;
	
	@UiField HasClickHandlers submitButton;
	@UiField HasClickHandlers cancelButton;
	
	public CategoriesEditView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return this.cancelButton;
	}

	@Override
	public HasClickHandlers getListButton() {
		return this.listButton;
	}

	@Override
	public HasClickHandlers getSubmitButton() {
		return this.submitButton;
	}

	@Override
	public void reset() {
		this.name.setValue("");
		this.description.setValue("");
	}

	@Override
	public TextBox getName() {
		return this.name;
	}

	/**
	 * @return the nombre
	 */
	public final HasValue<String> getDescription() {
		return description;
	}
}
