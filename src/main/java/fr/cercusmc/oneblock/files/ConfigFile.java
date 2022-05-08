package fr.cercusmc.oneblock.files;

import fr.cercusmc.oneblock.OneBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigFile {

    public ConfigFile() {

    }

    /**
     * Obtenir le rayon max d'île
     * @return le rayon max d'île
     */
    public int getMaxRadius() {
        return OneBlock.getInstance().getConfig().getInt("islands.max_radius");
    }

    /**
     * Obtenir le rayon min d'île
     * @return le rayon min d'île
     */
    public int getMinRadius() {
        return OneBlock.getInstance().getConfig().getInt("islands.min_radius");
    }

    /**
     * Obtenir la location du spawn
     * @return le lieu du spawn
     */
    public Location getSpawnLocation() {
        FileConfiguration config = OneBlock.getInstance().getConfig();
        Location loc = new Location(Bukkit.getWorld(config.getString("spawn.world")), config.getDouble("spawn.x"), config.getDouble("spawn.y"), config.getDouble("spawn.z"));
        return loc;
    }
}
