package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SubmitCancelEvent extends GwtEvent<SubmitCancelHandler> {
	public static final Type<SubmitCancelHandler> TYPE = 
		new Type<SubmitCancelHandler>();
	private enum Operation {SUBMIT, CANCEL};
	
	private Operation operation;
	
	public static SubmitCancelEvent submit() {
		return new SubmitCancelEvent(Operation.SUBMIT);
	}
	
	public static SubmitCancelEvent cancel() {
		return new SubmitCancelEvent(Operation.CANCEL);
	}
	
	private SubmitCancelEvent(Operation operation) {
		this.operation = operation;
	}

	public static Type<SubmitCancelHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SubmitCancelHandler handler) {
		switch(operation) {
		case SUBMIT:
			handler.onSubmit(this);
			break;
		case CANCEL:
			handler.onCancel(this);
			break;
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SubmitCancelHandler> getAssociatedType() {
		return getType();
	}
}
