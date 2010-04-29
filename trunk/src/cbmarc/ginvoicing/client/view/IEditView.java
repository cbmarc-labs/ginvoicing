/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface IEditView {
	
	public interface Presenter {
		void onListButtonClicked();
	}
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
