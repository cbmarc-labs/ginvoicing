/**
 * 
 */
package cbmarc.ginvoicing.client.customers.view;

import cbmarc.ginvoicing.client.customers.presenter.CustomersPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CustomersView extends Composite 
		implements CustomersPresenter.Display {
	
	interface uiBinder extends UiBinder<Widget, CustomersView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
		
	public CustomersView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}
}
