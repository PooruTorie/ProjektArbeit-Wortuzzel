package de.paul.triebel.schule.WordSort.assets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

public class assets {
	
	private static File folder = new File(System.getenv("APPDATA")+"/Wortuzzel");

	public static File getFile(String string) {
		File file = new File(folder.getAbsolutePath()+"/"+string);
		if (!file.exists()) {
			InputStream i = assets.class.getResourceAsStream(string);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			try {
				createParentFolder(file);
				file.createNewFile();
				
				FileOutputStream w = new FileOutputStream(file);
				BufferedInputStream in = new BufferedInputStream(i);
				while (in.available() > 0) {
					w.write(in.read());
				}
				in.close();
				w.close();
				i.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	private static void createParentFolder(File file) {
		File parent = file.getParentFile();
		
		if (parent != null) {
			if (!parent.exists()) {
				parent.mkdirs();
			}
		}
	}
	
}
