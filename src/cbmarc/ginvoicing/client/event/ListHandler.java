package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author MCOSTA
 * 
 * This class handle the list panel events.
 *
 */
public interface ListHandler extends EventHandler {
	public void onReload(ListEvent event);
	public void onAdd(ListEvent event);
	public void onDelete(ListEvent event);
	public void onList(ListEvent event, int row);
	public void onFilter(ListEvent event, String filter);
}
