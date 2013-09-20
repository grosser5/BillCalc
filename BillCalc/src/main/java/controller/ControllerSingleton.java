package main.java.controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ControllerSingleton {
	private static SessionFactory session_factory = null;
	private static ControllerFactory controller_factory = null;
	private ControllerSingleton() {	
	}
	
	public synchronized static SessionFactory getSessionFactory() {
		if(session_factory == null) {
			try{
		         session_factory = new Configuration().configure().buildSessionFactory();
		      }catch (Throwable ex) { 
		         System.err.println("Failed to create sessionFactory object." + ex);
		         throw new ExceptionInInitializerError(ex); 
		      }
		}
		return session_factory;
	}
	
	public static ControllerFactory getControllerFactory() {
		if(controller_factory == null) {
			controller_factory = new ControllerFactory();
		}
		return controller_factory;
	}
}
