/**
 * 
 */
package cbmarc.ginvoicing.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author MCOSTA
 *
 */
public interface Resources extends ClientBundle {
	public static final Resources INSTANCE =  GWT.create(Resources.class);
	
	public interface MyCss extends CssResource {
		public String content();
	}
	
	@Source("default.css") 
	public MyCss css();
	
	@Source("Invoice.png")
	ImageResource logo();
	
	//@Source("calendar.png") 
	//public ImageResource calendar(); 
}
