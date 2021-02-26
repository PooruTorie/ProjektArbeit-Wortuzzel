package de.paul.triebel.schule.WordSort.Gui;

import java.awt.Font;

import de.paul.triebel.schule.WordSort.assets.assets;

public class CustomFont {

	public static Font get(float size) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, assets.getFile("font.ttf")).deriveFont(0, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
