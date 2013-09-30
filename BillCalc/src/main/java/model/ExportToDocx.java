package main.java.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

import main.view.util.Log;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.OpcPackage;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


public class ExportToDocx {

	public void exportDocx(int quot_id, int cust_loc_id, String docx_path) 
			throws IllegalArgumentException {
		Log.getLog(this).debug("exportDocx called");
		Log.getLog(this).info("quot_id = " + quot_id + " cust_loc_id = " + cust_loc_id + " docx_path = " + docx_path);
		
		ModelFactory factory= ModelSingleton.getInstance().getModelFactory();
		ManageDatabase manage_db = factory.createManageDatabase();
		
		Quotation q = manage_db.getQuotation(quot_id);
		Customer cust = manage_db.getCustomer(q.getCustId());
		CustomerLocation loc = manage_db.getCustomerLocation(cust_loc_id);
		
		org.docx4j.wml.ObjectFactory obj = new org.docx4j.wml.ObjectFactory();
		
		OpcPackage doc = null;
	
		try {
			InputStream in = new FileInputStream(new File("Vorlage.docx"));
			doc = OpcPackage.load(in);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Vorlagedatei nicht gefunden.");
		} catch (Docx4JException e) {
			throw new IllegalArgumentException("Vorlagedatei hat falsches Format.");
		}

		if(doc == null) {
			return;
		}
		WordprocessingMLPackage wordMLPackage = (WordprocessingMLPackage)doc;
		
		OutputStream out_stream = new java.io.ByteArrayOutputStream();
		
		try{
			wordMLPackage.getMainDocumentPart().marshal(out_stream);
		} catch(JAXBException e) {
			throw new IllegalArgumentException("Vorlagedatei hat falsches Format.");
		}
		
		String document_xml = out_stream.toString();
		
		//add all replacement strings to a map
		List<Map<String, String>> template_list = new ArrayList();
		
		template_list.add(cust.getTemplateReplacement());
		template_list.add(q.getTemplateReplacement());
		template_list.add(loc.getTemplateReplacement());

		String quotation_prod_string = "";
		for(QuotationProduct qp : manage_db.getQuotationProducts(q.getQuotId())) {
			Map<String,String> quot_prod_template = qp.getTemplateReplacement();
			//format the text string from the product
			List<Product> p_list = manage_db.getProduct(qp.getProdId());
			if(!p_list.isEmpty()) {
				Product p = p_list.iterator().next();
				Map<String,String> prod_template = p.getTemplateReplacement();
				String text = prod_template.get(Product.templateFieldText);
				String split_text[] = text.split(" ");
				String formated_text = "";
				for(int i=0, chars_count=0; i < split_text.length; i++) {
					chars_count += split_text[i].length();
					formated_text += split_text[i] + " ";
					if(chars_count > 25) {
						chars_count = 0;
						formated_text += "\n";
					}
				}
				quotation_prod_string +=  getDocxText(formated_text) 
						+ "<w:tab/>" + getDocxText( quot_prod_template.get(QuotationProduct.templateFieldQuantity) 
						+  " " + prod_template.get(Product.templateFieldQuantityUnit) )
						+ "<w:tab/>" + getDocxText( quot_prod_template.get(QuotationProduct.templateFieldCostPerQuant) )
						+ "<w:tab/>" + getDocxText( quot_prod_template.get(QuotationProduct.templateFieldPrice) );
				
				
			}
		}
		Map<String, String> quot_prods_templates = new HashMap();
		Log.getLog(this).debug("quotation_prod_string: " + quotation_prod_string);
		quot_prods_templates.put("<w:t>" +QuotationProduct.templateFieldAllQuotProds + "</w:t>", quotation_prod_string);
		template_list.add(quot_prods_templates);
		
		//iterate through the template map
		for(Map<String,String> map : template_list) {
			Set<String> key_set = map.keySet();
			for(String key : key_set) {
				Log.getLog(this).debug("replace obj: " + key + " with: " + map.get(key));
				document_xml = document_xml.replace(key, (String )map.get(key));
			}
		}
		
		Log.getLog(this).debug("document xml: " + document_xml);
		InputStream in_stream = new java.io.ByteArrayInputStream(document_xml.getBytes());
		
		try {
		wordMLPackage.getMainDocumentPart().unmarshal(in_stream);
		
		wordMLPackage.save(new java.io.File(docx_path) );
		} catch(JAXBException  | Docx4JException e) {
			throw new IllegalArgumentException("Vorlage hat falsches Format.");
		}
	}
	
	private String getDocxText(String s) {
		return "<w:t>" + s + "</w:t>";
	}
}
