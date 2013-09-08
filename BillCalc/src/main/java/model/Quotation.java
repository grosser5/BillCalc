package main.java.model;

import java.util.Date;
import java.util.List;

public class Quotation {
	private int quotId;
	private int cutId;
	private Date date;
	private List<QuotationProduct> quotProducts;
	
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

	public int getCutId() {
		return cutId;
	}

	public void setCutId(int cutId) {
		this.cutId = cutId;
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
	
	
}
