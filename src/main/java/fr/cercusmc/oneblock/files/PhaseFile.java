package fr.cercusmc.oneblock.files;

import fr.cercusmc.oneblock.phases.Phase;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;

public class PhaseFile extends FileManager {

    private FileConfiguration phaseConfig;
    public PhaseFile() {
        super("phases.yml");
        this.phaseConfig = super.getFileConfiguration();
    }

    public FileConfiguration getPhaseConfig() {
        return phaseConfig;
    }

    /**
     * Obtenir toutes les phases
     * @return Liste de toutes les phases
     */
    public ArrayList<Phase> getAllPhases() {
        ArrayList<Phase> phases = new ArrayList<>();
        for(String i : phaseConfig.getConfigurationSection("").getKeys(false)){
            ConfigurationSection section = phaseConfig.getConfigurationSection(i);
            if(!ToolsFunctions.checkEnum(Material.class, section.getString("icon")))
                continue;
            HashMap<Material, Integer> blocks = getAllBlockForOnePhase(section);
            HashMap<EntityType, Integer> entities = getAllEntityForOnePhase(section);
            ArrayList<Material> items = getAllItemsForOnePhase(section);
            Phase phase = new Phase(Integer.parseInt(i), ToolsFunctions.format(section.getString("name")), ToolsFunctions.format(section.getString("description")), Material.valueOf(section.getString("icon")), section.getInt("block_to_reach"), blocks, entities, items);
            phases.add(phase);
        }
        return phases;
    }

    private HashMap<Material, Integer> getAllBlockForOnePhase(ConfigurationSection section){
        HashMap<Material, Integer> blocks = new HashMap<>();
        for(String j : section.getStringList("blocs")) {
            String[] tmp = j.split(" ");
            if(!ToolsFunctions.checkEnum(Material.class, tmp[0].toUpperCase()))
                continue;
            else
                blocks.put(Material.valueOf(tmp[0]), Integer.parseInt(tmp[1]));
        }
        return blocks;
    }

    private HashMap<EntityType, Integer> getAllEntityForOnePhase(ConfigurationSection section){
        HashMap<EntityType, Integer> entities = new HashMap<>();
        for(String j : section.getStringList("mobs")) {
            String[] tmp = j.split(" ");
            if(!ToolsFunctions.checkEnum(EntityType.class, tmp[0].toUpperCase()))
                continue;
            else
                entities.put(EntityType.valueOf(tmp[0]), Integer.parseInt(tmp[1]));
        }
        return entities;
    }

    private ArrayList<Material> getAllItemsForOnePhase(ConfigurationSection section) {
        ArrayList<Material> items = new ArrayList<>();
        for(String j : section.getStringList("items")) {
            if(!ToolsFunctions.checkEnum(Material.class, j.toUpperCase()))
                continue;
            else
            items.add(Material.valueOf(j.toUpperCase()));
        }
        return items;
    }
}
