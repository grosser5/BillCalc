package test;
import java.io.File;
import java.math.BigInteger;
import java.util.List;

import org.docx4j.XmlUtils;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.model.structure.jaxb.Sections.Section;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.wml.Numbering;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;
import org.docx4j.wml.PPrBase.NumPr;
import org.docx4j.wml.PPrBase.NumPr.Ilvl;
import org.docx4j.wml.PPrBase.NumPr.NumId;


public class DocxTest {
	 public static void main(String[] args) throws Exception {
		 
		 	
		 
	        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
	 
	        org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart numberpart = 
	        		new  org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart();
	        
	        wordMLPackage.getMainDocumentPart().addTargetPart(numberpart);
	        
	        numberpart.setJaxbElement( (Numbering) XmlUtils.unmarshalString(numbering) );
//	        wordMLPackage.getMainDocumentPart()
//	            .addStyledParagraphOfText("Title", "Hello Maven Central");
//	 
//	        wordMLPackage.getMainDocumentPart().addParagraphOfText("from docx4j!");
//	        
//	        
//	        
//	        wordMLPackage.getMainDocumentPart().addParagraphOfText("text asdff");
//	        
//	        

	        
	        
	        
	        
	        
	        
	        org.docx4j.wml.ObjectFactory obj = new org.docx4j.wml.ObjectFactory();
	         
	        // paragraph element / object
	        P para = obj.createP();
	         
	        // run object - number of runs may comprise a single paragraph element
	        R run = obj.createR();
	         
	        //run comprises of various elements such as text, format, tab etc...
	        // tab element
	        R.Tab tab = obj.createRTab();
	          // Text element
	          Text text = obj.createText();
	          
	          // set text value
	          text.setValue("testing tabs");
	         
	          // to add tab to a run
	          run.getContent().add(tab);
	          run.getContent().add(tab);
	          run.getContent().add(tab);
	         
	          // add text to run
	          run.getContent().add(text);
	          
	          // add break line
	          run.getContent().add(obj.createBr());
	          
	          //add new text
	          Text newText = obj.createText();
	          newText.setValue("asasdff");
	          run.getContent().add(newText);
	          
	          para.getContent().add(run);
	          
	          R run2 = obj.createR();
	          
	          Text newText2 = obj.createText();
	          newText2.setValue("test if a linebreak occurs by a new run line");
	          run2.getContent().add(newText2);
	          
	          para.getContent().add(run2);
	          
	          //add the paragraph object to the main document part
	          wordMLPackage.getMainDocumentPart().addObject(para);
	         
	          
	          text = obj.createText();
	          run = obj.createR();
	          para = obj.createP();
	          text.setValue("testing bold ");
	          // to preserve the space in an xml
	          text.setSpace("preserve");
	          
	          	          
	          org.docx4j.wml.RPr rpr = new org.docx4j.wml.RPr();
	          run.setRPr(rpr);
	          
	          rpr.setB(new org.docx4j.wml.BooleanDefaultTrue());
	          rpr.setI(new org.docx4j.wml.BooleanDefaultTrue());
	          org.docx4j.wml.Color color = new org.docx4j.wml.Color();
//	          color.setVal("0000FF");
	          rpr.setColor(color);
	          
	          org.docx4j.wml.U underline = obj.createU();
	          
	          //underline.setThemeColor(org.docx4j.wml.STThemeColor.);
	          
	          underline.setVal(org.docx4j.wml.UnderlineEnumeration.DOUBLE);
	          underline.setColor("0000FF");
	          
	          rpr.setU(underline);
	          
	          //rpr.setRFonts(value);
	          
	          run.setRPr(rpr);
	          
	          run.getContent().add(text);
	          para.getContent().add(run);
	          
	          
	          wordMLPackage.getMainDocumentPart().addObject(para);
	         
	          
	          
	          //adds a new line
	          wordMLPackage.getMainDocumentPart().addParagraphOfText("");
	          
	          //new paragraph
	          org.docx4j.wml.P para3 = obj.createP();
	          wordMLPackage.getMainDocumentPart().addObject(para3);
	          
	          org.docx4j.wml.R run3 = obj.createR();
	          para3.getContent().add(run3);
	          
	          org.docx4j.wml.PPr para3pr = obj.createPPr();
	          para3.setPPr(para3pr);
	          
	          
	          org.docx4j.wml.Tabs tabs = obj.createTabs();
	          para3pr.setTabs(tabs);
	          
	          
	          org.docx4j.wml.CTTabStop ctts = obj.createCTTabStop();	         
	          ctts.setPos(  new BigInteger("7800") );
	          ctts.setVal(org.docx4j.wml.STTabJc.RIGHT);
	          tabs.getTab().add(ctts);
	          
	          
	          
	          
//	          org.docx4j.wml.PPrBase.NumPr numpr = obj.createPPrBaseNumPr();
//	          para3pr.setNumPr(numpr);
//	          
//	          org.docx4j.wml.PPrBase.NumPr.Ilvl ilvl = obj.createPPrBaseNumPrIlvl();
//	          numpr.setIlvl(ilvl);
//	          ilvl.setVal(BigInteger.valueOf(0));
//	          
//	          org.docx4j.wml.PPrBase.NumPr.NumId num_id = obj.createPPrBaseNumPrNumId();
//	          numpr.setNumId(num_id);
//	          num_id.setVal(BigInteger.valueOf(2));
//	          
	       
	          
	          org.docx4j.wml.Text text3 = obj.createText();
	          
	          text3.setValue("create underline color blue");
	          run3.getContent().add(text3);
	          
	          //new numbered paragraph
	          
	          org.docx4j.wml.P para4 = createNumberedParagraph(1, 0, "test of numbering");
	          
	          wordMLPackage.getMainDocumentPart().addObject(para4);
	          
	          //adds a new line & the text in it
	          wordMLPackage.getMainDocumentPart().addParagraphOfText("newlines");

	          
	          
	       // Now save it
		      wordMLPackage.save(new java.io.File(System.getProperty("user.dir") + "/helloMavenCentral.docx") );
	 
	    }
	 
