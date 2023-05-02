package com.technodot.lifesteal;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HeartListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player player = event.getPlayer();
			try {
				if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cHeart")) {
					if (modifyMaxHealth(player, 2)) {
						ItemStack itemstack = player.getInventory().getItemInMainHand();
						itemstack.setAmount(itemstack.getAmount() - 1);
						player.getInventory().setItemInMainHand(itemstack);
						SpigotLogger.info(player.getDisplayName() + " redeemed a heart");
					} else {
						SpigotLogger.info(player.getDisplayName() + " has hit the 20 heart cap, new heart not given!");
						player.sendMessage("§7You have reached the 20 heart cap, new heart not given!");
					}
					
				}
			} catch (NullPointerException error) {
				SpigotLogger.info("Player triggered NullPointerException, ignoring");
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		SpigotLogger.debug("Death event detected");
		Player player = event.getEntity();
		Player killer = player.getKiller();
		event.setDeathMessage(null);
		if (killer == null || player.getDisplayName() == killer.getDisplayName()) {
			if (SpigotLifesteal.environmentalHeartLoss) {
				modifyMaxHealth(player, -2);
				SpigotLogger.debug("Death with killer == null (self-inflicted or mob-inflicted death) detected");
				SpigotLogger.chat("§c" + player.getDisplayName() + "§7 died to natrual causes!");
			}
		} else {
			modifyMaxHealth(player, -2);
			modifyMaxHealth(killer, 2);
			SpigotLogger.info("Death with killer != null (player-inflicted death) detected");
			SpigotLogger.chat("§c" + player.getDisplayName() + "§7 was killed by §c" + killer.getDisplayName() + "§7!");
		}
	}
	
	public static boolean modifyMaxHealth(Player target, int change) {
		double currentHealth = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		if (currentHealth >= 40 && change > 0) {
			return false;
		} else if (currentHealth < 3) {
			if (SpigotLifesteal.postMortalSpectation) {
				target.setGameMode(GameMode.SPECTATOR);
			} else {
				Bukkit.getBanList(Type.NAME).addBan(target.getDisplayName(), "\n\n§cYou ran out of lives! You will be unbanned when someone revives you.", null, null);
			}
			target.kickPlayer("§cYou ran out of lives! You will be unbanned when someone revives you.");
			SpigotLogger.chat("§c" + target.getDisplayName() + "§7 has ran out of lives, §c" + target.getDisplayName() + "§7 has been banned!");
			return false;
		}
		target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(currentHealth + (double) change);
		return true;
	}
}