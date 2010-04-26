/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.ArrayList;
import java.util.List;

import cbmarc.ginvoicing.client.presenter.MainPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainView extends Composite implements MainPresenter.Display {
	
	interface uiBinder extends UiBinder<Widget, MainView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Panel content;
	@UiField Anchor LangCA, LangES, LangEN, LangFR;
	@UiField Hyperlink categoriesTab, productsTab, 
		customersTab, invoicesTab;
	
	private List<Hyperlink> menuTab = new ArrayList<Hyperlink>();
	
	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		menuTab.add(invoicesTab);
		menuTab.add(customersTab);
		menuTab.add(productsTab);
		menuTab.add(categoriesTab);
	}
	
	public Widget asWidget() {
		return this;
	}
	
	protected void changeLanguage(String lang) {
		UrlBuilder builder = Location.createUrlBuilder()
			.setParameter("locale", lang);
		Window.Location.replace(builder.buildString());
	}
	
	// BEGIN LANG PANEL
	@UiHandler("LangCA")
	protected void LangCAClicked(ClickEvent event) {
		changeLanguage("es_CA");
	}
	
	@UiHandler("LangES")
	protected void LangESClicked(ClickEvent event) {
		changeLanguage("es_ES");
	}
	
	@UiHandler("LangEN")
	protected void LangENClicked(ClickEvent event) {
		changeLanguage("en_EN");
	}
	
	@UiHandler("LangFR")
	protected void LangFRClicked(ClickEvent event) {
		changeLanguage("fr_FR");
	}
	// END LANG PANEL
	
	@Override
	public void setActiveTab(Hyperlink hyperlink) {
		for(Hyperlink h: menuTab)
			h.setStyleName("menuItem");
		
		if(hyperlink != null)
			hyperlink.setStyleName("menuItem-selected");
	}

	@Override
	public HasWidgets getContent() {
		return content;
	}

	@Override
	public List<Hyperlink> getMenuTab() {
		return menuTab;
	}
}
