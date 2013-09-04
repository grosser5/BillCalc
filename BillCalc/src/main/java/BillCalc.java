package main.java;

import java.util.HashSet;
import java.util.List;

import main.java.model.Certificate;
import main.java.model.Employee;
import main.java.model.ManageEmployee;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;


public class BillCalc {

	 public static void main(String[] args) {
	      try{
	         ManageEmployee.setFactory( new Configuration().configure().buildSessionFactory() );
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	      
	      Session session = ManageEmployee.getFactory().openSession();
	      
	      Criteria cr = session.createCriteria(Employee.class);
	      List<Employee> results = cr.list();
	      
	      for (Employee e : results) {
	    	  System.out.println("First Name: " + e.getFirstName() + " Last Name: " + e.getLastName());
	      }
	      session.close();

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
	   }
	
}
