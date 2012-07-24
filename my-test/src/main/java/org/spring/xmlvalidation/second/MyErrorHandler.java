package org.spring.xmlvalidation.second;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {
	public void warning(SAXParseException e) throws SAXException {
		System.err.println("Warning");
		System.err.println(e);
		throw e;
	}

	public void error(SAXParseException e) throws SAXException {
		System.err.println("Error");
		System.err.println(e);
		throw e;
	}

	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("Fatal Error");
		System.err.println(e);
		throw e;
	}

}
