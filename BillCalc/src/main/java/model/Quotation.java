package main.java.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Quotation {
	private int custId = 0;
	private int quotId = 0;
	private Date date = null;
	private List<QuotationProduct> quotProducts;
	
	public Quotation(){
		quotProducts = new ArrayList<QuotationProduct>();
	}
	
	public Quotation(Date date, List<QuotationProduct> quotProducts) {
		this.date = date;
		this.quotProducts = quotProducts;
	}

	public Quotation(Date date, int custId) {
		this.date = date;
		quotProducts = new ArrayList<QuotationProduct>();
		this.custId = custId;
	}
	
	public Quotation(Quotation q) {
		custId = q.getCustId();
		quotId = q.getQuotId();
		date = (Date) q.getDate().clone();
		quotProducts = new ArrayList<QuotationProduct>();
		
		for( QuotationProduct p : q.getQuotProducts() ) {
			quotProducts.add( new QuotationProduct(p) );
		}
		
	}

	public int getQuotId() {
		return quotId;
	}

	public void setQuotId(int quotId) {
		this.quotId = quotId;
	}

	public List<QuotationProduct> getQuotProducts() {
		return quotProducts;
	}

	public void setQuotProducts(List<QuotationProduct> quotProducts) {
		this.quotProducts = quotProducts;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getCustId() {
		return custId;
	}

	public void setCustId(int cutId) {
		this.custId = cutId;
	}

	public String toString() {
		
		return "Nr. " + quotId;
	}
}
