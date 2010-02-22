package cbmarc.ginvoicing.client.categories.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesEditHandler extends EventHandler {
	public void onList(CategoriesEditEvent event);
	public void onSubmit(CategoriesEditEvent event);
	public void onCancel(CategoriesEditEvent event);
}
