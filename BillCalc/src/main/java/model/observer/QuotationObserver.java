package main.java.model.observer;

import java.util.List;

import main.java.model.Quotation;

public interface QuotationObserver {
	void updateQuotationList(List<Quotation> quotList);
}
