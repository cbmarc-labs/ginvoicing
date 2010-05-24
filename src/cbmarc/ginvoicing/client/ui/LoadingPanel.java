/**
 * 
 */
package cbmarc.ginvoicing.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class LoadingPanel extends Composite {
	
	@UiTemplate("LoadingPanel.ui.xml")
	interface uiBinder extends UiBinder<Widget, LoadingPanel> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
		
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.UIObject#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	/**
	 * 
	 */
	public LoadingPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
