/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("invoices")
public interface InvoicesService extends RemoteService {
	Boolean delete(String id) throws ServerException;
	Invoice save(Invoice bean) throws ServerException;
	
	void delete(ArrayList<String> ids);
	List<Invoice> select(String filter) throws ServerException;
	
	Integer count();
	Invoice selectById(String id);
}
