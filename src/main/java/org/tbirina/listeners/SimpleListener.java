package org.tbirina.listeners;

import org.tbirina.interfaces.GenericEvent;
import org.tbirina.interfaces.GenericListener;

public class SimpleListener implements GenericListener {
	public void onEvent (GenericEvent<String> event) {
		System.out.println("SimpleListener handled an event: " + event.getSource());
	}

}
