/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.invoices.InvoicesEditViewImpl;
import cbmarc.ginvoicing.client.view.invoices.InvoicesListViewImpl;
import cbmarc.ginvoicing.client.view.invoices.InvoicesView;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class InvoicesPresenter implements Presenter, InvoicesView.Presenter {
	
	protected final InvoicesView view;

	private InvoicesListPresenter invoicesListPresenter = new InvoicesListPresenter(new InvoicesListViewImpl());
	private InvoicesEditPresenter invoicesEditPresenter = new InvoicesEditPresenter(new InvoicesEditViewImpl());
	
	public InvoicesPresenter(InvoicesView view) {
	    this.view = view;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		processHistoryToken();
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = invoicesListPresenter;
			
			if(token.startsWith("main/invoices/edit")) {
				presenter = invoicesEditPresenter;
			}
			
			presenter.go(view.getContent());
		}
	}

}
