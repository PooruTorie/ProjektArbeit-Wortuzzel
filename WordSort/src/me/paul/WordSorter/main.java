package me.paul.WordSorter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import me.paul.WordSorter.gui.Gui;
import me.paul.api.DragDrop.JElements.JMoveComponent;

public class main {
	
	public static HashMap<JMoveComponent, ArrayList<JMoveComponent>> words = new HashMap<JMoveComponent, ArrayList<JMoveComponent>>();
	public static File config = new File("Data/config.data");
	public static Config Config;
	public static Language Language;
	public static Gui g;

	public static void main(String[] args) {
		Config = new Config(config);
		Language = new Language(Config);
		g = new Gui().StartGui();
	}

}
