/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.i18n.CustomersConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.CustomersServiceAsync;
import cbmarc.ginvoicing.client.view.customers.CustomersListView;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 * 
 */
public class CustomersListPresenter 
		implements Presenter, CustomersListView.Presenter {
	
	private final CustomersListView view;
	
	private CustomersServiceAsync service = CustomersEventBus.getService();
	private CustomersConstants constants = CustomersEventBus.getConstants();
	
	private List<EntityDisplay> list;
	
	public CustomersListPresenter(CustomersListView view) {
		this.view = view;
		view.setPresenter(this);
	}
	
	/**
	 * 
	 */
	private void deleteSelectedRows(List<Integer> rows) {
		List<String> ids = new ArrayList<String>();

		if(rows.isEmpty()) {
			Window.alert(constants.noItemsSelected());
		} else {
			if(Window.confirm(constants.areYouSure())) {
				
				for(Integer row : rows) {
					ids.add(list.get(row - 1).getData()[0]);
				}
				
				view.setListHeaderLabel(constants.loading());
				service.delete(ids, new AppAsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						doLoad();
					}
			
				});
			}
		}
	}
	
	private void doLoad() {
		view.getLoadingPanel().setVisible(true);
		view.getListPanel().setVisible(false);
		service.selectDisplay(new AppAsyncCallback<List<EntityDisplay>>() {
			
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
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		doLoad();
	}
	
	public void updateDisplayFromData() {
		view.setData(list);
	}

	@Override
	public void onAddButtonClicked() {
		History.newItem("main/customers/edit");
	}

	@Override
	public void onDeleteButtonClicked(List<Integer> rows) {
		deleteSelectedRows(rows);
	}

	@Override
	public void onItemClicked(int item) {
		if(item > 0) {
			String id = list.get(item - 1).getData()[0];
		
			History.newItem("main/customers/edit/" + id);
		}
	}

	@Override
	public void onReloadButtonClicked() {
		doLoad();
	}

}
