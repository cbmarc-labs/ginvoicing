/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.LinesEventBus;
import cbmarc.ginvoicing.client.event.ListEditEvent;
import cbmarc.ginvoicing.client.event.ProductsEventBus;
import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.i18n.AppMessages;
import cbmarc.ginvoicing.client.i18n.LinesConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
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
		
		String getPrice();
		void setPrice(String value);
		
		public void focus();
		public void reset();
		
		public HandlerRegistration addSubmitCancelHandler(
				SubmitCancelHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private EventBus eventBus = EventBus.getEventBus();
	private LinesConstants constants = LinesEventBus.getConstants();
	private AppMessages messages = EventBus.getMessages();
	
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
		StringBuilder sb = new StringBuilder();
		
		String prod = null;
		EntityDisplay product = display.getProduct();
		if(product != null) prod = product.getData()[0];
		
		if(!FieldVerifier.isValidNumber(display.getQuantity())) {
			sb.append(messages.errorField(constants.formQuantity()) + "\n");
			valid = false;
		}
		
		if(!FieldVerifier.isValidString(prod)) {
			sb.append(messages.errorField(constants.formProductName()) + "\n");
			valid = false;
		}
		
		if(!FieldVerifier.isValidNumber(display.getPrice())) {
			sb.append(messages.errorField(constants.formPrice()) + "\n");
			valid = false;
		}
		
		sb.append("   ");
		if(valid == false)
			Window.alert(sb.toString());
		
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
		line.setQuantity(Float.parseFloat(display.getQuantity()));
		
		EntityDisplay product = display.getProduct();
		line.setProduct(product.getData()[0]);
		line.setProductName(product.getData()[1]);
		
		line.setPrice(Float.parseFloat(display.getPrice()));
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		display.setQuantity(line.getQuantity().toString());
		display.setPrice(line.getPrice().toString());
		
		ProductsEventBus.getService().selectDisplay(null, 
				new AppAsyncCallback<List<EntityDisplay>>() {
					
					@Override
					public void onSuccess(List<EntityDisplay> result) {
						display.setProduct(result, line.getProduct());
						
						// no update price if is a modification
						if(line.getProduct() != null)
							display.setPrice(line.getPrice().toString());
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

}
