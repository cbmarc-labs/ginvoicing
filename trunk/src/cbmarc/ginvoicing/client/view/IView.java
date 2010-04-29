/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface IView {
	
	public interface Presenter {
	}
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
