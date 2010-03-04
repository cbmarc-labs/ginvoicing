package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesEvent extends GwtEvent<CategoriesHandler> {
	private static final Type<CategoriesHandler> TYPE = 
		new Type<CategoriesHandler>();
	private enum Operation {LIST, EDIT};
	
	private Operation operation;
	private String id;
	
	public static CategoriesEvent listPanel() {
		return new CategoriesEvent(Operation.LIST);
	}
	
	public static CategoriesEvent editPanel(String id) {
		return new CategoriesEvent(Operation.EDIT, id);
	}
	
	private CategoriesEvent(Operation operation) {
		this.operation = operation;
	}
	
	private CategoriesEvent(Operation operation, String id) {
		this.operation = operation;
		this.id = id;
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
			handler.onEdit(this, id);
			break;
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CategoriesHandler> getAssociatedType() {
		return getType();
	}
}
