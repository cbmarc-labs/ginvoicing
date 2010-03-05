/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import cbmarc.ginvoicing.client.presenter.MainPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainView extends Composite implements MainPresenter.Display {
	
	interface uiBinder extends UiBinder<Widget, MainView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Label LangCA;
	@UiField Label LangES;
	@UiField Label LangEN;
	@UiField Label LangFR;
	
	@UiField Label homeTab;
	@UiField Label categoriesTab;
	@UiField Label productsTab;
	@UiField Label customersTab;
	@UiField Label invoicesTab;
	
	@UiField Panel content;
	
	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		return this;
	}

	// TODO: Rewrite this ugly method
	@Override
	public void setTabLinkActive(int link) {
		homeTab.setStyleName("tabItem");
		categoriesTab.setStyleName("tabItem");
		productsTab.setStyleName("tabItem");
		customersTab.setStyleName("tabItem");
		invoicesTab.setStyleName("tabItem");
		
		switch(link) {
		case 0:
			homeTab.setStyleName("tabItemActive");
			break;
		case 1:
			categoriesTab.setStyleName("tabItemActive");
			break;
		case 2:
			productsTab.setStyleName("tabItemActive");
			break;
		case 3:
			customersTab.setStyleName("tabItemActive");
			break;
		case 4:
			invoicesTab.setStyleName("tabItemActive");
			break;
		}
	}
	
	// BEGIN LANG PANEL
	@UiHandler("LangCA")
	protected void LangCAClicked(ClickEvent event) {
		// TODO: something real
		
	}
	
	@UiHandler("LangES")
	protected void LangESClicked(ClickEvent event) {
		// TODO: something real
		
	}
	
	@UiHandler("LangEN")
	protected void LangENClicked(ClickEvent event) {
		// TODO: something real
		
	}
	
	@UiHandler("LangFR")
	protected void LangFRClicked(ClickEvent event) {
		// TODO: something real
		
	}
	// END LANG PANEL
	
	// BEGIN MENU PANEL
	@UiHandler("homeTab")
	protected void homeClicked(ClickEvent event) {
		History.newItem("main/home");
	}
	
	@UiHandler("categoriesTab")
	protected void categoriesClicked(ClickEvent event) {
		History.newItem("main/categories");
	}
	
	@UiHandler("productsTab")
	protected void productsClicked(ClickEvent event) {
		History.newItem("main/products");
	}
	
	@UiHandler("customersTab")
	protected void customersClicked(ClickEvent event) {
		History.newItem("main/customers");
	}
	
	@UiHandler("invoicesTab")
	protected void invoicesClicked(ClickEvent event) {
		History.newItem("main/invoices");
	}
	// END MENU PANEL

	@Override
	public HasWidgets getContent() {
		return content;
	}
}
