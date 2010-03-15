/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface LinesServiceAsync {
	public void delete(String id, AsyncCallback<Boolean> callback);
	public void saveList(Invoice invoice, List<Line> list, AsyncCallback<Void> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(List<String> keys, AsyncCallback<Void> callback);
	public void select(String filter, AsyncCallback<List<Line>> callback);
	public void selectById(String id, AsyncCallback<Line> callback);
}
