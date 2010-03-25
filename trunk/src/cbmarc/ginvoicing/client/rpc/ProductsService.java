/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Product;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("products")
public interface ProductsService extends RemoteService {
	void save(Product product) throws ServerException;
	void delete(ArrayList<String> ids) throws ServerException;
	
	List<Product> select(String filter) throws ServerException;
	List<EntityDisplay> selectDisplay(String filter) throws ServerException;
	Product selectById(String id);
}
