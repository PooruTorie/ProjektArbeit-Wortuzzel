package me.paul.WordSorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Config {
	
	private File f;
	private JSONObject o;
	private String Language;
	private int FontSize;
	
	public Config(File config) {
		f = config;
		o = read(config);
		Language = (String) o.get("Language");
		FontSize = ((Long) o.get("FontSize")).intValue();
	}
	
	public String getLanguage() {
		return Language;
	}
	
	public void setLanguage(String language) {
		Language = language;
		o.put("Language", Language);
		save();
	}
	
	public int getFontSize() {
		return FontSize;
	}
	
	public void setFontSize(int fontSize) {
		FontSize = fontSize;
		o.put("FontSize", fontSize);
		save();
	}
	
	private void save() {
		try {
			Files.write(f.toPath(), o.toJSONString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
}
