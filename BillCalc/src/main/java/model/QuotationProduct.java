package main.java.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuotationProduct {

	private int quotProdId = 0;
	private int prodId = 0;
	private int quotId = 0;
	private int costPerQuantity = 0;
	private int quantity = 0;
	private int mwst = 0;
	private String place = "";
	// not in the database 
	private int price = 0;
	
	public final static String templateFieldQuantity = "QUANTITY";
	public final static String templateFieldMWST = "MWST";
	public final static String templateFieldPlace = "PLACE";
	public final static String templateFieldPrice = "PRICE";
	public final static String templateFieldCostPerQuant = "COST_PER_QUANT";
	public final static String templateFieldAllQuotProds = "QUOTPRODUCTS";
	
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
		this.costPerQuantity = costPerQuantity;
		this.quantity = quantity;
		this.mwst = mwst;
		this.place = place;
	}

	public QuotationProduct(QuotationProduct p) {
		quotProdId = p.getQuotProdId();
		prodId = p.getProdId();
		quotId = p.getQuotId();
		costPerQuantity = p.getCostPerQuantity();
		quantity = p.getQuantity();
		mwst = p.getMwst();
		place = p.getPlace();
		price = p.getPrice();
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
	
	public String toString() {
		return "Id: " + getQuotProdId() + " Euro/Menge: " + costPerQuantity + " Menge: " + quantity 
				+ " MWST: " + mwst + " Wo?: " + place;
	}
	
	public Map<String, String> getTemplateReplacement() {
		Map<String,String> temp_replacement= new HashMap<String,String>();
		temp_replacement.put(templateFieldQuantity, Integer.toString(quantity) );
		temp_replacement.put(templateFieldMWST, Integer.toString(mwst) );
		temp_replacement.put(templateFieldPlace, place );
		temp_replacement.put(templateFieldPrice, Integer.toString(price));
		temp_replacement.put(templateFieldCostPerQuant, Integer.toString(costPerQuantity));
		return temp_replacement;
	}
	
}
