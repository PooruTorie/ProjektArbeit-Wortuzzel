package assets;

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

	public static ArrayList<File> getFolder(String string) {
		ArrayList<File> files = new ArrayList<>();
		try {
			URI uri = assets.class.getResource(string).toURI();
			Path myPath;
	        if (uri.getScheme().equals("jar")) {
	            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
	            myPath = fileSystem.getPath("/resources");
	        } else {
	            myPath = Paths.get(uri);
	        }
	        Stream<Path> walk = Files.walk(myPath, 1);
	        for (Iterator<Path> it = walk.iterator(); it.hasNext();) {
	        	File f = it.next().toFile();
	        	if (!f.getName().equals(string)) {
	        		files.add(f);
	        	}
	        }
	        walk.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return files;
	}
	
}
