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
public class CustomersPresenter implements Presenter {
	
	public interface Display {
		Widget asWidget();
	}
	
	protected final Display display;
	
	public CustomersPresenter(Display display) {
	    this.display = display;

	    bind();
	}
	
	private void bind() {
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}

}
