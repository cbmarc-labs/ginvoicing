package cbmarc.ginvoicing.client.categories.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesListHandler extends EventHandler {
	public void onReload(CategoriesListEvent event);
	public void onAdd(CategoriesListEvent event);
	public void onDelete(CategoriesListEvent event);
	public void onList(CategoriesListEvent event, int row);
}
