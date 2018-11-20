package com.alchemi.health.object.event;

import java.lang.reflect.InvocationTargetException;

import org.lwjgl.input.Keyboard;

import com.alchemi.health.Config;
import com.alchemi.health.Health;
import com.alchemi.health.Reference;
import com.alchemi.health.client.key.KeyBindings;
import com.alchemi.health.object.gui.GUIOptions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class Events {

	private final static Minecraft mc = Minecraft.getMinecraft();
	private static Entity entity;
	private static boolean shouldRender = false;
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void playerTick(PlayerTickEvent e) {
		EntityPlayer player = e.player;
		
		if (Keyboard.isKeyDown(KeyBindings.options.getKeyCode()) && 
				!player.world.isRemote && 
				!Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen() && 
				mc.currentScreen == null){
			
			mc.displayGuiScreen(new GUIOptions());
		}
		
		if (player != null && !player.world.isRemote && 
				mc.objectMouseOver != null && mc.objectMouseOver.entityHit != null) {
			entity = mc.objectMouseOver.entityHit;
			if (!shouldRender) shouldRender = true;
		}
		else if (!player.world.isRemote) shouldRender = false;
	}
	
	@SubscribeEvent
	public static void onRenderGUI(RenderGameOverlayEvent.Post e) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		if (e.getType() != ElementType.EXPERIENCE) return;
		
		if (shouldRender) {
			Health.gui_reg.getByName(Config.indicator).getDeclaredConstructors()[0].newInstance(mc, entity);
		}
	}
	
	@SubscribeEvent
	public static void onConfigChanged(OnConfigChangedEvent e) {
		
		if (e.getModID().equals(Reference.MOD_ID)) {
			ConfigManager.sync(Reference.MOD_ID, Type.INSTANCE);
		}
		
	}
		
}

