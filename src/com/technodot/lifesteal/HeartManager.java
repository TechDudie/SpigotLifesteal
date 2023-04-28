package com.technodot.lifesteal;

import org.bukkit.entity.Player;

public class HeartManager {
	public static void init() {
		// nothing here :(
	}
	
	@SuppressWarnings("deprecation") // Couldn't figure out how to make attributes work... will do that later
	public static void modifyMaxHealth(Player target, int change) {
		target.setMaxHealth(target.getMaxHealth() + (double) change);
	}
}
