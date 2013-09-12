package main.java.model;

import java.util.Date;

public class QuotationProduct {

	private int quotProdId;
	private int prodId;
	private int quotId;
	private int costPerQuantity = 0;
	private int quantity = 0;
	private int mwst = 20;
	private String place = "";
	// not in the database 
	private int price = 0;
	
	public QuotationProduct () {	}
	
	public QuotationProduct (Product p, Quotation q) {
		prodId = p.getProdId();
		quotId = q.getQuotId();
		costPerQuantity = p.getDefaultCostPerQuantity();
		
	}

	
	
	public QuotationProduct(int prodId, int costPerQuantity,
			int quantity, int mwst, String place) {
		super();
		this.prodId = prodId;
		this.quotId = quotId;
		this.costPerQuantity = costPerQuantity;
		this.quantity = quantity;
		this.mwst = mwst;
		this.place = place;
	}

	public int getQuotProdId() {
		return quotProdId;
	}

	public void setQuotProdId(int quotProdId) {
		this.quotProdId = quotProdId;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public int getQuotId() {
		return quotId;
	}

	public void setQuotId(int quotId) {
		this.quotId = quotId;
	}

	public int getCostPerQuantity() {
		return costPerQuantity;
	}

	public void setCostPerQuantity(int costPerQuantity) {
		this.costPerQuantity = costPerQuantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMwst() {
		return mwst;
	}

	public void setMwst(int mwst) {
		this.mwst = mwst;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
