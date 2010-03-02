package cbmarc.ginvoicing.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AppAsyncCallback<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		System.out.println("Server error: " + caught.getMessage());
		Window.alert("Server error: " + caught.getMessage());
	}
	
}
