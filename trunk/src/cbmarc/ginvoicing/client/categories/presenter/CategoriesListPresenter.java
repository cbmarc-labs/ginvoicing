/**
 * 
 */
package cbmarc.ginvoicing.client.categories.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.event.CategoriesEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.categories.event.CategoriesListEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesListHandler;
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
public class CategoriesListPresenter implements Presenter, CategoriesListHandler {

	public interface Display {
		Label getLoadingLabel();
		Label getErrorLabel();

		void setData(List<Categories> data);
		List<Integer> getSelectedRows();
		
		int getClickedRow(ClickEvent event);
		
		public HandlerRegistration addHandler(CategoriesListHandler handler);
		Widget asWidget();
	}
	
	private CategoriesEventBus eventBus = CategoriesEventBus.getInstance();
	private final Display display;
	
	private String filter = null;
	private List<Categories> lista;
	
	public CategoriesListPresenter(Display display) {
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
		
				CategoriesEventBus.getService().delete(
						ids, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
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
	public void getData() {
		display.getLoadingLabel().setVisible(true);
		CategoriesEventBus.getService().select(
				this.filter, new AsyncCallback<List<Categories>>() {

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
		eventBus.fireEvent(CategoriesEvent.editPanel());
	}

	@Override
	public void onDelete(CategoriesListEvent event) {
		Window.alert("onDelete");
	}

	@Override
	public void onReload(CategoriesListEvent event) {
		Window.alert("onReload");
	}

	@Override
	public void onTableClicked(CategoriesListEvent event, int row) {
		Window.alert("onTableClicked");
	}
}