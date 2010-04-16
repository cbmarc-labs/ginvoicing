/**
 * 
 */
package cbmarc.ginvoicing.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.ginvoicing.client.rpc.InvoicesService;
import cbmarc.ginvoicing.shared.entity.Customer;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Invoice;
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
public class InvoicesServiceImpl extends RemoteServiceServlet 
		implements InvoicesService {
	
	private static final SimpleDateFormat sdf = 
		new SimpleDateFormat("dd/MM/yyyy HH:mm");

	@Override
	public void delete(ArrayList<String> ids) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String result = "";
		
		for(String id : ids) {
			try {
				Invoice invoice = pm.getObjectById(Invoice.class, id);
				pm.deletePersistent(invoice);
			} catch(Exception e) {
				result = result + e.toString();
			}
		}
		
		if(!result.isEmpty()) {
			throw new ServerException(result);
		}
	}

	@Override
	public Invoice selectById(String id) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Invoice invoice, detached = null;
		
		try {
			invoice = pm.getObjectById(Invoice.class, id);
			
			// http://www.mail-archive.com/google-appengine-java@googlegroups.com/msg02176.html
			invoice.getLines();
			
			detached = pm.detachCopy(invoice);
			
			List<Line> lines = detached.getLines();
			for(Line line: lines) {
				Product product = pm.getObjectById(
						Product.class, line.getProduct());
				line.setProductName(product.getName());
			}
			
		} catch(Exception e) {
			throw new ServerException(e.toString());
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
			Query query = pm.newQuery(Invoice.class, filter);
			query.setOrdering("date desc");
			
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
	public void save(Invoice invoice) throws ServerException {		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Invoice temp = null;
		
		// Is a insert statement?
		if(invoice.getId() == null) {
			Query q = pm.newQuery(Invoice.class);
			q.setResult("count(this)");
			
			// Register limit
			if((Integer)q.execute() > 25) 
				throw new ServerException("Row limit exceeded.");
			
			invoice.setDate(new Date());
		}
		
		//pm.setDetachAllOnCommit(true);
		
		try {			
			pm.currentTransaction().begin();
			
			// TODO check this
			if(invoice.getId() != null) {
				temp = pm.getObjectById(Invoice.class, invoice.getId());
				pm.deletePersistentAll(temp.getLines());
				
				List<Line> newlines = new ArrayList<Line>();
				for(Line oldline: invoice.getLines()) {
					Line newline = new Line();
					newline.setProduct(oldline.getProduct());
					newline.setPrice(oldline.getPrice());
					newline.setQuantity(oldline.getQuantity());
					
					newlines.add(newline);
				}
				
				invoice.setLines(newlines);
			}
			
			pm.makePersistent(invoice);
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntityDisplay> selectDisplay(String filter)
			throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<EntityDisplay> result = new ArrayList<EntityDisplay>();
		
		try {
			Query query = pm.newQuery(Invoice.class, filter);
			query.setOrdering("date desc");
			
			List<Invoice> invoices = (List<Invoice>) query.execute(filter);
			for(Invoice invoice : invoices) {
				Customer customer = (Customer)pm.getObjectById(
						Customer.class, invoice.getCustomer());
								
				result.add(new EntityDisplay(new String[] {
						invoice.getId(), customer.getName(), 
						sdf.format(invoice.getDate()), invoice.getAmount()}));
			}
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<EntityDisplay> selectCustomerFilter(void) 
		throwns ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return null;
	}*/

}
