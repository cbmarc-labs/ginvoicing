package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesAddEventHandler extends EventHandler {
  void onAdd(CategoriesAddEvent event);
}
