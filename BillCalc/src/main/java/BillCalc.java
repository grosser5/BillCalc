package main.java;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.java.controller.BillController;
import main.java.controller.ControllerInterface;
import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.ManageDatabase;
import main.java.model.ModelFactory;
import main.java.model.Quotation;
import main.java.model.QuotationProduct;
import main.java.view.MainWindow;
import main.java.view.ViewInterface;
import main.view.util.Log;


public class BillCalc {
	
	 
	 
	 public static void main(String[] args) {
		 
		 
		 Log.getLog(new BillCalc()).debug("start app");
		 
		 EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ViewInterface view = new MainWindow();						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		 
		 
//		 ModelFactory mf = new ModelFactory();
//		 ManageDatabase manage_db = mf.createManageDatabase();
//		 
//		 
//		 
//		// List<CustomerLocation> cl = new ArrayList<CustomerLocation>(); 
//		 //cl.add( new CustomerLocation( "graz","Conrad von Hoetz. Strasse 12",8010 ) );
//		 
//		 //List<Quotation> quotations = new ArrayList<Quotation>();
//		 List<QuotationProduct> qproduct = new ArrayList<QuotationProduct>();
//		 qproduct.add(new QuotationProduct(2, 12,
//					100, 20, "trainingsplatz"));
//		 //quotations.add( new Quotation(new java.sql.Date(2013, 11, 19), qproduct) );
//
//		 Log.getLog(new BillCalc()).debug("get customer in next line");
//		 Customer cust = manage_db.getCustomer(1);
//		 cust.addLocation(new CustomerLocation( "graz","Conrad von Hoetz. Strasse 12",8010 ));
//		 cust.addQuotation( new Quotation(new java.sql.Date(2013, 11, 19), qproduct));
//		 Log.getLog(new BillCalc()).debug("cust: " + cust);
//		 manage_db.updateObj(cust);
//		 
//		 
//		manage_cust.saveCustomer("Franz mustermann", "GERa", cl, quotations);
		 
//	      try{
//	         ManageEmployee.setFactory( new Configuration().configure().buildSessionFactory() );
//	      }catch (Throwable ex) { 
//	         System.err.println("Failed to create sessionFactory object." + ex);
//	         throw new ExceptionInInitializerError(ex); 
//	      }
//	      
//	      Session session = ManageEmployee.getFactory().openSession();
//	      
//	      Criteria cr = session.createCriteria(Employee.class);
//	      List<Employee> results = cr.list();
//	      
//	      for (Employee e : results) {
//	    	  System.out.println("First Name: " + e.getFirstName() + " Last Name: " + e.getLastName());
//	      }
//	      session.close();
	      
	      

//	      HashSet set1 = new HashSet();
//	      set1.add(new Certificate("MCA"));
//	      set1.add(new Certificate("MBA"));
//	      set1.add(new Certificate("PMP"));
//	     
//	      /* Add employee records in the database */
//	      Integer empID1 = ME.addEmployee("Manoj", "Kumar", 4000, set1);
//
//	      /* Another set of certificates for the second employee  */
//	      HashSet set2 = new HashSet();
//	      set2.add(new Certificate("BCA"));
//	      set2.add(new Certificate("BA"));
//
//	      /* Add another employee record in the database */
//	      Integer empID2 = ME.addEmployee("Dilip", "Kumar", 3000, set2);
//
//	      /* List down all the employees */
//	      ME.listEmployees();
//
//	      /* Update employee's salary records */
//	      ME.updateEmployee(empID1, 5000);
//
//	      /* Delete an employee from the database */
//	      ME.deleteEmployee(empID2);
//
//	      /* List down all the employees */
//	      ME.listEmployees();
		 
		 Log.getLog(new BillCalc()).debug("End of App");
	   }
	
}
