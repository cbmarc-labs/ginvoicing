package cbmarc.ginvoicing.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract interface Presenter {
	public void processHistoryToken(String token);
	public abstract void go(final HasWidgets container);
}
