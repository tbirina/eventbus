package org.tbirina.interfaces;

public class GenericEvent<T> {
	private T source;
	
	public GenericEvent  () {
		
	}
	
	public GenericEvent (T source) {
		this.source = source;
	}
	public T getSource() {
		return source;
	}
	
}
