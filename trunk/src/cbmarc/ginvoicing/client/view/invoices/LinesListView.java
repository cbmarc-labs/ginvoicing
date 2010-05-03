/**
 * 
 */
package cbmarc.ginvoicing.client.view.invoices;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface LinesListView {
	
	public interface Presenter {
		void onReloadButtonClicked();
		void onAddButtonClicked();
		void onDeleteButtonClicked(List<Integer> items);
		void onItemClicked(int item);
	}
	
	void setListHeaderLabel(String text);
	void setData(List<Line> data);
	void setListFooterLabel(String text);
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
