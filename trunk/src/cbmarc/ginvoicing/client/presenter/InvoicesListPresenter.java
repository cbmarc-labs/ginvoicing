/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.event.InvoicesEventBus;
import cbmarc.ginvoicing.client.event.ListEvent;
import cbmarc.ginvoicing.client.event.ListHandler;
import cbmarc.ginvoicing.client.i18n.InvoicesConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.InvoicesServiceAsync;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 * 
 */
public class InvoicesListPresenter implements Presenter, ListHandler {

	public interface Display {
		public void setFilterBox(List<EntityDisplay> data);
		public void setListContentLabel(String msg);
		
		List<Integer> getSelectedRows();
		void setData(List<EntityDisplay> data);
		
		public HandlerRegistration addHandler(ListHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private InvoicesServiceAsync service = InvoicesEventBus.getService();
	private InvoicesConstants constants = InvoicesEventBus.getConstants();
	
	private String filter = null;
	private List<EntityDisplay> list;
	
	public InvoicesListPresenter(Display display) {
		this.display = display;
		
		bind();
	}
	
	private void bind() {
		display.addHandler(this);
	}
	
	/**
	 * 
	 */
	public void deleteSelectedRows() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();

		if(selectedRows.isEmpty()) {
			Window.alert(constants.noItemsSelected());
		} else {
			if(Window.confirm(constants.areYouSure())) {
				
				for(Integer row : selectedRows) {
					ids.add(list.get(row - 1).getData()[0]);
				}
		
				service.delete(ids, new AppAsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						updateDisplayFromData();
					}
			
				});
			}
		}
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		
		updateDisplayFromData();
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		// Nothing to do
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.setListContentLabel(constants.loading());
		
		service.selectDisplay(filter, 
				new AppAsyncCallback<List<EntityDisplay>>() {

			@Override
			public void onSuccess(List<EntityDisplay> result) {
				list = result;
				display.setData(list);
			}
		});
		
		// Load customers filter list on listbox
		if(this.filter == null)
			CustomersEventBus.getService().selectDisplay(
					null, new AppAsyncCallback<List<EntityDisplay>>() {
	
						@Override
						public void onSuccess(List<EntityDisplay> result) {
							display.setFilterBox(result);
						}
				
			});
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	@Override
	public void onAdd(ListEvent event) {
		History.newItem("main/invoices/edit");
	}

	@Override
	public void onDelete(ListEvent event) {
		deleteSelectedRows();
	}

	@Override
	public void onReload(ListEvent event) {
		this.filter = null;
		
		updateDisplayFromData();
	}

	@Override
	public void onList(ListEvent event, int row) {
		String id = list.get(row).getData()[0];
		
		History.newItem("main/invoices/edit/" + id);
	}

	@Override
	public void processHistoryToken(String token) {
		// Nothing to do.
	}

	@Override
	public void onFilter(ListEvent event, String filter) {
		this.filter = null;
		if(!filter.isEmpty())
			this.filter = "customer == '" + filter + "'";
		
		updateDisplayFromData();
	}
}
