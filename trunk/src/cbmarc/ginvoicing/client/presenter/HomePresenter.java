/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.HomeEvent;
import cbmarc.ginvoicing.client.event.HomeHandler;
import cbmarc.ginvoicing.client.event.MainEvent;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class HomePresenter implements Presenter, HomeHandler {
	
	public interface Display {
		public HandlerRegistration addHandler(HomeHandler handler);
		
		Widget asWidget();
	}
	
	protected final Display display;
	private EventBus eventBus = EventBus.getEventBus();
	
	public HomePresenter(Display display) {
	    this.display = display;
	    
	    bind();
	}
	
	private void bind() {
		display.addHandler(this);
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}

	@Override
	public void categories(HomeEvent event) {
		eventBus.fireEvent(MainEvent.categories());
	}

	@Override
	public void home(HomeEvent event) {
		eventBus.fireEvent(MainEvent.home());
	}

	@Override
	public void products(HomeEvent event) {
		eventBus.fireEvent(MainEvent.products());
	}

}
