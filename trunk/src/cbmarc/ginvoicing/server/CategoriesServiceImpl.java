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
import cbmarc.ginvoicing.shared.entity.CategoryDisplay;
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

	/**
	 * 
	 */
	public CategoriesServiceImpl() {
	}

	@Override
	public Boolean delete(String id) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			Category bean = pm.getObjectById(Category.class, id);
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
		for (int i = 0; i < ids.size(); ++i) {
			try {
				delete(ids.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Category selectById(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Category bean = pm.getObjectById(Category.class, id);

		return bean;
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
			//query.setRange(first, first + count);
			
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
	public List<CategoryDisplay> selectDisplay(String filter) 
			throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<CategoryDisplay> result = new ArrayList<CategoryDisplay>();
		
		try {
			Query query = pm.newQuery(Category.class);
			
			query.setFilter(filter);
			query.setOrdering("name asc");
			//query.setRange(first, first + count);
			
			List<Category> categories = (List<Category>) query.execute();
			for(Category i : categories) {
				result.add(new CategoryDisplay(i.getId(), i.getName()));
			}
			
			
			//result = Lists.newArrayList(pm.detachCopyAll(result));
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	@Override
	public Category save(Category bean) throws ServerException {
		// Verify that the input is valid. 
		if(!FieldVerifier.isValidName(bean.getName())) {
			throw new IllegalArgumentException(
				"Name must be at least 4 characters long");
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// Is a insert statement?
		if(bean.getId() == null) {
			Query query = pm.newQuery(Category.class);
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
		final Query query = pm.newQuery(Category.class);
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
