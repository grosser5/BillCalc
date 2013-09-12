package main.java.model;

public class Product {
	int prodId;
	String name = "";
	int defaultCostPerQuantity = 0;
	String quantityUnit = "";
	
	public Product() { }

	public Product(String name, int defaultCostPerQuantity, String quantityUnit) {
		super();
		this.name = name;
		this.defaultCostPerQuantity = defaultCostPerQuantity;
		this.quantityUnit = quantityUnit;
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
	
	public String toString() {
		return name;
	}
	
}
