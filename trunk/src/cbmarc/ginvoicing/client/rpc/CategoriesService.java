/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;


import cbmarc.ginvoicing.shared.entity.Category;
import cbmarc.ginvoicing.shared.entity.CategoryDisplay;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("categories")
public interface CategoriesService extends RemoteService {
	Boolean delete(String id) throws ServerException;
	Category save(Category bean) throws ServerException;
	
	void delete(ArrayList<String> ids);
	List<Category> select(String filter) throws ServerException;
	List<CategoryDisplay> selectDisplay(String filter) throws ServerException;
	
	Integer count();
	Category selectById(String id);
}
