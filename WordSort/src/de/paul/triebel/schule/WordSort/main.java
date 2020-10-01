package de.paul.triebel.schule.WordSort;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import assets.assets;
import de.paul.triebel.schule.WordSort.Data.Config;
import de.paul.triebel.schule.WordSort.Data.LanguageFile;
import de.paul.triebel.schule.WordSort.Gui.CustomFont;
import de.paul.triebel.schule.WordSort.Gui.Gui;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragObject;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragPanel;
import de.paul.triebel.schule.WordSort.Utils.MathUtils;

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
}
