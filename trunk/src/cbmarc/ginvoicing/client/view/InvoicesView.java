/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import cbmarc.ginvoicing.client.presenter.InvoicesPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class InvoicesView extends Composite 
		implements InvoicesPresenter.Display {
	
	interface uiBinder extends UiBinder<Widget, InvoicesView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
		
	public InvoicesView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}
}
