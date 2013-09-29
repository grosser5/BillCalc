package main.java.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.view.util.Log;

import org.apache.log4j.Logger;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.OpcPackage;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.CustomXmlDataStoragePart;
import org.docx4j.openpackaging.parts.JaxbXmlPart;
import org.docx4j.openpackaging.parts.WordprocessingML.StyleDefinitionsPart;
import org.docx4j.wml.*;
import org.docx4j.wml.R.Tab;



public class DocxFromScratch {


	private AddressPart address = null;
	private MetaPart metapart = null;
	private ArrayList<SettlementPart> settlementList = null;
	
	
	DocxFromScratch (AddressPart address, MetaPart metapart) {
		this.setAddressPart(address);
		this.setMetapart(metapart);
	}
	DocxFromScratch () {}
	
	public AddressPart getAddressPart() {
		return address;
	}

	public void setAddressPart(AddressPart address) {
		this.address = address;
	}
	
	public MetaPart getMetapart() {
		return metapart;
	}

	public void setMetapart(MetaPart metapart) {
		this.metapart = metapart;
	}
	
	
	
	public void write() throws Exception{
		Log.getLog(this).debug("start program");
		//WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
		org.docx4j.wml.ObjectFactory obj = new org.docx4j.wml.ObjectFactory();
		
		OpcPackage doc = null;
		try {
			InputStream in = new FileInputStream( new File("helloMavenCentral.docx") );
			doc = OpcPackage.load(in);
		} catch(Exception e) {
			Log.getLog(this).debug(e.getMessage());
		}
		if(doc == null) {
			return;
		}
		WordprocessingMLPackage wordMLPackage = (WordprocessingMLPackage)doc;
		
		OutputStream out_stream = new java.io.ByteArrayOutputStream();
		
		wordMLPackage.getMainDocumentPart().marshal(out_stream);
		
		String document_xml = out_stream.toString();
		
		String new_document_xml = document_xml.replace("Firma", "new Firma");
		
		InputStream in_stream = new java.io.ByteArrayInputStream(new_document_xml.getBytes());
		
		wordMLPackage.getMainDocumentPart().unmarshal(in_stream);
		
//		wordMLPackage.getMainDocumentPart().setJaxbElement((Document)XmlUtils.unmarshalString(getDocDef()));
//		wordMLPackage.getMainDocumentPart().getStyleDefinitionsPart ()
//		.setJaxbElement((Styles)XmlUtils.unmarshalString(styledef));
//		Log.getLog(this).debug("addParagraphOfText");
//		wordMLPackage.getMainDocumentPart().addParagraphOfText("\n\n\n\n");
//		
//		Log.getLog(this).debug("sort out the styles");
//		// Sort out the styles.
//        StyleDefinitionsPart sdp = wordMLPackage.getMainDocumentPart().getStyleDefinitionsPart ();
//
//       // sdp.setJaxbElement((Styles)XmlUtils.unmarshalString(styledef));
//                        
//        
//		
//		//create Paragraph with run
//		P para = obj.createP();
//		wordMLPackage.getMainDocumentPart().addObject(para);
//		R run = obj.createR();
//		para.getContent().add(run);
//		
//		
//		PPr ppr = obj.createPPr();
//		para.setPPr(ppr);
//		
//		PPrBase.PStyle pstyle = obj.createPPrBasePStyle();
//		pstyle.setVal("Text body");
//		
//		
//		ppr.setPStyle(pstyle);
//	
//		
////		//set Tabs for paragraph
//		Tabs tabs = obj.createTabs();
//		ppr.setTabs(tabs);
//
//		CTTabStop ctts = obj.createCTTabStop();
//		tabs.getTab().add(ctts);
//		
//        ctts.setPos(  new BigInteger("6800") );
//        ctts.setVal(org.docx4j.wml.STTabJc.RIGHT);
////        
////		//set text addressPart and methaPart
//		
//		Text text = obj.createText();
////	
//		text.setValue("Firma");
//		
//		run.getContent().add(text);
//		run.getContent().add(new Tab());
		
		Log.getLog(this).debug("save it");
		// Now save it
	    wordMLPackage.save(new java.io.File(System.getProperty("user.dir") + "/helloMavenCentral.docx") );
		
		
		
	}
	
public static void main(String[] args) throws InvalidFormatException  {
		
		
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
		org.docx4j.wml.ObjectFactory obj_factory = new org.docx4j.wml.ObjectFactory();
		
		
		
		AddressPart address = new DocxFromScratch().new AddressPart("Richter Rasen","Ges.m.b.H.",
				"Kirchengasse 2","2443","Deutsch Brodersdorf");
		MetaPart meta = new DocxFromScratch().new MetaPart("6.8.2013", "muendlichHerr Neumeiseter", "muendlich", "juli 2013",
				"Sportplatz Gleinstaetten", "ATU 234324", "13-233");
		
		SettlementPart sett = new DocxFromScratch().new SettlementPart("Verlegearbeiten von " +
				"Rollrasen im \"Tondachstadion Gleinstaetten\"","7.000", "2,5/l","17.500","20");
		DocxFromScratch test = new DocxFromScratch(address, meta);
		
		try {
		test.write();
		}
		catch ( Exception e) {
			Log.getLog(new DocxFromScratch()).debug(e);
			System.exit(1);
		}
		
	}


