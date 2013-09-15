package main.java;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import main.java.controller.BillController;
import main.java.controller.ControllerInterface;
import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.ManageDatabase;
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
		 
		 
		 
//		 ManageDatabase manage_cust = new ManageDatabase();
//		 
//		 
//		 List<CustomerLocation> cl = new ArrayList<CustomerLocation>(); 
//		 cl.add( new CustomerLocation( "tirol","Griesplatz 12",8010 ) );
//		 
//		 List<Quotation> quotations = new ArrayList<Quotation>();
//		 List<QuotationProduct> qproduct = new ArrayList<QuotationProduct>();
//		 qproduct.add(new QuotationProduct(1, 12,
//					300, 20, "trainingsplatz"));
//		 quotations.add( new Quotation(new java.util.Date(2013,9,20), qproduct) );
//		 
//		 List<Customer> cust_list = manage_cust.listCustomers();
//		 
//		 Log.getBillCalcLogger().info("size of cust_list: " + cust_list.size() + "\n");
//		 for(Customer cust : cust_list) {
//			 Log.getBillCalcLogger().info("custId="+cust.getCustId()+", custName=" + 
//					 	cust.getName()+"\n");
//			 cust.setName("Hannes");
//			 manage_cust.updateCustomer(cust);
//		 }
		 
		 
		 
//		 cust.setName("Gustav");
//		 manage_cust.updateCustomer(cust);
		 //manage_cust.saveCustomer("Franz mustermann", "GERa", cl, quotations);
		 
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
