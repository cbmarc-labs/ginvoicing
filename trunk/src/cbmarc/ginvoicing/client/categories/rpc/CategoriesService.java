/**
 * 
 */
package cbmarc.ginvoicing.client.categories.rpc;

import java.util.ArrayList;
import java.util.List;


import cbmarc.ginvoicing.shared.entity.Categories;
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
	Categories save(Categories bean) throws ServerException;
	
	void delete(ArrayList<String> ids);
	List<Categories> select(String filter);
	
	Integer count();
	Categories selectById(String id);
}
