/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.shared.entity.Customer;
import cbmarc.ginvoicing.shared.entity.CustomerDisplay;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("customers")
public interface CustomersService extends RemoteService {
	Boolean delete(String id) throws ServerException;
	Customer save(Customer bean) throws ServerException;
	
	void delete(ArrayList<String> ids);
	List<Customer> select(String filter) throws ServerException;
	List<CustomerDisplay> selectDisplay(String filter) throws ServerException;
	
	Integer count();
	Customer selectById(String id);
}
