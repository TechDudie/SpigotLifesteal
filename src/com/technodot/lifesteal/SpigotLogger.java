package com.technodot.lifesteal;

import java.util.logging.Level;

import org.bukkit.Bukkit;

public class SpigotLogger {
	public static void info(String message) {
		Bukkit.getServer().getLogger().log(Level.INFO, "[SpigotLifesteal] " + message);
	}
	public static void warn(String message) {
		Bukkit.getServer().getLogger().log(Level.WARNING, "[SpigotLifesteal] " + message);
	}
	public static void error(String message) {
		Bukkit.getServer().getLogger().log(Level.SEVERE, "[SpigotLifesteal] " + message);
	}
	public static void chat(String message) {
		Bukkit.broadcastMessage(message);
	}
	public static void show(String message) {
		Bukkit.broadcastMessage(message);
		info(message);
	}
}
