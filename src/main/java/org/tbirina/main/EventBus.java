package org.tbirina.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import org.apache.commons.collections.CollectionUtils;
import org.tbirina.interfaces.GenericEvent;
import org.tbirina.interfaces.GenericListener;

public class EventBus<T> {
	private static EventBus eventBus = new EventBus();
	private EventBus () {
		
	}
	
	public static EventBus getEventBus () {
		return eventBus;
	}
	
	private Map<Class <? extends GenericEvent<?>>,Set <GenericListener>> listenersToEventsMapping = new
			HashMap<Class <? extends GenericEvent<?>>,Set <GenericListener>>();
	
	private Map<GenericListener, String> methodsToListenersMapping = new HashMap<GenericListener, String>();
	
	public void addListener(GenericListener listener,
			Class<? extends GenericEvent<T>> eventClass, String methodName) {
		System.out.println(eventClass.getCanonicalName());
		if (!listenersToEventsMapping.isEmpty()) {
			if (listenersToEventsMapping.containsKey(eventClass)) {
				listenersToEventsMapping.get(eventClass).add(listener);

			} else {
				addListenersToEventsMappingWithNoKey(listener, eventClass);
			}

			addMethodsToListenersMapping(listener, methodName);

		} else {
			addListenersToEventsMappingWithNoKey(listener, eventClass);
			addMethodsToListenersMapping(listener, methodName);
		}
	}
	
	private void addListenersToEventsMappingWithNoKey (GenericListener listener, Class <? extends GenericEvent<T>> eventClass) {
		Set<GenericListener> listeners = new HashSet<GenericListener>();
		listeners.add(listener);
		listenersToEventsMapping.put(eventClass, listeners);		
	}
	
	private void addMethodsToListenersMapping (GenericListener listener, String methodName) {
		methodsToListenersMapping.put(listener, methodName);		
	}
	
	public void removeListener (GenericListener listener, Class <? extends GenericEvent<T>> eventClass) {
		listenersToEventsMapping.get(eventClass).remove(listener);
		methodsToListenersMapping.remove(listener);	
	}
	
	public void removeListener (GenericListener listener) {
		for (Class <? extends GenericEvent<?>> eventClass: listenersToEventsMapping.keySet()) {
			Set<GenericListener> listeners = listenersToEventsMapping.get(eventClass);
			if (CollectionUtils.isNotEmpty(listeners))
				listeners.remove(listener);
		}
		
		methodsToListenersMapping.remove(listener);	
		
	}
	
	public void removeAll () {
		listenersToEventsMapping.clear();
		methodsToListenersMapping.clear();
	}
	
	//TODO
	public void publish (GenericEvent<?> event) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		for (Class <? extends GenericEvent<?>> eventClass: listenersToEventsMapping.keySet()) {
			if (eventClass.getName().equals(event.getClass().getName())) {
				System.out.println("Found match!!!");
				Set<GenericListener> listeners = listenersToEventsMapping.get(eventClass);
				Iterator<GenericListener> it = listeners.iterator();
				while (it.hasNext()) {
					GenericListener tempListener = it.next();
					String methodName = methodsToListenersMapping.get(tempListener);
					System.out.println("Method name is " + methodName);
					Method [] methods = tempListener.getClass().getDeclaredMethods();
					for (int i = 0; i < methods.length; i++) {
						if (methods[i].getName().equals(methodName)) {
							methods[i].invoke(tempListener, new Object[] {event});
						}
					}
				}
			}
		}
		
	}

}
