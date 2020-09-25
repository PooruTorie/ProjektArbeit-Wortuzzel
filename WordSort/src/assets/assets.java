package assets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class assets {
	
	private static File folder = new File("data");

	public static File getFile(String string) {
		File file = new File(folder.getAbsolutePath()+"/"+string);
		if (!file.exists()) {
			InputStream in = assets.class.getResourceAsStream(string);
			byte[] data = new byte[54576];
			if (!folder.exists()) {
				folder.mkdirs();
			}
			try {
				createParentFolder(file);
				file.createNewFile();
				
				
				FileOutputStream w = new FileOutputStream(file);
				int size = in.read(data);
				for (int i = 0; i < size; i++) {
					int b = data[i];
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
