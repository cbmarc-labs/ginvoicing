/**
 * 
 */
package cbmarc.ginvoicing.client.rpc;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.entity.Line;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("lines")
public interface LinesService extends RemoteService {
	Boolean delete(String id) throws ServerException;
	void saveList(Invoice invoice, List<Line> list) throws ServerException;
	
	void delete(List<String> ids);
	List<Line> select(String filter) throws ServerException;
	
	Integer count();
	Line selectById(String id);
}
