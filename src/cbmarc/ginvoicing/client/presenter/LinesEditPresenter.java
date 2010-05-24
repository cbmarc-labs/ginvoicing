/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.LinesEventBus;
import cbmarc.ginvoicing.client.event.ListEditEvent;
import cbmarc.ginvoicing.client.event.ProductsEventBus;
import cbmarc.ginvoicing.client.i18n.AppMessages;
import cbmarc.ginvoicing.client.i18n.LinesConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.view.invoices.LinesEditView;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class LinesEditPresenter implements Presenter, LinesEditView.Presenter {
		
	private final LinesEditView view;

	private EventBus eventBus = EventBus.getEventBus();
	private LinesConstants constants = LinesEventBus.getConstants();
	private AppMessages messages = EventBus.getMessages();
	
	private Line line;
	
	public LinesEditPresenter(LinesEditView view, Line line) {
	    this.view = view;
	    view.setPresenter(this);
	    
	    this.line = new Line();
	    if(line != null)
	    	this.line = line;
	    
	    updateDisplayFromData();
	}
	
	/**
	 * @return
	 */
	public boolean hasValidInput() {
		boolean valid = true;
		StringBuilder sb = new StringBuilder();
		
		if(!FieldVerifier.isValidNumber(view.getQuantity().getValue())) {
			sb.append(messages.errorField(constants.formQuantity()) + "\n");
			valid = false;
		}
		
		String prod = null;
        EntityDisplay product = view.getProduct();
        if(product != null) prod = product.getData()[0];
		
		if(!FieldVerifier.isValidString(prod)) {
			sb.append(messages.errorField(constants.formProductName()) + "\n");
			valid = false;
		}
		
		if(!FieldVerifier.isValidNumber(view.getPrice().getValue())) {
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
	    container.add(view.asWidget());
	    
		view.focus();
	}

	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		line.setQuantity(Float.parseFloat(view.getQuantity().getValue()));
		
		EntityDisplay product = view.getProduct();
		line.setProduct(product.getData()[0]);
		line.setProductName(product.getData()[1]);
		
		line.setPrice(Float.parseFloat(view.getPrice().getValue()));
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		view.reset();
		view.getQuantity().setValue(line.getQuantity().toString());
		updateProductsList();
		view.getPrice().setValue(line.getPrice().toString());
		view.focus();
	}
	
	private void updateProductsList() {
		view.getProductList().setEnabled(false);
		ProductsEventBus.getService().selectDisplay(null, 
				new AppAsyncCallback<List<EntityDisplay>>() {
					
					@Override
					public void onSuccess(List<EntityDisplay> result) {
						if(!result.isEmpty()) {
							view.getProductList().setEnabled(false);
							view.setProductList(result, line.getProduct());
						}
						
						// no update price if is a modification
						if(line.getProduct() != null)
							view.getPrice().setValue(line.getPrice().toString());
					}
					
		});
	}

	@Override
	public void onCancelButtonClicked() {
		eventBus.fireEvent(ListEditEvent.list(null));
	}

	@Override
	public void onListButtonClicked() {
		eventBus.fireEvent(ListEditEvent.list(null));
	}

	@Override
	public void onSubmitButtonClicked() {
		if(hasValidInput()) doSave();
	}

	@Override
	public void onProductsReloadButtonClicked() {
		updateProductsList();
	}

}
