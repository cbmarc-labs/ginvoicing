package cbmarc.ginvoicing.client.categories.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesEditEvent extends GwtEvent<CategoriesEditHandler> {
	public static final Type<CategoriesEditHandler> TYPE = 
		new Type<CategoriesEditHandler>();
	private enum Operation {LIST, SUBMIT, CANCEL};
	
	private Operation operation;
	
	public static CategoriesEditEvent list() {
		return new CategoriesEditEvent(Operation.LIST);
	}
	
	public static CategoriesEditEvent submit() {
		return new CategoriesEditEvent(Operation.SUBMIT);
	}
	
	public static CategoriesEditEvent cancel() {
		return new CategoriesEditEvent(Operation.CANCEL);
	}
	
	private CategoriesEditEvent(Operation operation) {
		this.operation = operation;
	}

	public static Type<CategoriesEditHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CategoriesEditHandler handler) {
		switch(operation) {
		case LIST:
			handler.onList(this);
			break;
		case SUBMIT:
			handler.onSubmit(this);
			break;
		case CANCEL:
			handler.onCancel(this);
			break;
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CategoriesEditHandler> getAssociatedType() {
		return getType();
	}
}
