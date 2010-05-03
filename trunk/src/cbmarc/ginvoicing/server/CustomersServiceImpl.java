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
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class CustomersServiceImpl extends RemoteServiceServlet 
		implements CustomersService {

	@Override
	public void delete(List<String> ids) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String result = "";
		
		for(String id : ids) {
			try {
				Customer customer = pm.getObjectById(Customer.class, id);
				
				// Check if invoice have any customer
				Query q = pm.newQuery(Invoice.class, "customer == '" + id + "'");
				q.setResult("count(this)");
				
				if((Integer)q.execute() == 0) {
					pm.deletePersistent(customer);
				} else {
					throw new ServerException("Customer " + id + " not empty, can't delete it.");
				}
			} catch(Exception e) {
				result = result + e.toString();
			}
		}
		
		if(!result.isEmpty()) {
			throw new ServerException(result);
		}
	}

	@Override
	public Customer selectById(String id) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Customer customer;
		
		try {
			customer = pm.getObjectById(Customer.class, id);
		} catch(Exception e) {
			throw new ServerException(e.toString());
		}

		return customer;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntityDisplay> selectDisplay() throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<EntityDisplay> result = new ArrayList<EntityDisplay>();
		
		try {
			Query query = pm.newQuery(Customer.class);
			query.setOrdering("name asc");
			
			List<Customer> customers = (List<Customer>) query.execute();
			for(Customer i : customers) {
				result.add(new EntityDisplay(
						new String[] {i.getId(), i.getName(), i.getContact(),
								i.getAddress(), i.getCity(), i.getCountry()}));
			}
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	@Override
	public void save(Customer bean) throws ServerException {
		// Verify that the input is valid. 
		if(!FieldVerifier.isValidString(bean.getName())) {
			throw new IllegalArgumentException("FieldVerifier error.");
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
	}

}