	public class AddressPart {
		
		public String name = null, company_type = null,
			   street = null, plz = null,
			   district = null;
		
		AddressPart() {			
		}
		
		AddressPart(String name, String company_type, String street, String plz, String district) {
			this.name = name;
			this.company_type = company_type;
			this.street = street;
			this.plz = plz;
			this.district = district;
		}
	}

	
	public class MetaPart {
		
		public String datum = null, bestellung = null, angebotnr = null,
				datum_geliefert = null, baustelle = null, uidnr = null,
				rechnungs_nr = null;
		
		MetaPart(String datum, String bestellung, String angebotnr, String datum_geliefert,
				String baustelle, String uidnr, String rechnungs_nr) {
			this.datum = datum;
			this.bestellung = bestellung;
			this.angebotnr = angebotnr;
			this.datum_geliefert = datum_geliefert;
			this.baustelle = baustelle;
			this.uidnr = uidnr;
			this.rechnungs_nr = rechnungs_nr;
		}
		
		MetaPart(){}
	}
	
	public class SettlementPart {
		String text, menge, preis, betrag, mwst;
		
		SettlementPart() {}
		
		SettlementPart(String text,String  menge,String  preis,String  betrag,String  mwst) {
			this.text = text;
			this.menge = menge;
			this.preis = preis;
			this.betrag = betrag;
			this.mwst = mwst;
		}
	}
	
	
	private String styledef = 
			"<w:style w:type=\"paragraph\" w:styleId=\"Normal\">" +
				"<w:name w:val=\"Normal\"/>" +
				"<w:qFormat/>" +
				"<w:rPr>" +
					"<w:rFonts w:ascii=\"Courier New\" w:hAnsi=\"Courier New\"/>" +
					"<w:sz w:val=\"16\"/>" +
				"</w:rPr>" +
			"</w:style>";
	private String getDocDef() {
		StringBuilder str = new StringBuilder();
		str.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		str.append("<w:document xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\" xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\">");
		str.append("<w:body>");
		str.append("<w:p>");
		str.append("<w:pPr>");
		str.append("<w:pStyle w:val=\"style0\"/>");
		str.append("</w:pPr>");
		str.append("<w:r>");
		str.append("<w:rPr>");
		str.append("</w:rPr>");
		str.append("<w:br/>");
		str.append("<w:br/>");
		str.append("<w:br/>");
		str.append("<w:br/>");
		str.append("</w:r>");
		str.append("</w:p>");
		str.append("<w:p>");
		str.append("<w:pPr>");
		str.append("<w:pStyle w:val=\"style26\"/>");
		str.append("<w:tabs>");
		str.append("<w:tab w:leader=\"none\" w:pos=\"6800\" w:val=\"right\"/>");
		str.append("</w:tabs>");
		str.append("<w:spacing w:after=\"120\" w:before=\"0\"/>");
		str.append("<w:contextualSpacing w:val=\"false\"/>");
		str.append("</w:pPr>");
		str.append("<w:r>");
		str.append("<w:rPr>");
		str.append("<w:rFonts w:ascii=\"Courier\" w:hAnsi=\"Courier\"/>");
		str.append("</w:rPr>");
		str.append("<w:t>Firma</w:t>");
		str.append("</w:r>");
		str.append("<w:r>");
		str.append("<w:rPr>");
		str.append("</w:rPr>");
		str.append("<w:tab/>");
		str.append("</w:r>");
		str.append("</w:p>");
		str.append("<w:sectPr>");
		str.append("<w:type w:val=\"nextPage\"/>");
		str.append("<w:pgSz w:h=\"15840\" w:w=\"12240\"/>");
		str.append("<w:pgMar w:bottom=\"1440\" w:footer=\"0\" w:gutter=\"0\" w:header=\"0\" w:left=\"1440\" w:right=\"1440\" w:top=\"1440\"/>");
		str.append("<w:pgNumType w:fmt=\"decimal\"/>");
		str.append("<w:formProt w:val=\"false\"/>");
		str.append("<w:textDirection w:val=\"lrTb\"/>");
		str.append("<w:docGrid w:charSpace=\"4096\" w:linePitch=\"240\" w:type=\"default\"/>");
		str.append("</w:sectPr>");
		str.append("</w:body>");
		str.append("</w:document>");
		return str.toString();
	}
	
}

