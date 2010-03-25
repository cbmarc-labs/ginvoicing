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
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
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
public class InvoicesServiceImpl extends RemoteServiceServlet 
		implements InvoicesService {

	@Override
	public void delete(ArrayList<String> ids) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		for(String id : ids) {
			Invoice invoice = pm.getObjectById(Invoice.class, id);
			
			pm.deletePersistent(invoice);
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
					newline.setProductPrice(oldline.getProductPrice());
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
			Query query = pm.newQuery(Invoice.class);
			
			query.setFilter(filter);
			query.setOrdering("date desc");
			//query.setRange(first, first + count);
			
			List<Invoice> invoices = (List<Invoice>) query.execute();
			for(Invoice i : invoices) {
				result.add(new EntityDisplay(
						new String[] {i.getId(), "cust",
						i.getDate().toString(), i.getAmount()}));
			}
		} catch(Exception e) {
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return result;
	}

}
