package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesSelectHandler extends EventHandler {
	public void onReload(CategoriesSelectEvent event);
	public void onCancel(CategoriesSelectEvent event);
	public void onTableClicked(CategoriesSelectEvent event, int row);
}
