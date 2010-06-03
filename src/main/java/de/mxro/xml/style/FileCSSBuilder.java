package de.mxro.xml.style;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

import de.mxro.filesystem.File;
import de.mxro.filesystem.Folder;
import de.mxro.filesystem.v01.IncludedFile;
import de.mxro.filesystem.v01.IncludedRootFolder;
import de.mxro.utils.log.UserError;

public class FileCSSBuilder extends CSSBuilder {

	protected File file;
	
	public static FileCSSBuilder fromInputStream(InputStream is) {
		final FileCSSBuilder res = new FileCSSBuilder();
		res.file.fromStream(is);
		return res;
	}
	
	public static FileCSSBuilder fromFile(java.io.File file) {
		final FileCSSBuilder res = new FileCSSBuilder();
		try {
			res.file.fromStream(new FileInputStream(file));
		} catch (final FileNotFoundException e) {
			de.mxro.utils.log.UserError.singelton.log(e);
		}
		return res;
	}
	
	public FileCSSBuilder() {
		try {
			this.file = new IncludedFile("file.css", IncludedRootFolder.createInstance());
		} catch (URISyntaxException e) {
			UserError.singelton.log(e);
		}
	}
	
	@Override
	public void generateCSS(OutputStream os, Folder files) {
		
		try {
			final InputStream fs = this.file.getInputStream();
			int buffer;
			while ((buffer = fs.read()) != -1) {
				os.write(buffer);
			}
		} catch (final FileNotFoundException e) {
			de.mxro.utils.log.UserError.singelton.showError("Document Style could not be created! Error reading external css ");
		} catch (final IOException e) {
			de.mxro.utils.log.UserError.singelton.showError("Document Style could not be created! Error reading external css ");
		}
	}

}
