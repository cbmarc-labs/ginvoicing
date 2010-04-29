/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.invoices.InvoicesEditView;
import cbmarc.ginvoicing.client.view.invoices.InvoicesListView;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class InvoicesPresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	protected final Display display;

	private InvoicesListPresenter invoicesListPresenter;
	private InvoicesEditPresenter invoicesEditPresenter;
	
	public InvoicesPresenter(Display display) {
	    this.display = display;

	    invoicesListPresenter = new InvoicesListPresenter(new InvoicesListView());
	    invoicesEditPresenter = new InvoicesEditPresenter(new InvoicesEditView());

	    bind();
	}
	
	private void bind() {}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		processHistoryToken();
	    container.add(display.asWidget());
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = invoicesListPresenter;
			
			if(token.startsWith("main/invoices/edit")) {
				presenter = invoicesEditPresenter;
			}
			
			presenter.go(display.getContent());
		}
	}

}
