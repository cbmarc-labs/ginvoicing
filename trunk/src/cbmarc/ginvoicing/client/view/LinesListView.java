/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.List;

import cbmarc.ginvoicing.client.event.LinesEventBus;
import cbmarc.ginvoicing.client.event.ListEvent;
import cbmarc.ginvoicing.client.event.ListHandler;
import cbmarc.ginvoicing.client.i18n.LinesConstants;
import cbmarc.ginvoicing.client.presenter.LinesListPresenter;
import cbmarc.ginvoicing.client.ui.ListFlexTable;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class LinesListView extends Composite 
		implements LinesListPresenter.Display {
	
	interface uiBinder extends UiBinder<Widget, LinesListView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	private LinesConstants constants = LinesEventBus.getConstants();
	
	@UiField ListFlexTable listContent;
	@UiField Label listContentLabel;
	@UiField Label listHeaderLabel;
	@UiField Label listFooterLabel;
	
	public LinesListView() {
		initWidget(uiBinder.createAndBindUi(this));		
	}
	
	/**
	 * @param data
	 */
	public void setData(List<Line> data) {
		int size = data.size();
		
		setListContentLabel(null);
		listContent.removeAllRows();
		listContent.addData(new String[] {
				constants.listProductName(),
				constants.listQuantity(),
				constants.listProductPrice()});

		if(data != null) {
			for(Line line : data) {
				listContent.addData(new String[] {
						line.getProductName(),
						line.getQuantity(),
						line.getProductPrice()});
			}
		}
		
		listHeaderLabel.setText(size + " " + constants.itemsLabel());
		
		if(data.isEmpty()) 
			setListContentLabel(constants.noData());
	}
	
	@UiHandler("reloadButton")
	protected void reloadClicked(ClickEvent event) {
		fireEvent(ListEvent.reload());
	}
	
	@UiHandler("addButton")
	protected void addClicked(ClickEvent event) {
		fireEvent(ListEvent.add());
	}
	
	@UiHandler("deleteButton")
	protected void deleteClicked(ClickEvent event) {
		fireEvent(ListEvent.delete());
	}
	
	@UiHandler("listContent")
	protected void listContentClicked(ClickEvent event) {
		int row = listContent.getClickedRow(event);
		
		if(row > 0)
			fireEvent(ListEvent.list(row - 1));
	}

	public Widget asWidget() {
		  return this;
	}

	/**
	 * @param listContentLabel the listContentLabel to set
	 */
	public void setListContentLabel(String msg) {
		if(msg == null) {
			this.listContentLabel.setVisible(false);
		} else {
			this.listContentLabel.setVisible(true);
			this.listContentLabel.setText(msg);
		}
	}

	@Override
	public HandlerRegistration addHandler(ListHandler handler) {
		return addHandler(handler, ListEvent.getType());
	}

	@Override
	public List<Integer> getSelectedRows() {
		return listContent.getSelectedRows();
	}
}
