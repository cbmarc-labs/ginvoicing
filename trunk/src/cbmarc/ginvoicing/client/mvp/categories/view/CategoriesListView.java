/**
 * 
 */
package cbmarc.ginvoicing.client.mvp.categories.view;

import java.util.ArrayList;
import java.util.List;


import cbmarc.ginvoicing.client.mvp.categories.presenter.CategoriesListPresenter;
import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
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
	interface uiBinder extends UiBinder<Widget, CategoriesListView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Button addButton;
	@UiField Button deleteButton;
	
	@UiField FlexTable table;
	@UiField Label tableLabel;
	@UiField Label listheaderLabel;
	
	public CategoriesListView() {
		initWidget(uiBinder.createAndBindUi(this));

		table.getColumnFormatter().setWidth(0, "15px");
	}
	
	/**
	 * @param e
	 */
	@UiHandler("addButton")
	void onAddButtonClick(ClickEvent e) {
	}
	
	/**
	 * @param data
	 */
	@Override
	public void setData(List<Categories> data) {
		int size = data.size();
		
		table.removeAllRows();
		
		table.setWidget(0, 0, new CheckBox());
		table.setText(0, 1, "Name");
		table.setText(0, 2, "Description");
		table.getRowFormatter().addStyleName(0, "flexTableHeader");

		if (data != null) {
			for (int i = 0; i < size; ++i) {
				table.setWidget(i+1, 0, new CheckBox());
				table.setText(i+1, 1, data.get(i).getName());
				table.setText(i+1, 2, data.get(i).getDescription());
			}
		}
		
		listheaderLabel.setText(size + " Items");
		
		if(size==0) {
			tableLabel.setText("No data.");
			tableLabel.setVisible(true);
		}
	}
	
	/**
	 * @return
	 */
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();
		
		for (int i = 0; i < table.getRowCount(); ++i) {
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

	@Override
	public HasClickHandlers getAddButton() {
		return addButton;
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}

	@Override
	public HasClickHandlers getTable() {
		return table;
	}

	public Widget asWidget() {
		  return this;
	}

	@Override
	public Label getTableLabel() {
		return tableLabel;
	}
}
