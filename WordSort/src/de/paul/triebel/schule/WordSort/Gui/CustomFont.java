package de.paul.triebel.schule.WordSort.Gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import assets.assets;

public class CustomFont {

	public static Font get() {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, assets.getFile("font.ttf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
