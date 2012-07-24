package org.spring.xmlvalidation.third;

import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class TestValidator {

	public static void main(String[] args) throws Exception {
		Validator validator = SchemaFactory
				.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI)
				.newSchema(new File("src/main/java/org/spring/xmlvalidation/third/Artist.xsd")).newValidator();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setValidating(false);
		Document xml = dbf.newDocumentBuilder().parse(
				new InputSource(new FileReader("src/main/java/org/spring/xmlvalidation/third/Artist.xml")));
		validator.validate(new DOMSource(xml));
	}

}
