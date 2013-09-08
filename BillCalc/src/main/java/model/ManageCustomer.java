package main.java.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.view.util.Log;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class ManageCustomer {

	public ManageCustomer() { }
	
	public int saveCustomer(String name, String compType, List<CustomerLocation> locations) {
		Session session = ModelSingleton.getSessionFactory().openSession();
		Log.getManageCustomerLogger().info("factory\n");
		Transaction tx = null;
		Integer custId = null;

		try {
			tx = session.beginTransaction();
			session.createSQLQuery("PRAGMA foreign_keys = ON;");
			
			//check if the location is already in the database
			for(Iterator<CustomerLocation> it = locations.iterator(); it.hasNext();) {
				Location loc = it.next().getLocation();
				List<Location> fount_locs = getLocation(loc.getCity(),loc.getStreet(),loc.getPostal());
				if(!fount_locs.isEmpty()) {					
					loc.setLocId(fount_locs.iterator().next().getLocId());
				}
			}
			//check if the customer is already in the database
			List<Customer> custs = getCustomer(name, compType);
			Customer new_cust = new Customer(name,compType,locations);
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
	
	public List<Location> getLocation(String city, String street, int postal) {
		Session session = ModelSingleton.getSessionFactory().openSession();
		Transaction tx = null;
		List<Location> results = null;
		try {
			tx = session.beginTransaction();
			String hql = "FROM Location L WHERE L.city = '" + city + "' AND"
					+ " L.street='"+street+"' AND"
					+ " L.postal=" + postal;
			Query query = session.createQuery(hql);
			results = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return results;
	}
	
	public List<Customer> getCustomer( String name, String compType ) {
		Session session = ModelSingleton.getSessionFactory().openSession();
		Transaction tx = null;
		List<Customer> results = null;
		try {
			tx = session.beginTransaction();
			String hql = "FROM Customer C WHERE C.name = '" + name + "' AND"
					+ " C.compType='"+ compType + "'";
			Query query = session.createQuery(hql);
			results = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return results;
	}
	
	public int saveProduct(Product p) {
		Session session = ModelSingleton.getSessionFactory().openSession();
		Transaction tx = null;
		Integer custId = null;
		try {
			tx = session.beginTransaction();
			session.createSQLQuery("PRAGMA foreign_keys = ON;");
			
			custId = (Integer) session.save(p);
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
