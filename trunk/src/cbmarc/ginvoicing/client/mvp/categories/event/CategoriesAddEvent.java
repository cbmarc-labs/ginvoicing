package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesAddEvent extends GwtEvent<CategoriesAddEventHandler> {
  public static Type<CategoriesAddEventHandler> TYPE = 
	  new Type<CategoriesAddEventHandler>();
  
  @Override
  public Type<CategoriesAddEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CategoriesAddEventHandler handler) {
    handler.onAdd(this);
  }
}
