/**
 * 
 */
package cbmarc.ginvoicing.client.view.categories;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesViewImpl extends Composite implements CategoriesView {
	
	@UiTemplate("CategoriesView.ui.xml")
	interface uiBinder extends UiBinder<Widget, CategoriesViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField HasWidgets content;
	
	public CategoriesViewImpl() {
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
