package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesDeleteEventHandler extends EventHandler {
  void onDelete(CategoriesDeleteEvent event);
}
