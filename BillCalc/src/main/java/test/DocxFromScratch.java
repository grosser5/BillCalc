package main.java.test;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
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
		
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
		org.docx4j.wml.ObjectFactory obj = new org.docx4j.wml.ObjectFactory();
		
		wordMLPackage.getMainDocumentPart().addParagraphOfText("\n\n\n\n");
		
		// Sort out the styles.
        StyleDefinitionsPart sdp = wordMLPackage.getMainDocumentPart().getStyleDefinitionsPart ();

        sdp.setJaxbElement((Styles)XmlUtils.unmarshalString(styledef));
                        
        
		
		//create Paragraph with run
		P para = obj.createP();
		wordMLPackage.getMainDocumentPart().addObject(para);
		R run = obj.createR();
		para.getContent().add(run);
		
		
		PPr ppr = obj.createPPr();
		para.setPPr(ppr);
		
		PPrBase.PStyle pstyle = obj.createPPrBasePStyle();
		pstyle.setVal("Text body");
		
		
		ppr.setPStyle(pstyle);
	
		
//		//set Tabs for paragraph
		Tabs tabs = obj.createTabs();
		ppr.setTabs(tabs);

		CTTabStop ctts = obj.createCTTabStop();
		tabs.getTab().add(ctts);
		
        ctts.setPos(  new BigInteger("6800") );
        ctts.setVal(org.docx4j.wml.STTabJc.RIGHT);
//        
//		//set text addressPart and methaPart
		
		Text text = obj.createText();
//	
		text.setValue("Firma");
		
		run.getContent().add(text);
		run.getContent().add(new Tab());
		
		
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
			System.out.println("exception occured");
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
	
}
