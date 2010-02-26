/**
 * 
 */
package cbmarc.ginvoicing.client.ui;

import com.google.gwt.user.client.ui.FlexTable;

/**
 * @author MCOSTA
 *
 */
public class CFlexTable extends FlexTable {
	
	private static final String STYLE  = "listContent";
	private static final String STYLE_HEADER  = "listContentHeader";
	private static final String STYLE_ROW = "listContentLine";

	/**
	 * 
	 */
	public CFlexTable() {
		super();
		
		addStyleName(STYLE);
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
