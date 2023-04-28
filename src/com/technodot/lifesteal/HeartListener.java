package com.technodot.lifesteal;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HeartListener implements Listener {
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player player = event.getPlayer();
			Bukkit.getServer().getLogger().log(Level.INFO, "[DEBUG] Right click detected");
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == "§cHeart") {
				HeartManager.modifyMaxHealth(player, 2);
				Bukkit.getServer().getLogger().log(Level.INFO, "[DEBUG] Health added");
			}
		}
	}
}