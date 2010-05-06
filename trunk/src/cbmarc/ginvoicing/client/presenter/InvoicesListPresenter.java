/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.event.InvoicesEventBus;
import cbmarc.ginvoicing.client.i18n.InvoicesConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.InvoicesServiceAsync;
import cbmarc.ginvoicing.client.view.invoices.InvoicesListView;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 * 
 */
public class InvoicesListPresenter 
		implements Presenter, InvoicesListView.Presenter {
	
	private final InvoicesListView view;
	
	private InvoicesServiceAsync service = InvoicesEventBus.getService();
	private InvoicesConstants constants = InvoicesEventBus.getConstants();
	
	private String filter = null;
	private List<EntityDisplay> list;
	
	public InvoicesListPresenter(InvoicesListView view) {
		this.view = view;
		view.setPresenter(this);
	}
	
	/**
	 * 
	 */
	public void deleteSelectedRows(List<Integer> items) {
		List<String> ids = new ArrayList<String>();

		if(items.isEmpty()) {
			Window.alert(constants.noItemsSelected());
		} else {
			if(Window.confirm(constants.areYouSure())) {
				
				for(Integer row : items) {
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
	
	private void doLoad() {
		view.getLoadingPanel().setVisible(true);
		view.getListPanel().setVisible(false);
		service.selectDisplay(filter, 
				new AppAsyncCallback<List<EntityDisplay>>() {

			@Override
			public void onSuccess(List<EntityDisplay> result) {
				list = result;
				view.getListPanel().setVisible(true);
				view.getLoadingPanel().setVisible(false);
				updateDisplayFromData();
			}
		});
	}

	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		doLoad();
		updateCategoriesList();
	}
	
	private void updateDisplayFromData() {
		view.setData(list);
	}
	
	private void updateCategoriesList() {
		CustomersEventBus.getService().selectDisplay(
				new AppAsyncCallback<List<EntityDisplay>>() {
	
					@Override
					public void onSuccess(List<EntityDisplay> result) {
						view.setFilterBox(result);
					}
				
		});
	}

	@Override
	public void onAddButtonClicked() {
		History.newItem("main/invoices/edit");
	}

	@Override
	public void onDeleteButtonClicked(List<Integer> items) {
		deleteSelectedRows(items);
	}

	@Override
	public void onFilterBoxChanged(String item) {
		filter = null;
		if(!item.isEmpty())
			this.filter = item;
		
		updateDisplayFromData();
	}

	@Override
	public void onItemClicked(int item) {
		if(item > 0) {
			String id = list.get(item - 1).getData()[0];
		
			History.newItem("main/invoices/edit/" + id);
		}
	}

	@Override
	public void onReloadButtonClicked() {
		this.filter = null;
		
		doLoad();
		updateCategoriesList();
	}

}
