/**
 * 
 */
package cbmarc.ginvoicing.client.categories.view;

import java.util.List;

import cbmarc.ginvoicing.client.categories.CategoriesConstants;
import cbmarc.ginvoicing.client.categories.event.CategoriesEventBus;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesSelectHandler;
import cbmarc.ginvoicing.client.categories.presenter.CategoriesSelectPresenter;
import cbmarc.ginvoicing.shared.entity.Categories;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
	
	private static final String STYLE_LIST  = "listContent";
	private static final String STYLE_LIST_HEADER  = "listContentHeader";
	private static final String STYLE_LIST_ROWA = "listContentLineA";
	private static final String STYLE_LIST_ROWB = "listContentLineB";
	
	private CategoriesConstants constants = CategoriesEventBus.getConstants();
	
	interface uiBinder extends UiBinder<Widget, CategoriesSelectView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
		
	@UiField FlexTable table;
	@UiField Label noDataLabel;
	@UiField Label loadingLabel;
	@UiField Label errorLabel;
	@UiField Label listheaderLabel;
	
	/**
	 * 
	 */
	public CategoriesSelectView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		table.addStyleName(STYLE_LIST);
	}
	
	/**
	 * @param data
	 */
	@Override
	public void setData(List<Categories> data) {
		int size = data.size();
		int p;
		
		table.removeAllRows();
		
		table.setText(0, 0, constants.listName());
		table.setText(0, 1, constants.listDescription());
		table.getRowFormatter().addStyleName(0, STYLE_LIST_HEADER);

		if (data != null) {
			for (int i = 0; i < size; ++i) {
				p = i+1;
				
				table.setText(i+1, 0, data.get(i).getName());
				table.setText(i+1, 1, data.get(i).getDescription());
				table.getRowFormatter().addStyleName(
						p, (p%2)==0?STYLE_LIST_ROWA:STYLE_LIST_ROWB);
			}
		}
		
		listheaderLabel.setText(size + " Items");
		noDataLabel.setVisible(data.isEmpty());
	}
	
	/**
	 * @param event
	 * @return
	 */
	private int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = table.getCellForEvent(event);
	    
		if (cell != null) {
			selectedRow = cell.getRowIndex();
		}
	    
		return selectedRow;
	}
	
	/**
	 * @param event
	 */
	@UiHandler("reloadButton")
	protected void reloadClicked(ClickEvent event) {
		fireEvent(CategoriesSelectEvent.reload());
	}
	
	/**
	 * @param event
	 */
	@UiHandler("cancelButton")
	protected void cancelClicked(ClickEvent event) {
		fireEvent(CategoriesSelectEvent.cancel());
	}
	
	/**
	 * @param event
	 */
	@UiHandler("table")
	protected void tableClicked(ClickEvent event) {
		fireEvent(CategoriesSelectEvent.table(getClickedRow(event)));
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.categories.presenter.CategoriesSelectPresenter.Display#asWidget()
	 */
	public Widget asWidget() {
		  return this;
	}

	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.categories.presenter.CategoriesSelectPresenter.Display#getErrorLabel()
	 */
	@Override
	public Label getErrorLabel() {
		return errorLabel;
	}

	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.categories.presenter.CategoriesSelectPresenter.Display#getLoadingLabel()
	 */
	@Override
	public Label getLoadingLabel() {
		return loadingLabel;
	}

	@Override
	public HandlerRegistration addHandler(CategoriesSelectHandler handler) {
		return addHandler(handler, CategoriesSelectEvent.getType());
	}
}
