package de.paul.triebel.schule.WordSort.Gui;

import java.awt.Font;
import java.io.File;

import de.paul.triebel.schule.WordSort.assets.Assets;

public class CustomFont {
	
	public static Font get(float size) {
		return new Font("Arial", Font.PLAIN, (int) size);
	}
}
