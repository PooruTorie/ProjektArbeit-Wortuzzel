package assets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class assets {
	
	private static File folder = new File("data");

	public static File getFile(String string) {
		InputStream in = assets.class.getResourceAsStream(string);
		File file = new File(folder.getAbsolutePath()+"/"+string);
		if (!file.exists()) {
			if (!folder.exists()) {
				folder.mkdirs();
			}
			try {
				createParentFolder(file);
				file.createNewFile();
				FileOutputStream w = new FileOutputStream(file);
				for (int i = 0; i < in.available()*1000; i++) {
					int b = in.read();
					System.out.println(b);
					w.write(b);
				}
				in.close();
				w.close();
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
