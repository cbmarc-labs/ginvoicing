/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.ListEditEvent;
import cbmarc.ginvoicing.client.event.ProductsEventBus;
import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class LinesEditPresenter 
		implements Presenter, SubmitCancelHandler {
	
	public interface Display {
		String getQuantity();
		void setQuantity(String value);
		
		String getProduct();
		void setProduct(List<EntityDisplay> list, String selected);
		
		String getProductPrice();
		void setProductPrice(String value);
		
		public void focus();
		public void reset();
		
		public HandlerRegistration addSubmitCancelHandler(
				SubmitCancelHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private EventBus eventBus = EventBus.getEventBus();
	private boolean isInsert = false;
	private Line line = new Line();
	
	public LinesEditPresenter(Display view) {
	    this.display = view;
		
		bind();
	}
	
	private void bind() {
		display.addSubmitCancelHandler(this);
	}
	
	/**
	 * @return
	 */
	protected boolean hasValidInput() {
		boolean valid = true;
		
		return valid;
	}

	/**
	 * @return
	 */
	private void doSave() {
		updateDataFromDisplay();
		
		if(isInsert == true)
			eventBus.fireEvent(ListEditEvent.list(line));
		else
			eventBus.fireEvent(ListEditEvent.list(null));
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());

		updateDisplayFromData();
	}
	
	/**
	 * @param line the line to set
	 */
	public void setLine(Line line) {
		isInsert = false;
		
		if(line == null) {
			isInsert = true;
			
			line = new Line();
		}
		
		this.line = line;
	}

	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		line.setQuantity(display.getQuantity());
		line.setProduct(display.getProduct());		
		line.setProductPrice(display.getProductPrice());
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		display.setQuantity(line.getQuantity());
		
		ProductsEventBus.getService().selectDisplay(null, 
				new AppAsyncCallback<List<EntityDisplay>>() {
					
					@Override
					public void onSuccess(List<EntityDisplay> result) {
						display.setProduct(result, line.getProduct());
					}
					
		});
	}

	@Override
	public void onCancel(SubmitCancelEvent event) {
		eventBus.fireEvent(ListEditEvent.list(null));
	}

	@Override
	public void onSubmit(SubmitCancelEvent event) {
		if(hasValidInput())
			doSave();
	}

	@Override
	public void processHistoryToken(String token) {
		// Nothing to do.
	}

}
