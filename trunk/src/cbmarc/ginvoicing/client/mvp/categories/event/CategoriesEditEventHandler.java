package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesEditEventHandler extends EventHandler {
  void onEdit(CategoriesEditEvent event);
}
