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
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.LinesServiceAsync;
import cbmarc.ginvoicing.shared.entity.Line;
import cbmarc.ginvoicing.shared.entity.ProductDisplay;

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
		Integer getQuantity();
		void setQuantity(Integer value);
		
		String getProduct();
		void setProduct(List<ProductDisplay> products, String selected);
		
		public void focus();
		public void reset();
		
		public HandlerRegistration addSubmitCancelHandler(
				SubmitCancelHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private EventBus eventBus = EventBus.getEventBus();
	private LinesServiceAsync service = LinesEventBus.getService();
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
		
		service.save(line, new AppAsyncCallback<Line>() {

			@Override
			public void onSuccess(Line result) {
				eventBus.fireEvent(ListEditEvent.list());
			}
			
		});
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
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		display.setQuantity(line.getQuantity());
		
		ProductsEventBus.getService().selectDisplay(null, 
				new AppAsyncCallback<List<ProductDisplay>>() {
					
					@Override
					public void onSuccess(List<ProductDisplay> result) {
						display.setProduct(result, line.getProduct());
					}
					
		});
	}

	@Override
	public void onCancel(SubmitCancelEvent event) {
		eventBus.fireEvent(ListEditEvent.list());
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
