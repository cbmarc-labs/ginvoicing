/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Invoice;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface InvoicesServiceAsync {
	public void save(Invoice invoice, AsyncCallback<Void> callback);	
	public void delete(List<String> keys, AsyncCallback<Void> callback);
	
	public void selectDisplay(String filter, AsyncCallback<List<EntityDisplay>> callback);
	public void selectById(String id, AsyncCallback<Invoice> callback);
}
