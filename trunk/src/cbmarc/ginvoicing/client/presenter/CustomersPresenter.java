/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.CustomersEditView;
import cbmarc.ginvoicing.client.view.CustomersListView;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CustomersPresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	protected final Display display;

	private CustomersListPresenter customersListPresenter;
	private CustomersEditPresenter customersEditPresenter;
	
	public CustomersPresenter(Display display) {
	    this.display = display;

	    customersListPresenter = new CustomersListPresenter(new CustomersListView());
	    customersEditPresenter = new CustomersEditPresenter(new CustomersEditView());
		
	    bind();
	}
	
	private void bind() {
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}

	@Override
	public void processHistoryToken(String token) {
		if(token != null) {
			Presenter presenter = customersListPresenter;
			
			if(token.startsWith("main/customers/edit")) {
				presenter = customersEditPresenter;
			}

			presenter.go(display.getContent());
		}
	}

}
