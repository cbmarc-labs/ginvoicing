package cbmarc.ginvoicing.client.products.event;

import com.google.gwt.event.shared.GwtEvent;

public class ProductsEditEvent extends GwtEvent<ProductsEditHandler> {
	public static final Type<ProductsEditHandler> TYPE = 
		new Type<ProductsEditHandler>();
	private enum Operation {LIST, SUBMIT, CANCEL, LISTCATEGORY};
	
	private Operation operation;
	
	public static ProductsEditEvent list() {
		return new ProductsEditEvent(Operation.LIST);
	}
	
	public static ProductsEditEvent submit() {
		return new ProductsEditEvent(Operation.SUBMIT);
	}
	
	public static ProductsEditEvent cancel() {
		return new ProductsEditEvent(Operation.CANCEL);
	}
	
	public static ProductsEditEvent listCategory() {
		return new ProductsEditEvent(Operation.LISTCATEGORY);
	}
	
	private ProductsEditEvent(Operation operation) {
		this.operation = operation;
	}

	public static Type<ProductsEditHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ProductsEditHandler handler) {
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
		case LISTCATEGORY:
			handler.onListCategory(this);
			break;
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ProductsEditHandler> getAssociatedType() {
		return getType();
	}
}
