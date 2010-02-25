/**
 * 
 */
package cbmarc.ginvoicing.client.categories.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.CategoriesServiceAsync;
import cbmarc.ginvoicing.client.categories.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectHandler;
import cbmarc.ginvoicing.shared.entity.Category;

import com.google.gwt.event.shared.HandlerManager;
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
public class CategoriesSelectPresenter 
		implements Presenter, CategoriesSelectHandler {

	public interface Display {		
		Label getLoadingLabel();
		Label getErrorLabel();
		
		void setData(List<Category> data);
		public HandlerRegistration addHandler(CategoriesSelectHandler handler);

		Widget asWidget();
	}

	private HandlerManager eventBus;
	private CategoriesServiceAsync service = CategoriesEventBus.getService();
	private final Display display;
	
	private String filter = null;
	private List<Category> lista;

	public CategoriesSelectPresenter(Display display) { 
		this.display = display;
		this.eventBus = CategoriesEventBus.getInstance();
		
		display.addHandler(this);
	}
	
	public CategoriesSelectPresenter(HandlerManager eventBus, Display display) { 
		this.display = display;
		this.eventBus = eventBus;
		
		display.addHandler(this);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());

		doReload();
	}
	
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public void doReload() {
		display.getLoadingLabel().setVisible(true);
		
		service.select(this.filter, new AsyncCallback<List<Category>>() {

			@Override
			public void onFailure(Throwable caught) {
				display.getLoadingLabel().setVisible(false);
				display.getErrorLabel().setVisible(true);
				
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<Category> result) {
				display.getLoadingLabel().setVisible(false);
				
				lista = result;
				display.setData(lista);
			}
			
		});
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void onCancel(CategoriesSelectEvent event) {
		eventBus.fireEvent(CategoriesSelectEvent.cancel());
	}

	@Override
	public void onReload(CategoriesSelectEvent event) {
		eventBus.fireEvent(CategoriesSelectEvent.reload());
	}

	@Override
	public void onTableClicked(CategoriesSelectEvent event, int row) {
		//Window.alert(" desde select presenter " + row);
		//eventBus.fireEvent(CategoriesSelectEvent.table(10));
	}
}
