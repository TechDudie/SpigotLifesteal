package com.technodot.lifesteal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BeaconGUI {
	public static void openGUI(Player player) {
		Inventory gui = Bukkit.createInventory(player, 27, "§bRevive Beacon Menu");
		
		ItemStack empty = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
		ItemMeta empty_meta = empty.getItemMeta();
		empty_meta.setDisplayName(" ");
		empty.setItemMeta(empty_meta);
		
		for (int i=0; i<27; i++) {
			if (!(i == 10 || i == 12 || i == 14 || i == 16)) {
				gui.setItem(i, empty);
			}
		}
		
		player.openInventory(gui);
	}
}
