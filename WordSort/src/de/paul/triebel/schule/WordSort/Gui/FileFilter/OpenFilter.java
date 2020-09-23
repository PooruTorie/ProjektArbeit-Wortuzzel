package de.paul.triebel.schule.WordSort.Gui.FileFilter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import de.paul.triebel.schule.WordSort.main;

public class OpenFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
            return true;
        }
		
		String[] nameTiles = f.getName().replace('.', '#').split("#");
        String extension = nameTiles[nameTiles.length-1];
        if (extension != null) {
        	if (extension.equals("txt") || extension.equals("wuzz")) {
				return true;
			}
        }
        
		return false;
	}

	@Override
	public String getDescription() {
		return (String) main.getLanguageFile().get("filter");
	}

}
