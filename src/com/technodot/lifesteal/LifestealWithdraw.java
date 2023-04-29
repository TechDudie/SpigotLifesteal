package com.technodot.lifesteal;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LifestealWithdraw implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You're not a player!");
			return true;
		}
		
		Player player = (Player) sender;
		if (command.getName().equals("withdraw")) {
			player.getInventory().addItem(LifestealHeart.heart);
			HeartListener.modifyMaxHealth(player, -2);
			SpigotLogger.info(player.getDisplayName() + " withdrew a heart");
		}
		
		// Debug commands - make sure to remove in real releases!
		
		if (command.getName().equals("free")) {
			player.getInventory().addItem(LifestealHeart.heart);
		}
		
		if (command.getName().equals("reset")) {
			player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
		}
		return true;
	}
}
