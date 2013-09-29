package main.java.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import main.view.util.Log;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class ModelSingleton {
	private SessionFactory session_factory = null;
	private ModelFactory model_factory = null;
	private static ModelSingleton instance = null;
	private Properties properties;
	private String db_path;
	
	private ModelSingleton() {
		model_factory = new ModelFactory();
		properties = new Properties();
		properties.setProperty("hibernate.dialect", "main.java.model.SQLiteDialect");
		properties.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
		properties.setProperty("hibernate.connection.username", "");
		properties.setProperty("hibernate.connection.password", "");
	}
	public void initialize(String db_path) {
		this.db_path = db_path;
		if(session_factory == null) {
			resetSessionFactory(db_path);
		}
	}
	public void resetSessionFactory(String db_path) {
		try{
			this.db_path = db_path;
			properties.setProperty("hibernate.connection.url", "jdbc:sqlite:" + db_path);
			Log.getLog(this).debug("properties are set");
	        session_factory = new Configuration()
	        		.setProperties(properties)
	        		.addResource("sqlite_mapping.hbm.xml")
	        		.buildSessionFactory();
	        Log.getLog(this).debug("session_factory for hibernate created");
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex.getMessage());
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	
	
	public synchronized SessionFactory getSessionFactory() {
		return session_factory;
	}
	
	public ModelFactory getModelFactory() {
		return model_factory;
	}
	
	public static ModelSingleton getInstance() {
		if(instance == null) {
			instance = new ModelSingleton();
		}
		return instance;
	}
	public void setOverrideSheme(Boolean state) {
		Log.getLog(this).debug("setOverrideSheme called");
		if(state)
			properties.setProperty("hibernate.hbm2ddl.auto", "create");
		else
			properties.remove("hibernate.hbm2ddl.auto");
		resetSessionFactory(db_path);
	}
}
