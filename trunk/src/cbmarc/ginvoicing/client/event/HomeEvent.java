package cbmarc.ginvoicing.client.event;


import com.google.gwt.event.shared.GwtEvent;

public class HomeEvent extends GwtEvent<HomeHandler> {
	private static final Type<HomeHandler> TYPE = new Type<HomeHandler>();
	private enum Operation {HOME, CATEGORIES, PRODUCTS};
	
	private Operation operation;
	
	public static HomeEvent home() {
		return new HomeEvent(Operation.HOME);
	}
	
	public static HomeEvent categories() {
		return new HomeEvent(Operation.CATEGORIES);
	}
	
	public static HomeEvent products() {
		return new HomeEvent(Operation.PRODUCTS);
	}
	
	private HomeEvent(Operation operation) {
		this.operation = operation;
	}

	public static Type<HomeHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(HomeHandler handler) {
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
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<HomeHandler> getAssociatedType() {
		return getType();
	}
}
