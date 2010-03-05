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
import cbmarc.ginvoicing.shared.entity.CategoryDisplay;
import cbmarc.ginvoicing.shared.entity.Product;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
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
		
		public void setCategory(List<CategoryDisplay> categories);
		
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
	public void doSave() {
		updateDataFromDisplay();
		
		service.save(product, new AppAsyncCallback<Product>() {
			
			@Override
			public void onSuccess(Product result) {
				//eventBus.fireEvent(ProductsEditEvent.submit());
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();

		display.reset();
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
		
		CategoriesEventBus.getService().selectDisplay(null, 
				new AppAsyncCallback<List<CategoryDisplay>>() {
					
					@Override
					public void onSuccess(List<CategoryDisplay> result) {
						display.setCategory(result);
					}
					
		});
		
		// TODO
		// display.setName(bean.getName());
	}

	@Override
	public void onCancel(SubmitCancelEvent event) {
		History.newItem("main/products");
	}

	@Override
	public void onSubmit(SubmitCancelEvent event) {
		// TODO Auto-generated method stub
		//if(!hasValidInput()) doSave();
		Window.alert("onSubmit");
	}

}
