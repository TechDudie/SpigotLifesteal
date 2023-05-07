package com.technodot.lifesteal;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class LifestealItem {
	
	public static ItemStack heart;
	public static ItemStack crystal;
	
	public static void init(JavaPlugin context) {
		// Define heart item
		
		ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta metadata = item.getItemMeta();
		metadata.setDisplayName("§cHeart");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Life is precious.");
		metadata.setLore(lore);
		metadata.addEnchant(Enchantment.DAMAGE_ALL, 10, false);
		metadata.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(metadata);
		heart = item;
		
		// Define heart crafting recipe
		
		NamespacedKey key = new NamespacedKey(context, "heart");
		ShapedRecipe heartRecipe = new ShapedRecipe(key, heart);
		heartRecipe.shape("dnd", "ntn", "dnd");
		heartRecipe.setIngredient('d', Material.DIAMOND_BLOCK);
		heartRecipe.setIngredient('n', Material.NETHERITE_INGOT);
		heartRecipe.setIngredient('t', Material.TOTEM_OF_UNDYING);
		Bukkit.addRecipe(heartRecipe);
		
		// Define revive crystal item
		
		ItemStack item2 = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta metadata2 = item2.getItemMeta();
		metadata2.setDisplayName("§bRevive Crystal");
		List<String> lore2 = new ArrayList<String>();
		lore2.add("§7You have a second chance. Don't screw it up.");
		metadata2.setLore(lore2);
		metadata2.addEnchant(Enchantment.DAMAGE_ALL, 20, false);
		metadata2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item2.setItemMeta(metadata2);
		crystal = item2;
		
		// Define revive crystal crafting recipe
		
		NamespacedKey key2 = new NamespacedKey(context, "crystal");
		ShapedRecipe crystalRecipe = new ShapedRecipe(key2, crystal);
		crystalRecipe.shape("gdg", "dhd", "gdg");
		crystalRecipe.setIngredient('g', Material.GOLD_BLOCK);
		crystalRecipe.setIngredient('d', Material.DIAMOND_BLOCK);
		crystalRecipe.setIngredient('h', Material.NETHER_STAR);
		Bukkit.addRecipe(crystalRecipe);
	}
}
