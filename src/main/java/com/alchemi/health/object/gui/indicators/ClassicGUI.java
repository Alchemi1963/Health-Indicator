package com.alchemi.health.object.gui.indicators;

import java.awt.Color;

import com.alchemi.health.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ClassicGUI extends AbstractIndicator{

	public ClassicGUI(Minecraft mc, Entity ent) {
		this.name = "gui.classic";
		this.mc = mc;
		this.ent = ent;
		
		health_texture_x = 82;
		health_texture_y = 22;
		health_overlay_texture_x = 0;
		health_overlay_texture_y = 87;
		health_width = 156;
		health_height = 26;
		
		width = 237;
		height = 87;
		
		type_width = 18;
		type_heigth = 26;
		type_texture_x = 5;
		type_texture_y = 59;

		name_width = 141;
		name_height = 15;
		name_x = 160;
		name_y = 4;
		
		entity_width = 45;
		entity_height = 45;
		entity_x = 46;
		entity_y = 22;
		
		this.TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/gui/classic_indicator.png");
		
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
