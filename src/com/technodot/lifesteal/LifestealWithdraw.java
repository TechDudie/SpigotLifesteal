package com.technodot.lifesteal;

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
    	}
    	
    	return true;
    }
}
