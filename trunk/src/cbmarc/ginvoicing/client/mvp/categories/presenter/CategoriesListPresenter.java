/**
 * 
 */
package cbmarc.ginvoicing.client.mvp.categories.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.EventBus;
import cbmarc.ginvoicing.client.mvp.Presenter;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesEditEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesListEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesListHandler;
import cbmarc.ginvoicing.client.mvp.categories.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 * 
 */
public class CategoriesListPresenter 
		implements Presenter, CategoriesListHandler {

	public interface Display {
		Label getLoadingLabel();
		Label getErrorLabel();

		void setData(List<Categories> data);
		List<Integer> getSelectedRows();
		
		int getClickedRow(ClickEvent event);
		
		public HandlerRegistration addHandler(CategoriesListHandler handler);
		Widget asWidget();
	}
	
	private EventBus eventBus = EventBus.getInstance();
	private final CategoriesServiceAsync rpcService;
	private final Display display;
	
	private String filter = null;
	private List<Categories> lista;
	
	public CategoriesListPresenter(CategoriesServiceAsync rpcService, 
			Display display) {
		this.rpcService = rpcService;
		this.display = display;
		
		display.addHandler(this);
	}
	
	/**
	 * 
	 */
	public void deleteSelected() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();

		if(selectedRows.isEmpty()) {
			Window.alert("No items selected.");
		} else {
			if(Window.confirm("Delete selected items ?")) {
				for (int i = 0; i < selectedRows.size(); ++i) {
					if(selectedRows.get(i) > 0)
						ids.add(lista.get(selectedRows.get(i) - 1).getId());
				}
		
				rpcService.delete(ids, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR: " + caught.toString());
					}

					@Override
					public void onSuccess(Void result) {
						getData();
					}
			
				});
			}
		}
	}
	
	/**
	 * @param result
	 */
	private void setData(List<Categories> result) {		
		lista = result;
		display.setData(lista);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());

		getData();
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
	private void getData() {
		display.getLoadingLabel().setVisible(true);
		rpcService.select(this.filter, new AsyncCallback<List<Categories>>() {

			@Override
			public void onFailure(Throwable caught) {
				display.getLoadingLabel().setVisible(false);
				display.getErrorLabel().setVisible(true);
				
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<Categories> result) {
				display.getLoadingLabel().setVisible(false);
				
				setData(result);
			}
			
		});
	}

	@Override
	public void onAdd(CategoriesListEvent event) {
		eventBus.fireEvent(CategoriesListEvent.add());
	}

	@Override
	public void onDelete(CategoriesListEvent event) {
		eventBus.fireEvent(CategoriesListEvent.delete());
	}

	@Override
	public void onReload(CategoriesListEvent event) {
		getData();
	}

	@Override
	public void onTableClicked(CategoriesListEvent event, int row) {
		if(row > 0) {
			String id = lista.get(row - 1).getId();
			eventBus.fireEvent(new CategoriesEditEvent(id));
		}
	}
}
