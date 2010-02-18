package cbmarc.ginvoicing.client.mvp.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesSaveEvent extends GwtEvent<CategoriesSaveEventHandler> {
	public static Type<CategoriesSaveEventHandler> TYPE = 
		new Type<CategoriesSaveEventHandler>();

	@Override
	public Type<CategoriesSaveEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CategoriesSaveEventHandler handler) {
	  handler.onSave(this);
	}
}
