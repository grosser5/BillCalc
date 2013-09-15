package main.java.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.view.util.Log;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl.CriterionEntry;



public class ManageDatabase  {

	ModelFactory factory;
	
	public ManageDatabase(ModelFactory factory) { 
		this.factory = factory; 
	}
	
	private void prepareDb(Session session) {
		session.createSQLQuery("PRAGMA foreign_keys = ON;");
	}
	
	public int saveCustomer(String name, String compType, List<CustomerLocation> locations,
			List<Quotation> quotations) {
		Session session = ModelSingleton.getSessionFactory().openSession();
		Log.getLog(this).info("factory\n");
		Transaction tx = null;
		Integer custId = null;

		try {
			tx = session.beginTransaction();
			prepareDb(session);
			
			//check if the location is already in the database
			for(Iterator<CustomerLocation> it = locations.iterator(); it.hasNext();) {
				Location loc = it.next().getLocation();
				List<Location> fount_locs = getLocation(loc.getCity(),loc.getStreet(),loc.getPostal());
				if( !fount_locs.isEmpty() ) {
					loc.setLocId(fount_locs.iterator().next().getLocId());
				}
			}
			//check if the customer is already in the database
			List<Customer> custs = getCustomer(name, compType);
			Customer new_cust = new Customer(name,compType,locations);
			new_cust.setQuotations(quotations);
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
	
	public List<Customer> getCustomer( String name, String compType ) {
		Session session = ModelSingleton.getSessionFactory().openSession();
		Transaction tx = null;
		List<Customer> results = null;
		try {
			tx = session.beginTransaction();
			prepareDb(session);
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
	
	public List<Customer> listCustomers( ){
	      Session session = ModelSingleton.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<Customer> result = new ArrayList();
	      try{
	         tx = session.beginTransaction();
	         prepareDb(session);
	         Criteria cr = session.createCriteria(Customer.class);
	         result = cr.list();
	         
//	         Query query = session.createQuery("FROM Customer");
//	         result = query.list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return result;
	   }
	
	public void updateCustomer( Customer customer ){
	      Session session = ModelSingleton.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         prepareDb(session);
			 session.update(customer); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	
	public int saveProduct(Product p) {
		Session session = ModelSingleton.getSessionFactory().openSession();
		Transaction tx = null;
		Integer custId = null;
		try {
			tx = session.beginTransaction();
			prepareDb(session);
			
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
	
	public List<Product> listProducts( ){
	      Session session = ModelSingleton.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<Product> result = new ArrayList();
	      try{
	         tx = session.beginTransaction();
	         prepareDb(session);
//	         Criteria cr = session.createCriteria(Employee.class);
//	         customers = cr.list();
	         
	         Query query = session.createQuery("FROM Product");
	         result = query.list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return result;
	   }
	
	public void updateProduct( Product product ){
	      Session session = ModelSingleton.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         prepareDb(session);
			 session.update(product); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	
	public List<Location> getLocation(String city, String street, int postal) {
		Session session = ModelSingleton.getSessionFactory().openSession();
		Transaction tx = null;
		List<Location> results = null;
		try {
			tx = session.beginTransaction();
			prepareDb(session);
			String hql = "FROM Location L WHERE L.city = '" + city + "' AND"
					+ " L.street='"+street+"' AND"
					+ " L.postal=" + postal;
			Query query = session.createQuery(hql);
			Log.getLog(this).info("call querry by getLocation \n");
			results = query.list();
			tx.commit();
			Log.getLog(this).info("after querry by getLocation \n");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return results;
	}
	
	
}
