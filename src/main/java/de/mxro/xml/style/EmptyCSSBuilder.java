package de.mxro.xml.style;

import java.io.OutputStream;

import de.mxro.filesystem.Folder;

public class EmptyCSSBuilder extends CSSBuilder {
	
	public static final EmptyCSSBuilder SINGELTON = new EmptyCSSBuilder();
	
	@Override
	public void generateCSS(OutputStream os, Folder files) {
		// TODO Auto-generated method stub
		
	}

	
}
