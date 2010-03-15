/**
 * 
 */
package cbmarc.ginvoicing.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.ginvoicing.client.rpc.LinesService;
import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.entity.Line;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class LinesServiceImpl extends RemoteServiceServlet 
		implements LinesService {

	/**
	 * 
	 */
	public LinesServiceImpl() {
	}

	@Override
	public Boolean delete(String id) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			Line bean = pm.getObjectById(Line.class, id);
			pm.deletePersistent(bean);
			
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return true;
	}

	@Override
	public void delete(List<String> ids) {
		for(String i : ids) {
			try {
				delete(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Line selectById(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Line bean = pm.getObjectById(Line.class, id);

		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> select(String filter) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Line> result;
		
		try {
			Query query = pm.newQuery(Line.class);
			
			query.setFilter(filter);
			//query.setOrdering("name asc");
			//query.setRange(first, first + count);
			
			result = (List<Line>) query.execute();
			result = Lists.newArrayList(pm.detachCopyAll(result));
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	private Line save(Line bean) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {				
			pm.currentTransaction().begin();
			pm.makePersistent(bean);
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return bean;
	}
	
	@Override
	public void saveList(Invoice invoice, List<Line> list)
			throws ServerException {
		List<Line> ids = select("invoice=='" + invoice.getId() + "'");
		int i = 0;
		
		// First remove all lines
		for(Line line: ids) {
			delete(line.getId());
		}
		
		// Next insert new lines list
		for(Line line: list) {
			try {
				line.setId(null);
				//line.setInvoice(invoice.getId());
				
				save(line);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServerException(e.toString());
			}
			
			// Only save 25 lines per invoice
			if(++i > 25) {
				break;
			}
		}
	}

	@Override
	public Integer count() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Line.class);
		Integer res;

		query.setResult("count(this)");
		
		try {
			res = (Integer) query.execute();
		} finally {
			pm.close();
		}
		
		return res;
	}

}
