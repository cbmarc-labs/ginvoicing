/**
 * 
 */
package cbmarc.ginvoicing.client;

import cbmarc.ginvoicing.client.presenter.CategoriesEditPresenter;
import cbmarc.ginvoicing.client.view.categories.CategoriesEditView;
import cbmarc.ginvoicing.client.view.categories.CategoriesEditViewImpl;

/**
 * @author MCOSTA
 *
 */
public class CategoriesTestCase extends GinvoicingTestCase {
	
	private CategoriesEditPresenter presenter;
	private CategoriesEditView view;
	
	public void gwtSetUp() {
		view = new CategoriesEditViewImpl();
		presenter = new CategoriesEditPresenter(view, null);
	}
	
	public void testEdit() {
		view.getName().setValue("aaaaaaa");
		
		assertTrue(presenter.hasValidInput());
	}

}
