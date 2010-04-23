/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.List;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.event.ListEvent;
import cbmarc.ginvoicing.client.event.ListHandler;
import cbmarc.ginvoicing.client.i18n.CustomersConstants;
import cbmarc.ginvoicing.client.presenter.CustomersListPresenter;
import cbmarc.ginvoicing.client.ui.ListFlexTable;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CustomersListView extends Composite 
		implements CustomersListPresenter.Display {
	
	interface uiBinder extends UiBinder<Widget, CustomersListView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	private CustomersConstants constants = CustomersEventBus.getConstants();
	
	@UiField ListFlexTable listContent;
	@UiField Label listContentLabel;
	@UiField Label listheaderLabel;
	
	public CustomersListView() {
		initWidget(uiBinder.createAndBindUi(this));		
	}
	
	/**
	 * @param data
	 */
	public void setData(List<EntityDisplay> data) {
		int size = data.size();
		
		setListContentLabel(null);
		listContent.removeAllRows();
		listContent.addData(new String[] {
				constants.listName(),
				constants.listContact(),
				constants.listAddress(),
				constants.listCity(),
				constants.listCountry()});
		
		if(data != null) {
			for(EntityDisplay customer : data) {
				String d[] = customer.getData();
				listContent.addData(new String[] {
						d[1], d[2], d[3], d[4], d[5]});
			}
		}
		
		listheaderLabel.setText(size + " " + constants.itemsLabel());
		
		if(data.isEmpty()) 
			setListContentLabel(constants.noData());
	}
	
	@UiHandler("reloadButton")
	protected void reloadClicked(ClickEvent event) {
		fireEvent(ListEvent.reload());
	}
	
	@UiHandler("addButton")
	protected void addClicked(ClickEvent event) {
		fireEvent(ListEvent.add());
	}
	
	@UiHandler("deleteButton")
	protected void deleteClicked(ClickEvent event) {
		fireEvent(ListEvent.delete());
	}
	
	@UiHandler("listContent")
	protected void listContentClicked(ClickEvent event) {
		int row = listContent.getClickedRow(event);
		
		if(row > 0)
			fireEvent(ListEvent.list(row - 1));
	}

	public Widget asWidget() {
		  return this;
	}

	/**
	 * @param listContentLabel the listContentLabel to set
	 */
	public void setListContentLabel(String msg) {
		if(msg == null) {
			this.listContentLabel.setVisible(false);
		} else {
			this.listContentLabel.setVisible(true);
			this.listContentLabel.setText(msg);
		}
	}

	@Override
	public HandlerRegistration addHandler(ListHandler handler) {
		return addHandler(handler, ListEvent.getType());
	}

	@Override
	public List<Integer> getSelectedRows() {
		return listContent.getSelectedRows();
	}
}
