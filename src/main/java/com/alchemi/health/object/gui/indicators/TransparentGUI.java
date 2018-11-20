package com.alchemi.health.object.gui.indicators;

import java.awt.Color;

import com.alchemi.health.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class TransparentGUI extends AbstractIndicator {
	
	public TransparentGUI(Minecraft mc, Entity ent) {
		
		this.mc = mc;
		this.ent = ent;
		this.name = "gui.transparent";
		
		TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/gui/transparent_indicator.png");
		
		health_texture_x = 47;
		health_texture_y = 25;
		health_overlay_texture_y = 63;
		health_width = 120;
		health_height = 12;
		width = 174;
		height = 63;
		type_texture_x = 14;
		type_texture_y = 43;
		name_x = 107;
		name_y = 4;
		entity_x = 22;
		entity_y = 7;
		colour = new Color(255, 255, 255, 192);
		
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
