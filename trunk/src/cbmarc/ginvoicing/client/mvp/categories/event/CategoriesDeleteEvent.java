package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesDeleteEvent extends GwtEvent<CategoriesDeleteEventHandler> {
  public static Type<CategoriesDeleteEventHandler> TYPE = 
	  new Type<CategoriesDeleteEventHandler>();
  
  @Override
  public Type<CategoriesDeleteEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CategoriesDeleteEventHandler handler) {
    handler.onDelete(this);
  }
}
