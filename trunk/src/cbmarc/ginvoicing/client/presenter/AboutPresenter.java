/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.about.AboutView;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 * 
 * Represents a about section.
 *
 */
public class AboutPresenter implements Presenter, AboutView.Presenter {

	private final AboutView view;
	
	/**
	 * @param view
	 */
	public AboutPresenter(AboutView view) {
		this.view = view;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(view.asWidget());
	}

}
