/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.suppliers.SuppliersEditViewImpl;
import cbmarc.ginvoicing.client.view.suppliers.SuppliersListViewImpl;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class SuppliersPresenter implements Presenter {
	
	private HasWidgets container;
	
	private SuppliersListPresenter suppliersListPresenter;
	private SuppliersEditPresenter suppliersEditPresenter;
	
	public SuppliersPresenter() {		
		suppliersListPresenter = new SuppliersListPresenter(new SuppliersListViewImpl());
		suppliersEditPresenter = new SuppliersEditPresenter(new SuppliersEditViewImpl());
	}
	
	@Override
	public void go(HasWidgets container) {
		this.container = container;
		
		processHistoryToken();
	}
	
	private void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = suppliersListPresenter;
			
			if(token.startsWith("main/suppliers/edit")) {
				presenter = suppliersEditPresenter;
			}

			presenter.go(container);
		}
	}

}
