/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

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
	
	protected final LinesView view;
	private EventBus eventBus = EventBus.getEventBus();
	
	private LinesListPresenter linesListPresenter = new LinesListPresenter(new LinesListViewImpl());
	private LinesEditPresenter linesEditPresenter = new LinesEditPresenter(new LinesEditViewImpl());
	
	private Line lineEdit = new Line();
	
	public LinesPresenter(LinesView view) {
		this.view = view;
		
	    bind();
	}
	
	private void bind() {
		eventBus.addHandler(ListEditEvent.getType(), this);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(view.asWidget());
	    
	    linesListPresenter.go(view.getContent());
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
		// Perform an insert on list
		if(object != null && lineEdit == null)
			linesListPresenter.getList().add((Line)object);
		
		linesListPresenter.go(view.getContent());
	}

	@Override
	public void onEdit(ListEditEvent event, Object object) {
		Line line = new Line();
		
		lineEdit = (Line)object;
		if(object != null) line = lineEdit;
		
		linesEditPresenter.setLine(line);
		linesEditPresenter.go(view.getContent());
	}

}
