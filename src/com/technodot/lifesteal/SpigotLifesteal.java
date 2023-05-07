package com.technodot.lifesteal;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotLifesteal extends JavaPlugin {
	
	public static boolean environmentalHeartLoss = true;
	public static boolean postMortalSpectation = false;
	public static boolean debugMode = true;
	
	private static final Listener EventListener = new HeartListener();
	private static final Listener GUIListener = new ReviveClick();
	
	@Override
	public void onEnable() {
		// Register items
		LifestealItem.init(this);
		
		// Register revive GUI
		ReviveGUI.init();
		
		// Register commands
		getCommand("withdraw").setExecutor(new LifestealCommand());
		getCommand("revive").setExecutor(new LifestealCommand());
		getCommand("free").setExecutor(new LifestealCommand());
		getCommand("reset").setExecutor(new LifestealCommand());
		getCommand("sethealth").setExecutor(new LifestealCommand());
		
		// Register EventListeners
		getServer().getPluginManager().registerEvents(SpigotLifesteal.EventListener, this);
		getServer().getPluginManager().registerEvents(SpigotLifesteal.GUIListener, this);
		
		Bukkit.getServer().getLogger().log(Level.INFO, "SpigotLifesteal v1.0 loaded!");
	}
	
	@Override
	public void onDisable() {
		// Nothing needed for disable
	}
}
