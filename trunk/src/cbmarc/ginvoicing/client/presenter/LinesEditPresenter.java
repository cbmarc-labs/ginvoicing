/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.LinesEventBus;
import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.LinesServiceAsync;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
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
		
		public void focus();
		public void reset();
		
		public HandlerRegistration addSubmitCancelHandler(
				SubmitCancelHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private LinesServiceAsync service = 
		LinesEventBus.getService();
	private Line invoiceLine = new Line();
	
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
	public void doSave() {
		updateDataFromDisplay();
		
		service.save(invoiceLine, new AppAsyncCallback<Line>() {

			@Override
			public void onSuccess(Line result) {
				//History.newItem("main/customers");
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void doLoad(String id) {
		service.selectById(id, new AppAsyncCallback<Line>() {

			@Override
			public void onSuccess(Line result) {
				invoiceLine = result;
				updateDisplayFromData();
				display.focus();
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	    
	    // TODO: fix up this shit
	    String token = History.getToken();
	    String[] parts = token.split("/");
	    if(parts.length > 3) doLoad(parts[parts.length - 1]);
	    else invoiceLine = new Line();

		updateDisplayFromData();
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		invoiceLine.setQuantity(display.getQuantity());
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		display.setQuantity(invoiceLine.getQuantity());
	}

	@Override
	public void onCancel(SubmitCancelEvent event) {
		//History.newItem("main/customers");
	}

	@Override
	public void onSubmit(SubmitCancelEvent event) {
		if(hasValidInput())
			doSave();
	}

	@Override
	public void processHistoryToken(String token) {
		// TODO Auto-generated method stub
		
	}

}
