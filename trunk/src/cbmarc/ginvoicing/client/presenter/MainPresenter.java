/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbmarc.ginvoicing.client.view.about.AboutView;
import cbmarc.ginvoicing.client.view.categories.CategoriesViewImpl;
import cbmarc.ginvoicing.client.view.customers.CustomersView;
import cbmarc.ginvoicing.client.view.invoices.InvoicesView;
import cbmarc.ginvoicing.client.view.products.ProductsViewImpl;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainPresenter implements Presenter {
	
	public interface Display {
		List<Hyperlink> getMenuTab();
		void setActiveTab(Hyperlink hyperlink);
		
		HasWidgets getContent();
		Widget asWidget();
	}
	
	public static final String VIEW_ABOUT = "main/about";
	
	private final Display display;
	
	private Map<Hyperlink, Presenter> presenters = 
		new HashMap<Hyperlink, Presenter>();
	private Presenter aboutPresenter;
	
	public MainPresenter(Display display) {
	    this.display = display;
	    
	    List<Hyperlink> l = display.getMenuTab();
	    
	    presenters.put(l.get(0), new InvoicesPresenter(new InvoicesView()));
	    presenters.put(l.get(1), new CustomersPresenter(new CustomersView()));
	    presenters.put(l.get(2), new ProductsPresenter(new ProductsViewImpl()));
	    presenters.put(l.get(3), new CategoriesPresenter(new CategoriesViewImpl()));
	    presenters.put(l.get(4), new SuppliersPresenter());
	    
	    aboutPresenter = new AboutPresenter(new AboutView());
	    	    
	    bind();
	}
	
	private void bind() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		processHistoryToken();
		container.add(display.asWidget());
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		Presenter presenter = null;
		display.setActiveTab(null);
		
		if(token != null) {
			if (token.startsWith(VIEW_ABOUT)) {
				presenter = aboutPresenter;
			} else {
				for(Hyperlink h: presenters.keySet())
					if(token.startsWith(h.getTargetHistoryToken())) {
						display.setActiveTab(h);
						presenter = presenters.get(h);
					
						break;
					}
			}
		}

		if(presenter != null) {
			presenter.go(display.getContent());
		}
	}
	
}
