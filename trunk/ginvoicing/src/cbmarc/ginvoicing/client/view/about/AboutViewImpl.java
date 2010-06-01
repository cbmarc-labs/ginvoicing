/**
 * 
 */
package cbmarc.ginvoicing.client.view.about;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class AboutViewImpl extends Composite implements AboutView {

	@UiTemplate("AboutView.ui.xml")
	interface uiBinder extends UiBinder<Widget, AboutViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	public AboutViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}
	
}
