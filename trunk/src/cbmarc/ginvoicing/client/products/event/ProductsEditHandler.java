package cbmarc.ginvoicing.client.products.event;

import com.google.gwt.event.shared.EventHandler;

public interface ProductsEditHandler extends EventHandler {
	public void onList(ProductsEditEvent event);
	public void onSubmit(ProductsEditEvent event);
	public void onCancel(ProductsEditEvent event);
	public void onListCategory(ProductsEditEvent event);
}
