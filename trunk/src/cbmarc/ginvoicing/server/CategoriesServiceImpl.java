/**
 * 
 */
package cbmarc.ginvoicing.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.ginvoicing.client.rpc.CategoriesService;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.Category;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Product;
import cbmarc.ginvoicing.shared.exception.ServerException;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class CategoriesServiceImpl extends RemoteServiceServlet 
		implements CategoriesService {

	@Override
	public void delete(List<String> ids) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String result = "";
		
		for(String id : ids) {
			try {
				Category category = pm.getObjectById(Category.class, id);
			
				// Check if categories have any products
				Query q = pm.newQuery(Product.class, "category == '" + id + "'");
				q.setResult("count(this)");
			
				if((Integer)q.execute() == 0) {
					pm.deletePersistent(category);
				} else {
					throw new ServerException("Category '" + id + "' not empty, can't delete it.");
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
	public Category selectById(String id) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Category category;
		
		try {
			category = pm.getObjectById(Category.class, id);
		} catch(Exception e) {
			throw new ServerException(e.toString());
		}

		return category;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> select(String filter) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Category> result;
		
		try {
			Query query = pm.newQuery(Category.class);
			
			query.setFilter(filter);
			query.setOrdering("name asc");
			
			result = (List<Category>) query.execute();
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
	public List<EntityDisplay> selectDisplay(String filter) 
			throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<EntityDisplay> result = new ArrayList<EntityDisplay>();
		String numProducts = "0";
		
		try {
			Query query = pm.newQuery(Category.class, filter);
			query.setOrdering("name asc");
			
			List<Category> categories = (List<Category>) query.execute();
			for(Category category: categories) {
				query = pm.newQuery(Product.class,
						"category=='" + category.getId() + "'");
				query.setResult("count(this)");
				numProducts = ((Integer)query.execute()).toString();
				
				result.add(new EntityDisplay(new String[] {
						category.getId(), category.getName(),
						category.getDescription(), numProducts}));
			}
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	@Override
	public void save(Category category) throws ServerException {
		// Verify that the input is valid. 
		if(!FieldVerifier.isValidString(category.getName())) {
			throw new IllegalArgumentException("FieldVerifier error.");
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// Is a insert statement?
		if(category.getId() == null) {
			Query query = pm.newQuery(Category.class);
			query.setResult("count(this)");
			Integer count = (Integer)query.execute();
			
			// Register limit
			if(count > 25) 
				throw new ServerException("Limit of 25 rows exceeded.");
		}

		try {				
			pm.currentTransaction().begin();
			pm.makePersistent(category);
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
	}

}
