package com.alchemi.health.object.gui;

import java.awt.Color;

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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class IndicatorGUI extends Gui {

	private final Minecraft mc;
	private static ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/gui/default_indicator.png");
	
	private ProgressBar health;
	
	public final String name;
	
	private static int pos_x = 10;
	private static int pos_y = 10;
	
	public static int health_texture_x = 48;
	public static int health_texture_y = 45;
	public static int health_overlay_texture_x = 0;
	public static int health_overlay_texture_y = 69;
	public static int health_width = 124;
	public static int health_height = 16;
	
	public static int width = 180;
	public static int height = 69;
	
	public static int type_width = 16;
	public static int type_heigth = 16;
	public static int type_texture_x = 17;
	public static int type_texture_y = 46;
	
	public static int name_width = 124;
	public static int name_height = 34;
	public static int name_x = 52;
	public static int name_y = 20;
	
	public static int entity_x = 12;
	public static int entity_y = 12;
	public static int entity_width = 34;
	public static int entity_height = 34;
	
	private Color colour = new Color(255,255,255,255);

	public IndicatorGUI(Minecraft mc, Entity ent, String name) {
		this.name = name;
		this.mc = mc;
		mc.getTextureManager().bindTexture(TEXTURES);
		
		if (ent instanceof MultiPartEntityPart) {
			ent = new EntityDragon(ent.world);
		}
		
		health = new ProgressBar(TEXTURES, PBDirection.LEFT_RIGHT, health_texture_x + pos_x, health_texture_y + pos_y, health_width, health_height, health_overlay_texture_x, health_overlay_texture_y);
	
		health.setMax(((EntityLiving)ent).getMaxHealth());
		health.setMin(((EntityLiving)ent).getHealth());
		
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
		GlStateManager.scale(scale, scale, scale);
		mc.fontRenderer.drawString(ent.getName(), (name_x+pos_x)/scale, (name_y+mc.fontRenderer.FONT_HEIGHT/2)/scale, colour.getRGB(), false);
		GlStateManager.scale(1.0/scale,1.0/scale,1.0/scale);
		
	}
	
	private void drawEnt(Entity ent) {
		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		int scaleY = MathHelper.ceil(entity_width / ent.height);
		int scaleX = MathHelper.ceil(entity_height / ent.width);
		int	scale = (int) (Math.min(scaleX, scaleY)/1.2);
	
		GlStateManager.translate(entity_x*2 + pos_x, entity_y*2 + pos_y, 50.0F);
		GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-100.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(0.0f, 1.0F, 0.0F, 0.0F);
	
		RenderHelper.enableStandardItemLighting();
	
		GlStateManager.translate(0.0F, 0.0F, 0.0F);
		RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		rendermanager.setPlayerViewY(180.0F);
		rendermanager.setRenderShadow(false);
		rendermanager.renderEntity(ent, 0.0D, -ent.height/2, 0.0D, 0.0F, 1.0F, false);
		rendermanager.setRenderShadow(true);
	
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
	
	public void setColour(Color col) {
		colour = col;
	}
	
	public void setPosition(int x, int y) {
		pos_x = x;
		pos_y = y;
	}
	
	public void setTextures(ResourceLocation new_location) {
		TEXTURES = new_location;
	}
}
