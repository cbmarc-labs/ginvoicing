/**
 * 
 */
package cbmarc.ginvoicing.client.mvp.categories.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.mvp.Presenter;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesAddEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesDeleteEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesEditEvent;
import cbmarc.ginvoicing.client.mvp.categories.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 * 
 */
public class CategoriesListPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getAddButton();
		HasClickHandlers getDeleteButton();
		HasClickHandlers getTable();
		Label getTableLabel();

		void setData(List<Categories> data);
		List<Integer> getSelectedRows();
		
		int getClickedRow(ClickEvent event);

		Widget asWidget();
	}

	private final CategoriesServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private String filter = null;
	private List<Categories> lista;

	/**
	 * @param eventBus
	 * @param view
	 */
	public CategoriesListPresenter(CategoriesServiceAsync rpcService, 
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		bind();
	}

	/**
	 * 
	 */
	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CategoriesAddEvent());
			}

		});
		
		display.getDeleteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CategoriesDeleteEvent());
			}
			
		});
		
		display.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);
				
				if(selectedRow > 0) {
					String id = lista.get(selectedRow - 1).getId();
					eventBus.fireEvent(new CategoriesEditEvent(id));
				}
			}
			
		});
	}
	
	/**
	 * 
	 */
	public void deleteSelected() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();

		if(selectedRows.isEmpty()) {
			Window.alert("No hay ningun elemento seleccionado");
		} else {
			if(Window.confirm("Borrar los elementos seleccionados ?")) {
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
		display.getTableLabel().setText("Loading view, please wait ...");
		display.getTableLabel().setVisible(true);
		rpcService.select(this.filter, new AsyncCallback<List<Categories>>() {

			@Override
			public void onFailure(Throwable caught) {
				display.getTableLabel().setVisible(true);
				display.getTableLabel().setText("Error fetching data: " + caught.toString());
			}

			@Override
			public void onSuccess(List<Categories> result) {
				display.getTableLabel().setText("");
				display.getTableLabel().setVisible(false);
				setData(result);
			}
			
		});
	}
}
