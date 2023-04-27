package com.technodot.lifesteal;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotLifesteal extends JavaPlugin {
	@Override
	public void onEnable() {
		LifestealHeart.init(this);
		getCommand("withdraw").setExecutor(new LifestealWithdraw());
	}
	
	@Override
	public void onDisable() {
		
	}
}
