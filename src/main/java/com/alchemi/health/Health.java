package com.alchemi.health;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.alchemi.health.client.key.KeyBindings;
import com.alchemi.health.object.GUIRegistry;
import com.alchemi.health.object.event.Events;
import com.alchemi.health.object.gui.indicators.ClassicGUI;
import com.alchemi.health.object.gui.indicators.DefaultGUI;
import com.alchemi.health.object.gui.indicators.DoctorWhoGUI;
import com.alchemi.health.object.gui.indicators.TransparentGUI;
import com.alchemi.health.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_MC, canBeDeactivated = true)

public class Health {

	@Instance
	public static Health instance;
	
	public static Minecraft mc;
	public static GUIRegistry gui_reg = new GUIRegistry();
	
	public static DecimalFormat df = new DecimalFormat("#.#");
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		
		MinecraftForge.EVENT_BUS.register(this);
		mc = Minecraft.getMinecraft();
		df.setRoundingMode(RoundingMode.CEILING);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		
		KeyBindings.init();
		gui_reg.register(DefaultGUI.class, "gui.default");
		gui_reg.register(TransparentGUI.class, "gui.transparent");
		gui_reg.register(ClassicGUI.class, "gui.classic");
		gui_reg.register(DoctorWhoGUI.class, "gui.doctor_who");
		
		ConfigManager.sync(Reference.MOD_ID, Type.INSTANCE);
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		
		MinecraftForge.EVENT_BUS.register(new Events());
		
	}
}
