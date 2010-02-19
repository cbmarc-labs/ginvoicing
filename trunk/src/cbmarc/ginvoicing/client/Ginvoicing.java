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
	    
	    //RootPanel.get().setStyleName("body");
	    appViewer.go(RootPanel.get());
	    //appViewer.go(RootLayoutPanel.get());
	}
	
}
