package com.alchemi.health.object.gui.indicators;

import java.awt.Color;

import com.alchemi.health.Config;
import com.alchemi.health.Health;
import com.alchemi.health.Reference;
import com.alchemi.health.object.progressbar.ProgressBar;
import com.alchemi.health.object.progressbar.ProgressBar.PBDirection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public abstract class AbstractIndicator extends Gui {

	Minecraft mc;
	Entity ent;
	ResourceLocation TEXTURES;
	
	ProgressBar health;
	
	public String name;
	
	public int pos_x = Config.pos.x;
	public int pos_y = Config.pos.y;
	
	int health_texture_x = 48;
	int health_texture_y = 26;
	int health_overlay_texture_x = 0;
	int health_overlay_texture_y = 69;
	int health_width = 124;
	int health_height = 16;
	
	int width = 180;
	int height = 69;
	
	int type_width = 16;
	int type_heigth = 16;
	int type_texture_x = 17;
	int type_texture_y = 46;
	
	int name_width = 125;
	int name_height = 17;
	int name_x = 110;
	int name_y = 8;
	
	int entity_x = 25;
	int entity_y = 10;
	int entity_width = 30;
	int entity_height = 30;
	
	Color colour = new Color(255,255,255,255);

	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	
	public void draw() {
		if (Config.col.r != 255 || Config.col.g != 255 || Config.col.b != 255 || Config.col.a != 255) {
			colour = new Color(Config.col.r, Config.col.g, Config.col.b, Config.col.a);
		}
		
		if (TEXTURES == null) {
			TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/gui/default_indicator.png");
		}
		mc.getTextureManager().bindTexture(TEXTURES);
		
		if (ent instanceof MultiPartEntityPart) {
			ent = new EntityDragon(ent.world);
		}
		
		health = new ProgressBar(TEXTURES, PBDirection.LEFT_RIGHT, health_texture_x + pos_x, health_texture_y + pos_y, health_width, health_height, health_overlay_texture_x, health_overlay_texture_y);
	
		health.setMax(((EntityLivingBase)ent).getMaxHealth());
		health.setMin(((EntityLivingBase)ent).getHealth());
		
		//draw background
		drawTexturedModalRect(pos_x, pos_y, 0, 0, width, height);
		drawEnt(ent);
		health.draw(this.mc);
		
		int ENTITY_TYPE;
		if (ent instanceof IMob) ENTITY_TYPE = 0;
		else if (ent instanceof IAnimals) ENTITY_TYPE = 1;
		else if (ent instanceof EntityPlayer) ENTITY_TYPE = 2;
		else ENTITY_TYPE = 0;
		
		//draw entity type
		//0 = aggressive
		//1 = passive
		//2 = player
		drawTexturedModalRect(type_texture_x + pos_x, type_texture_y + pos_y, width, ENTITY_TYPE*type_heigth, type_width, type_heigth);
		
		GlStateManager.enableBlend();
		float scaleX = name_width/mc.fontRenderer.getStringWidth(ent.getName());
		float scaleY = name_height/mc.fontRenderer.FONT_HEIGHT;
		float scale = Math.min(scaleX, scaleY);
		mc.fontRenderer.drawString(Health.df.format(health.getMin()) + "/" + Health.df.format(health.getMax()), health_width/2 + health.posX - mc.fontRenderer.getStringWidth(Health.df.format(health.getMin()) + "/" + Health.df.format(health.getMax()))/2, health_height/2 - mc.fontRenderer.FONT_HEIGHT/2 + health.posY, colour.getRGB());
		GlStateManager.scale(scale, scale, scale);
		mc.fontRenderer.drawString(ent.getName(), (name_x+pos_x - mc.fontRenderer.getStringWidth(ent.getName()) / 2)/scale, (pos_y + name_y+mc.fontRenderer.FONT_HEIGHT/2)/scale, colour.getRGB(), false);
		GlStateManager.scale(1.0/scale,1.0/scale,1.0/scale);
	}

	abstract void setColour(Color col);
	
	abstract void setPosition(int x, int y);
	
	public void drawEnt(Entity ent) {
		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		float scaleX;
		
		if (ent.width <= 0.8) scaleX = MathHelper.ceil(entity_width / ent.width);
		else scaleX = MathHelper.ceil((entity_width*2/3)/ent.width);
		
		float scaleY = MathHelper.ceil(entity_height / ent.height);
		double scale = Math.min(scaleX, scaleY);
		
		GlStateManager.translate(entity_x + pos_x, pos_y + entity_y + entity_height, 50.0F);
		GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-100.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(0.0f, 1.0F, 0.0F, 0.0F); 
		if (ent.getLookVec().z < 0) GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
	
		RenderHelper.enableStandardItemLighting();
	
		GlStateManager.translate(0.0F, 0.0F, 0.0F);
		RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		rendermanager.setPlayerViewY(180.0F);
		rendermanager.setRenderShadow(false);
		rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
		rendermanager.setRenderShadow(true);
	
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
	
}
