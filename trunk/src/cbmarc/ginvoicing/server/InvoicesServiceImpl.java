/**
 * 
 */
package cbmarc.ginvoicing.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.ginvoicing.client.rpc.InvoicesService;
import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class InvoicesServiceImpl extends RemoteServiceServlet 
		implements InvoicesService {

	/**
	 * 
	 */
	public InvoicesServiceImpl() {
	}

	@Override
	public Boolean delete(String id) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			Invoice bean = pm.getObjectById(Invoice.class, id);
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
	public void delete(ArrayList<String> ids) {
		for(String i : ids) {
			try {
				delete(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Invoice selectById(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Invoice invoice, detached = null;
		
		try {
			invoice = pm.getObjectById(Invoice.class, id);
			
			// http://www.mail-archive.com/google-appengine-java@googlegroups.com/msg02176.html
			invoice.getLines();
			
			detached = pm.detachCopy(invoice);
		} finally {
			pm.close();
		}
		
		return detached;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invoice> select(String filter) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Invoice> result;
		
		try {
			Query query = pm.newQuery(Invoice.class);
			
			query.setFilter(filter);
			query.setOrdering("date desc");
			//query.setRange(first, first + count);
			
			result = (List<Invoice>) query.execute();
			result = Lists.newArrayList(pm.detachCopyAll(result));
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	@Override
	public Invoice save(Invoice bean) throws ServerException {		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// Is a insert statement?
		if(bean.getId() == null) {
			Query query = pm.newQuery(Invoice.class);
			query.setResult("count(this)");
			Integer count = (Integer)query.execute();
			
			// Register limit
			if(count > 25) 
				throw new ServerException("Limit of 25 rows exceeded.");
			
			bean.setDate(new Date());
		}
		
		try {				
			pm.currentTransaction().begin();
			pm.makePersistent(bean);
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new ServerException(e.toString());
		} finally {
			bean = pm.detachCopy(bean);
			pm.close();
		}
		
		return bean;
	}

	@Override
	public Integer count() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Invoice.class);
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
