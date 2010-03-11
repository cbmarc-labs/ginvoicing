/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.shared.entity.Product;
import cbmarc.ginvoicing.shared.entity.ProductDisplay;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("products")
public interface ProductsService extends RemoteService {
	Boolean delete(String id) throws ServerException;
	Product save(Product product) throws ServerException;
	
	void delete(ArrayList<String> ids);
	List<Product> select(String filter) throws ServerException;
	List<ProductDisplay> selectDisplay(String filter) throws ServerException;
	
	Integer count();
	Product selectById(String id);
}
