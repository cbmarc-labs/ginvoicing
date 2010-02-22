/**
 * 
 */
package cbmarc.ginvoicing.client.categories.view;

import cbmarc.ginvoicing.client.categories.event.CategoriesEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesHandler;
import cbmarc.ginvoicing.client.categories.presenter.CategoriesPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesView extends Composite 
		implements CategoriesPresenter.Display {
	interface uiBinder extends UiBinder<Widget, CategoriesView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField Panel content;
	
	public CategoriesView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}
	
	public HandlerRegistration addHandler(CategoriesHandler handler) {
		return addHandler(handler, CategoriesEvent.getType());
	}

	@Override
	public HasWidgets getContent() {
		return content;
	}
}
