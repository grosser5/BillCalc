package main.java.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.view.util.Log;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl.CriterionEntry;



public class ManageDatabase  {

	
	public ManageDatabase() { 
		
	}
	
	private void prepareDb(Session session) {
		session.createSQLQuery("PRAGMA foreign_keys = ON;");
	}
	
	public int saveCustomer(String name, String compType, List<CustomerLocation> locations,
			List<Quotation> quotations) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
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
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
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
	
	public List<Customer> getCustomer( String name ) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
		Transaction tx = null;
		List<Customer> results = null;
		try {
			tx = session.beginTransaction();
			prepareDb(session);
			String hql = "FROM Customer C WHERE C.name = '" + name + "'";
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
	
	public Customer getCustomer( int custId ) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
		Transaction tx = null;
		List<Customer> results = null;
		try {
			tx = session.beginTransaction();
			prepareDb(session);
			String hql = "FROM Customer C WHERE C.custId =" + custId ;
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
		if(results.iterator().hasNext())
			return results.iterator().next();
		return null;
	}
	
	
	public List getClasses(Class c ){
	      Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
	      Transaction tx = null;
	      List result = new ArrayList();
	      try{
	         tx = session.beginTransaction();
	         prepareDb(session);
	         Criteria cr = session.createCriteria(c);        
	         result = cr.list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return result;
	   }
	
	public void updateObj( Object obj ){
	      Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         prepareDb(session);
			 session.update(obj); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	
	public int saveObj(Object obj) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
		Transaction tx = null;
		Integer Id = null;
		try {
			tx = session.beginTransaction();
			prepareDb(session);
			Id = (Integer) session.save(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Id;
	}
	
	public void deleteObj(Object obj) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			prepareDb(session);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<Location> getLocation(String city, String street, int postal) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
		Transaction tx = null;
		List<Location> results = null;
		try {
			tx = session.beginTransaction();
			prepareDb(session);
			String hql = "FROM Location L WHERE L.city = '" + city + "' AND"
					+ " L.street='" + street + "' AND" + " L.postal=" + postal;
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
	
	public CustomerLocation getCustomerLocation( int custLocId ) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
		Transaction tx = null;
		List<CustomerLocation> results = null;
		try {
			tx = session.beginTransaction();
			prepareDb(session);
			String hql = "FROM CustomerLocation C WHERE C.id =" + custLocId ;
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
		if(results.iterator().hasNext())
			return results.iterator().next();
		return null;
	}

	public List<QuotationProduct> getQuotationProducts(int quot_id) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
		Transaction tx = null;
		List<QuotationProduct> result = new ArrayList();
		try{
			tx = session.beginTransaction();
		    prepareDb(session);
		    Criteria cr = session.createCriteria(QuotationProduct.class);
		    cr.add(Restrictions.eq("quotId", quot_id));
		    result = cr.list();
		    tx.commit();
		}catch (HibernateException e) {
		    if (tx!=null) tx.rollback();
		    	e.printStackTrace(); 
		    }finally {
		    	session.close(); 
		    }
		return result;
	}

	public List<Object> getFirstObject(Class c) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
	      Transaction tx = null;
	      List result = new ArrayList();
	      try{
	         tx = session.beginTransaction();
	         prepareDb(session);
	         Criteria cr = session.createCriteria(c);
	         cr.setFirstResult(0);
	         cr.setMaxResults(1);
	         result = cr.list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return result;
	}

	public Object getLastObj(Class c, String table_name) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
	      Transaction tx = null;
	      List result = new ArrayList();
	      try{
	         tx = session.beginTransaction();
	         prepareDb(session);
	         Criteria cr = session.createCriteria(c);
	         cr.addOrder(Order.desc(table_name));
	         cr.setFirstResult(0);
	         cr.setMaxResults(1);
	         
	         result = cr.list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return result;
	}

	public List<Quotation> getQuotations(int quot_number) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
	      Transaction tx = null;
	      List result = new ArrayList();
	      try{
	         tx = session.beginTransaction();
	         prepareDb(session);
	         Criteria cr = session.createCriteria(Quotation.class);
	         cr.add( Restrictions.eq("quotNumber", quot_number) );
	         result = cr.list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return result;
	}

	public List<Product> getProduct(int prodId) {
		Session session = ModelSingleton.getInstance().getSessionFactory().openSession();
	      Transaction tx = null;
	      List result = new ArrayList();
	      try{
	         tx = session.beginTransaction();
	         prepareDb(session);
	         Criteria cr = session.createCriteria(Product.class);
	         cr.add( Restrictions.eq("prodId", prodId) );
	         result = cr.list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return result;
	}

}
