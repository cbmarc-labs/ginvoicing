package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesEditCancelledEventHandler extends EventHandler {
  void onEditCancelled(CategoriesEditCancelledEvent event);
}
