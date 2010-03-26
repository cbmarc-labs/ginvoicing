/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.shared.entity.EntityDisplay;
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
	void save(Invoice invoice) throws ServerException;
	void delete(ArrayList<String> ids) throws ServerException;
	
	List<Invoice> select(String filter) throws ServerException;
	List<EntityDisplay> selectDisplay(String filter) throws ServerException;
	Invoice selectById(String id) throws ServerException;
}
