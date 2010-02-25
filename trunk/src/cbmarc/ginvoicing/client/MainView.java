/**
 * 
 */
package cbmarc.ginvoicing.client;

import cbmarc.ginvoicing.client.event.MainEvent;
import cbmarc.ginvoicing.client.event.MainHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainView extends Composite implements MainPresenter.Display {
	
	interface uiBinder extends UiBinder<Widget, MainView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Panel content;
	
	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}
	
	@UiHandler("homeAnchor")
	protected void homeClicked(ClickEvent event) {
		fireEvent(MainEvent.home());
	}
	
	@UiHandler("categoriesAnchor")
	protected void categoriesClicked(ClickEvent event) {
		fireEvent(MainEvent.categories());
	}
	
	@UiHandler("productsAnchor")
	protected void productsClicked(ClickEvent event) {
		fireEvent(MainEvent.products());
	}
	
	public HandlerRegistration addHandler(MainHandler handler) {
		return addHandler(handler, MainEvent.getType());
	}

	@Override
	public HasWidgets getContent() {
		return content;
	}
}
