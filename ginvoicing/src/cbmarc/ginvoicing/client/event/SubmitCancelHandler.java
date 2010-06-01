package cbmarc.ginvoicing.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface SubmitCancelHandler extends EventHandler {
	public void onSubmit(SubmitCancelEvent event);
	public void onCancel(SubmitCancelEvent event);
}
