package com.alchemi.health.object.gui;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.alchemi.health.Config;
import com.alchemi.health.Health;
import com.alchemi.health.Reference;
import com.alchemi.health.object.gui.indicators.AbstractIndicator;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;

public class GUIPosition extends GuiScreen {
	
	private GuiButton buttonClose;
	private GuiButtonTransparent buttonInd;
	
	private AbstractIndicator instance;
	
	
	@Override
	public void initGui() {
		
		this.buttonList.add(buttonClose = new GuiButton(0, this.width / 2 - 50, this.height - (this.height / 4) + 10, 100, 20, "DONE"));
		this.buttonList.add(buttonInd = new GuiButtonTransparent(1, Config.pos.x, Config.pos.y, 185, 74));
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		
		try {
			instance = (AbstractIndicator) Health.gui_reg.getByName(Config.indicator).getDeclaredConstructors()[0].newInstance(mc, mc.player);
		
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			e.printStackTrace();
		}
		
		buttonInd.width = instance.getWidth() + 5;
		buttonInd.height = instance.getHeight() + 5;
		
		Config.pos.x = buttonInd.x;
		Config.pos.y = buttonInd.y;
		
		drawCenteredString(mc.fontRenderer, "gui.position", this.width/2, 10, Color.WHITE.getRGB());
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {

		if (button == buttonClose) {
			ConfigManager.sync(Reference.MOD_ID, Type.INSTANCE);
			mc.displayGuiScreen(new GUIOptions());
		} else if (button == buttonInd) {
			
		}
	
	}
	
	@Override
	public boolean doesGuiPauseGame() {return true;}
	

}
