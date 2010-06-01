/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.event.ProductsEventBus;
import cbmarc.ginvoicing.client.i18n.ProductsConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.ProductsServiceAsync;
import cbmarc.ginvoicing.client.view.products.ProductsListView;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 * 
 */
public class ProductsListPresenter 
		implements Presenter, ProductsListView.Presenter {
	
	private final ProductsListView view;
		
	private ProductsServiceAsync service = ProductsEventBus.getService();
	private ProductsConstants constants = ProductsEventBus.getConstants();
	
	private String filter = null;
	private List<EntityDisplay> list;
	
	public ProductsListPresenter(ProductsListView view) {
		this.view = view;
		view.setPresenter(this);
	}
	
	private void deleteSelectedRows(List<Integer> items) {
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
						doLoad();
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
		view.getFilterBox().setEnabled(false);
		CategoriesEventBus.getService().selectDisplay(
				new AppAsyncCallback<List<EntityDisplay>>() {

					@Override
					public void onSuccess(List<EntityDisplay> result) {
						view.setFilterBox(result);
						view.getFilterBox().setEnabled(true);
					}
			
		});
	}

	@Override
	public void onAddButtonClicked() {
		History.newItem("main/products/edit");
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
		
		doLoad();
	}

	@Override
	public void onItemClicked(int item) {
		if(item > 0) {
			String id = list.get(item - 1).getData()[0];
		
			History.newItem("main/products/edit/" + id);
		}
	}

	@Override
	public void onReloadButtonClicked() {
		this.filter = null;
		
		doLoad();
		updateCategoriesList();
	}
}
