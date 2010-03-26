/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.event.ProductsEventBus;
import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.ProductsServiceAsync;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Product;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ProductsEditPresenter implements Presenter, SubmitCancelHandler {
	
	public interface Display {
		String getName();
		void setName(String value);
		
		String getDescription();
		void setDescription(String value);
		
		String getCategory();
		void setCategory(List<EntityDisplay> categories, String selected);
		
		String getPrice();
		void setPrice(String value);
		
		public void reset();
		public void focus();
		
		public HandlerRegistration addSubmitCancelHandler(
				SubmitCancelHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private ProductsServiceAsync service = ProductsEventBus.getService();
	private Product product = new Product();
	
	public ProductsEditPresenter(Display view) {
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
		// TODO check input values
		
		return valid;
	}

	/**
	 * @return
	 */
	public void doSave() {
		updateDataFromDisplay();
		
		service.save(product, new AppAsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				History.newItem("main/products");
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void doLoad(String id) {
		service.selectById(id, new AppAsyncCallback<Product>() {

			@Override
			public void onSuccess(Product result) {
				product = result;
				updateDisplayFromData();
				display.focus();
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		product = new Product();
		
		String[] parts = History.getToken().split("/");
	    if(parts.length > 3) 
	    	doLoad(parts[parts.length - 1]);

	    updateDisplayFromData();
	    container.add(display.asWidget());
		display.focus();
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		product.setName(display.getName());
		product.setDescription(display.getDescription());
		product.setCategory(display.getCategory());
		product.setPrice(display.getPrice());
	}
	
	public void updateDisplayFromData() {
		display.reset();
		display.setName(product.getName());
		display.setDescription(product.getDescription());
		
		CategoriesEventBus.getService().selectDisplay(null, 
				new AppAsyncCallback<List<EntityDisplay>>() {
					
					@Override
					public void onSuccess(List<EntityDisplay> result) {
						display.setCategory(result, product.getCategory());
					}
					
		});
		
		display.setPrice(product.getPrice());
	}

	@Override
	public void onCancel(SubmitCancelEvent event) {
		// TODO: check this
		History.newItem("main/products");
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
