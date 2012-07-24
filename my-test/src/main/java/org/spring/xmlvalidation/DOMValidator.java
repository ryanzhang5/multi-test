package org.spring.xmlvalidation;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DOMValidator {
	public static void main(String args[]) {
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new MyErrorHandler());
			 Document doc =builder.parse(new InputSource(DOMValidator.class.getResourceAsStream(
					"order.xml")));
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
