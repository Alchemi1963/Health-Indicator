package com.alchemi.health.object.progressbar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ProgressBar extends Gui{

	private ResourceLocation texture;
	private PBDirection direction;
	private int posX, posY;
	private int width, height;
	private int textureX, textureY;
	
	private float min, max = 0;
	
	public ProgressBar(ResourceLocation texture, PBDirection direction, int posX, int posY, int width,
			int height, int textureX, int textureY) {
		
		this.texture = texture;
		this.direction = direction;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.textureX = textureX;
		this.textureY = textureY;
	}
	
	public ProgressBar setMin(float f) {
		this.min = f;
		return this;
	}
	
	public ProgressBar setMax(float f) {
		this.max = f;
		return this;
	}
	
	private int getProgressWidth() {
		return (int) (this.min != 0 && this.max != 0 ? this.min/this.max * this.width : 0);
	}
	
	private int getProgressHeight() {
		return (int) (this.min != 0 && this.max != 0 ? this.min/this.max * this.height : 0);
	}
	
	public void draw(Minecraft mc) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		
		switch(this.direction) {
		
		case LEFT_RIGHT:
			this.drawTexturedModalRect(this.posX, this.posY, this.textureX, this.textureY, getProgressWidth(), this.height);
			break;
		
		case RIGHT_LEFT:
			this.drawTexturedModalRect(this.posX, this.posY, this.textureX, this.textureY, this.width, this.height);
			this.drawTexturedModalRect(this.posX, this.posY, this.posX, this.posY, this.width - getProgressWidth(), this.height);
			break;
			
		case TOP_BOTTOM:
			this.drawTexturedModalRect(this.posX, this.posY, this.textureX, this.textureY, this.width, getProgressHeight());
			break;
			
		case BOTTOM_TOP:
			this.drawTexturedModalRect(this.posX, this.posY, this.textureX, this.textureY, this.width, this.height);
			this.drawTexturedModalRect(this.posX, this.posY, this.posX, this.posY, this.width, this.height - getProgressHeight());
			break;
			
		case DIAGONAL_TOP_RIGHT:
			this.drawTexturedModalRect(this.posX, this.posY, this.textureX, this.textureY, this.width, this.height);
			this.drawTexturedModalRect(this.posX - getProgressWidth(), this.posY + getProgressHeight(), this.posX, this.posY, this.width + getProgressWidth(), this.height - getProgressHeight());
			
		case DIAGONAL_TOP_LEFT:
			this.drawTexturedModalRect(this.posX, this.posY, this.textureX, this.textureY, this.width, this.height);
			this.drawTexturedModalRect(this.posX + getProgressWidth(), this.posY + getProgressHeight(), this.posX, this.posY, this.width - getProgressWidth(), this.height - getProgressHeight());
			break;
			
		case DIAGONAL_BOTTOM_RIGHT:
			this.drawTexturedModalRect(this.posX, this.posY, this.textureX, this.textureY, this.width, this.height);
			this.drawTexturedModalRect(this.posX - getProgressWidth(), this.posY - getProgressHeight(), this.posX, this.posY, this.width + getProgressWidth(), this.height + getProgressHeight());
			break;
			
		case DIAGONAL_BOTTOM_LEFT:
			this.drawTexturedModalRect(this.posX, this.posY, this.textureX, this.textureY, this.width, this.height);
			this.drawTexturedModalRect(this.posX + getProgressWidth(), this.posY - getProgressHeight(), this.posX, this.posY, this.width - getProgressWidth(), this.height + getProgressHeight());
			break;
			
		default:
			this.drawTexturedModalRect(this.posX, this.posY, this.textureX, this.textureY, this.width, this.height);
			break;
		}
	}
	
	public static enum PBDirection {
		LEFT_RIGHT, RIGHT_LEFT, TOP_BOTTOM, BOTTOM_TOP, DIAGONAL_TOP_RIGHT, DIAGONAL_TOP_LEFT, DIAGONAL_BOTTOM_RIGHT, DIAGONAL_BOTTOM_LEFT;
	}
}
