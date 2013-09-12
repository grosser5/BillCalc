package main.view.util;

import java.io.File;

import main.java.*;
import main.java.controller.*;
import main.java.model.*;
import main.java.view.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	private static boolean initialized = false;
	private static Logger billCalcLogger;
	private static Logger manageCustomerLogger;
	private static Logger customerLogger;
	private static Logger locationLogger;
	private static Logger quotationLogger;
	private static Logger abstractCommandLogger;
	private static Logger billControllerLogger;
	private static Logger newProductCommandLogger;
	private static Logger remoteLogger;
	private static Logger searchCustomerLogger;
	private static Logger searchCustomerCommandLogger;
	private static Logger mainWindowLogger;
	private static Logger productTableModelLogger;
	
	
	private Log() {	}
	
	private static void initialize() {
		if (initialized == true) {
			return;
		}
		
		File f = new File("log4j.properties");
		if (f.isFile()) {
			PropertyConfigurator.configure("log4j.properties");
		} else {
			BasicConfigurator.configure();
		}
		billCalcLogger =  Logger.getLogger(BillCalc.class);
		manageCustomerLogger = Logger.getLogger(ManageDatabase.class);
		customerLogger =  Logger.getLogger(Customer.class);
		locationLogger =  Logger.getLogger(Location.class);
		quotationLogger =  Logger.getLogger(Quotation.class);
		abstractCommandLogger =  Logger.getLogger(BillController.class);
		billControllerLogger =  Logger.getLogger(BillController.class);
		newProductCommandLogger =  Logger.getLogger(NewProductCommand.class);
		remoteLogger =  Logger.getLogger(Remote.class);
		searchCustomerLogger =  Logger.getLogger(SearchCustomer.class);
		mainWindowLogger=  Logger.getLogger(MainWindow.class);		
		productTableModelLogger =  Logger.getLogger(main.java.view.ProductTableModel.class);
	}
	public static Logger getBillCalcLogger() {
		initialize();
		return billCalcLogger;
	}
	
	public static Logger getManageCustomerLogger() {
		initialize();
		return manageCustomerLogger;
	}

	public static boolean isInitialized() {
		initialize();
		return initialized;
	}

	public static Logger getCustomerLogger() {
		initialize();
		return customerLogger;
	}

	public static Logger getLocationLogger() {
		initialize();
		return locationLogger;
	}

	public static Logger getQuotationLogger() {
		initialize();
		return quotationLogger;
	}

	public static Logger getAbstractCommandLogger() {
		initialize();
		return abstractCommandLogger;
	}

	public static Logger getBillControllerLogger() {
		initialize();
		return billControllerLogger;
	}

	public static Logger getNewProductCommandLogger() {
		initialize();
		return newProductCommandLogger;
	}

	public static Logger getRemoteLogger() {
		initialize();
		return remoteLogger;
	}

	public static Logger getSearchCustomerLogger() {
		initialize();
		return searchCustomerLogger;
	}

	public static Logger getSearchCustomerCommandLogger() {
		initialize();
		return searchCustomerCommandLogger;
	}

	public static Logger getMainWindowLogger() {
		initialize();
		return mainWindowLogger;
	}

	public static Logger getProductTableModelLogger() {
		initialize();
		return productTableModelLogger;
	}
	
}
