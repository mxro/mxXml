package de.mxro.xml.style;

import org.xml.sax.SAXException;

import de.mxro.filesystem.Folder;
import de.mxro.xml.MyContentHandler;

public class EmptyXSLBuilder extends XSLBuilder {

	@Override
	public boolean writeXSL(MyContentHandler hd, Folder files, String path)
			throws SAXException {
		
		return true;
	}

}
