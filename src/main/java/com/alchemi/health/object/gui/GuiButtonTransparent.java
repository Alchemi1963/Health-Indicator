package com.alchemi.health.object.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonTransparent extends GuiButton {

	private boolean stuck;
	
	public GuiButtonTransparent(int buttonId, int x, int y, int widthIn, int heightIn) {
		super(buttonId, x, y, widthIn, heightIn, "");
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		
		if ( stuck ) {
			this.x = mouseX;
			this.y = mouseY;
		}
		
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		stuck = true;
		return super.mousePressed(mc, mouseX, mouseY);
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		stuck = false;
	}
	
}
