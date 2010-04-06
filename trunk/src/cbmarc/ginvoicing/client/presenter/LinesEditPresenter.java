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
		
		EntityDisplay getProduct();
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
		
		eventBus.fireEvent(ListEditEvent.list(line));
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
		this.line = line;
	}

	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		line.setQuantity(display.getQuantity());
		
		EntityDisplay product = display.getProduct();
		line.setProduct(product.getData()[0]);
		line.setProductName(product.getData()[1]);
		
		line.setPrice(display.getProductPrice());
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		display.setQuantity(line.getQuantity());
		display.setProductPrice(line.getPrice());
		
		ProductsEventBus.getService().selectDisplay(null, 
				new AppAsyncCallback<List<EntityDisplay>>() {
					
					@Override
					public void onSuccess(List<EntityDisplay> result) {
						display.setProduct(result, line.getProduct());
						
						//if(line.getPrice() != null)
						//	display.setProductPrice(line.getPrice());
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
