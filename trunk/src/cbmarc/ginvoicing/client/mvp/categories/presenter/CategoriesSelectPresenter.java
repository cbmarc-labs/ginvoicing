/**
 * 
 */
package cbmarc.ginvoicing.client.mvp.categories.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.mvp.Presenter;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesSelectCancelledEvent;
import cbmarc.ginvoicing.client.mvp.categories.event.CategoriesSelectEvent;
import cbmarc.ginvoicing.client.mvp.categories.rpc.CategoriesServiceAsync;
import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 * 
 */
public class CategoriesSelectPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getCancelButton();
		HasClickHandlers getTable();

		void setData(List<Categories> data);
		
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
	public CategoriesSelectPresenter(CategoriesServiceAsync rpcService, 
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
		display.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CategoriesSelectCancelledEvent());
			}

		});
		
		display.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);
				
				if(selectedRow > 0) {
					Categories bean = lista.get(selectedRow - 1);
					eventBus.fireEvent(new CategoriesSelectEvent(bean));
				}
			}
			
		});
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
		rpcService.select(this.filter, new AsyncCallback<List<Categories>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching: " + caught.toString());
			}

			@Override
			public void onSuccess(List<Categories> result) {
				setData(result);
			}
			
		});
	}
}
