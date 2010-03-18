/**
 * 
 */
package cbmarc.ginvoicing.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.ginvoicing.client.rpc.ProductsService;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.Product;
import cbmarc.ginvoicing.shared.entity.ProductDisplay;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class ProductsServiceImpl extends RemoteServiceServlet 
		implements ProductsService {

	/**
	 * 
	 */
	public ProductsServiceImpl() {
	}

	@Override
	public Boolean delete(String id) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			Product bean = pm.getObjectById(Product.class, id);
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
	public Product selectById(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Product bean = pm.getObjectById(Product.class, id);

		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> select(String filter) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Product> result;
		
		try {
			Query query = pm.newQuery(Product.class);
			
			query.setFilter(filter);
			query.setOrdering("name asc");
			//query.setRange(first, first + count);
			
			result = (List<Product>) query.execute();
			result = Lists.newArrayList(pm.detachCopyAll(result));
			
			// TODO
			/*for(Product i : result) {
				Category cat = pm.getObjectById(
						Category.class, i.getCategory());
			}*/
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	@Override
	public Product save(Product bean) throws ServerException {
		// TODO
		// Verify that the input is valid. 
		if(!FieldVerifier.isValidName(bean.getName())) {
			throw new IllegalArgumentException(
				"Name must be at least 4 characters long");
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// Is a insert statement?
		if(bean.getId() == null) {
			Query query = pm.newQuery(Product.class);
			query.setResult("count(this)");
			Integer count = (Integer)query.execute();
			
			// Register limit
			if(count > 25) 
				throw new ServerException("Limit of 25 rows exceeded.");
		}
		
		// TODO: check if category exist before make persistent

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
		final Query query = pm.newQuery(Product.class);
		Integer res;

		query.setResult("count(this)");
		
		try {
			res = (Integer) query.execute();
		} finally {
			pm.close();
		}
		
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductDisplay> selectDisplay(String filter)
			throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<ProductDisplay> result = new ArrayList<ProductDisplay>();
		
		try {
			Query query = pm.newQuery(Product.class);
			
			query.setFilter(filter);
			query.setOrdering("name asc");
			//query.setRange(first, first + count);
			
			List<Product> product = (List<Product>) query.execute();
			for(Product i : product) {
				result.add(new ProductDisplay(
						i.getId(), i.getName(), i.getPrice()));
			}
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}

}
