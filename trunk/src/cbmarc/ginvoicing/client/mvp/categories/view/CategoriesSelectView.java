/**
 * 
 */
package cbmarc.ginvoicing.client.mvp.categories.view;

import java.util.List;

import cbmarc.ginvoicing.client.mvp.categories.presenter.CategoriesSelectPresenter;
import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesSelectView extends Composite 
		implements CategoriesSelectPresenter.Display {
	interface uiBinder extends UiBinder<Widget, CategoriesSelectView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Button cancelButton;
	
	@UiField FlexTable table;
	@UiField Label nodataLabel;
	@UiField Label listheaderLabel;
	
	public CategoriesSelectView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/**
	 * @param data
	 */
	@Override
	public void setData(List<Categories> data) {
		int size = data.size();
		
		table.removeAllRows();
		
		table.setText(0, 0, "Name");
		table.setText(0, 1, "Description");
		table.getRowFormatter().addStyleName(0, "flexTableHeader");

		if (data != null) {
			for (int i = 0; i < size; ++i) {
				table.setText(i+1, 0, data.get(i).getName());
				table.setText(i+1, 1, data.get(i).getDescription());
			}
		}
		
		listheaderLabel.setText(size + " Items");
		nodataLabel.setVisible(size==0?true:false);
	}
	
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = table.getCellForEvent(event);
	    
		if (cell != null) {
			selectedRow = cell.getRowIndex();
		}
	    
		return selectedRow;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public HasClickHandlers getTable() {
		return table;
	}
	
	public Widget asWidget() {
		  return this;
	}
}
