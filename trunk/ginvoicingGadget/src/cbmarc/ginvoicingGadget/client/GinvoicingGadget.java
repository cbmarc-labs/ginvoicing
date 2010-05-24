package cbmarc.ginvoicingGadget.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

@ModulePrefs(title = "GinvoicingGadget", author = "cbmarc", author_email = "cbmarc@gmail.com")
public class GinvoicingGadget extends Gadget<UserPreferences> {

	@Override
	protected void init(UserPreferences preferences) {
		Button simpleButton = new Button("GinvoicingGadget");
		
		simpleButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Hello World!");
			}
			
		});
		
		RootPanel.get().add(simpleButton);
	}

}
