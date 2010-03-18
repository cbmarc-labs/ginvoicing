package cbmarc.ginvoicing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ginvoicing implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	    AppController appViewer = new AppController();

	    appViewer.go(RootPanel.get());
	    
	    // FOR DEBUGING PURPOSES
	    //RootPanel.get().add(new Label(RootPanel.get().toString()));
	}
	
}
