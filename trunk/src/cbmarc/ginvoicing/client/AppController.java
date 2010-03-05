package cbmarc.ginvoicing.client;

import cbmarc.ginvoicing.client.presenter.MainPresenter;
import cbmarc.ginvoicing.client.presenter.Presenter;
import cbmarc.ginvoicing.client.view.MainView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	
	private HasWidgets container;
	private MainPresenter mainPresenter;
  
	public AppController() {
		mainPresenter = new MainPresenter(new MainView());
		
		bind();
	}
	
	private void bind() {
		History.addValueChangeHandler(this);
	}
	
	public void go(final HasWidgets container) {
		this.container = container;
		
		if("".equals(History.getToken())) {
			History.newItem("main");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if(token != null) {
			Presenter presenter = mainPresenter;
			
			if(token.startsWith("main")) {
				presenter = mainPresenter;
			}

			if(presenter != null) {
				presenter.go(container);
			}
		}
	}
}
