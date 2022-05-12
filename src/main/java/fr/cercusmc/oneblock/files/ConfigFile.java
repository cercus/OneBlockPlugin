package fr.cercusmc.oneblock.files;

import fr.cercusmc.oneblock.OneBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

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

    public int getNbItemMinChest() { return OneBlock.getInstance().getConfig().getInt("random_block_chest.nb_item_min_chest"); }

    public int getNbItemMaxChest() { return OneBlock.getInstance().getConfig().getInt("random_block_chest.nb_item_max_chest"); }

    public int getQuantityMinForOneItem() { return OneBlock.getInstance().getConfig().getInt("random_block_chest.quantity_min_for_one_item"); }

    public int getQuantityMaxForOneItem() { return OneBlock.getInstance().getConfig().getInt("random_block_chest.quantity_max_for_one_item"); }

    public ArrayList<String> getItemSingleInChest() { return new ArrayList<>(OneBlock.getInstance().getConfig().getStringList("random_block_chest.item_single_in_chest")); }
}
