package com.technodot.lifesteal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class LifestealHeart {
	
	public static ItemStack heart;
	private static String[] quotes = {"Life is a dream.\nWe wake up when we die.", "Life has a meaning but do not set out to find out.\nJust live it out.", "Don’t complain against life,\nit may hear you and double your suffering.", "This life is not perfect, live it to the full,\nit's all we have.", "To life, liberty,\nand freedom of happiness!", "Life is not a problem to be solved,\nbut a reality to be experienced."};
	
	public static void init(JavaPlugin context) {
		// Define heart item
		
		ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta metadata = item.getItemMeta();
		metadata.setDisplayName("§cHeart");
		List<String> lore = new ArrayList<String>();
		Random random = new Random();
		lore.add("§7" + quotes[random.nextInt(quotes.length)]);
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
	}
}
