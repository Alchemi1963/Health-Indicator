package com.alchemi.health;

import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeInt;

@net.minecraftforge.common.config.Config(modid = Reference.MOD_ID, category = "general")
public class Config {

	@Comment({"The indicator to be used.",
		"Default: gui.default"})
	public static String indicator = "gui.default";
	
	@Comment({"Position of the indicator (top left).",
		"Default: x = 10, y = 10"})
	public static Position pos = new Position();
	
	public static class Position {
		public int x = 10;
		public int y = 10;
	}
	
	@Comment({"Colour of the text used.",
		"r is the red value, g is the green value, b is the blue value and a is the transparency.",
		"Default: r = 255, g = 255, b = 255, a = 255"})
	public static Colour col = new Colour();
	
	public static class Colour {
		@RangeInt(min = 0, max = 255)
		public int r = 255;
		@RangeInt(min = 0, max = 255)
		public int g = 255;
		@RangeInt(min = 0, max = 255)
		public int b = 255;
		@RangeInt(min = 0, max = 255)
		public int a = 255;
	}
			
}
