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
	
	// ICONS
	@Source("reload.png")
	ImageResource reloadIcon();

	@Source("add.png")
	ImageResource addIcon();

	@Source("delete.png")
	ImageResource deleteIcon();

	@Source("application_view_list.png")
	ImageResource listIcon();

	@Source("tick.png")
	ImageResource okIcon();

	@Source("decline.png")
	ImageResource cancelIcon();

	@Source("erase.png")
	ImageResource resetIcon();
	
	@Source("ajax-loader.gif")
	ImageResource loadingIcon();
	
	//@Source("calendar.png") 
	//public ImageResource calendar(); 
}
