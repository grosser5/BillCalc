package main.java.model;

import java.util.HashMap;
import java.util.Map;

public class Product {
	int prodId;
	String name = "";
	int defaultCostPerQuantity = 0;
	String quantityUnit = "";
	String text = "";
	
	public final static String templateFieldName = "PRODUCTNAME";
	public final static String templateFieldQuantityUnit = "QUANTITYUNIT";
	public final static String templateFieldText = "PRODUCTTEXT";
	
	
	
	public Product() { }

	public Product(String name, int defaultCostPerQuantity, String quantityUnit, String text) {
		super();
		this.name = name;
		this.defaultCostPerQuantity = defaultCostPerQuantity;
		this.quantityUnit = quantityUnit;
		this.text = text;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDefaultCostPerQuantity() {
		return defaultCostPerQuantity;
	}

	public void setDefaultCostPerQuantity(int defaultCostPerQuantity) {
		this.defaultCostPerQuantity = defaultCostPerQuantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return name;
	}

	public Map<String, String> getTemplateReplacement() {
		Map<String,String> temp_replacement= new HashMap<String,String>();
		temp_replacement.put(templateFieldName, name);
		temp_replacement.put(templateFieldQuantityUnit, quantityUnit);
		temp_replacement.put(templateFieldText, text);
		return temp_replacement;
	}
	
	
}
