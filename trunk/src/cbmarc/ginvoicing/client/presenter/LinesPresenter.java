/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.LinesEventBus;
import cbmarc.ginvoicing.client.event.ListEditEvent;
import cbmarc.ginvoicing.client.event.ListEditHandler;
import cbmarc.ginvoicing.client.i18n.LinesConstants;
import cbmarc.ginvoicing.client.view.LinesEditView;
import cbmarc.ginvoicing.client.view.LinesListView;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.user.client.Window;
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
	private LinesConstants constants = LinesEventBus.getConstants();

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
		// TODO check this
		if(object != null) {
			if(linesListPresenter.getList().size() > 9) {
				Window.alert(constants.limitExceeded());
				return;
			} else {
				linesListPresenter.getList().add((Line)object);
			}
		}

		linesListPresenter.go(display.getContent());
	}

	@Override
	public void onEdit(ListEditEvent event, Object object) {
		linesEditPresenter.setLine((Line)object);
		linesEditPresenter.go(display.getContent());
	}

}
