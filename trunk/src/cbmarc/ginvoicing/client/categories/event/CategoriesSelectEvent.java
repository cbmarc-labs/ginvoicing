package cbmarc.ginvoicing.client.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesSelectEvent extends GwtEvent<CategoriesSelectHandler> {
	public static final Type<CategoriesSelectHandler> TYPE = 
		new Type<CategoriesSelectHandler>();
	private enum Operation {RELOAD, CANCEL, TABLE};
	
	private Operation operation;
	private int row;
	
	public static CategoriesSelectEvent reload() {
		return new CategoriesSelectEvent(Operation.RELOAD);
	}
	
	public static CategoriesSelectEvent cancel() {
		return new CategoriesSelectEvent(Operation.CANCEL);
	}
	
	public static CategoriesSelectEvent table(int row) {
		return new CategoriesSelectEvent(Operation.TABLE, row);
	}
	
	private CategoriesSelectEvent(Operation operation) {
		this.operation = operation;
	}
	
	private CategoriesSelectEvent(Operation operation, int row) {
		this.operation = operation;
		this.row = row;
	}

	public static Type<CategoriesSelectHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CategoriesSelectHandler handler) {
		switch(operation) {
		case RELOAD:
			handler.onReload(this);
			break;
		case CANCEL:
			handler.onCancel(this);
			break;
		case TABLE:
			handler.onTableClicked(this, row);
			break;
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CategoriesSelectHandler> getAssociatedType() {
		return getType();
	}
}
