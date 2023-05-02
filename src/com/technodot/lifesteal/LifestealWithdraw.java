package com.technodot.lifesteal;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LifestealWithdraw implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (command.getName().equals("free")) {
				Bukkit.getServer().getPlayer(args[0]).getInventory().addItem(LifestealHeart.heart);
				return true;
			}
			
			if (command.getName().equals("reset")) {
				Bukkit.getServer().getPlayer(args[0]).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
			}
			return true;
		} else {
			Player player = (Player) sender;
			if (command.getName().equals("withdraw")) {
				player.getInventory().addItem(LifestealHeart.heart);
				HeartListener.modifyMaxHealth(player, -2);
				SpigotLogger.info(player.getDisplayName() + " withdrew a heart");
				BeaconGUI.openGUI(player);
			}
		}
		
		return true;
	}
}
