package de.paul.triebel.schule.WordSort;

import java.io.IOException;

import assets.assets;
import de.paul.triebel.schule.WordSort.Data.Config;
import de.paul.triebel.schule.WordSort.Gui.Gui;

public class main {
	
	private static Config config;
	private static Gui gui;

	public static void main(String[] args) {
		try {
			config = new Config(assets.getFile("config.ini"));
			gui = new Gui();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
