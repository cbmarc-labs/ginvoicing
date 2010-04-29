/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface IListView<T> {
	
	public interface Presenter<T> {
		void onReloadButtonClicked();
		void onAddButtonClicked();
		void onDeleteButtonClicked(List<T> data);
		void onItemClicked(T clickedItem);
	}
	
	void setPresenter(Presenter<T> presenter);
	Widget asWidget();
}
