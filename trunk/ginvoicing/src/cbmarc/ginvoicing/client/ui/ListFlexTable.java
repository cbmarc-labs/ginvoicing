/**
 * 
 */
package cbmarc.ginvoicing.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;

/**
 * @author MCOSTA
 *
 */
public class ListFlexTable extends FlexTable {
	
	private static final String STYLE  = "listContent";
	private static final String STYLE_HEADER  = "listContentHeader";
	private static final String STYLE_ROW = "listContentLine";

	/**
	 * 
	 */
	public ListFlexTable() {
		super();
		
		addStyleName(STYLE);
		getColumnFormatter().setWidth(0, "15px");
	}
	
	/**
	 * @param data
	 */
	public void addData(String[] data) {
		int row = getRowCount();
		int columns = data.length;
		
		setWidget(row, 0, new CheckBox());
		for(int i = 0; i < columns; i++) {
			setWidget(row, i + 1, new Label(data[i]));
		}
	}
	
	/**
	 * 
	 */
	public void setSelectedRows() {
		boolean check = ((CheckBox)getWidget(0, 0)).getValue();
		int row = getRowCount();
		
		for(int i = 1; i < row; ++i) {
			CheckBox checkBox = (CheckBox)getWidget(i, 0);
			checkBox.setValue(check);
		}
	}
	
	/**
	 * @return
	 */
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();
		int row = getRowCount();
		
		for (int i = 1; i < row; ++i) {
			CheckBox checkBox = (CheckBox)getWidget(i, 0);
			if (checkBox.getValue()) selectedRows.add(i);
		}
		
		return selectedRows;
	}
	
	/**
	 * @param event
	 * @return
	 */
	public int getClickedRow(ClickEvent event) {
		HTMLTable.Cell cell = getCellForEvent(event);
		int row = -1;
	    
		if (cell != null) {
			// Suppress clicks if the user is actually selecting the check box
			if(cell.getRowIndex() == 0) setSelectedRows();
			if(cell.getCellIndex() > 0) row = cell.getRowIndex();
		}
	    
		return row;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.FlexTable#insertRow(int)
	 */
	@Override
	public int insertRow(int beforeRow) {		
		int row = super.insertRow(beforeRow);
		
		String style = row==0 ? STYLE_HEADER : STYLE_ROW + (row%2);
		getRowFormatter().addStyleName(row, style);
		
		return beforeRow;
	}
	
}
