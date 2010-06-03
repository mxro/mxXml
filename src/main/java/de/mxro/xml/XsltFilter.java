package de.mxro.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Node;
import org.w3c.tidy.Tidy;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;


import de.mxro.string.filter.Filter;

/**
 * Wendet  eine xslt-Transformation auf den uebergebenen String an.
 * Der String sollte daher moeglichst ein xml-Dokument beinhalten oder zumindest eingermassen "ordentliches" HTML.
 * Zum Parsen wird JTidy verwendet.
 * @author mer
 *
 */

public class XsltFilter extends Filter {
	
	private static void removeDocumentType(org.w3c.dom.Document doc) {
		
		if (doc.getChildNodes().getLength() > 0) {
			for (int i = doc.getChildNodes().getLength()-1; i >=0; i--) {
				final Node node = doc.getChildNodes().item(i);
				//System.out.println(Node.DOCUMENT_TYPE_NODE);
				
				if (node.getNodeType()==Node.DOCUMENT_TYPE_NODE) {
					doc.removeChild(node);
					//System.out.println("removed");
				}
			}
		}
	}
	
	private String domToString(org.w3c.dom.Document doc) throws UnsupportedEncodingException {
		final XMLSerializer serializer = new XMLSerializer();
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		doc.normalize();
		removeDocumentType(doc);
		serializer.setOutputByteStream(bos);
		
        try {
			serializer.serialize(doc);
		} catch (final IOException e) {
			return "";
		}
		//System.out.println(bos.toString("utf-8").toLowerCase());
		return bos.toString("utf-8");
	}
	
	private final String stylesheet;
	
	private transient Transformer trans=null;
	//private static final Filter resolveHTMLEntities = Filter.resolveHTMLEntities(Filter.identity);
	//private static final Filter createHTMLEntities = Filter.createHTMLEntities(Filter.identity);
	
	@Override
	public String perform(String s) {
		s = s.replaceAll("<br>", "<br/>"); // tidy is too d*** to convert this correctly ...
		//s = createHTMLEntities.perform(s);
		
		final Tidy tidy = new Tidy();
		tidy.setMakeClean(false);
		//tidy.setForceOutput(true);
		tidy.setOnlyErrors(true);
		tidy.setQuiet(true);
		//tidy.setXmlTags(true); 
		//tidy.setXmlOut(true);
		//tidy.setXHTML(true);
		tidy.setInputEncoding("utf-8");
		//tidy.setOutputEncoding(System.getProperty("file.encoding"));
		try {
			final ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes("utf-8"));
			//ByteArrayOutputStream bos = new ByteArrayOutputStream();
			final org.w3c.dom.Document doc = tidy.parseDOM(is, null);
			
			//System.out.println(domToString(doc));
		
			final Source xmlSource = new StreamSource(new ByteArrayInputStream(this.domToString(doc).getBytes("utf-8")));
			
			if (this.trans == null) {
				final TransformerFactory transFact = TransformerFactory.newInstance();
				final Source xsltSource = new StreamSource(new ByteArrayInputStream(this.stylesheet.getBytes()));
				this.trans = transFact.newTransformer(xsltSource);
			}
			final ByteArrayOutputStream resData = new ByteArrayOutputStream();
			this.trans.transform(xmlSource, new StreamResult(resData));
			
			return this.getNext().perform(resData.toString("utf-8"));
		
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (final TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (final TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (final TransformerException e) {
			e.printStackTrace();
		}
		
		return this.getNext().perform(s);
	}

	public XsltFilter(final String stylesheet, final Filter next) {
		super(next);
		this.stylesheet ="<?xml version='1.0' encoding='UTF-8'?>"+
		"<xsl:stylesheet version='1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>"+
		//"<xsl:template match='*'>" +
		stylesheet +
		//"</xsl:template>"+
		"</xsl:stylesheet>\n";
		 
	}

	
}
