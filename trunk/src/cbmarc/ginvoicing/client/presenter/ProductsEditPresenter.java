/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.ProductsEventBus;
import cbmarc.ginvoicing.client.i18n.AppMessages;
import cbmarc.ginvoicing.client.i18n.ProductsConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.ProductsServiceAsync;
import cbmarc.ginvoicing.client.view.products.ProductsEditView;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.Category;
import cbmarc.ginvoicing.shared.entity.Product;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class ProductsEditPresenter 
		implements Presenter, ProductsEditView.Presenter {
	
	private final ProductsEditView view;
	
	private ProductsServiceAsync service = ProductsEventBus.getService();
	private ProductsConstants constants = ProductsEventBus.getConstants();
	private AppMessages messages = EventBus.getMessages();
	
	private Product product;
	
	public ProductsEditPresenter(ProductsEditView view, String id) {
	    this.view = view;
	    view.setPresenter(this);
	    
	    product = new Product();
	    if(id != null) doLoad(id);
	    	else updateDisplayFromData();
	}
	
	private void updateCategoryList() {
		view.getCategoryList().setEnabled(false);
		CategoriesEventBus.getService().select(null, 
				new AppAsyncCallback<List<Category>>() {

					@Override
					public void onSuccess(List<Category> result) {
						view.setCategoryList(result, product.getCategory());
					}
			
		});
	}
	
	/**
	 * @return
	 */
	public boolean hasValidInput() {
		boolean valid = true;
		StringBuilder sb = new StringBuilder();
		
		if(!FieldVerifier.isValidString(view.getName().getValue())) {
			sb.append(messages.errorField(constants.formName()) + "\n");
			valid = false;
		}
		
		if(!FieldVerifier.isValidString(view.getCategory())) {
			sb.append(messages.errorField(constants.formCategory()) + "\n");
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
		view.getFormPanel().setVisible(false);
		view.getLoadingPanel().setVisible(true);
		service.selectById(id, new AppAsyncCallback<Product>() {

			@Override
			public void onSuccess(Product result) {
				product = result;
				view.getFormPanel().setVisible(true);
				view.getLoadingPanel().setVisible(false);
				updateDisplayFromData();
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());

		view.focus();
	}
	
	public void updateDataFromDisplay() {
		product.setName(view.getName().getValue());
		product.setDescription(view.getDescription().getValue());
		product.setCategory(view.getCategory());
		product.setPrice(Float.parseFloat(view.getPrice().getValue()));
	}
	
	public void updateDisplayFromData() {
		view.reset();
		view.getName().setValue(product.getName());
		view.getDescription().setValue(product.getDescription());
		updateCategoryList();
		view.getPrice().setValue(product.getPrice().toString());
		view.focus();
	}

	@Override
	public void onCancelButtonClicked() {
		History.newItem("main/products");
	}

	@Override
	public void onListButtonClicked() {
		History.newItem("main/products");
	}

	@Override
	public void onSubmitButtonClicked() {
		if(hasValidInput()) doSave();
	}

	@Override
	public void onCategoriesReloadButtonClicked() {
		updateCategoryList();
	}

}
