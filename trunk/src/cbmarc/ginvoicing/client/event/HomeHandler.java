package cbmarc.ginvoicing.client.event;


import com.google.gwt.event.shared.EventHandler;

public interface HomeHandler extends EventHandler {
	public void home(HomeEvent event);
	public void categories(HomeEvent event);
	public void products(HomeEvent event);
}
