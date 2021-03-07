package de.paul.triebel.schule.WordSort;

import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONArray;

import de.paul.triebel.schule.WordSort.Data.Config;
import de.paul.triebel.schule.WordSort.Data.LanguageFile;
import de.paul.triebel.schule.WordSort.Gui.CustomFont;
import de.paul.triebel.schule.WordSort.Gui.Gui;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragObject;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragPanel;
import de.paul.triebel.schule.WordSort.assets.assets;

public class main {
	
	private static final String VERSION = "1.2";
	
	private static Config config;
	private static Gui gui;
	private static LanguageFile lang;
	private static float FontSize = 50;

	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		
		try {
			config = new Config(assets.getFile("config.ini"));
			lang = new LanguageFile(assets.getFile("lang/"+config.get("lang")));
			gui = new Gui();
			
			testUpdate();
			
			testOpenFile(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testUpdate() {
		try {
			URL u = new URL("https://nutellajunge.github.io/ProjektArbeit-Wortuzzel/");
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			Config json = new Config(in.readLine());
			String version = (String) json.get("version");
			int newestVersion = Integer.parseInt(version.replace(".", ""));
			int currentVersion = Integer.parseInt(VERSION.replace(".", ""));
			if (newestVersion > currentVersion) {
				JPanel panel = new JPanel();
				panel.add(new JLabel("<html>"+main.getLanguageFile().get("versionnewer")+"<br>Link: "));
				panel.add(new JLink(version+" Update", new URI((String) json.get("link"))));
				int status = JOptionPane.showConfirmDialog(main.getGui(), panel, (String) main.getLanguageFile().get("versionnewer"), JOptionPane.ERROR_MESSAGE);
				if (status == JOptionPane.OK_OPTION) {
					Desktop.getDesktop().browse(new URI((String) json.get("link")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testOpenFile(String[] args) {
		if (args.length != 0) {
			File f = new File(args[0]);
			System.out.println(f);
			if (f.exists()) {
				gui.dragPanel.openFromFile(f);
			}
		}
	}

	public static LanguageFile getLanguageFile() {
		return lang;
	}
	
	public static Gui getGui() {
		return gui;
	}
	
	public static Config getConfig() {
		return config;
	}
	
	public static float getFontSize() {
		return FontSize;
	}
	
	public static void setFontSize(float fontSize) {
		FontSize = fontSize;
		
		DragObject.updateFont();
		DragPanel.updateFont();
	}

	public static Font getDragObjectFont() {
		return CustomFont.get(FontSize);
	}

	public static LanguageFile[] getAllLanguageFiles() {
		JSONArray f = (JSONArray) config.get("lang_files");
		LanguageFile[] list = new LanguageFile[f.size()];
		for (int i = 0; i < list.length; i++) {
			try {
				list[i] = new LanguageFile(assets.getFile("lang/"+f.get(i)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static void updateLanguage(LanguageFile lang) {
		config.set("lang", lang.getFile().getName());
		
		main.lang = lang;
		
		DragPanel dragPanel = gui.close();
		gui = new Gui(dragPanel);
	}
}
