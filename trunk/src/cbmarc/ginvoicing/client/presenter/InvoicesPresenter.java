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
	
	private static final String VIEW = MainPresenter.VIEW_INVOICES;

	private static InvoicesListViewImpl invoicesListView;
	private static InvoicesEditViewImpl invoicesEditView;

	private final InvoicesView view;
	
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
			if (token.startsWith(VIEW + "/edit")) {
				String[] parts = token.split("/");
				String id = null; 
				
				if(invoicesEditView == null)
					invoicesEditView = new InvoicesEditViewImpl();
				
				// is a edit statement?
				if(parts.length > 3) id = parts[parts.length - 1];
				
				new InvoicesEditPresenter(invoicesEditView, id).
					go(view.getContent());
			} else {
				if(invoicesListView == null)
					invoicesListView = new InvoicesListViewImpl();
				
				new InvoicesListPresenter(invoicesListView).
					go(view.getContent());
			}
		}
	}

}
