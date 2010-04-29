/**
 * 
 */
package cbmarc.ginvoicing.client.view.suppliers;

import cbmarc.ginvoicing.client.view.IView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class SuppliersViewImpl extends Composite implements IView {
	
	@UiTemplate("SuppliersView.ui.xml")
	interface SuppliersViewUiBinder extends UiBinder<Widget, SuppliersViewImpl> {}
	private static SuppliersViewUiBinder uiBinder =	GWT.create(SuppliersViewUiBinder.class);
		
	@SuppressWarnings("unused")
	private Presenter presenter;
	
	/**
	 * 
	 */
	public SuppliersViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
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
