package de.paul.triebel.schule.WordSort;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;

import de.paul.triebel.schule.WordSort.Data.Config;
import de.paul.triebel.schule.WordSort.Data.LanguageFile;
import de.paul.triebel.schule.WordSort.Gui.CustomFont;
import de.paul.triebel.schule.WordSort.Gui.Gui;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragObject;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragPanel;
import de.paul.triebel.schule.WordSort.assets.assets;

public class main {
	
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
			
			testOpenFile(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testOpenFile(String[] args) {
		if (args.length != 0) {
			File f = new File(args[0]);
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
