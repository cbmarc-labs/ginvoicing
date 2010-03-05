package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ListHandler extends EventHandler {
	public void onReload(ListEvent event);
	public void onAdd(ListEvent event);
	public void onDelete(ListEvent event);
	public void onList(ListEvent event, int row);
}
