/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.CategoriesEditView;
import cbmarc.ginvoicing.client.view.CategoriesListView;
import cbmarc.ginvoicing.shared.entity.Category;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesPresenter 
		implements Presenter, ValueChangeHandler<String> {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	protected final Display display;
	
	private CategoriesListPresenter categoriesListPresenter;
	public CategoriesEditPresenter categoriesEditPresenter;
	
	public CategoriesPresenter(Display display) {
	    this.display = display;
	    
	    categoriesListPresenter = new CategoriesListPresenter(new CategoriesListView());
	    categoriesEditPresenter = new CategoriesEditPresenter(new CategoriesEditView());
		
	    bind();
	}
	
	private void bind() {
		History.addValueChangeHandler(this);
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}

	/*@Override
	public void onEdit(ListEditEvent event, String id) {
		if(id == null) {
			categoriesEditPresenter.setCategories(new Category());
			categoriesEditPresenter.go(display.getContent());
		} else {
			service.selectById(id, new AppAsyncCallback<Category>() {

				@Override
				public void onSuccess(Category result) {
					categoriesEditPresenter.setCategories(result);
					categoriesEditPresenter.go(display.getContent());
				}
				
			});
		}
	}

	@Override
	public void onList(ListEditEvent event) {
		//categoriesListPresenter.go(display.getContent());
		Window.alert("onList de categories");
	}*/

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if(token != null) {
			Presenter presenter = null;
			
			if(token.equals("main/categories")) {
				presenter = categoriesListPresenter;
			} else if(token.startsWith("main/categories/edit")) {
				categoriesEditPresenter.setCategories(new Category());
				presenter = categoriesEditPresenter;
			}

			if(presenter != null) {
				presenter.go(display.getContent());
			}
		}
	}

}
