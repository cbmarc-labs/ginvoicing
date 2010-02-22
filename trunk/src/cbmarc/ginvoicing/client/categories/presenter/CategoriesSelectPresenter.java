/**
 * 
 */
package cbmarc.ginvoicing.client.categories.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectHandler;
import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.event.dom.client.ClickEvent;
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

		void setData(List<Categories> data);
		
		int getClickedRow(ClickEvent event);

		Widget asWidget();
	}

	private CategoriesEventBus eventBus = CategoriesEventBus.getInstance();
	private final Display display;
	
	private String filter = null;
	private List<Categories> lista;

	/**
	 * @param eventBus
	 * @param view
	 */
	public CategoriesSelectPresenter(Display view) { 
		this.display = view;
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
	public void onCancel(CategoriesSelectEvent event) {
		eventBus.fireEvent(CategoriesSelectEvent.cancel());
	}

	@Override
	public void onReload(CategoriesSelectEvent event) {
		getData();
	}

	@Override
	public void onTableClicked(CategoriesSelectEvent event, int row) {
		eventBus.fireEvent(CategoriesSelectEvent.table(row));
	}
}