	 private static P createNumberedParagraph(long numId, long ilvl, String paragraphText ) {
			
		    org.docx4j.wml.ObjectFactory factory = new org.docx4j.wml.ObjectFactory();
			P  p = factory.createP();

			org.docx4j.wml.Text  t = factory.createText();
			t.setValue(paragraphText);

			org.docx4j.wml.R  run = factory.createR();
			run.getRunContent().add(t);		
			
			p.getParagraphContent().add(run);
							
		    org.docx4j.wml.PPr ppr = factory.createPPr();	    
		    p.setPPr( ppr );
		    
		    // Create and add <w:numPr>
		    NumPr numPr =  factory.createPPrBaseNumPr();
		    ppr.setNumPr(numPr);
		    
		    // The <w:ilvl> element
		    Ilvl ilvlElement = factory.createPPrBaseNumPrIlvl();
		    numPr.setIlvl(ilvlElement);
		    ilvlElement.setVal(BigInteger.valueOf(ilvl));
		    	    
		    // The <w:numId> element
		    NumId numIdElement = factory.createPPrBaseNumPrNumId();
		    numPr.setNumId(numIdElement);
		    numIdElement.setVal(BigInteger.valueOf(numId));
		    
			return p;
			
		}
	 
	 static final String numbering = "<w:numbering xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\" xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">"
			    + "<w:abstractNum w:abstractNumId=\"0\">"
			    + "<w:nsid w:val=\"2DD860C0\"/>"
			    + "<w:multiLevelType w:val=\"multilevel\"/>"
			    + "<w:tmpl w:val=\"0409001D\"/>"
			    + "<w:lvl w:ilvl=\"0\">"
			        + "<w:start w:val=\"1\"/>"
			        + "<w:numFmt w:val=\"decimal\"/>"
			        + "<w:lvlText w:val=\"%1)\"/>"
			        + "<w:lvlJc w:val=\"left\"/>"
			        + "<w:pPr>"
			            + "<w:ind w:left=\"360\" w:hanging=\"360\"/>"
			        + "</w:pPr>"
			    + "</w:lvl>"
			    + "<w:lvl w:ilvl=\"1\">"
			        + "<w:start w:val=\"1\"/>"
			        + "<w:numFmt w:val=\"lowerLetter\"/>"
			        + "<w:lvlText w:val=\"%2)\"/>"
			        + "<w:lvlJc w:val=\"left\"/>"
			        + "<w:pPr>"
			            + "<w:ind w:left=\"720\" w:hanging=\"360\"/>"
			        + "</w:pPr>"
			    + "</w:lvl>"
			    + "<w:lvl w:ilvl=\"2\">"
			        + "<w:start w:val=\"1\"/>"
			        + "<w:numFmt w:val=\"lowerRoman\"/>"
			        + "<w:lvlText w:val=\"%3)\"/>"
			        + "<w:lvlJc w:val=\"left\"/>"
			        + "<w:pPr>"
			            + "<w:ind w:left=\"1080\" w:hanging=\"360\"/>"
			        + "</w:pPr>"
			    + "</w:lvl>"
			    + "<w:lvl w:ilvl=\"3\">"
			        + "<w:start w:val=\"1\"/>"
			        + "<w:numFmt w:val=\"decimal\"/>"
			        + "<w:lvlText w:val=\"(%4)\"/>"
			        + "<w:lvlJc w:val=\"left\"/>"
			        + "<w:pPr>"
			            + "<w:ind w:left=\"1440\" w:hanging=\"360\"/>"
			        + "</w:pPr>"
			    + "</w:lvl>"
			    + "<w:lvl w:ilvl=\"4\">"
			        + "<w:start w:val=\"1\"/>"
			        + "<w:numFmt w:val=\"lowerLetter\"/>"
			        + "<w:lvlText w:val=\"(%5)\"/>"
			        + "<w:lvlJc w:val=\"left\"/>"
			        + "<w:pPr>"
			            + "<w:ind w:left=\"1800\" w:hanging=\"360\"/>"
			        + "</w:pPr>"
			    + "</w:lvl>"
			    + "<w:lvl w:ilvl=\"5\">"
			        + "<w:start w:val=\"1\"/>"
			        + "<w:numFmt w:val=\"lowerRoman\"/>"
			        + "<w:lvlText w:val=\"(%6)\"/>"
			        + "<w:lvlJc w:val=\"left\"/>"
			        + "<w:pPr>"
			            + "<w:ind w:left=\"2160\" w:hanging=\"360\"/>"
			        + "</w:pPr>"
			    + "</w:lvl>"
			    + "<w:lvl w:ilvl=\"6\">"
			        + "<w:start w:val=\"1\"/>"
			        + "<w:numFmt w:val=\"decimal\"/>"
			        + "<w:lvlText w:val=\"%7.\"/>"
			        + "<w:lvlJc w:val=\"left\"/>"
			        + "<w:pPr>"
			            + "<w:ind w:left=\"2520\" w:hanging=\"360\"/>"
			        + "</w:pPr>"
			    + "</w:lvl>"
			    + "<w:lvl w:ilvl=\"7\">"
			        + "<w:start w:val=\"1\"/>"
			        + "<w:numFmt w:val=\"lowerLetter\"/>"
			        + "<w:lvlText w:val=\"%8.\"/>"
			        + "<w:lvlJc w:val=\"left\"/>"
			        + "<w:pPr>"
			            + "<w:ind w:left=\"2880\" w:hanging=\"360\"/>"
			        + "</w:pPr>"
			    + "</w:lvl>"
			    + "<w:lvl w:ilvl=\"8\">"
			        + "<w:start w:val=\"1\"/>"
			        + "<w:numFmt w:val=\"lowerRoman\"/>"
			        + "<w:lvlText w:val=\"%9.\"/>"
			        + "<w:lvlJc w:val=\"left\"/>"
			        + "<w:pPr>"
			            + "<w:ind w:left=\"3240\" w:hanging=\"360\"/>"
			        + "</w:pPr>"
			    + "</w:lvl>"
			+ "</w:abstractNum>"
			+ "<w:num w:numId=\"1\">"
			    + "<w:abstractNumId w:val=\"0\"/>"
			 + "</w:num>"
			+ "</w:numbering>";
}
