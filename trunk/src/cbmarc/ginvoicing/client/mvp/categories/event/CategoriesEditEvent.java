package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesEditEvent extends GwtEvent<CategoriesEditEventHandler> {
  public static Type<CategoriesEditEventHandler> TYPE = 
	  new Type<CategoriesEditEventHandler>();
  private final String key;
  
  public CategoriesEditEvent(String key) {
	  this.key = key;
  }
  
  public String getKey() { return key; }
  
  @Override
  public Type<CategoriesEditEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CategoriesEditEventHandler handler) {
    handler.onEdit(this);
  }
}
