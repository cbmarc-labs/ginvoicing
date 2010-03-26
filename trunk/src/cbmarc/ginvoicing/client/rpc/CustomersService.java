/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.shared.entity.Customer;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("customers")
public interface CustomersService extends RemoteService {
	void save(Customer bean) throws ServerException;
	void delete(ArrayList<String> ids) throws ServerException;
	
	List<Customer> select(String filter) throws ServerException;
	List<EntityDisplay> selectDisplay(String filter) throws ServerException;
	Customer selectById(String id) throws ServerException;
}
