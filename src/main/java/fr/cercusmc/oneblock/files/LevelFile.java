package fr.cercusmc.oneblock.files;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class LevelFile extends FileManager  {

    private FileConfiguration levelConfig;

    public LevelFile() {



        super("levels.yml");
        levelConfig = super.getFileConfiguration();
    }

    /**
     * Obtenir tout les levels de chaque bloc présent dans le level.yml
     * @return Liste de tout les blocs dans le level.yml associé a leurs valeurs
     */
    public HashMap<Material, Double> getAllLevelsBlocks() {
        HashMap<Material, Double> res = new HashMap<>();
        for(String i : levelConfig.getConfigurationSection("").getKeys(false)) {
            if(ToolsFunctions.checkEnum(Material.class, i)) {
                res.put(Material.valueOf(i), levelConfig.getDouble(i));
            }
        }
        return res;
    }

    /**
     * Obtneir le level d'un bloc en particulier
     * @param blocksMap : Liste de tout les blocks associé a leurs levels
     * @param mat : Le material a obtneir son level
     * @return la valeur du block
     */
    public double getValueFromBlock(HashMap<Material, Double> blocksMap, Material mat) {
        if(!ToolsFunctions.checkEnum(Material.class, mat.name())) return 0;
        if(blocksMap.get(Material.valueOf(mat.name())) == null)
            return 0;
        return blocksMap.get(Material.valueOf(mat.name()));
    }
}
