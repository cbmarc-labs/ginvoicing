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
import cbmarc.ginvoicing.shared.entity.Category;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Line;
import cbmarc.ginvoicing.shared.entity.Product;
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

	@Override
	public void delete(List<String> ids) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String result = "";
		
		for(String id : ids) {
			try {
				Product product = pm.getObjectById(Product.class, id);
				
				// Check if product is into lines
				Query q = pm.newQuery(Line.class, "product == '" + id + "'");
				q.setResult("count(this)");
				
				if((Integer)q.execute() == 0) {
					pm.deletePersistent(product);
				} else {
					throw new ServerException("Product " + id + " not empty, can't delete it.");
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
	public Product selectById(String id) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Product product;
		
		try {
			product = pm.getObjectById(Product.class, id);			
		} catch(Exception e) {
			throw new ServerException(e.toString());
		}
		
		return product;
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
			
			result = (List<Product>) query.execute();
			result = Lists.newArrayList(pm.detachCopyAll(result));
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	@Override
	public void save(Product product) throws ServerException {
		// TODO
		// Verify that the input is valid. 
		if(!FieldVerifier.isValidString(product.getName())) {
			throw new IllegalArgumentException("FieldVerifier error.");
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// Is a insert statement?
		if(product.getId() == null) {
			Query query = pm.newQuery(Product.class);
			query.setResult("count(this)");
			Integer count = (Integer)query.execute();
			
			// Register limit
			if(count > 25) 
				throw new ServerException("Limit of 25 rows exceeded.");
		}
		
		try {
			pm.getObjectById(Category.class, product.getCategory());
			pm.makePersistent(product);
		} catch(Exception e) {
			throw new ServerException(e.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntityDisplay> selectDisplay(String filter)
			throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<EntityDisplay> result = new ArrayList<EntityDisplay>();
		
		try {
			Query query = pm.newQuery(Product.class);
			
			if(filter != null) {
				query.setFilter("category == categoryParam");
				query.declareParameters("String categoryParam");				
			}
			
			query.setOrdering("name asc");
			
			List<Product> product = (List<Product>) query.execute(filter);
			for(Product i : product) {
				Category category = pm.getObjectById(
						Category.class, i.getCategory());
				
				result.add(new EntityDisplay(
						new String[] {i.getId(), i.getName(), 
								i.getDescription(), category.getName(),
								i.getPrice().toString()}));
			}
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}

}
