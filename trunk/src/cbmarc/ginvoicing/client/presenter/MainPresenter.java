/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbmarc.ginvoicing.client.view.AboutView;
import cbmarc.ginvoicing.client.view.CategoriesView;
import cbmarc.ginvoicing.client.view.CustomersView;
import cbmarc.ginvoicing.client.view.InvoicesView;
import cbmarc.ginvoicing.client.view.ProductsView;

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
	    presenters.put(l.get(2), new ProductsPresenter(new ProductsView()));
	    presenters.put(l.get(3), new CategoriesPresenter(new CategoriesView()));
	    
	    aboutPresenter = new AboutPresenter(new AboutView());
	    	    
	    bind();
	}
	
	private void bind() {}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void processHistoryToken(String token) {
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
			presenter.processHistoryToken(token);
			presenter.go(display.getContent());
		}
	}
	
}
