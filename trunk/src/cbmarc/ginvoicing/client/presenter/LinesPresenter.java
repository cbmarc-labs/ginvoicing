/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.ListEditEvent;
import cbmarc.ginvoicing.client.event.ListEditHandler;
import cbmarc.ginvoicing.client.view.invoices.LinesEditViewImpl;
import cbmarc.ginvoicing.client.view.invoices.LinesListViewImpl;
import cbmarc.ginvoicing.client.view.invoices.LinesView;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class LinesPresenter 
		implements Presenter, LinesView.Presenter, ListEditHandler {
	
	private EventBus eventBus = EventBus.getEventBus();
	
	private static LinesListViewImpl linesListView = null;
	private static LinesEditViewImpl linesEditView = null;
	
	private final LinesView view;
	
	private List<Line> list = null;
	private Line line = null;
	
	public LinesPresenter(LinesView view, List<Line> list) {
		this.view = view;
		this.list = list;
		
	    bind();
	}
	
	private void bind() {
		eventBus.addHandler(ListEditEvent.getType(), this);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(view.asWidget());
	    
	    // maybe better way to initialize the view.
	    onList(null, null);
	}

	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.event.ListEditHandler#onList(cbmarc.ginvoicing.client.event.ListEditEvent, java.lang.Object)
	 */
	@Override
	public void onList(ListEditEvent event, Object object) {
	    if(linesListView == null)
	    	linesListView = new LinesListViewImpl();
	    
	    if(object != null && line == null)
	    	this.list.add((Line)object);
		
		new LinesListPresenter(linesListView, list).go(view.getContent());
	}

	@Override
	public void onEdit(ListEditEvent event, Object object) {
		if(linesEditView == null)
	    	linesEditView = new LinesEditViewImpl();
		
		line = (Line)object;
		new LinesEditPresenter(linesEditView, line).go(view.getContent());
	}

}
