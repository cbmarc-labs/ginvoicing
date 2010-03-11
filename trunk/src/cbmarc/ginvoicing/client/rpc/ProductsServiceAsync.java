/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.shared.entity.Product;
import cbmarc.ginvoicing.shared.entity.ProductDisplay;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface ProductsServiceAsync {
	public void delete(String id, AsyncCallback<Boolean> callback);
	public void save(Product bean, AsyncCallback<Product> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(ArrayList<String> keys, AsyncCallback<Void> callback);
	public void select(String filter, AsyncCallback<List<Product>> callback);
	public void selectDisplay(String filter, 
			AsyncCallback<List<ProductDisplay>> callback);
	public void selectById(String id, AsyncCallback<Product> callback);
}
