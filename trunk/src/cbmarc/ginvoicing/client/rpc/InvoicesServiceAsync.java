/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.entity.InvoiceDisplay;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface InvoicesServiceAsync {
	public void delete(String id, AsyncCallback<Boolean> callback);
	public void save(Invoice invoice, AsyncCallback<Void> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(ArrayList<String> keys, AsyncCallback<Void> callback);
	public void select(String filter, AsyncCallback<List<Invoice>> callback);
	public void selectDisplay(String filter, AsyncCallback<List<InvoiceDisplay>> callback);
	public void selectById(String id, AsyncCallback<Invoice> callback);
}
