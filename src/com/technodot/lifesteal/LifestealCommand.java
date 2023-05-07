package com.technodot.lifesteal;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LifestealCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!(sender instanceof Player)) || ((Player) sender).isOp()) {
			if (command.getName().equals("free")) {
				Bukkit.getServer().getPlayer(args[0]).getInventory().addItem(LifestealItem.heart);
				return true;
			}
			
			if (command.getName().equals("reset")) {
				Bukkit.getServer().getPlayer(args[0]).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
				return true;
			}
			
			if (command.getName().equals("sethealth")) {
				Bukkit.getServer().getPlayer(args[0]).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Integer.valueOf(args[1]));
				return true;
			}
		} else {
			Player player = (Player) sender;
			
			if (command.getName().equals("withdraw")) {
				player.getInventory().addItem(LifestealItem.heart);
				HeartListener.modifyMaxHealth(player, -2);
				SpigotLogger.info(player.getDisplayName() + " withdrew a heart");
				return true;
			}
			
			if (command.getName().equals("revive")) {
				ReviveGUI.openGUI(player);
				return true;
			}
		}
		return true;
	}
}
