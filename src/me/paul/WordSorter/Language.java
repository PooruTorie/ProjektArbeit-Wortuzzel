package me.paul.WordSorter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.craftbukkit.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Language {
	
	JSONObject o;
	
	public Language(Config config) {
		o = read(new File("Data/Lang/"+config.getLanguage()));
	}
	
	public Language(String name) {
		o = read(new File("Data/Lang/"+name));
	}
	
	public String getName() {
		return (String) o.get("NAME");
	}
	
	public String get(String s) {
		return (String) o.get(s.toUpperCase());
	}
	
	private JSONObject read(File config) {
		JSONParser p = new JSONParser();
		try {
			FileReader in = new FileReader(config);
			JSONObject out = (JSONObject) p.parse(in);
			return out;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void load(String lang) {
		o = read(new File("Data/Lang/"+lang));
		main.Config.setLanguage(lang);
		main.g.close();
		main.main(null);
	}

}
