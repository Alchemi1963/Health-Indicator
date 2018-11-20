package com.alchemi.health.object.gui.indicators;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class DefaultGUI extends AbstractIndicator {

	public DefaultGUI(Minecraft mc, Entity ent) {
		this.name = "gui.default";
		this.mc = mc;
		this.ent = ent;
		
		draw();
	}

	@Override
	void setColour(Color col) {
		colour = col;
		
	}

	@Override
	void setPosition(int x, int y) {
		
		pos_x = x;
		pos_y = y;
		
	}
}
