/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.view.IListView;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class SuppliersListPresenter implements Presenter, 
		IListView.Presenter<EntityDisplay> {
	
	private final IListView<EntityDisplay> view;
	
	/**
	 * @param view
	 */
	public SuppliersListPresenter(IListView<EntityDisplay> view) {
		this.view = view;
		view.setPresenter(this);
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
	}

	@Override
	public void onAddButtonClicked() {
		History.newItem("main/suppliers/edit");
	}

	@Override
	public void onReloadButtonClicked() {
		Window.alert("onDeleteButtonClicked");
	}

	@Override
	public void onDeleteButtonClicked(List<EntityDisplay> data) {
		Window.alert("onDeleteButtonClicked");
	}

	@Override
	public void onItemClicked(EntityDisplay clickedItem) {
		Window.alert("onItemClicked");
	}

}
