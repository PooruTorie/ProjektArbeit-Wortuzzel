package assets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.BufferUnderflowException;
import java.nio.file.Path;
import java.util.jar.JarFile;

public class assets {
	
	private static File folder = new File("data");

	public static File getFile(String string) {
		File file = new File(folder.getAbsolutePath()+"/"+string);
		if (!file.exists()) {
			InputStream in = assets.class.getResourceAsStream(string);
			byte[] data = new byte[16384];
			if (!folder.exists()) {
				folder.mkdirs();
			}
			try {
				createParentFolder(file);
				file.createNewFile();
				FileOutputStream w = new FileOutputStream(file);
				int size = in.read(data);
				System.out.println(size);
				for (int i = 0; i < size; i++) {
					int b = data[i];
					System.out.println(Integer.toString(b, 16));
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
