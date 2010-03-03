package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface MainHandler extends EventHandler {
	public void home(MainEvent event);
	public void categories(MainEvent event);
	public void products(MainEvent event);
	public void customers(MainEvent event);
	public void invoices(MainEvent event);
}
