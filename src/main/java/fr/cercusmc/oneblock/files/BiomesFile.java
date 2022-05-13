package fr.cercusmc.oneblock.files;

import fr.cercusmc.oneblock.utils.Biome;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class BiomesFile extends FileManager {

    private FileConfiguration biomeConfig;


    public BiomesFile() {
        super("biomes.yml");

        biomeConfig = super.getFileConfiguration();
    }

    public ArrayList<Biome> getAllBiomes() {
        ArrayList<Biome> res = new ArrayList<>();
        for(String i : biomeConfig.getConfigurationSection("biomes").getKeys(false)) {
            ConfigurationSection section = biomeConfig.getConfigurationSection("biomes."+i);
            if(!ToolsFunctions.checkEnum(org.bukkit.block.Biome.class, i) ||
                    !ToolsFunctions.checkEnum(Material.class, section.getString("icon")))
                continue;
            Biome b = new Biome(org.bukkit.block.Biome.valueOf(i.toUpperCase()),
                    ToolsFunctions.format(section.getString("name")),
                    Material.valueOf(section.getString("icon").toUpperCase()),
                    ToolsFunctions.format(new ArrayList<>(section.getStringList("description"))),
                    section.getBoolean("permission"));
            res.add(b);
        }
        return res;
    }

    public FileConfiguration getBiomeConfig() {
        return biomeConfig;
    }
}
