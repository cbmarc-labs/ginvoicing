package cbmarc.ginvoicing.client.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesListEvent extends GwtEvent<CategoriesListHandler> {
	public static final Type<CategoriesListHandler> TYPE = 
		new Type<CategoriesListHandler>();
	private enum Operation {RELOAD, ADD, DELETE, TABLE};
	
	private Operation operation;
	private int row;
	
	public static CategoriesListEvent reload() {
		return new CategoriesListEvent(Operation.RELOAD);
	}
	
	public static CategoriesListEvent add() {
		return new CategoriesListEvent(Operation.ADD);
	}
	
	public static CategoriesListEvent delete() {
		return new CategoriesListEvent(Operation.DELETE);
	}
	
	public static CategoriesListEvent table(int row) {
		return new CategoriesListEvent(Operation.TABLE, row);
	}
	
	private CategoriesListEvent(Operation operation) {
		this.operation = operation;
	}
	
	private CategoriesListEvent(Operation operation, int row) {
		this.operation = operation;
		this.row = row;
	}

	public static Type<CategoriesListHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CategoriesListHandler handler) {
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
		case TABLE:
			handler.onTableClicked(this, row);
			break;
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CategoriesListHandler> getAssociatedType() {
		return getType();
	}
}
