package fr.cercusmc.oneblock;

import fr.cercusmc.oneblock.commands.OneBlockCommand;
import fr.cercusmc.oneblock.files.ConfigFile;
import fr.cercusmc.oneblock.files.LevelFile;
import fr.cercusmc.oneblock.files.MessageFile;
import fr.cercusmc.oneblock.files.PlayerFile;
import fr.cercusmc.oneblock.islands.IslandManager;
import fr.cercusmc.oneblock.world.OneBlockGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.HashMap;

public class OneBlock extends JavaPlugin {

    private static OneBlock instance;
    private static PlayerFile playerFile;
    private static LevelFile levelFile;
    private static HashMap<Material, Double> levels;
    private static IslandManager islandManager;
    private static ConfigFile fileConfig;
    private static MessageFile messageFile;

    private static World overworld;
    private String ONEBLOCK_OVERWORLD = "OneBlock_Overworld";

    public OneBlock() {
        super();
    }

    protected OneBlock(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }


    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        makeOverworld();
        fileConfig = new ConfigFile();
        messageFile = new MessageFile();
        playerFile = new PlayerFile();
        levelFile = new LevelFile();
        levels = levelFile.getAllLevelsBlocks();
        islandManager = new IslandManager();

        getCommand("oneblock").setExecutor(new OneBlockCommand());

    }


    public WorldCreator makeOverworld() {
        WorldCreator wc = new WorldCreator(ONEBLOCK_OVERWORLD);
        wc.generateStructures(false);
        wc.environment(World.Environment.NORMAL);
        wc.generator(new OneBlockGenerator());
        if(Bukkit.getWorld(ONEBLOCK_OVERWORLD) == null) {
            Bukkit.getServer().createWorld(wc);
        }
        overworld = Bukkit.getWorld(ONEBLOCK_OVERWORLD);
        return wc;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static OneBlock getInstance() {
        return instance;
    }

    public static PlayerFile getPlayerFile() {
        return playerFile;
    }

    public static World getOverworld() {
        return overworld;
    }

    public static LevelFile getLevelFile() {
        return levelFile;
    }

    public static HashMap<Material, Double> getLevels() {
        return levels;
    }

    public static void setLevels(HashMap<Material, Double> levels) {
        OneBlock.levels = levels;
    }

    public static IslandManager getIslandManager() {
        return islandManager;
    }

    public static ConfigFile getFileConfig() {
        return fileConfig;
    }

    public static MessageFile getMessageFile() {
        return messageFile;
    }
}
