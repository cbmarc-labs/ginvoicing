package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesEditCancelledEvent 
		extends GwtEvent<CategoriesEditCancelledEventHandler> {
  public static Type<CategoriesEditCancelledEventHandler> TYPE = 
	  new Type<CategoriesEditCancelledEventHandler>();
  
  @Override
  public Type<CategoriesEditCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CategoriesEditCancelledEventHandler handler) {
    handler.onEditCancelled(this);
  }
}
