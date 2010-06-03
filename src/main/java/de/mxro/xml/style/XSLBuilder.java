package de.mxro.xml.style;

import org.xml.sax.SAXException;

import de.mxro.xml.MyContentHandler;

public abstract class XSLBuilder {
	public abstract boolean writeXSL(MyContentHandler hd, de.mxro.filesystem.Folder files, String path) throws SAXException;
}
