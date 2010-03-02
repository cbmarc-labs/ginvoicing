/**
 * 
 */
package cbmarc.ginvoicing.client.products.presenter;

import cbmarc.ginvoicing.client.AppAsyncCallback;
import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.products.ProductsServiceAsync;
import cbmarc.ginvoicing.client.products.event.ProductsEditEvent;
import cbmarc.ginvoicing.client.products.event.ProductsEditHandler;
import cbmarc.ginvoicing.client.products.event.ProductsEventBus;
import cbmarc.ginvoicing.shared.entity.Product;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ProductsEditPresenter implements Presenter, ProductsEditHandler {
	
	public interface Display {
		String getName();
		void setName(String value);
		
		String getDescription();
		void setDescription(String value);
		
		public void reset();
		public void focus();
		
		public HandlerRegistration addHandler(ProductsEditHandler handler);
		Widget asWidget();
	}
	
	private ProductsServiceAsync service = ProductsEventBus.getService();
	private final Display display;
	
	private Product product = new Product();
	
	public ProductsEditPresenter(Display view) {
	    this.display = view;
	    
		bind();
	}
	
	private void bind() {
		display.addHandler(this);
		
		//eventBus.addHandler(CategoriesSelectEvent.getType(), this);
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
	public void onCancel(ProductsEditEvent event) {
		// TODO Auto-generated method stub
		Window.alert("onCancel");
	}

	@Override
	public void onList(ProductsEditEvent event) {
		// TODO Auto-generated method stub
		Window.alert("onList");
	}

	@Override
	public void onSubmit(ProductsEditEvent event) {
		// TODO Auto-generated method stub
		Window.alert("onSubmit");
	}

	@Override
	public void onListCategory(ProductsEditEvent event) {
		// TODO Auto-generated method stub
		Window.alert("onListCategory");
	}

}
