package com.technodot.lifesteal;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.BanList.Type;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ReviveClick implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (event.getView().getTitle().equalsIgnoreCase("§bRevive Beacon Menu") & event.getClickedInventory() != player.getInventory()) {
			Inventory inventory = event.getInventory();
			ItemStack playerHead = inventory.getItem(10);
			ItemStack reviveCrystal = inventory.getItem(12);
			switch (event.getCurrentItem().getType()) {
				case LIME_CONCRETE:
					event.setCancelled(true);
					try {
						if (!(reviveCrystal.getItemMeta().getDisplayName().equalsIgnoreCase("§bReviveCrystal"))) {
							SpigotLogger.info("No revive crystal was inserted, revive cancelled");
							SpigotLogger.debug("Above exception caused by name not equal");
							player.getInventory().addItem(playerHead);
							player.getInventory().addItem(reviveCrystal);
							return;
						}
					} catch (NullPointerException exception) {
						SpigotLogger.info("No revive crystal was inserted, revive cancelled");
						SpigotLogger.debug("Above exception caused by NullPointerException");
						player.getInventory().addItem(playerHead);
						player.getInventory().addItem(reviveCrystal);
						return;
					}
					try {
						SkullMeta headMeta = (SkullMeta) playerHead.getItemMeta();
						String targetName = headMeta.getOwner();
						Player target = Bukkit.getServer().getPlayer(targetName);
						target.setGameMode(GameMode.SURVIVAL);
						Bukkit.getBanList(Type.NAME).pardon(targetName);
						SpigotLogger.info(targetName + " has been unbanned");
						inventory.clear(10);
						inventory.clear(12);
					} catch (NullPointerException exception) {
						SpigotLogger.info("No skull inserted, revive cancelled");
						SpigotLogger.debug("Above exception caused by NullPointerException");
						player.getInventory().addItem(playerHead);
						player.getInventory().addItem(reviveCrystal);
						return;
					}
					break;
				case RED_CONCRETE:
					event.setCancelled(true);
					player.closeInventory();
					player.getInventory().addItem(playerHead);
					player.getInventory().addItem(reviveCrystal);
					break;
				default:
					event.setCancelled(true);
					break;
			}
		}
		
	}
}
