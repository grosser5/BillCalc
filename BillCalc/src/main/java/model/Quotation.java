package main.java.model;

import java.sql.Date;
import java.util.List;

public class Quotation {
	private int quotId = 0;
	private Date date = null;
	private List<QuotationProduct> quotProducts = null;
	
	public Quotation(){}
	
	public Quotation(Date date, List<QuotationProduct> quotProducts) {
		this.date = date;
		this.quotProducts = quotProducts;
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
	
	public String toString() {
		
		return "Nr. " + quotId;
	}
}
