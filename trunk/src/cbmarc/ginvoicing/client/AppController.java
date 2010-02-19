package cbmarc.ginvoicing.client;

import cbmarc.ginvoicing.client.mvp.MainPresenter;
import cbmarc.ginvoicing.client.mvp.MainView;
import cbmarc.ginvoicing.client.mvp.Presenter;

import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter {
	private final MainPresenter main;
  
	public AppController() {
		this.main = new MainPresenter(new MainView());
		
		bind();
	}
	
	private void bind() {
	}
	
	public void go(final HasWidgets container) {
		container.clear();
		main.go(container);
	}
}
