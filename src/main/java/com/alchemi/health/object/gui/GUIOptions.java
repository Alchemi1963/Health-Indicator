package com.alchemi.health.object.gui;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GUIOptions extends GuiScreen{

	private GuiButton buttonClose;
	private GuiButton buttonStyle;
	private GuiButton buttonColour;
	private GuiButton buttonPosition;
	
	@Override
	public boolean doesGuiPauseGame() {return true;}
	
	@Override
	public void initGui() {
		
		this.mc = Minecraft.getMinecraft();
		this.buttonList.add(buttonClose = new GuiButton(0, this.width / 2 - 50, this.height - (this.height / 4) + 10, 100, 20, "DONE"));
		this.buttonList.add(buttonStyle = new GuiButton(1, this.width / 2 - 100, (this.height * 2/7) + 10, "Change style"));
		this.buttonList.add(buttonColour = new GuiButton(2, this.width / 2 - 100, (this.height * 3/7) + 10, "Change colour"));
		this.buttonList.add(buttonPosition = new GuiButton(3, this.width / 2 - 100, (this.height *4/7) + 10, "Change position"));
	}
	
	@Override
	public void updateScreen() {}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.mc = Minecraft.getMinecraft();
		drawDefaultBackground();
		drawCenteredString(mc.fontRenderer, "gui.options", this.width/2, 10, Color.WHITE.getRGB());
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button == buttonClose) {
			mc.player.closeScreen();
		} else if (button == buttonStyle) {
			mc.displayGuiScreen(new GUIStyle());
		} else if (button == buttonColour) {
			mc.displayGuiScreen(new GUIColour());
		} else if (button == buttonPosition) {
			mc.displayGuiScreen(new GUIPosition());
		}
	}
	
	
	
}
