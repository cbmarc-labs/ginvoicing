/**
 * 
 */
package cbmarc.ginvoicing.client.view.about;

import cbmarc.ginvoicing.client.presenter.AboutPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class AboutView extends Composite 
		implements AboutPresenter.Display {
		
	interface uiBinder extends UiBinder<Widget, AboutView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	public AboutView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}
	
}
