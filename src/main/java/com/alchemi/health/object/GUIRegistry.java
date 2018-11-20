package com.alchemi.health.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alchemi.health.object.gui.indicators.AbstractIndicator;

public class GUIRegistry {

	public GUIRegistry() {}
	
	private static ArrayList<Class<? extends AbstractIndicator>> registry = new ArrayList<Class<? extends AbstractIndicator>>();
	
	private static Map<String, Integer> regMap = new HashMap<String, Integer>();
	
	public void register(Class<? extends AbstractIndicator> gui, String name) {
		registry.add(gui);
		regMap.put(name, registry.size() - 1);
	}
	
	public Class<? extends AbstractIndicator> get(int id) {
		if (registry.size() >= id) return null;
		return registry.get(id);
	}
	
	public Class<? extends AbstractIndicator> getRandom() {
		if (registry.size() == 0) return null;
		Random rand = new Random();
		int max = registry.size() - 1;
		return registry.get(rand.nextInt(max));
	}
	
	public Class<? extends AbstractIndicator> getByName(String name) {
		if (!regMap.containsKey(name)) return null;
		int id = regMap.get(name);
		return registry.get(id);
	}
	
	public String printer(){
		String s = "[";
		for (Class<? extends AbstractIndicator> c : registry) {
			s += c.getSimpleName() + ", ";
		}
		s = s.substring(0, s.length() - 2);
		s += "]";
		return s;
	}
	
	public String[] getAllNames() {
		return regMap.keySet().toArray(new String[regMap.size()]);
	}
	
}
