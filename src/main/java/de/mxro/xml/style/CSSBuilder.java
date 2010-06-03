package de.mxro.xml.style;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public abstract class CSSBuilder {
	public abstract void generateCSS(OutputStream os, de.mxro.filesystem.Folder files);
	public String generateCSS(de.mxro.filesystem.Folder files) {
		final ByteArrayOutputStream bs = new ByteArrayOutputStream();
		this.generateCSS(bs, files);
		return bs.toString();
	}
}
