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
	
	@Override
	public void onEnable() {
		// Create heart item
		LifestealHeart.init(this);
		
		// Register commands
		getCommand("withdraw").setExecutor(new LifestealWithdraw());
		getCommand("free").setExecutor(new LifestealWithdraw());
		getCommand("reset").setExecutor(new LifestealWithdraw());
		
		// Register EventListener
		getServer().getPluginManager().registerEvents(SpigotLifesteal.EventListener, this);
		
		Bukkit.getServer().getLogger().log(Level.INFO, "SpigotLifesteal v1.0 loaded!");
	}
	
	@Override
	public void onDisable() {
		// Nothing needed for disable
	}
}
