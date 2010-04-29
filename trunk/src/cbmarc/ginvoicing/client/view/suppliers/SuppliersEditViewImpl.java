/**
 * 
 */
package cbmarc.ginvoicing.client.view.suppliers;

import cbmarc.ginvoicing.client.view.IEditView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class SuppliersEditViewImpl extends Composite implements IEditView {
	
	@UiTemplate("SuppliersEditView.ui.xml")
	interface SuppliersEditViewUiBinder extends UiBinder<Widget, SuppliersEditViewImpl> {}
	private static SuppliersEditViewUiBinder uiBinder =	GWT.create(SuppliersEditViewUiBinder.class);
	
	@UiField Button listButton;
	
	private Presenter presenter;
	
	/**
	 * 
	 */
	public SuppliersEditViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("listButton")
	void onListButtonClicked(ClickEvent event) {
		if (presenter != null) {
			presenter.onListButtonClicked();
		}
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
