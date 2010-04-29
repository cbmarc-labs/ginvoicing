/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.ListEditEvent;
import cbmarc.ginvoicing.client.event.ListEditHandler;
import cbmarc.ginvoicing.client.view.invoices.LinesEditView;
import cbmarc.ginvoicing.client.view.invoices.LinesListView;
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
	
	private Line lineEdit = new Line();
	
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
	
	/**
	 * @return the linesListPresenter
	 */
	public LinesListPresenter getLinesListPresenter() {
		return linesListPresenter;
	}

	/**
	 * @return the linesEditPresenter
	 */
	public LinesEditPresenter getLinesEditPresenter() {
		return linesEditPresenter;
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.event.ListEditHandler#onList(cbmarc.ginvoicing.client.event.ListEditEvent, java.lang.Object)
	 */
	@Override
	public void onList(ListEditEvent event, Object object) {
		if(object != null) {
			if(lineEdit == null) {
				linesListPresenter.getList().add((Line)object);
			}
		}
		
		linesListPresenter.go(display.getContent());
	}

	@Override
	public void onEdit(ListEditEvent event, Object object) {
		Line line = new Line();
		
		lineEdit = (Line)object;
		if(object != null) line = lineEdit;
		
		linesEditPresenter.setLine(line);
		linesEditPresenter.go(display.getContent());
	}

}
