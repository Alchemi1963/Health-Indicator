package com.alchemi.health.object.gui;

import java.awt.Color;

import com.alchemi.health.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class TransparentIndicatorGUI extends IndicatorGUI {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/gui/transparent_indicator.png");
	
	private final Color colour = new Color(255, 255, 255, 191);
	
	public TransparentIndicatorGUI(Minecraft mc, Entity ent, String name) {

		super(mc, ent, name);
		super.setColour(colour);
		super.setTextures(TEXTURES);
		
	}
	
}
