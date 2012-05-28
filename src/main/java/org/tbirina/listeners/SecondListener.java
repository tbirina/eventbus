package org.tbirina.listeners;

import org.tbirina.interfaces.GenericEvent;
import org.tbirina.interfaces.GenericListener;

public class SecondListener implements GenericListener {
	
	public void onSecondEvent (GenericEvent<String> event) {
		System.out.println("SecondListener handled an event: " + event.getSource());
	}

}
