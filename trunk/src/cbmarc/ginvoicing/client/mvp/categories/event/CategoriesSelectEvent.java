package cbmarc.ginvoicing.client.mvp.categories.event;


import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesSelectEvent 
		extends GwtEvent<CategoriesSelectEventHandler> {
  public static Type<CategoriesSelectEventHandler> TYPE = 
	  new Type<CategoriesSelectEventHandler>();
  private final Categories bean;
  
  public CategoriesSelectEvent(Categories bean) {
	  this.bean = bean;
  }
  
  public Categories getBean() { return bean; }
  
  @Override
  public Type<CategoriesSelectEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CategoriesSelectEventHandler handler) {
    handler.onSelect(this);
  }
}
