/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Product;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface ProductsServiceAsync {
	public void save(Product product, AsyncCallback<Void> callback);
	public void delete(List<String> keys, AsyncCallback<Void> callback);
	
	public void select(String filter, AsyncCallback<List<Product>> callback);
	public void selectDisplay(String filter, AsyncCallback<List<EntityDisplay>> callback);
	public void selectById(String id, AsyncCallback<Product> callback);
}
