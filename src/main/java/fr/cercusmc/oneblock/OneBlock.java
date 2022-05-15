package fr.cercusmc.oneblock;

import fr.cercusmc.oneblock.commands.OneBlockCommand;
import fr.cercusmc.oneblock.events.BreakListener;
import fr.cercusmc.oneblock.files.*;
import fr.cercusmc.oneblock.islands.IslandManager;
import fr.cercusmc.oneblock.phases.Phase;
import fr.cercusmc.oneblock.utils.BossBarBuilder;
import fr.cercusmc.oneblock.utils.object.Biome;
import fr.cercusmc.oneblock.world.OneBlockGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class OneBlock extends JavaPlugin {

    private static OneBlock instance;
    private static PlayerFile playerFile;
    private static LevelFile levelFile;
    private static HashMap<Material, Double> levels;
    private static IslandManager islandManager;
    private static ConfigFile fileConfig;
    private static MessageFile messageFile;
    private static PhaseFile phaseFile;
    private static ArrayList<Phase> phases;
    private static ArrayList<Biome> biomes;
    private static BiomesFile biomeFile;

    private static HashMap<UUID, BossBarBuilder> bossbars = new HashMap<>();


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
        Bukkit.getPluginManager().registerEvents(new BreakListener(), this);
        saveDefaultConfig();

        makeOverworld();
        fileConfig = new ConfigFile();
        messageFile = new MessageFile();
        playerFile = new PlayerFile();
        levelFile = new LevelFile();
        levels = levelFile.getAllLevelsBlocks();
        islandManager = new IslandManager();
        phaseFile = new PhaseFile();
        phases = phaseFile.getAllPhases();
        biomeFile = new BiomesFile();
        biomes = biomeFile.getAllBiomes();

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

    public static ArrayList<Phase> getPhases() {
        return phases;
    }

    public static void setPhases(ArrayList<Phase> phases) {
        OneBlock.phases = phases;
    }

    public static PhaseFile getPhaseFile() {
        return phaseFile;
    }

    public static ArrayList<Biome> getBiomes() {
        return biomes;
    }

    public static BiomesFile getBiomeFile() {
        return biomeFile;
    }

    public static void setBiomes(ArrayList<Biome> biomes) {
        OneBlock.biomes = biomes;
    }

    public static HashMap<UUID, BossBarBuilder> getBossbars() {
        return bossbars;
    }
}
