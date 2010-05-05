/**
 * 
 */
package cbmarc.ginvoicing.client.view.invoices;

import java.util.List;

import cbmarc.ginvoicing.client.event.LinesEventBus;
import cbmarc.ginvoicing.client.i18n.LinesConstants;
import cbmarc.ginvoicing.client.ui.ListFlexTable;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class LinesListViewImpl extends Composite implements LinesListView {

	@UiTemplate("LinesListView.ui.xml")
	interface uiBinder extends UiBinder<Widget, LinesListViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	private LinesConstants constants = LinesEventBus.getConstants();
	
	@UiField ListFlexTable listTable;
	@UiField HasText listHeaderLabel;
	@UiField HasText listFooterLabel;
	
	private Presenter presenter = null;
	
	public LinesListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/**
	 * @param data
	 */
	public void setData(List<Line> data) {
		listTable.removeAllRows();
		listTable.addData(new String[] { constants.listProductName(),
				constants.listQuantity(), constants.listPrice()});

		setListHeaderLabel(constants.noData());
		if(data == null || data.isEmpty()) return;
		
		for(Line line : data) {
			listTable.addData(new String[] { line.getProductName(),
					line.getQuantity().toString(),
					line.getPrice().toString()});
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
	public void setListFooterLabel(String text) {
		listFooterLabel.setText(text);
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
	public FlexTable getListTable() {
		return listTable;
	}
	
}
