/**
 * 
 */
package cbmarc.ginvoicing.client.products.presenter;

import cbmarc.ginvoicing.client.Presenter;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectHandler;
import cbmarc.ginvoicing.client.categories.presenter.CategoriesSelectPresenter;
import cbmarc.ginvoicing.client.categories.view.CategoriesSelectView;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.products.ProductsServiceAsync;
import cbmarc.ginvoicing.client.products.event.ProductsEditEvent;
import cbmarc.ginvoicing.client.products.event.ProductsEditHandler;
import cbmarc.ginvoicing.client.products.event.ProductsEventBus;
import cbmarc.ginvoicing.shared.entity.Product;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ProductsEditPresenter 
		implements Presenter, ProductsEditHandler, CategoriesSelectHandler {
	
	public interface Display {
		TextBox getName();
		
		Panel getListCategoryPanel();
		
		void reset();
		public HandlerRegistration addHandler(ProductsEditHandler handler);
		Widget asWidget();
	}
	
	private EventBus eventBus = EventBus.getEventBus();
	private ProductsServiceAsync service = ProductsEventBus.getService();
	private final Display display;
	
	private CategoriesSelectView categoriesSelectView;
	private CategoriesSelectPresenter categoriesSelectPresenter;
	
	private Product product = new Product();
	
	public ProductsEditPresenter(Display view) {
	    this.display = view;
		
	    categoriesSelectView = new CategoriesSelectView();
	    categoriesSelectPresenter = 
	    	new CategoriesSelectPresenter(categoriesSelectView);
	    //categoriesSelectView.addHandler(this);
	    categoriesSelectPresenter.go(display.getListCategoryPanel());
	    
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
		
		service.save(product, new AsyncCallback<Product>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save failed: " + caught.toString());
			}

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
	    display.getName().setFocus(true);
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		product.setName(display.getName().getValue());
		// TODO
		// bean.setName(display.getName());
	}
	
	public void updateDisplayFromData() {
		display.getName().setValue(product.getName());
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

	@Override
	public void onCancel(CategoriesSelectEvent event) {
		// TODO Auto-generated method stub

		Window.alert("onCancel(CategoriesSelectEvent event)");
	}

	@Override
	public void onReload(CategoriesSelectEvent event) {
		// TODO Auto-generated method stub

		Window.alert("onReload(CategoriesSelectEvent event)");
	}

	@Override
	public void onTableClicked(CategoriesSelectEvent event, int row) {
		// TODO Auto-generated method stub

		Window.alert("onTableClicked(CategoriesSelectEvent event, int row)");
	}

}
