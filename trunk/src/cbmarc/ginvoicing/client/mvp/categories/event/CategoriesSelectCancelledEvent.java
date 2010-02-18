package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesSelectCancelledEvent 
		extends GwtEvent<CategoriesSelectCancelledEventHandler> {
  public static Type<CategoriesSelectCancelledEventHandler> TYPE = 
	  new Type<CategoriesSelectCancelledEventHandler>();
  
  @Override
  public Type<CategoriesSelectCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CategoriesSelectCancelledEventHandler handler) {
    handler.onSelectCancelled(this);
  }
}
