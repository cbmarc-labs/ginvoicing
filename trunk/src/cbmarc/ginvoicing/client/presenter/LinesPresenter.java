/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.ListEditEvent;
import cbmarc.ginvoicing.client.event.ListEditHandler;
import cbmarc.ginvoicing.client.view.LinesEditView;
import cbmarc.ginvoicing.client.view.LinesListView;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class LinesPresenter implements Presenter, ListEditHandler {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	protected final Display display;
	private EventBus eventBus = EventBus.getEventBus();

	private LinesListPresenter linesListPresenter;
	private LinesEditPresenter linesEditPresenter;
	
	public LinesPresenter(Display display) {
	    this.display = display;
	    
	    linesListPresenter = new LinesListPresenter(new LinesListView());
	    linesEditPresenter = new LinesEditPresenter(new LinesEditView());
		
	    bind();
	}
	
	private void bind() {
		eventBus.addHandler(ListEditEvent.getType(), this);
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
		// Nothing to do.
	}

	@Override
	public void onList(ListEditEvent event) {
		linesListPresenter.go(display.getContent());
	}

	@Override
	public void onEdit(ListEditEvent event, Object object) {
		if(object == null) {
			linesEditPresenter.setLine(new Line());
		} else {
			linesEditPresenter.setLine((Line)object);
		}
		
		linesEditPresenter.go(display.getContent());
	}

}
