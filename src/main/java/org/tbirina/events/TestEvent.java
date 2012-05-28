package org.tbirina.events;
import org.tbirina.interfaces.GenericEvent;


public class TestEvent<T> extends GenericEvent<T> {

	public TestEvent() {
		super();

	}
	
	public TestEvent(T source) {
		super(source);
	
	}
	
	

}
