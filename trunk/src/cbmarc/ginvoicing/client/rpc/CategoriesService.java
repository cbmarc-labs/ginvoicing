/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.Category;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("categories")
public interface CategoriesService extends RemoteService {
	void save(Category category) throws ServerException;
	void delete(List<String> ids) throws ServerException;
	
	List<Category> select(String filter) throws ServerException;
	List<EntityDisplay> selectDisplay(String filter) throws ServerException;
	Category selectById(String id) throws ServerException;
}
