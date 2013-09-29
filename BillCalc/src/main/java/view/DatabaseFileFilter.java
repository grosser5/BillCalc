package main.java.view;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import main.view.util.Utils;





public class DatabaseFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		
		if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.sqlite3))
                    return true;
        }

		return false;
	}

	@Override
	public String getDescription() {
		return "sqlite3";
	}

}
