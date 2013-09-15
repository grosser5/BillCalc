package main.java.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ModelSingleton {
	private static SessionFactory session_factory = null;
	private static ModelFactory model_factory = null;
	private ModelSingleton() {	
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
	
	public static ModelFactory getModelFactory() {
		if(model_factory == null) {
			model_factory = new ModelFactory();
		}
		return model_factory;
	}
}
