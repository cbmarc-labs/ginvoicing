package cbmarc.ginvoicing.client.categories.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesHandler extends EventHandler {
	public void onList(CategoriesEvent event);
	public void onEdit(CategoriesEvent event, String id);
}
