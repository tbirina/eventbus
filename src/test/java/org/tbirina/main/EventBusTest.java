package org.tbirina.main;


import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tbirina.events.TestEvent;
import org.tbirina.interfaces.GenericEvent;
import org.tbirina.interfaces.GenericListener;
import org.tbirina.listeners.SecondListener;
import org.tbirina.listeners.SimpleListener;

public class EventBusTest {
	private static EventBus bus;
	private GenericListener simpleListener = new SimpleListener(); 
	private GenericListener simpleListener2 = new SimpleListener(); 
	private SecondListener secondListener = new SecondListener();
	private TestEvent<String> testEvent;
	private GenericEvent<Integer> genericEvent;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bus = EventBus.getEventBus();
		
	}

	@Before
	public void setUp() throws Exception {
		testEvent = new TestEvent<String>("My source");
		genericEvent = new GenericEvent<Integer>(11111);
	}
	
	@Test
	public void addListenerTest () throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		bus.addListener(simpleListener, TestEvent.class, "onEvent");
		bus.addListener(simpleListener2, GenericEvent.class, "onEvent");
		bus.addListener(secondListener, TestEvent.class, "onSecondEvent");
		bus.publish(testEvent);
		
	}
	
	@Test
	public void removeListenerTest () {
		
	}

}
