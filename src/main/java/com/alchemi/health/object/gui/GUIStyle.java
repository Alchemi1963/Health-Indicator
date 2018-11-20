package com.alchemi.health.object.gui;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.alchemi.health.Config;
import com.alchemi.health.Health;
import com.alchemi.health.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Config.Type;

public class GUIStyle extends GuiScreen {
	
	private GuiButton buttonClose;
	private GuiButton buttonNext;
	private GuiButton buttonPrev;
	
	private String[] styles;
	private int currentPage = 0;
	private List<GuiButton[]> pages;
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {

		if (button == buttonClose) {
			
			ConfigManager.sync(Reference.MOD_ID, Type.INSTANCE);
			mc.displayGuiScreen(new GUIOptions());
		} else if (button == buttonNext) {
			if (currentPage < pages.size() - 1) {
				
				currentPage ++;
			}
		} else if (button == buttonPrev) {
			if (currentPage > 0) {
				
				currentPage --;
			}
		} else {
			
			for (String name : styles) if (button.displayString == name) {
				
				Config.indicator = name;
				
			}
					
		}
		
		super.actionPerformed(button);
	}
	
	@Override
	public boolean doesGuiPauseGame() {return true;}
	
	@Override
	public void updateScreen() {}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		try {
			Health.gui_reg.getByName(Config.indicator).getDeclaredConstructors()[0].newInstance(mc, mc.player);
		
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			e.printStackTrace();
		}
		drawCenteredString(mc.fontRenderer, "gui.style", this.width/2, 10, Color.WHITE.getRGB());
		
		while (this.buttonList.size() > 3) {
			this.buttonList.remove(this.buttonList.size() - 1);
		}
		
		
		if (pages.get(currentPage).length > 0 && pages.get(currentPage)[0] != null) this.buttonList.add(pages.get(currentPage)[0]);
		if (pages.get(currentPage).length > 1 && pages.get(currentPage)[1] != null) this.buttonList.add(pages.get(currentPage)[1]);
		if (pages.get(currentPage).length > 2 && pages.get(currentPage)[2] != null) this.buttonList.add(pages.get(currentPage)[2]);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
	}
	
	@Override
	public void initGui() {
		super.initGui();
				
		styles = Health.gui_reg.getAllNames();
		
		this.buttonList.add(buttonClose = new GuiButton(0, this.width / 2 - 50, this.height - (this.height / 4) + 10, 100, 20, "DONE"));
		this.buttonList.add(buttonPrev = new GuiButton(1, this.width / 10 - 50, this.height - (this.height / 4) + 10, 100, 20, "PREV"));
		this.buttonList.add(buttonNext = new GuiButton(2, this.width - this.width / 10 - 50, this.height - (this.height / 4) + 10, 100, 20, "NEXT"));
		
		int page = 0;
		int i = 1;
		pages = new ArrayList<GuiButton[]>();
		pages.add(new GuiButton[3]);
		for (String name : styles) {
			GuiButton but = new GuiButton(i, this.width / 2 - 100, this.height * (i+1)/7 + 10, name);
			GuiButton[] pageAr = pages.get(page);
			pageAr[i-1] = but;
			pages.remove(page);
			pages.add(page, pageAr);
			
			i++;
			if (i > 3) {
				i = 1;
				page++;
				pages.add(new GuiButton[3]);
			}
		}
	}
	
}
