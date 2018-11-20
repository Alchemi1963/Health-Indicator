package com.alchemi.health.object.gui;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.alchemi.health.Config;
import com.alchemi.health.Health;
import com.alchemi.health.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;

public class GUIColour extends GuiScreen {

	private GuiButton buttonClose;
	private GuiButton buttonR;
	private GuiButton buttonG;
	private GuiButton buttonB;
	private GuiButton buttonA;
	
	private ResourceLocation sliderBase = new ResourceLocation(Reference.MOD_ID, "textures/gui/sliders.png");
	
	private int MODE_R = 0;
	private int MODE_G = 0;
	private int MODE_B = 0;
	private int MODE_A = 0;
	
	@Override
	public void initGui() {
		
		this.buttonList.add(buttonClose = new GuiButton(0, this.width / 2 - 50, this.height - (this.height / 4) + 10, 100, 20, "DONE"));
		this.buttonList.add(buttonR = new GuiButton(1, this.width / 2 + 95, (this.height * 2 / 8) + 5, 10, 20, "R"));
		this.buttonList.add(buttonG = new GuiButton(2, this.width / 2 + 95, (this.height * 3 / 8) + 5, 10, 20, "G"));
		this.buttonList.add(buttonB = new GuiButton(3, this.width / 2 + 95, (this.height * 4 / 8) + 5, 10, 20, "B"));
		this.buttonList.add(buttonA = new GuiButton(4, this.width / 2 + 95, (this.height * 5 / 8) + 5, 10, 20, "A"));
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		
		if (button == buttonClose) {
			ConfigManager.sync(Reference.MOD_ID, Type.INSTANCE);
			mc.displayGuiScreen(new GUIOptions());
		} else if (button == buttonR && MODE_R != 1) MODE_R = 1;
		else if (button == buttonG && MODE_G != 1) MODE_G = 1;
		else if (button == buttonB && MODE_B != 1) MODE_B = 1;
		else if (button == buttonA && MODE_A != 1) MODE_A = 1;
		else if (button == buttonR && MODE_R == 1) MODE_R = 0;
		else if (button == buttonG && MODE_G == 1) MODE_G = 0;
		else if (button == buttonB && MODE_B == 1) MODE_B = 0;
		else if (button == buttonA && MODE_A == 1) MODE_A = 0;
		
	}
	
	private int calcColour(GuiButton button) {
		float x = button.x + button.width/2;
		float x0 = this.width / 2 - 100;
		float x1 = x - x0;
		return Math.round(Math.max(0.0F, Math.min(x1/200, 1.0F)) * 255.0F);
	}
	
	@Override
	public boolean doesGuiPauseGame() {return true;}
	
	@Override
	public void updateScreen() {
		
		Config.col.r = calcColour(buttonR);
		Config.col.g = calcColour(buttonG);
		Config.col.b = calcColour(buttonB);
		Config.col.a = calcColour(buttonA);
		
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		
		drawDefaultBackground();
		
		try {
			Health.gui_reg.getByName(Config.indicator).getDeclaredConstructors()[0].newInstance(mc, mc.player);
		
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			e.printStackTrace();
		}
		drawCenteredString(mc.fontRenderer, "gui.colour", this.width/2, 10, Color.WHITE.getRGB());
		
		int xMouse = mouseX - this.buttonA.width/2;
		if (MODE_R == 1) {
			if (xMouse < this.width / 2 + 100 && xMouse > this.width / 2 - 100) {
				buttonR.x = xMouse;
			}
		} else if (MODE_G == 1) {
			if (xMouse < this.width / 2 + 100 && xMouse > this.width / 2 - 100) {
				buttonG.x = xMouse;
			}
		} else if (MODE_B == 1) {
			if (xMouse < this.width / 2 + 100 && xMouse > this.width / 2 - 100) {
				buttonB.x = xMouse;
			}
		} else if (MODE_A == 1) {
			if (xMouse < this.width / 2 + 100 && xMouse > this.width / 2 - 100) {
				buttonA.x = xMouse;
			}
		}
		
		GlStateManager.disableRescaleNormal();
		mc.getTextureManager().bindTexture(sliderBase);
		drawModalRectWithCustomSizedTexture(this.width / 2 - 100, this.height * 2 / 8 + 10, 0, 14 * MODE_R, 200, 14, 200, 28);
		drawModalRectWithCustomSizedTexture(this.width / 2 - 100, this.height * 3 / 8 + 10, 0, 14 * MODE_G, 200, 14, 200, 28);
		drawModalRectWithCustomSizedTexture(this.width / 2 - 100, this.height * 4 / 8 + 10, 0, 14 * MODE_B, 200, 14, 200, 28);
		drawModalRectWithCustomSizedTexture(this.width / 2 - 100, this.height * 5 / 8 + 10, 0, 14 * MODE_A, 200, 14, 200, 28);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
}
