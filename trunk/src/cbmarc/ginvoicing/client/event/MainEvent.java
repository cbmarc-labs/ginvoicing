package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class MainEvent extends GwtEvent<MainHandler> {
	private static final Type<MainHandler> TYPE = new Type<MainHandler>();
	private enum Operation {HOME, CATEGORIES, PRODUCTS, CUSTOMERS, INVOICES};
	
	private Operation operation;
	
	public static MainEvent home() {
		return new MainEvent(Operation.HOME);
	}
	
	public static MainEvent categories() {
		return new MainEvent(Operation.CATEGORIES);
	}
	
	public static MainEvent products() {
		return new MainEvent(Operation.PRODUCTS);
	}
	
	public static MainEvent customers() {
		return new MainEvent(Operation.CUSTOMERS);
	}
	
	public static MainEvent invoices() {
		return new MainEvent(Operation.INVOICES);
	}
	
	private MainEvent(Operation operation) {
		this.operation = operation;
	}

	public static Type<MainHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(MainHandler handler) {
		switch(operation) {
		case HOME:
			handler.home(this);
			break;
		case CATEGORIES:
			handler.categories(this);
			break;
		case PRODUCTS:
			handler.products(this);
			break;
		case CUSTOMERS:
			handler.customers(this);
			break;
		case INVOICES:
			handler.invoices(this);
			break;
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MainHandler> getAssociatedType() {
		return getType();
	}
}
