package main.java.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ModelSingleton {
	private static SessionFactory session_factory = null;
	
	private ModelSingleton() {	
	}
	
	public static SessionFactory getSessionFactory() {
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
}
