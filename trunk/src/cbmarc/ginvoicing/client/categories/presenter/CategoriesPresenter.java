/**
 * 
 */
package cbmarc.ginvoicing.client.categories.presenter;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.categories.event.CategoriesHandler;
import cbmarc.ginvoicing.client.categories.event.CategoriesListEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesListHandler;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectHandler;
import cbmarc.ginvoicing.client.categories.view.CategoriesEditView;
import cbmarc.ginvoicing.client.categories.view.CategoriesListView;
import cbmarc.ginvoicing.client.categories.view.CategoriesSelectView;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesPresenter 
		implements Presenter, CategoriesSelectHandler, CategoriesListHandler {
	
	public interface Display {
		public HandlerRegistration addHandler(CategoriesHandler handler);
		
		HasWidgets getContent();
		
		Widget asWidget();
	}
	
	private CategoriesEventBus eventBus = CategoriesEventBus.getInstance();
	protected final Display display;
	
	private CategoriesListView categoriesListView;
	private CategoriesEditView categoriesEditView;
	private CategoriesSelectView categoriesSelectView;
	
	private CategoriesListPresenter categoriesListPresenter;
	private CategoriesEditPresenter categoriesEditPresenter;
	private CategoriesSelectPresenter categoriesSelectPresenter;
	
	public CategoriesPresenter(Display display) {
	    this.display = display;
	    
	    categoriesListView = new CategoriesListView();
	    categoriesEditView = new CategoriesEditView();
	    categoriesSelectView = new CategoriesSelectView();
	    
	    categoriesListPresenter = new CategoriesListPresenter(categoriesListView);
	    categoriesEditPresenter = new CategoriesEditPresenter(categoriesEditView);
	    categoriesSelectPresenter = new CategoriesSelectPresenter(categoriesSelectView);
		
	    eventBus.addHandler(CategoriesSelectEvent.getType(), this);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		//categoriesListPresenter.go(display.getContent());
		categoriesSelectPresenter.go(display.getContent());
	    container.add(display.asWidget());
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void onCancel(CategoriesSelectEvent event) {
		Window.alert("CANCELADO DESDE CATEGORIESPRESENTER");
	}

	@Override
	public void onReload(CategoriesSelectEvent event) {
		Window.alert("onReload DESDE CATEGORIESPRESENTER");
	}

	@Override
	public void onTableClicked(CategoriesSelectEvent event, int row) {
		Window.alert(" ==> " + row);
	}

	@Override
	public void onAdd(CategoriesListEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete(CategoriesListEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReload(CategoriesListEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTableClicked(CategoriesListEvent event, int row) {
		// TODO Auto-generated method stub
		
	}

}
