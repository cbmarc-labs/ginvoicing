/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import cbmarc.ginvoicing.client.presenter.HomePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
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
}
