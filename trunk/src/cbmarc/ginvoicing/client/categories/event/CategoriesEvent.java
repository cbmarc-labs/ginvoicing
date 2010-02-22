package cbmarc.ginvoicing.client.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesEvent extends GwtEvent<CategoriesHandler> {
	private static final Type<CategoriesHandler> TYPE = 
		new Type<CategoriesHandler>();
	private enum Operation {LIST, EDIT};
	
	private Operation operation;
	
	public static CategoriesEvent listPanel() {
		return new CategoriesEvent(Operation.LIST);
	}
	
	public static CategoriesEvent editPanel() {
		return new CategoriesEvent(Operation.EDIT);
	}
	
	private CategoriesEvent(Operation operation) {
		this.operation = operation;
	}

	public static Type<CategoriesHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CategoriesHandler handler) {
		switch(operation) {
		case LIST:
			handler.onList(this);
			break;
		case EDIT:
			handler.onEdit(this);
			break;
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CategoriesHandler> getAssociatedType() {
		return getType();
	}
}
