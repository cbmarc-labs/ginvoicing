/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.Category;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface CategoriesServiceAsync {
	public void save(Category category, AsyncCallback<Void> callback);
	public void delete(List<String> keys, AsyncCallback<Void> callback);
	
	public void select(String filter, AsyncCallback<List<Category>> callback);
	public void selectDisplay(AsyncCallback<List<EntityDisplay>> callback);
	public void selectById(String id, AsyncCallback<Category> callback);
}
