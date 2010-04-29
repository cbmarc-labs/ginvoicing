/**
 * 
 */
package cbmarc.ginvoicing.client.view.products;

import cbmarc.ginvoicing.client.presenter.ProductsPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ProductsViewImpl extends Composite 
		implements ProductsPresenter.Display {
	
	@UiTemplate("ProductsView.ui.xml")
	interface uiBinder extends UiBinder<Widget, ProductsViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField Panel content;
	
	public ProductsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasWidgets getContent() {
		return content;
	}
}
