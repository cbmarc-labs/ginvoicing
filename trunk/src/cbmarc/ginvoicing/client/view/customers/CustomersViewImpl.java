/**
 * 
 */
package cbmarc.ginvoicing.client.view.customers;

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
public class CustomersViewImpl extends Composite 
		implements CustomersView {

	@UiTemplate("CustomersView.ui.xml")
	interface uiBinder extends UiBinder<Widget, CustomersViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField HasWidgets content;
	
	@SuppressWarnings("unused")
	private Presenter presenter = null;
		
	public CustomersViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasWidgets getContent() {
		return content;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
