package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesCreatedEvent 
		extends GwtEvent<CategoriesCreatedEventHandler> {
	public static Type<CategoriesCreatedEventHandler> TYPE = 
		new Type<CategoriesCreatedEventHandler>();
  
	@Override
	public Type<CategoriesCreatedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CategoriesCreatedEventHandler handler) {
		handler.onCreated(this);
	}
}
