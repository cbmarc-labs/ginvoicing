/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.List;

import cbmarc.ginvoicing.client.event.InvoicesEventBus;
import cbmarc.ginvoicing.client.event.ListEvent;
import cbmarc.ginvoicing.client.event.ListHandler;
import cbmarc.ginvoicing.client.i18n.InvoicesConstants;
import cbmarc.ginvoicing.client.presenter.InvoicesListPresenter;
import cbmarc.ginvoicing.client.ui.ListFlexTable;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class InvoicesListView extends Composite 
		implements InvoicesListPresenter.Display {
	
	interface uiBinder extends UiBinder<Widget, InvoicesListView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	private InvoicesConstants constants = InvoicesEventBus.getConstants();
	
	@UiField ListBox filterBox;
	@UiField ListFlexTable listContent;
	@UiField Label listContentLabel;
	@UiField Label listheaderLabel;
	
	public InvoicesListView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setData(List<EntityDisplay> data) {
		int size = data.size();
		
		setListContentLabel(null);
		listContent.removeAllRows();
		listContent.addData(new String[] {constants.listCustomerName(),
				constants.listDate(), constants.listAmount()});
		
		if(data != null) {
			for(EntityDisplay i : data) {
				String d[] = i.getData();
				
				listContent.addData(new String[] {d[1], d[2], d[3]});
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
	
	@UiHandler("filterBox")
	protected void filterChange(ChangeEvent event) {
		String id = filterBox.getValue(filterBox.getSelectedIndex());
		
		fireEvent(ListEvent.filter(id));
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

	@Override
	public void setFilterBox(List<EntityDisplay> data) {
		this.filterBox.clear();
		
		this.filterBox.addItem("", "");
		for(EntityDisplay item: data) {
			String[] d = item.getData();
			
			this.filterBox.addItem(d[1], d[0]);
		}
	}
	
}
