package com.technodot.lifesteal;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotLifesteal extends JavaPlugin {
	private static final Listener RightClickEvent = new HeartListener();
	
	@Override
	public void onEnable() {
		LifestealHeart.init(this);
		getCommand("withdraw").setExecutor(new LifestealWithdraw());
		getServer().getPluginManager().registerEvents(SpigotLifesteal.RightClickEvent, this);
		Bukkit.getServer().getLogger().log(Level.INFO, "SpigotLifesteal v1.0 loaded!");
	}
	
	@Override
	public void onDisable() {
		
	}
}
