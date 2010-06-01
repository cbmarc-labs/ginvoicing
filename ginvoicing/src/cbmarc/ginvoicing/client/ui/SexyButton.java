/**
 * 
 */
package cbmarc.ginvoicing.client.ui;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;

/**
 * @author MCOSTA
 * 
 * Thanks to :
 * http://stackoverflow.com/questions/1853042/creating-custom-button-in-gwt
 * http://code.google.com/p/sexybuttons/
 *
 */
public class SexyButton extends Button {
	
	private String text;
	
	/**
	 * 
	 */
	public SexyButton() {
		super();
		
		//setStylePrimaryName(sexyStyle);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.ButtonBase#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) {
        Element span = DOM.createElement("span");
        this.text = text;
        
        span.setInnerText(text);
        span.setAttribute("style", "padding-left:3px; vertical-align:middle;");
        
        DOM.insertChild(getElement(), span, 0);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.ButtonBase#getText()
	 */
	@Override
	public String getText() {
		return this.text;
	}

	/**
	 * @param resource
	 */
	public void setResource(ImageResource resource) {
		Image img = new Image(resource);
		String definedStyles = img.getElement().getAttribute("style");
		
		img.getElement().setAttribute("style", definedStyles + "; vertical-align:middle;");
		DOM.insertBefore(getElement(), img.getElement(), DOM.getFirstChild(getElement()));
		
		//this.setHTML("<img src=\"ginvoicing/SexyButtons/images/icons/silk/" 
		//		+ resource + ".png\" />&nbsp;<span>" + this.text + "</span>");
	}
	
}
