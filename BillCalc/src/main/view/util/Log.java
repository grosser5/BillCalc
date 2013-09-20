package main.view.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import main.java.*;
import main.java.controller.*;
import main.java.model.*;
import main.java.view.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	private static Log instance = null;
	
	private Map<Class,Logger> loggers = new HashMap< Class,Logger>();
	
	private Log() {	}
	
	private static void initialize() {
		if (instance != null) {
			return;
		}
		
		File f = new File("log4j.properties");
		if (f.isFile()) {
			PropertyConfigurator.configure("log4j.properties");
		} else {
			BasicConfigurator.configure();
		}
		instance = new Log();
	}
	
	private Map<Class,Logger> getLoggers( ) {
		return this.loggers;
	}
	
	public static Logger getLog(Object obj) {
		initialize();
		if(! instance.getLoggers().containsKey(obj.getClass())) {
			instance.getLoggers().put(obj.getClass(), Logger.getLogger(obj.getClass()));
		}
		return instance.getLoggers().get(obj.getClass());
	}
	
	public static Logger getLog(Class c) {
		initialize();
		if(! instance.getLoggers().containsKey(c)) {
			instance.getLoggers().put(c, Logger.getLogger(c));
		}
		return instance.getLoggers().get(c.getClass());
	}
	
}
