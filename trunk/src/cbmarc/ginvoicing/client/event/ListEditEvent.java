package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author MCOSTA
 *
 */
public class ListEditEvent extends GwtEvent<ListEditHandler> {
	public static final Type<ListEditHandler> TYPE = 
		new Type<ListEditHandler>();
	private enum Operation {LIST, EDIT};
	
	private Operation operation;
	private Object object;
	
	public static ListEditEvent list() {
		return new ListEditEvent(Operation.LIST);
	}
	
	public static ListEditEvent edit(Object object) {
		return new ListEditEvent(Operation.EDIT, object);
	}
	
	private ListEditEvent(Operation operation) {
		this.operation = operation;
	}
	
	private ListEditEvent(Operation operation, Object object) {
		this.operation = operation;
		this.object = object;
	}

	public static Type<ListEditHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ListEditHandler handler) {
		switch(operation) {
		case LIST:
			handler.onList(this);
			break;
		case EDIT:
			handler.onEdit(this, object);
			break;
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ListEditHandler> getAssociatedType() {
		return getType();
	}
}
