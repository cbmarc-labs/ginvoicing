/**
 * 
 */
package cbmarc.ginvoicing.client.view.products;

import java.util.List;

import cbmarc.ginvoicing.client.event.ProductsEventBus;
import cbmarc.ginvoicing.client.i18n.ProductsConstants;
import cbmarc.ginvoicing.client.ui.ListFlexTable;
import cbmarc.ginvoicing.client.ui.LoadingPanel;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ProductsListViewImpl extends Composite 
		implements ProductsListView {
	
	@UiTemplate("ProductsListView.ui.xml")
	interface uiBinder extends UiBinder<Widget, ProductsListViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	private ProductsConstants constants = ProductsEventBus.getConstants();

	@UiField LoadingPanel loadingPanel;
	@UiField Panel listPanel;
	@UiField ListBox filterBox;
	@UiField ListFlexTable listTable;
	@UiField HasText listHeaderLabel;
	
	private Presenter presenter = null;
	
	public ProductsListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));		
	}
	
	public void setData(List<EntityDisplay> data) {
		listTable.removeAllRows();
		listTable.addData(new String[] {constants.listName(),
				constants.listDescription(), constants.listCategory(),
				constants.listPrice()});

		setListHeaderLabel(constants.noData());
		if(data == null || data.isEmpty()) return;
		
		for(EntityDisplay product : data) {
			String d[] = product.getData();
			listTable.addData(new String[] {d[1], d[2], d[3], d[4]});
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
	    	List<Integer> items = listTable.getSelectedRows();
	    	
			presenter.onDeleteButtonClicked(items);
	    }
	}
	
	@UiHandler("filterBox")
	protected void filterChange(ChangeEvent event) {
	    if(presenter != null) {
	    	String item = filterBox.getValue(filterBox.getSelectedIndex());
	    	
	    	presenter.onFilterBoxChanged(item);
	    }
	}
	
	@UiHandler("listTable")
	protected void listContentClicked(ClickEvent event) {
	    if(presenter != null) {
	    	int item = listTable.getClickedRow(event);
	    	
	    	presenter.onItemClicked(item);
	    }
	}

	public Widget asWidget() {
		  return this;
	}

	@Override
	public void setFilterBox(List<EntityDisplay> data) {
		filterBox.clear();
		
		filterBox.setEnabled(false);
		filterBox.addItem("", "");
		for(EntityDisplay item: data) {
			String[] d = item.getData();
			
			filterBox.addItem(d[1], d[0]);
		}
		
		if(filterBox.getItemCount() > 1)
			filterBox.setEnabled(true);
	}

	@Override
	public void setListHeaderLabel(String text) {
		listHeaderLabel.setText(text);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public ListBox getFilterBox() {
		return filterBox;
	}

	@Override
	public LoadingPanel getLoadingPanel() {
		return loadingPanel;
	}

	@Override
	public Panel getListPanel() {
		return listPanel;
	}
}
