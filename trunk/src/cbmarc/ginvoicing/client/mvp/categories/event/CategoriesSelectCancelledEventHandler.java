package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesSelectCancelledEventHandler extends EventHandler {
  void onSelectCancelled(CategoriesSelectCancelledEvent event);
}
