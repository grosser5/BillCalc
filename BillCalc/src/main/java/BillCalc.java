package main.java;

import main.java.model.ManageEmployee;

import org.hibernate.cfg.Configuration;


public class BillCalc {

	 public static void main(String[] args) {
		 ManageEmployee ME = new ManageEmployee();
	      try{
	         ManageEmployee.setFactory( new Configuration().configure().buildSessionFactory() );
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	      

	      /* Add few employee records in database */
	      Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
	      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
	      Integer empID3 = ME.addEmployee("John", "Paul", 10000);

	      /* List down all the employees */
	      ME.listEmployees();

	      /* Update employee's records */
	      ME.updateEmployee(empID1, 9000);

	      /* Delete an employee from the database */
	      ME.deleteEmployee(empID2);

	      /* List down new list of the employees */
	      ME.listEmployees();
	   }
	
}
