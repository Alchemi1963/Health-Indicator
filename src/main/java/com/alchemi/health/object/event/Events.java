package com.alchemi.health.object.event;

import org.lwjgl.input.Keyboard;

import com.alchemi.health.Reference;
import com.alchemi.health.client.key.KeyBindings;
import com.alchemi.health.object.gui.GUIOptions;
import com.alchemi.health.object.gui.TransparentIndicatorGUI;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@EventBusSubscriber
public class Events {

	private final static Minecraft mc = Minecraft.getMinecraft();
	private static Entity entity;
	private static boolean shouldRender = false;
	
	@SubscribeEvent
	public static void playerTick(PlayerTickEvent e) {
		EntityPlayer player = e.player;
		
		if (Keyboard.isKeyDown(KeyBindings.options.getKeyCode()) && !player.world.isRemote) {
			
			new GUIOptions();
			
		}
		
		if (player != null && !player.world.isRemote && mc.objectMouseOver != null && mc.objectMouseOver.entityHit != null) {
			entity = mc.objectMouseOver.entityHit;
			if (!shouldRender) shouldRender = true;
		}
		else if (!player.world.isRemote) shouldRender = false;
	}
	
	@SubscribeEvent
	public static void onRenderGUI(RenderGameOverlayEvent.Post e) {
		if (e.getType() != ElementType.EXPERIENCE) return;
		if (shouldRender) new TransparentIndicatorGUI(mc, entity, Reference.MOD_ID + ":" + Reference.TRANSPARENT_GUI);
	}
		
}

