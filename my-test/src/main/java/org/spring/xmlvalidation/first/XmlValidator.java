package org.spring.xmlvalidation.first;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlValidator {
	private static final String SCHEMA_LANGUAGE_ATTRIBUTE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	private static final String XSD_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";
	

	public void doValidator() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE);
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		docBuilder.setEntityResolver(new EntityResolver() {

			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {

				try {
					InputSource source = new InputSource(this.getClass()
							.getResourceAsStream("ryan-spring-beans-3.0.xsd"));
					
					System.out.println(publicId + "         "+systemId);
					source.setPublicId(publicId);
					source.setSystemId(systemId);
					return source;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return null;
			}

		});
		docBuilder.setErrorHandler(new ErrorHandler() {

			public void warning(SAXParseException exception)
					throws SAXException {
				exception.printStackTrace();

			}

			public void error(SAXParseException exception) throws SAXException {
				exception.printStackTrace();
			}

			public void fatalError(SAXParseException exception)
					throws SAXException {
				exception.printStackTrace();

			}

		});

		docBuilder.parse(new InputSource(this.getClass().getResourceAsStream(
				"context2.xml")));

	}

	public static void main(String args[]) {
		XmlValidator va = new XmlValidator();

		try {
			va.doValidator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
