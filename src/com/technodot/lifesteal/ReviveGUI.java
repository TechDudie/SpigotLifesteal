package com.technodot.lifesteal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

// TechnoDot is AWESOME!!!

public class ReviveGUI {
	public static ItemStack empty = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
	public static ItemStack infoHead = new ItemStack(Material.RED_STAINED_GLASS_PANE);
	public static ItemStack infoCrystal = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
	public static ItemStack name = new ItemStack(Material.NAME_TAG);
	public static ItemStack confirm = new ItemStack(Material.LIME_CONCRETE);
	public static ItemStack cancel = new ItemStack(Material.RED_CONCRETE);
	
	public static void init() {
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName(" ");
		empty.setItemMeta(emptyMeta);
		
		ItemMeta infoHeadMeta = infoHead.getItemMeta();
		infoHeadMeta.setDisplayName("§cPlace the §bPlayer Head§c in this slot!");
		infoHead.setItemMeta(infoHeadMeta);
		
		ItemMeta infoCrystalMeta = infoCrystal.getItemMeta();
		infoCrystalMeta.setDisplayName("§9Place the §bRevive Crystal§9 in this slot!");
		infoCrystal.setItemMeta(infoCrystalMeta);
		
		ItemMeta nameMeta = name.getItemMeta();
		nameMeta.setDisplayName(" ");
		name.setItemMeta(nameMeta);
		
		ItemMeta confirmMeta = confirm.getItemMeta();
		confirmMeta.setDisplayName("Confirm Revival");
		confirm.setItemMeta(confirmMeta);
		
		ItemMeta cancelMeta = cancel.getItemMeta();
		cancelMeta.setDisplayName("Cancel Revival");
		cancel.setItemMeta(cancelMeta);
	}
	
	private static boolean check(int[] array, int value) {
        Arrays.sort(array);
        int result = Arrays.binarySearch(array, value);
        return result >= 0 ? true : false;
    }
	
	public static void openGUI(Player player) {
		Inventory gui = Bukkit.createInventory(player, 27, "§bRevive Beacon Menu");
		int slots[] = {1, 3, 10, 12, 14, 15, 16, 19, 21};
		
		for (int i = 0; i < 27; i++) {
			if (!(check(slots, i))) {
				gui.setItem(i, empty);
			} else if (i == 14) {
				gui.setItem(i, name);
			} else if (i == 15) {
				gui.setItem(i, confirm);
			} else if (i == 16) {
				gui.setItem(i, cancel);
			} else if (i == 1 || i == 19) {
				gui.setItem(i, infoHead);
			} else if (i == 3 || i == 21) {
				gui.setItem(i, infoCrystal);
			}
		}
		
		player.openInventory(gui);
	}
}
