package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author MCOSTA
 * 
 * This class handle the List/Edit panel transition.
 *
 */
public interface ListEditHandler extends EventHandler {
	public void onList(ListEditEvent event, Object object);
	public void onEdit(ListEditEvent event, Object object);
}
