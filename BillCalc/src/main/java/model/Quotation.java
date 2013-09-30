package main.java.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quotation {
	private int custId = 0;
	private int quotId = 0;
	private Date date = null;
	private int quotNumber = 0;
	private String validUntil = "";
	private List<QuotationProduct> quotProducts;
	
	public final static String templateFieldId = "QUOTNUMBER";
	public final static String templateFieldDate = "DATE";
	public final static String templateFieldValidUntil = "VALIDUNTIL";
	
	public Quotation(){
		quotProducts = new ArrayList<QuotationProduct>();
	}
	
	public Quotation(Date date, List<QuotationProduct> quotProducts) {
		this.date = date;
		this.quotProducts = quotProducts;
	}

	public Quotation(Date date, int custId, int quotNumber, String validUntil) {
		this.date = date;
		quotProducts = new ArrayList<QuotationProduct>();
		this.custId = custId;
		this.quotNumber = quotNumber;
		this.validUntil = validUntil;
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

	public int getQuotNumber() {
		return quotNumber;
	}

	public void setQuotNumber(int quotNumber) {
		this.quotNumber = quotNumber;
	}

	public String getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
	}

	public String toString() {
		
		return "Nr. " + quotId;
	}
	
	public Map<String, String> getTemplateReplacement() {
		Map<String,String> temp_replacement= new HashMap<String,String>();
		temp_replacement.put(templateFieldId, Integer.toString(quotNumber) );
		temp_replacement.put(templateFieldDate, Integer.toString(date.getDay()) + "." +
				Integer.toString(date.getMonth()) + "." +
				Integer.toString(date.getYear()) );
		temp_replacement.put(templateFieldValidUntil, validUntil );
		
		return temp_replacement;
	}
}