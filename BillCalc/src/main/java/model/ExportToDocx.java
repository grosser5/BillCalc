package main.java.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

import main.view.util.Log;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.OpcPackage;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


public class ExportToDocx {

	public void export(Quotation q, CustomerLocation loc, String path) throws JAXBException, FileNotFoundException, Docx4JException {
		ModelFactory factory= ModelSingleton.getInstance().getModelFactory();
		ManageDatabase manage_db = factory.createManageDatabase();
		
		Customer cust = manage_db.getCustomer(q.getCustId());
		
		
		org.docx4j.wml.ObjectFactory obj = new org.docx4j.wml.ObjectFactory();
		
		OpcPackage doc = null;
	
		try {
			InputStream in = new FileInputStream(new File("Vorlage.docx"));
			doc = OpcPackage.load(in);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Vorlagedatei nicht gefunden.");
		} catch (Docx4JException e) {
			throw new Docx4JException("Vorlagedatei hat falsches Format.");
		}

		if(doc == null) {
			return;
		}
		WordprocessingMLPackage wordMLPackage = (WordprocessingMLPackage)doc;
		
		OutputStream out_stream = new java.io.ByteArrayOutputStream();
		
		try{
			wordMLPackage.getMainDocumentPart().marshal(out_stream);
		} catch(JAXBException e) {
			throw new JAXBException("Vorlagedatei hat falsches Format.");
		}
		
		String document_xml = out_stream.toString();
		
		//add all replacement strings to a map
		List<Map<String, String>> template_list = new ArrayList();
		
		template_list.add(cust.getTemplateReplacement());
		template_list.add(q.getTemplateReplacement());
		template_list.add(loc.getTemplateReplacement());

		for(QuotationProduct qp : manage_db.getQuotationProducts(q.getQuotId())) {
			template_list.add(qp.getTemplateReplacement());
			//format the text string from the product
			List<Product> p_list = manage_db.getProduct(qp.getProdId());
			if(!p_list.isEmpty()) {
				Product p = p_list.iterator().next();
				Map<String,String> prod_template = p.getTemplateReplacement();
				String text = prod_template.get(Product.templateFieldText);
				String split_text[] = text.split(" ");
				String new_text = "";
				for(int i=0, chars_count=0; i < split_text.length; i++) {
					chars_count += split_text[i].length();
					new_text += split_text[i];
					if(chars_count > 25) {
						chars_count = 0;
						new_text += "\n";
					}
				}
				prod_template.put(Product.templateFieldText, new_text);
				template_list.add(prod_template);
			}
			
		}
		
		//iterate through the template map
		for(Map<String,String> map : template_list) {
			Set<String> key_set = map.keySet();
			for(String key : key_set) {
				
			}
		}
		
		document_xml = document_xml.replace(Customer.templateFieldName, cust.getName());
		document_xml = document_xml.replace(Customer.templateFieldCompType, cust.getCompType());
		
		
		InputStream in_stream = new java.io.ByteArrayInputStream(document_xml.getBytes());
		
		wordMLPackage.getMainDocumentPart().unmarshal(in_stream);
		
		wordMLPackage.save(new java.io.File(System.getProperty("user.dir") + "/helloMavenCentral.docx") );
	}
}
