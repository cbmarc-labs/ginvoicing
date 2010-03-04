/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import cbmarc.ginvoicing.client.event.HomeEvent;
import cbmarc.ginvoicing.client.event.HomeHandler;
import cbmarc.ginvoicing.client.presenter.HomePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class HomeView extends Composite implements HomePresenter.Display {
	
	interface uiBinder extends UiBinder<Widget, HomeView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
		
	public HomeView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}
	
	@UiHandler("homeAnchor")
	protected void homeClicked(ClickEvent event) {
		fireEvent(HomeEvent.home());
	}
	
	@UiHandler("categoriesAnchor")
	protected void categoriesAnchorClicked(ClickEvent event) {
		fireEvent(HomeEvent.categories());
	}
	
	@UiHandler("productsAnchor")
	protected void productsAnchorClicked(ClickEvent event) {
		fireEvent(HomeEvent.products());
	}
	
	public HandlerRegistration addHandler(HomeHandler handler) {
		return addHandler(handler, HomeEvent.getType());
	}
}
