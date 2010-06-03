package de.mxro.xml;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public class CopyContentHandler extends MyContentHandler {

	private final MyContentHandler destHandler;

	public CopyContentHandler(final MyContentHandler destHandler) {
		super();
		this.destHandler = destHandler;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		this.destHandler.characters(ch, start, length);
	}

	@Override
	public void comment(char[] ch, int start, int length) throws SAXException {
		
		this.destHandler.comment(ch, start, length);
	}

	@Override
	public void endCDATA() throws SAXException {
		
		this.destHandler.endCDATA();
	}

	@Override
	public void endDocument() throws SAXException {
		
		// destHandler.endDocument();
	}

	@Override
	public void endDTD() throws SAXException {
		
		// destHandler.endDTD();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		this.destHandler.endElement(uri, localName, qName);
	}

	@Override
	public void endElement(String localName) throws SAXException {
		
		this.destHandler.endElement(localName);
	}

	@Override
	public void endEntity(String name) throws SAXException {
		
		this.destHandler.endEntity(name);
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		
		this.destHandler.endPrefixMapping(prefix);
	}

	@Override
	public void startCDATA() throws SAXException {
		
		this.destHandler.startCDATA();
	}

	@Override
	public void startDocument() throws SAXException {
		
		// destHandler.startDocument();
	}

	@Override
	public void startDTD(String name, String publicId, String systemId) throws SAXException {
		
		// destHandler.startDTD(name, publicId, systemId);
	}

	@Override
	public void startElement(String localName, Attributes attributes) throws SAXException {
		
		this.destHandler.startElement(localName, attributes);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		
		this.destHandler.startElement(uri, localName, qName, atts);
	}

	@Override
	public void startElement(String localName) throws SAXException {
		
		this.destHandler.startElement(localName);
	}

	@Override
	public void startEntity(String name) throws SAXException {
		
		this.destHandler.startEntity(name);
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		
		this.destHandler.startPrefixMapping(prefix, uri);
	}

	@Override
	public void write(String text) throws SAXException {
		
		this.destHandler.write(text);
	}

	@Override
	public void writeCDATA(String text) throws SAXException {
		
		this.destHandler.writeCDATA(text);
	}

	@Override
	public void writeComment(String text) throws SAXException {
		
		this.destHandler.writeComment(text);
	}

	@Override
	public ContentHandler getContentHandler() {
		
		return this.destHandler.getContentHandler();
	}

	@Override
	public LexicalHandler getLexicalHandler() {
		
		return this.destHandler.getLexicalHandler();
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		
		this.destHandler.ignorableWhitespace(ch, start, length);
	}

	@Override
	public void objectData(Object object) throws SAXException {
		
		this.destHandler.objectData(object);
	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		
		// destHandler.processingInstruction(target, data);
	}

	@Override
	public void setContentHandler(ContentHandler contentHandler) {
		
		this.destHandler.setContentHandler(contentHandler);
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		
		this.destHandler.setDocumentLocator(locator);
	}

	@Override
	public void setLexicalHandler(LexicalHandler lexicalHandler) {
		
		this.destHandler.setLexicalHandler(lexicalHandler);
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		
		this.destHandler.skippedEntity(name);
	}

	@Override
	public String toString() {
		
		return this.destHandler.toString();
	}
	
	
	

}
