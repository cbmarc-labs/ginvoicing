package cbmarc.ginvoicing.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final MainPresenter main;
  
	public AppController() {
		this.main = new MainPresenter(new MainView());
		
		History.addValueChangeHandler(this);
		History.fireCurrentHistoryState();
	}
	
	public void go(final HasWidgets container) {
		container.clear();
		main.go(container);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
	}
}
