package com.alchemi.health.object.gui;

import java.awt.Color;
import java.io.IOException;

import com.alchemi.health.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;

public class GUIOptions extends GuiScreen{

	private GuiButton buttonClose;
	private GuiLabel title;
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.add(buttonClose = new GuiButton(0, this.width / 2 - 100, this.height - (this.height / 4) + 10, "Close"));
		this.labelList.add(title = new GuiLabel(mc.fontRenderer, 1, this.width / 2 - 20, this.height / 2 + 40, 300, 20, Color.WHITE.getRGB()));
		
		title.addLine("Health Indicator Options");
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// TODO Auto-generated method stub
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		// TODO Auto-generated method stub
		super.actionPerformed(button);
	}
	
	
	
}
