/**
 * 
 */
package cbmarc.ginvoicing.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.ginvoicing.client.rpc.CustomersService;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.Customer;
import cbmarc.ginvoicing.shared.entity.CustomerDisplay;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class CustomersServiceImpl extends RemoteServiceServlet 
		implements CustomersService {

	/**
	 * 
	 */
	public CustomersServiceImpl() {
	}

	@Override
	public Boolean delete(String id) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			Customer bean = pm.getObjectById(Customer.class, id);
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
	public Customer selectById(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Customer bean = pm.getObjectById(Customer.class, id);

		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> select(String filter) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Customer> result;
		
		try {
			Query query = pm.newQuery(Customer.class);
			
			query.setFilter(filter);
			query.setOrdering("name asc");
			//query.setRange(first, first + count);
			
			result = (List<Customer>) query.execute();
			result = Lists.newArrayList(pm.detachCopyAll(result));
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerDisplay> selectDisplay(String filter) 
			throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<CustomerDisplay> result = new ArrayList<CustomerDisplay>();
		
		try {
			Query query = pm.newQuery(Customer.class);
			
			query.setFilter(filter);
			query.setOrdering("name asc");
			//query.setRange(first, first + count);
			
			List<Customer> customers = (List<Customer>) query.execute();
			for(Customer i : customers) {
				result.add(new CustomerDisplay(i.getId(), i.getName()));
			}
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	@Override
	public Customer save(Customer bean) throws ServerException {
		// Verify that the input is valid. 
		if(!FieldVerifier.isValidName(bean.getName())) {
			throw new IllegalArgumentException(
				"Name must be at least 4 characters long");
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// Is a insert statement?
		if(bean.getId() == null) {
			Query query = pm.newQuery(Customer.class);
			query.setResult("count(this)");
			Integer count = (Integer)query.execute();
			
			// Register limit
			if(count > 25) 
				throw new ServerException("Limit of 25 rows exceeded.");
		}

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
	public Integer count() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Customer.class);
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
