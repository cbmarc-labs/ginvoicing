/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.LinesEventBus;
import cbmarc.ginvoicing.client.event.ListEditEvent;
import cbmarc.ginvoicing.client.i18n.LinesConstants;
import cbmarc.ginvoicing.client.view.invoices.LinesListView;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 * 
 */
public class LinesListPresenter implements Presenter, LinesListView.Presenter {
	
	private final LinesListView view;
	
	private EventBus eventBus = EventBus.getEventBus();
	private LinesConstants constants = LinesEventBus.getConstants();
	
	private List<Line> list = null;
	
	public LinesListPresenter(LinesListView view, List<Line> list) {
		this.view = view;
		this.list = list;
		
		view.setPresenter(this);
	}
	
	private void deleteSelectedRows(List<Integer> rows) {
		if(rows.isEmpty()) {
			Window.alert(constants.noItemsSelected());
		} else {
			if(Window.confirm(constants.areYouSure())) {
				List<Line> ids = new ArrayList<Line>();
				
				for(Integer row : rows) {
					if(row > 0)
						ids.add(list.get(row - 1));
				}
				
				list.removeAll(ids);
				updateDisplayFromData();
			}
		}
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
				
		updateDisplayFromData();
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		view.setData(list);
		
		// Set total amount of invoice
		Float amount = 0.0f;
		for(Line l: list) amount += (l.getQuantity() * l.getPrice());
		
		view.setListFooterLabel(constants.totalAmount() + amount.toString());
	}

	@Override
	public void onAddButtonClicked() {
		if(list.size() > 9)
			Window.alert(constants.limitExceeded());
		else
			eventBus.fireEvent(ListEditEvent.edit(null));
	}

	@Override
	public void onDeleteButtonClicked(List<Integer> items) {
		deleteSelectedRows(items);
	}

	@Override
	public void onItemClicked(int item) {
		if(item > 0) {
			Line line = list.get(item - 1);
			
			eventBus.fireEvent(ListEditEvent.edit(line));
		}
	}

	@Override
	public void onReloadButtonClicked() {
		updateDisplayFromData();
	}
}
