package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesCreatedEventHandler extends EventHandler {
  void onCreated(CategoriesCreatedEvent event);
}
