/**
 * 
 */
package cbmarc.ginvoicing.client.categories.view;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.categories.CategoriesConstants;
import cbmarc.ginvoicing.client.categories.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.categories.event.CategoriesListEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesListHandler;
import cbmarc.ginvoicing.client.categories.presenter.CategoriesListPresenter;
import cbmarc.ginvoicing.client.ui.CFlexTable;
import cbmarc.ginvoicing.shared.entity.Category;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesListView extends Composite 
		implements CategoriesListPresenter.Display {
	
	private static final String STYLE_LIST_HEADER  = "listContentHeader";
	private static final String STYLE_LIST_ROWA = "listContentLineA";
	private static final String STYLE_LIST_ROWB = "listContentLineB";
	
	private CategoriesConstants constants = CategoriesEventBus.getConstants();
	
	interface uiBinder extends UiBinder<Widget, CategoriesListView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField CFlexTable table;
	
	@UiField Label noDataLabel;
	@UiField Label loadingLabel;
	@UiField Label errorLabel;
	@UiField Label listheaderLabel;
	
	public CategoriesListView() {
		initWidget(uiBinder.createAndBindUi(this));

		table.getColumnFormatter().setWidth(0, "15px");
	}
	
	/**
	 * @param data
	 */
	@Override
	public void setData(List<Category> data) {
		int size = data.size();
		int p;
		
		table.removeAllRows();
		table.setWidget(0, 0, new CheckBox());
		table.setText(0, 1, constants.listName());
		table.setText(0, 2, constants.listDescription());
		table.getRowFormatter().addStyleName(0, STYLE_LIST_HEADER);

		if (data != null) {
			for (int i = 0; i < size; ++i) {
				p = i+1;
				
				table.setWidget(p, 0, new CheckBox());
				table.setText(p, 1, data.get(i).getName());
				table.setText(p, 2, data.get(i).getDescription());
				//table.getRowFormatter().addStyleName(
				//		p, (p%2)==0?STYLE_LIST_ROWA:STYLE_LIST_ROWB);
			}
		}
		
		listheaderLabel.setText(size + " Items");
		noDataLabel.setVisible(data.isEmpty());
	}
	
	/**
	 * @return
	 */
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();
		
		for (int i = 1; i < table.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox)table.getWidget(i, 0);
			if (checkBox.getValue()) {
				selectedRows.add(i);
			}
		}
		
		return selectedRows;
	}
	
	/**
	 * 
	 */
	private void selectAllRows() {
		boolean check = ((CheckBox)table.getWidget(0, 0)).getValue(); 
		
		for(int i = 1; i < table.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox)table.getWidget(i, 0);
			checkBox.setValue(check);
		}
	}

	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = table.getCellForEvent(event);
	    
		if (cell != null) {
			// Suppress clicks if the user is actually selecting the check box
			if(cell.getRowIndex() == 0) selectAllRows();
			if(cell.getCellIndex() > 0) selectedRow = cell.getRowIndex();
		}
	    
		return selectedRow;
	}
	
	@UiHandler("reloadButton")
	protected void reloadClicked(ClickEvent event) {
		fireEvent(CategoriesListEvent.reload());
	}
	
	@UiHandler("addButton")
	protected void addClicked(ClickEvent event) {
		fireEvent(CategoriesListEvent.add());
	}
	
	@UiHandler("deleteButton")
	protected void deleteClicked(ClickEvent event) {
		fireEvent(CategoriesListEvent.delete());
	}
	
	@UiHandler("table")
	protected void tableClicked(ClickEvent event) {
		fireEvent(CategoriesListEvent.table(getClickedRow(event)));
	}

	public Widget asWidget() {
		  return this;
	}

	@Override
	public Label getErrorLabel() {
		return errorLabel;
	}

	@Override
	public Label getLoadingLabel() {
		return loadingLabel;
	}

	@Override
	public HandlerRegistration addHandler(CategoriesListHandler handler) {
		return addHandler(handler, CategoriesListEvent.getType());
	}
}
