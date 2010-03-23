/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.entity.InvoiceDisplay;
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
	void save(Invoice invoice) throws ServerException;
	
	void delete(ArrayList<String> ids);
	List<Invoice> select(String filter) throws ServerException;
	List<InvoiceDisplay> selectDisplay(String filter) throws ServerException;
	
	Integer count();
	Invoice selectById(String id);
}
