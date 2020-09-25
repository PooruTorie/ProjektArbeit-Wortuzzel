package de.paul.triebel.schule.WordSort.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Config {
	
	private JSONObject c;
	private File f;

	public Config(File f) throws IOException {
		c = readJSON(f);
	}
	
	public Config(JSONObject o) {
		c = o;
	}
	
	public void print(String name) {
		System.out.println(name+": ");
		System.out.println(c.toJSONString());
	}
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(c);
	}
	
	public String toJSONString() {
		return c.toJSONString();
	}
	
	public void set(String st, Object o) {
		c.put(st.toLowerCase(), o);
		save();
	}
	
	public Config(String string) {
		try {
			JSONParser p = new JSONParser();
			c = (JSONObject) p.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public boolean contains(String s) {
		return c.containsKey(s);
	}
	
	public void save() {
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(f));
			w.write(toString());
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return f;
	}
	
	public Object get(String st) {
		return c.get(st.toLowerCase());
	}

	private JSONObject readJSON(File f) throws IOException {
		try {
			FileReader r = new FileReader(f);
			JSONParser p = new JSONParser();
			JSONObject o = (JSONObject) p.parse(r);
			this.f = f;
			return o;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject toJSON() {
		return c;
	}

}
