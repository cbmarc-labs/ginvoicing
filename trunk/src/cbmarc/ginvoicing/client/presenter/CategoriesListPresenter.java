/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.i18n.CategoriesConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.client.view.categories.CategoriesListView;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 * 
 */
public class CategoriesListPresenter 
		implements Presenter, CategoriesListView.Presenter {
	
	private final CategoriesListView view;
	
	private CategoriesServiceAsync service = CategoriesEventBus.getService();
	private CategoriesConstants constants = CategoriesEventBus.getConstants();
	
	private List<EntityDisplay> list;
	
	public CategoriesListPresenter(CategoriesListView view) {
		this.view = view;
		view.setPresenter(this);
	}
	
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
						updateDisplayFromData();
					}
			
				});
			}
		}
	}

	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		updateDisplayFromData();
	}
	
	public void updateDataFromDisplay() { }
	
	public void updateDisplayFromData() {
		view.setListHeaderLabel(constants.loading());
		service.selectDisplay(new AppAsyncCallback<List<EntityDisplay>>() {
			
			@Override
			public void onSuccess(List<EntityDisplay> result) {
				list = result;
				view.setData(list);
			}
			
		});
	}

	@Override
	public void onAddButtonClicked() {
		History.newItem("main/categories/edit");
	}

	@Override
	public void onDeleteButtonClicked(List<Integer> rows) {
		deleteSelectedRows(rows);
	}

	@Override
	public void onItemClicked(int item) {
		if(item > 0) {
			String id = list.get(item - 1).getData()[0];
		
			History.newItem("main/categories/edit/" + id);
		}
	}

	@Override
	public void onReloadButtonClicked() {
		updateDisplayFromData();
	}
	
}
