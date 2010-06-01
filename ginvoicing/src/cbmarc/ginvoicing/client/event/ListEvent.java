package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ListEvent extends GwtEvent<ListHandler> {
	public static final Type<ListHandler> TYPE = 
		new Type<ListHandler>();
	private enum Operation {RELOAD, ADD, DELETE, LIST, FILTER};
	
	private Operation operation;
	private int row;
	private String filter;
	
	public static ListEvent reload() {
		return new ListEvent(Operation.RELOAD);
	}
	
	public static ListEvent add() {
		return new ListEvent(Operation.ADD);
	}
	
	public static ListEvent delete() {
		return new ListEvent(Operation.DELETE);
	}
	
	public static ListEvent list(int row) {
		return new ListEvent(Operation.LIST, row);
	}
	
	public static ListEvent filter(String filter) {
		return new ListEvent(Operation.FILTER, filter);
	}
	
	private ListEvent(Operation operation) {
		this.operation = operation;
	}
	
	private ListEvent(Operation operation, int row) {
		this.operation = operation;
		this.row = row;
	}
	
	private ListEvent(Operation operation, String filter) {
		this.operation = operation;
		this.filter = filter;
	}

	public static Type<ListHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ListHandler handler) {
		switch(operation) {
		case RELOAD:
			handler.onReload(this);
			break;
		case ADD:
			handler.onAdd(this);
			break;
		case DELETE:
			handler.onDelete(this);
			break;
		case LIST:
			handler.onList(this, row);
			break;
		case FILTER:
			handler.onFilter(this, filter);
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ListHandler> getAssociatedType() {
		return getType();
	}
}
