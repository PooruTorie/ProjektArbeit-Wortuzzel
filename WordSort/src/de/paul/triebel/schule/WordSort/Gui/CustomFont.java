package de.paul.triebel.schule.WordSort.Gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import assets.assets;

public class CustomFont {

	public static Font get() {
		try {
			return Font.createFont(Font.BOLD, assets.getFile("font.ttf")).deriveFont(Font.BOLD, 12f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
