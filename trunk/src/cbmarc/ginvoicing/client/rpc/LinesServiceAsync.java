/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface LinesServiceAsync {
	public void delete(String id, AsyncCallback<Boolean> callback);
	public void save(Line bean, AsyncCallback<Line> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(ArrayList<String> keys, AsyncCallback<Void> callback);
	public void select(String filter, AsyncCallback<List<Line>> callback);
	public void selectById(String id, AsyncCallback<Line> callback);
}
