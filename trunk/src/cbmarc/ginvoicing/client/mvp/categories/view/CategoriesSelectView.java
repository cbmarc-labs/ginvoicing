/**
 * 
 */
package cbmarc.ginvoicing.client.mvp.categories.view;

import java.util.List;

import cbmarc.ginvoicing.client.mvp.categories.i18n.CategoriesConstants;
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
	
	final private CategoriesConstants constants;
	
	interface uiBinder extends UiBinder<Widget, CategoriesSelectView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Button reloadButton;
	@UiField Button cancelButton;
	
	@UiField FlexTable table;
	@UiField Label noDataLabel;
	@UiField Label loadingLabel;
	@UiField Label errorLabel;
	@UiField Label listheaderLabel;
	
	public CategoriesSelectView() {
		constants = GWT.create(CategoriesConstants.class);

		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/**
	 * @param data
	 */
	@Override
	public void setData(List<Categories> data) {
		int size = data.size();
		
		table.removeAllRows();
		
		table.setText(0, 0, constants.listName());
		table.setText(0, 1, constants.listDescription());
		table.getRowFormatter().addStyleName(0, "flexTableHeader");

		if (data != null) {
			for (int i = 0; i < size; ++i) {
				table.setText(i+1, 0, data.get(i).getName());
				table.setText(i+1, 1, data.get(i).getDescription());
			}
		}
		
		listheaderLabel.setText(size + " Items");
		noDataLabel.setVisible(data.isEmpty());
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

	@Override
	public Label getErrorLabel() {
		return errorLabel;
	}

	@Override
	public Label getLoadingLabel() {
		return loadingLabel;
	}

	@Override
	public HasClickHandlers getReloadButton() {
		return reloadButton;
	}
}
