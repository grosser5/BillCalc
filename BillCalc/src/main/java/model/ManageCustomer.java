package main.java.model;

import java.util.ArrayList;

import main.view.util.Log;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ManageCustomer {

	SessionFactory factory;
	
	public ManageCustomer() {
		
		this.factory = ModelSingleton.getSessionFactory();
		Log.getManageCustomerLogger().info("constructor after factory");
	}
	
	public int saveCustomer(String name, String compType, Location location) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer custId = null;
		try {
			tx = session.beginTransaction();
			Customer new_cust = new Customer(name,compType,location);
			
			custId = (Integer) session.save(new_cust);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return custId;
	}
}
