package com.alchemi.health.client.key;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.alchemi.health.Reference;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyBindings {

	private static final String category = Reference.NAME;
	
	public static KeyBinding options;
	
	private static final List<KeyBinding> allBindings;
	
	static {
		allBindings = ImmutableList.of(
				options = new KeyBinding("key.health.options", KeyConflictContext.IN_GAME, Keyboard.KEY_P, category));
	}
	
	private KeyBindings() {}

	@SideOnly(Side.CLIENT)
	public static void init() {
		for (KeyBinding binding : allBindings) {
			ClientRegistry.registerKeyBinding(binding);
		}
	}
	
}
