/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.ProductsEventBus;
import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.ProductsServiceAsync;
import cbmarc.ginvoicing.shared.entity.Product;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
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
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
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
	public boolean doSave() {
		if(!hasValidInput()) return false;
		
		updateDataFromDisplay();
		
		service.save(product, new AppAsyncCallback<Product>() {

			@Override
			public void onSuccess(Product result) {
				//eventBus.fireEvent(ProductsEditEvent.submit());
			}
			
		});
		
		return true;
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();

		display.reset();
		if(product.getId() != null)
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
		// TODO
		// bean.setName(display.getName());
	}
	
	public void updateDisplayFromData() {
		display.setName(product.getName());
		display.setDescription(product.getDescription());
		// TODO
		// display.setName(bean.getName());
	}

	@Override
	public void onCancel(SubmitCancelEvent event) {
		// TODO Auto-generated method stub
		Window.alert("onCancel");
	}

	@Override
	public void onSubmit(SubmitCancelEvent event) {
		// TODO Auto-generated method stub
		Window.alert("onSubmit");
	}

}
