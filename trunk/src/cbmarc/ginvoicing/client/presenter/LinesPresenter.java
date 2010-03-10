/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.LinesEditView;
import cbmarc.ginvoicing.client.view.LinesListView;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class LinesPresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	protected final Display display;

	private LinesListPresenter linesListPresenter;
	private LinesEditPresenter linesEditPresenter;
	
	public LinesPresenter(Display display) {
	    this.display = display;
	    
	    linesListPresenter = new LinesListPresenter(new LinesListView());
	    linesEditPresenter = new LinesEditPresenter(new LinesEditView());
		
	    bind();
	}
	
	private void bind() {
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	    
	    linesListPresenter.go(display.getContent());
	}

	@Override
	public void processHistoryToken(String token) {
	}

}
