/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class AboutPresenter implements Presenter {
	
	public interface Display {
		Widget asWidget();
	}
	
	protected final Display display;
	
	public AboutPresenter(Display display) {
	    this.display = display;
	    bind();
	}
	
	private void bind() {}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}

	@Override
	public void processHistoryToken(String token) {}

}
