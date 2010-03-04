/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;


import cbmarc.ginvoicing.shared.entity.Category;
import cbmarc.ginvoicing.shared.entity.CategoryDisplay;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface CategoriesServiceAsync {
	public void delete(String id, AsyncCallback<Boolean> callback);
	public void save(Category bean, AsyncCallback<Category> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(ArrayList<String> keys, AsyncCallback<Void> callback);
	public void select(String filter, AsyncCallback<List<Category>> callback);
	public void selectDisplay(String filter, 
			AsyncCallback<List<CategoryDisplay>> callback);
	public void selectById(String id, AsyncCallback<Category> callback);
}
