/**
 * 
 */
package cbmarc.ginvoicing.client.view.invoices;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class InvoicesViewImpl extends Composite implements InvoicesView {

	@UiTemplate("InvoicesView.ui.xml")
	interface uiBinder extends UiBinder<Widget, InvoicesViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField HasWidgets content;
		
	public InvoicesViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasWidgets getContent() {
		return content;
	}
}
