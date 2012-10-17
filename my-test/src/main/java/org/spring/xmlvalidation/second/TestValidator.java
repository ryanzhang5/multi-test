package org.spring.xmlvalidation.second;

import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

public class TestValidator {

	public static void main(String[] args) throws Exception {
	/*	Validator validator = SchemaFactory
				.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI)
				.newSchema(new File("src/main/java/org/spring/xmlvalidation/third/Artist.xsd")).newValidator();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setValidating(false);
		Document xml = dbf.newDocumentBuilder().parse(
				new InputSource(new FileReader("src/main/java/org/spring/xmlvalidation/third/Artist.xml")));
		validator.validate(new DOMSource(xml));
		
		*/
		
		
		
		
		URL resource = TestValidator.class.getResource( "Artist.xsd" );  
		SchemaFactory factory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );  
		Schema schema = factory.newSchema( resource ); 
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();  
		builderFactory.setNamespaceAware( true );  
		// TODO: Workaround for BEA WebLogic  
		builderFactory.setSchema( schema );  
		builderFactory.setIgnoringElementContentWhitespace( true );  
		builderFactory.setIgnoringComments( true );  
		builderFactory.setValidating( false );
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();  
		ErrorHandler eh = new MyErrorHandler();  
		documentBuilder.setErrorHandler( eh );  
		InputSource is = new InputSource(  TestValidator.class.getResourceAsStream("Artist.xml") );  
		//is.setPublicId( NAMESPACE_URI );  
		//is.setSystemId( NAMESPACE_URI );  
		Document document = documentBuilder.parse( is ); 
		
		
		
		
	}

}
