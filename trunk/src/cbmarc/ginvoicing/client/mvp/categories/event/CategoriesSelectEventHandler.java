package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.EventHandler;

public interface CategoriesSelectEventHandler extends EventHandler {
  void onSelect(CategoriesSelectEvent event);
}
