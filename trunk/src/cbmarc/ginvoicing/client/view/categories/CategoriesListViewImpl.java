/**
 * 
 */
package cbmarc.ginvoicing.client.view.categories;

import java.util.List;

import cbmarc.ginvoicing.client.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.i18n.CategoriesConstants;
import cbmarc.ginvoicing.client.ui.ListFlexTable;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesListViewImpl extends Composite 
		implements CategoriesListView {
	
	@UiTemplate("CategoriesListView.ui.xml")
	interface uiBinder extends UiBinder<Widget, CategoriesListViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	private CategoriesConstants constants = CategoriesEventBus.getConstants();
	
	@UiField ListFlexTable listContent;
	@UiField HasText listHeaderLabel;
	
	private Presenter presenter = null;
	
	public CategoriesListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setData(List<EntityDisplay> data) {
		listContent.removeAllRows();
		listContent.addData(new String[] {constants.listName(),
				constants.listDescription(), constants.listProducts()});

		setListHeaderLabel(constants.noData());
		if(data == null || data.isEmpty()) return;
		
		for(EntityDisplay i : data) {
			String d[] = i.getData();
			listContent.addData(new String[] {d[1], d[2], d[3]});
		}
			
		setListHeaderLabel(data.size() + " " + constants.itemsLabel());
	}
	
	@UiHandler("reloadButton")
	protected void reloadClicked(ClickEvent event) {
	    if(presenter != null) {
	    	presenter.onReloadButtonClicked();
	    }
	}
	
	@UiHandler("addButton")
	protected void addClicked(ClickEvent event) {
	    if(presenter != null) {
			presenter.onAddButtonClicked();
	    }
	}
	
	@UiHandler("deleteButton")
	protected void deleteClicked(ClickEvent event) {
	    if(presenter != null) {
	    	presenter.onDeleteButtonClicked(listContent.getSelectedRows());
	    }
	}
	
	@UiHandler("listContent")
	protected void listContentClicked(ClickEvent event) {
	    if(presenter != null) {
	    	int row = listContent.getClickedRow(event);
	    	
	    	presenter.onItemClicked(row);
	    }
	}

	public Widget asWidget() {
		  return this;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setListHeaderLabel(String text) {
		listHeaderLabel.setText(text);
	}
	
}
