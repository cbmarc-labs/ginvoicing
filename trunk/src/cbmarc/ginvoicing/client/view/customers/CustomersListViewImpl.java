/**
 * 
 */
package cbmarc.ginvoicing.client.view.customers;

import java.util.List;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.i18n.CustomersConstants;
import cbmarc.ginvoicing.client.ui.ListFlexTable;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CustomersListViewImpl extends Composite 
		implements CustomersListView {

	@UiTemplate("CustomersListView.ui.xml")
	interface uiBinder extends UiBinder<Widget, CustomersListViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	private CustomersConstants constants = CustomersEventBus.getConstants();

	@UiField Panel loadingPanel;
	@UiField Panel listPanel;
	@UiField ListFlexTable listTable;
	@UiField HasText listHeaderLabel;
	
	private Presenter presenter = null;
	
	public CustomersListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));		
	}
	
	/**
	 * @param data
	 */
	public void setData(List<EntityDisplay> data) {
		listTable.removeAllRows();
		listTable.addData(new String[] {constants.listName(),
				constants.listContact(), constants.listAddress(),
				constants.listCity(), constants.listCountry()});

		setListHeaderLabel(constants.noData());
		if(data == null || data.isEmpty()) return;
		
		for(EntityDisplay i : data) {
			String d[] = i.getData();
			listTable.addData(new String[] {d[1], d[2], d[3], d[4], d[5]});
		}
			
		setListHeaderLabel(data.size() + " " + constants.itemsLabel());
	}
	
	@UiHandler("reloadButton")
	protected void reloadClicked(ClickEvent event) {
	    if(presenter != null) {
	    	presenter.onReloadButtonClicked();
	    }
	}
	
	@UiHandler("addButton")
	protected void addClicked(ClickEvent event) {
	    if(presenter != null) {
	    	presenter.onAddButtonClicked();
	    }
	}
	
	@UiHandler("deleteButton")
	protected void deleteClicked(ClickEvent event) {
	    if(presenter != null) {
	    	presenter.onDeleteButtonClicked(listTable.getSelectedRows());
	    }
	}
	
	@UiHandler("listTable")
	protected void listContentClicked(ClickEvent event) {
	    if(presenter != null) {
	    	int row = listTable.getClickedRow(event);

	    	presenter.onItemClicked(row);
	    }
	}

	public Widget asWidget() {
		  return this;
	}

	@Override
	public void setListHeaderLabel(String text) {
		listHeaderLabel.setText(text);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Panel getLoadingPanel() {
		return loadingPanel;
	}

	@Override
	public Panel getListPanel() {
		return listPanel;
	}
}
