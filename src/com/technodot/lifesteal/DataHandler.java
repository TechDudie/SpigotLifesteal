package com.technodot.lifesteal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;


public class DataHandler implements Serializable {
	private static transient final long serialVersionUID = 2104135565336474972L;
 
    public final HashMap<Location, String> blockSnapShot;
    public final HashSet<UUID> previouslyOnlinePlayers;
 
    // Can be used for saving
    public DataHandler(HashMap<Location, String> blockSnapShot, HashSet<UUID> previouslyOnlinePlayers) {
        this.blockSnapShot = blockSnapShot;
        this.previouslyOnlinePlayers = previouslyOnlinePlayers;
    }
    // Can be used for loading
    public DataHandler(DataHandler loadedData) {
        this.blockSnapShot = loadedData.blockSnapShot;
        this.previouslyOnlinePlayers = loadedData.previouslyOnlinePlayers;
    }
 
    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public static DataHandler loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            DataHandler data = (DataHandler) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    public static void getBlocksPlayersAndSave() {
        // HashMap used for storing blocks
        HashMap<Location, String> blockSnapShot = new HashMap<Location, String>();
        // HashSet used for storing the online players
        HashSet<UUID> previouslyOnlinePlayers = new HashSet<UUID>();
        // Grabs the spawn location of the first world the server finds
        Location spawnLocation = Bukkit.getServer().getWorlds().iterator().next().getSpawnLocation();    
        // One variable to store our blockLocation
        Location blockLocation;
        // Variables to store our x y z coordinates
        int x, y, z;    
        // We will first retrieve all the currently online players
        Bukkit.getServer().getOnlinePlayers().forEach(player -> previouslyOnlinePlayers.add(player.getUniqueId()));    
        // Next we will retrieve all block data in a 64 by 64 radius around the spawn.
   
        // While looping, using the new keyword and making declarations like "int x = 0;"
        // will create garbage, and that garbage will start to pile up if the loop is
        // extensive. We will take as much of a load off of the garbage collector as we
        // can here by re-assigning x,y,z not re-declaring, and re-assigning the declared
        // blockLocation to retrieve the block data. (we are going to store 262,144 blocks...)            
        for (x = 0; x <= 32; x++) {
            for (y = 0; y <= 32; y++) {
                for(z = 0; z <= 32; z++) {
                    blockSnapShot.put(blockLocation = new Location(spawnLocation.getWorld(),
                            spawnLocation.getX() - x,
                            spawnLocation.getY() - y,
                            spawnLocation.getZ() - z), blockLocation.getBlock().getBlockData().getAsString());
                    blockSnapShot.put(blockLocation = new Location(spawnLocation.getWorld(),
                            spawnLocation.getX() + x,
                            spawnLocation.getY() - y,
                            spawnLocation.getZ() - z), blockLocation.getBlock().getBlockData().getAsString());
                    blockSnapShot.put(blockLocation = new Location(spawnLocation.getWorld(),
                            spawnLocation.getX() - x,
                            spawnLocation.getY() + y,
                            spawnLocation.getZ() - z), blockLocation.getBlock().getBlockData().getAsString());
                    blockSnapShot.put(blockLocation = new Location(spawnLocation.getWorld(),
                            spawnLocation.getX() - x,
                            spawnLocation.getY() - y,
                            spawnLocation.getZ() + z), blockLocation.getBlock().getBlockData().getAsString());
                    blockSnapShot.put(blockLocation = new Location(spawnLocation.getWorld(),
                            spawnLocation.getX() + x,
                            spawnLocation.getY() + y,
                            spawnLocation.getZ() + z), blockLocation.getBlock().getBlockData().getAsString());
                    blockSnapShot.put(blockLocation = new Location(spawnLocation.getWorld(),
                            spawnLocation.getX() - x,
                            spawnLocation.getY() + y,
                            spawnLocation.getZ() + z), blockLocation.getBlock().getBlockData().getAsString());
                    blockSnapShot.put(blockLocation = new Location(spawnLocation.getWorld(),
                            spawnLocation.getX() + x,
                            spawnLocation.getY() - y,
                            spawnLocation.getZ() + z), blockLocation.getBlock().getBlockData().getAsString());
                    blockSnapShot.put(blockLocation = new Location(spawnLocation.getWorld(),
                            spawnLocation.getX() + x,
                            spawnLocation.getY() + y,
                            spawnLocation.getZ() - z), blockLocation.getBlock().getBlockData().getAsString());
                }
            }
        }
        // Finally we save the retrieved data to a file
   
        // You will most likely want to change the file location to your some other directory,
        // like your plugin's data directory, instead of the Tutorial's.
        new DataHandler(blockSnapShot, previouslyOnlinePlayers).saveData("Tutorial.data");
        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
    }
    public static void welcomePlayersAndResetBlocks() {
        // Load the data from disc using our loadData method.
    	DataHandler data = new DataHandler(DataHandler.loadData("Tutorial.data"));
        // For each player that is current online send them a message
        data.previouslyOnlinePlayers.forEach(playerId -> {
            if (Bukkit.getServer().getPlayer(playerId).isOnline()) {
                Bukkit.getServer().getPlayer(playerId).sendMessage("Thanks for coming back after downtime. Hope you see the spawn blocks change!");
            }
        });
        // Change all of the blocks around the spawn to what we have saved in our file.
        data.blockSnapShot.keySet().forEach(location -> location.getBlock().setBlockData(Bukkit.getServer().createBlockData(data.blockSnapShot.get(location))));
        Bukkit.getServer().getLogger().log(Level.INFO, "Data loaded");
    }
}