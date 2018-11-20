package com.alchemi.health.object.gui.indicators;

import java.awt.Color;

import com.alchemi.health.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class DoctorWhoGUI extends AbstractIndicator {

	public DoctorWhoGUI(Minecraft mc, Entity ent) {
		this.name = "gui.doctor_who";
		this.mc = mc;
		this.ent = ent;
		
		health_height = 22;
		type_heigth = 18;
		type_texture_y = 45;
		
		this.TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/gui/doctor_who_indicator.png");
		
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
