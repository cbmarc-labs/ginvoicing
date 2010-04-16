/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.LinesEventBus;
import cbmarc.ginvoicing.client.event.ListEditEvent;
import cbmarc.ginvoicing.client.event.ListEvent;
import cbmarc.ginvoicing.client.event.ListHandler;
import cbmarc.ginvoicing.client.i18n.LinesConstants;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 * 
 */
public class LinesListPresenter implements Presenter, ListHandler {

	public interface Display {
		public void setListContentLabel(String msg);
		
		List<Integer> getSelectedRows();
		void setData(List<Line> data);
		
		public HandlerRegistration addHandler(ListHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private EventBus eventBus = EventBus.getEventBus();
	private LinesConstants constants = LinesEventBus.getConstants();
	
	private List<Line> list = new ArrayList<Line>();
	
	public LinesListPresenter(Display display) {
		this.display = display;
		
		bind();
	}
	
	private void bind() {
		display.addHandler(this);
	}
	
	/**
	 * 
	 */
	public void deleteSelectedRows() {
		List<Integer> selectedRows = display.getSelectedRows();
		List<String> ids;

		if(selectedRows.isEmpty()) {
			Window.alert(constants.noItemsSelected());
		} else {
			if(Window.confirm(constants.areYouSure())) {
				ids = new ArrayList<String>();
				
				for(Integer row : selectedRows) {
					if(row > 0) 
						ids.add(list.get(row - 1).getId());
				}
				
				list.removeAll(ids);
				
				//updateDisplayFromData();
			}
		}
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());

		updateDisplayFromData();
	}
	
	/**
	 * @return the list
	 */
	public List<Line> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Line> list) {
		this.list = list;
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		// Nothing to do.
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.setData(list);
	}

	@Override
	public void onAdd(ListEvent event) {
		eventBus.fireEvent(ListEditEvent.edit(null));
	}

	@Override
	public void onDelete(ListEvent event) {
		deleteSelectedRows();
	}

	@Override
	public void onReload(ListEvent event) {
		updateDisplayFromData();
	}

	@Override
	public void onList(ListEvent event, int row) {
		eventBus.fireEvent(ListEditEvent.edit(list.get(row)));
	}

	@Override
	public void processHistoryToken(String token) {
		// Nothing to do.
	}

	@Override
	public void onFilter(ListEvent event, String filter) {
		// Nothing to do.
	}
}
