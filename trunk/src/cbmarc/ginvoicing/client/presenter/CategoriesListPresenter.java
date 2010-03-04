/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.event.CategoriesEvent;
import cbmarc.ginvoicing.client.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.event.CategoriesListEvent;
import cbmarc.ginvoicing.client.event.CategoriesListHandler;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.i18n.CategoriesConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.shared.entity.Category;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 * 
 */
public class CategoriesListPresenter 
		implements Presenter, CategoriesListHandler {

	public interface Display {
		public void setListContentLabel(String msg);
		
		List<Integer> getSelectedRows();
		void setData(List<Category> data);
		
		public HandlerRegistration addHandler(CategoriesListHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	private EventBus eventBus = EventBus.getEventBus();
	
	private CategoriesServiceAsync service = CategoriesEventBus.getService();
	private CategoriesConstants constants = CategoriesEventBus.getConstants();
	
	private String filter = null;
	private List<Category> list;
	
	public CategoriesListPresenter(Display display) {
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
		int row;

		if(selectedRows.isEmpty()) {
			Window.alert(constants.noItemsSelected());
		} else {
			if(Window.confirm(constants.areYouSure())) {
				
				for(Integer i : selectedRows) {
					row = selectedRows.get(i);
					if(row > 0) ids.add(list.get(row - 1).getId());
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
	 * @return the numParte
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param numParte the numParte to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
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
		
		service.select(this.filter, new AppAsyncCallback<List<Category>>() {

			@Override
			public void onFailure(Throwable caught) {
				display.setListContentLabel(caught.toString());
			}
			
			@Override
			public void onSuccess(List<Category> result) {
				display.setListContentLabel(null);
				
				list = result;
				display.setData(list);
			}
			
		});
	}

	@Override
	public void onAdd(CategoriesListEvent event) {
		eventBus.fireEvent(CategoriesEvent.editPanel(null));
	}

	@Override
	public void onDelete(CategoriesListEvent event) {
		deleteSelectedRows();
	}

	@Override
	public void onReload(CategoriesListEvent event) {
		updateDisplayFromData();
	}

	@Override
	public void onList(CategoriesListEvent event, int row) {
		String id = list.get(row).getId();
		
		eventBus.fireEvent(CategoriesEvent.editPanel(id));
	}
}
