package fr.cercusmc.oneblock.files;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class ConfigFile {

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

    public String getTitleOfBar() { return ToolsFunctions.format(OneBlock.getInstance().getConfig().getString("bossbar.title")); }

    public BarColor getColorOfBar() {
        String color = OneBlock.getInstance().getConfig().getString("bossbar.color");
        if(ToolsFunctions.checkEnum(BarColor.class, color.toUpperCase()))
            return BarColor.valueOf(color.toUpperCase());
        else
            return BarColor.GREEN;
    }

    public BarStyle getStyleOfBar() {
        String style = OneBlock.getInstance().getConfig().getString("bossbar.style");
        if(ToolsFunctions.checkEnum(BarStyle.class, style.toUpperCase()))
            return BarStyle.valueOf(style.toUpperCase());
        else
            return BarStyle.SOLID;
    }

    public BarFlag getFlagOfBar() {
        String flag = OneBlock.getInstance().getConfig().getString("bossbar.flag");
        if(ToolsFunctions.checkEnum(BarFlag.class, flag.toUpperCase()))
            return BarFlag.valueOf(flag.toUpperCase());
        else
            return null;
    }
}
