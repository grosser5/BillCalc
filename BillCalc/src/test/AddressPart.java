package test;

import java.util.Map;

import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.Text;

public class AddressPart implements DocumentPartInterface {
	Map<String, String> parts;
		
	AddressPart() {}

	@Override
	public void add(String key, String value) {
		parts.put(key, value);
	}

	@Override
	public R get(String key) {
		org.docx4j.wml.ObjectFactory obj = new org.docx4j.wml.ObjectFactory();
		R run = obj.createR();
		
		RPr rpr = obj.createRPr();
		run.setRPr(rpr);
		
		RFonts font = obj.createRFonts();
		font.setAscii("Courier");
		rpr.setRFonts(font);
		
		Text text = obj.createText();
		run.getContent().add(text);
		
		if(parts.containsKey(key))
			text.setValue(parts.get(key));
		else
			text.setValue("");
				
		return run;
	}
		
	

}
