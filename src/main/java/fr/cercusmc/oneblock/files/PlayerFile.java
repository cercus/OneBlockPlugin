package fr.cercusmc.oneblock.files;

import fr.cercusmc.oneblock.islands.Island;
import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerFile extends FileManager {

    private FileConfiguration playerConfig;

    public PlayerFile() {
        super("players.yml");

        playerConfig = super.getFileConfiguration();
    }

    public void addIslandInFile(Island island) {
        if(playerConfig.getConfigurationSection("players."+island.getOwner().toString()) != null)
            removeIslandInFile(island.getOwner());

        ConfigurationSection section = playerConfig.createSection("players."+island.getOwner().toString());
        section.set("spawn", island.getSpawn());
        section.set("home", island.getHome());
        section.set("warp", island.getWarp());
        section.set("center_block", island.getCenterBlock());
        section.set("membres", island.getMembers());
        section.set("bans", island.getBans());
        section.set("level", island.getLevel());
        section.set("phase", island.getPhase());
        section.set("nb_blocks", island.getNbBlocks());
        section.set("radius", island.getRadius());
        section.set("biome", island.getBiome().name());
        super.save();
    }

    /**
     * Enlever une île du fichier
     * @param uuid : UUID du propriétaire
     * @return true si l'île a bien été enlever
     */
    public boolean removeIslandInFile(UUID uuid) {
        if(playerConfig.getConfigurationSection("players."+uuid.toString()) != null) {
            playerConfig.set("players."+uuid.toString(), null);
            super.save();
            return true;
        }
        return false;
    }

    /**
     * Fonction pour obtenir toutes les îles dans une liste
     * @return Liste de toutes les îles
     */
    public List<Island> getAllIslandsInFile(){
        List<Island> res = new ArrayList<>();
        if(playerConfig.getConfigurationSection("players") == null || playerConfig.getConfigurationSection("players").getKeys(false).isEmpty())
            return res;
        for(String i : playerConfig.getConfigurationSection("players").getKeys(false)){
            ConfigurationSection section = playerConfig.getConfigurationSection("players."+i);
            res.add(new Island(UUID.fromString(i),
                    section.getLocation("spawn"),
                    section.getLocation("warp"),
                    section.getLocation("home"),
                    section.getLocation("center_block"),
                    new ArrayList<String>(section.getStringList("membres")),
                    new ArrayList<String>(section.getStringList("bans")),
                    section.getDouble("level"),
                    section.getInt("phase"),
                    section.getInt("nb_blocks"),
                    section.getInt("radius"),
                    Biome.valueOf(section.getString("biome"))));
        }
        return res;
    }

    public int getNbIslandTotal() {
        return playerConfig.getInt("nbIsland");
    }

    /**
     * Obtenir l'île d'un joueur
     * @param uuid : UUID de l'owner
     * @return l'île du joueur ou null
     */
    public Island getIslandByUuidOwner(UUID uuid) {
        for(Island i : getAllIslandsInFile())
            if(i.getOwner().equals(uuid))
                return i;
        return null;
    }

    /**
     * Update une île en particulier
     * @param island : île a update
     */
    public void updateIslandInFile(Island island){
        List<Island> islands = getAllIslandsInFile();
        for(Island i : islands) {
            if(island.getOwner().equals(i.getOwner())) {
                addIslandInFile(island);
                break;
            }
        }
    }
}
