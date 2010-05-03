/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.Customer;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface CustomersServiceAsync {
	public void save(Customer bean, AsyncCallback<Void> callback);
	public void delete(List<String> keys, AsyncCallback<Void> callback);
	
	public void selectDisplay(AsyncCallback<List<EntityDisplay>> callback);
	public void selectById(String id, AsyncCallback<Customer> callback);
}
