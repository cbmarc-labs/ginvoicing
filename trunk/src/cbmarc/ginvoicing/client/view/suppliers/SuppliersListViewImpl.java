/**
 * 
 */
package cbmarc.ginvoicing.client.view.suppliers;

import cbmarc.ginvoicing.client.view.IListView;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

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
public class SuppliersListViewImpl extends Composite 
		implements IListView<EntityDisplay> {
	
	@UiTemplate("SuppliersListView.ui.xml")
	interface SuppliersListViewUiBinder extends UiBinder<Widget, SuppliersListViewImpl> {}
	private static SuppliersListViewUiBinder uiBinder =	GWT.create(SuppliersListViewUiBinder.class);
	
	@UiField Button reloadButton;
	@UiField Button addButton;
	@UiField Button deleteButton;
	
	private Presenter<EntityDisplay> presenter;
	
	/**
	 * 
	 */
	public SuppliersListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("reloadButton")
	void onReloadButtonClicked(ClickEvent event) {
		if (presenter != null) {
			presenter.onReloadButtonClicked();
		}
	}
	
	@UiHandler("addButton")
	void onAddButtonClicked(ClickEvent event) {
		if (presenter != null) {
			presenter.onAddButtonClicked();
		}
	}
	
	@UiHandler("deleteButton")
	void onDeleteButtonClicked(ClickEvent event) {
		if (presenter != null) {
			// TODO populate list
			presenter.onDeleteButtonClicked(null);
		}
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter<EntityDisplay> presenter) {
		this.presenter = presenter;
	}

}
