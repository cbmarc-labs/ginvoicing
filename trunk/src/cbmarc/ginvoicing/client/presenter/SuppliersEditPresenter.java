/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.IEditView;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class SuppliersEditPresenter implements Presenter, IEditView.Presenter {
	
	private final IEditView view;
	
	/**
	 * @param view
	 */
	public SuppliersEditPresenter(IEditView view) {
		this.view = view;
		view.setPresenter(this);
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
	}

	@Override
	public void onListButtonClicked() {
		History.newItem("main/suppliers");
	}

}
