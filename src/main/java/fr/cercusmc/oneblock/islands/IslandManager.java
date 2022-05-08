package fr.cercusmc.oneblock.islands;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.api.events.IslandCreateEvent;
import fr.cercusmc.oneblock.api.events.IslandDeleteEvent;
import fr.cercusmc.oneblock.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.util.BoundingBox;

import java.util.*;

public class IslandManager {

    private IslandManager islandManager;
    private List<Island> islands;
    private int nbIslandTotal;

    public IslandManager() {
        islandManager = this;
        islands = loadIslands();
        nbIslandTotal = OneBlock.getPlayerFile().getNbIslandTotal();
    }

    /**
     * Fonction pour charger toutes les îles
     * @return La liste des îles avec leurs propriétaires
     */
    private List<Island> loadIslands() {
        return OneBlock.getPlayerFile().getAllIslandsInFile();

    }

    /**
     * Crée une île
     * @param uuid : UUID du propriétaire
     * @return true si la réalisation c'est bien passé
     */
    public boolean createIsland(UUID uuid) {
        if(playerHasIsland(uuid))
            return false;
        int nbIsland = this.islands.size();
        Position next = ToolsFunctions.findNext(OneBlock.getPlayerFile().getNbIslandTotal());
        Location loc = new Location(OneBlock.getOverworld(), next.getX(), next.getY(), next.getZ());

        Location locPlusY1 = loc.clone().add(0, 1, 0);
        Island island = new Island(uuid, locPlusY1, locPlusY1, locPlusY1, loc,
                Arrays.asList(uuid.toString()), Collections.emptyList(), 0, 1, 0,
                OneBlock.getFileConfig().getMinRadius(), Biome.PLAINS);
        //System.out.println("ok1");
        island.computeLevelOfIsland();
        //System.out.println("ok2");

        // Fire event when an island is created
        IslandCreateEvent event = new IslandCreateEvent(island);
        Bukkit.getPluginManager().callEvent(event);
        putBlocks(loc);
        this.islands.add(island);
        OneBlock.getPlayerFile().addIslandInFile(island);
        Bukkit.getPlayer(uuid).teleport(ToolsFunctions.getCenterOfBlock(locPlusY1));
        return true;
    }

    /**
     * Initialise l'île (place un bloc d'herbe ainsi qu'un bloc de bedrock en dessous)
     * @param loc : Location du bloc d'herbe
     */
    private void putBlocks(Location loc) {
        loc.getBlock().setType(Material.GRASS);
        //System.out.println(loc);
        loc.clone().add(0, -1, 0).getBlock().setType(Material.BEDROCK);
    }

    /**
     * Supprimer une île
     * @param uuid : UUID du proprio
     * @return
     */
    public boolean removeIsland(UUID uuid){
        if(!playerHasIsland(uuid) || !isOwner(uuid))
            return false;
        Island is = getIslandOfPlayer(uuid);
        IslandDeleteEvent event = new IslandDeleteEvent(is);
        Bukkit.getPluginManager().callEvent(event);
        removeBlocks(is);
        this.islands.remove(is);
        OneBlock.getPlayerFile().removeIslandInFile(uuid);
        Bukkit.getPlayer(uuid).teleport(OneBlock.getFileConfig().getSpawnLocation());
        return true;
    }

    /**
     * Enlever tout les blocks
     */
    private void removeBlocks(Island is) {
        Location center = is.getCenterBlock();
        // Remove all entities in island
        BoundingBox boundingBox = new BoundingBox(center.getX()-OneBlock.getFileConfig().getMaxRadius(),
                OneBlock.getOverworld().getMaxHeight(),
                center.getZ()-OneBlock.getFileConfig().getMaxRadius(),
                center.getX()+OneBlock.getFileConfig().getMaxRadius(),
                OneBlock.getOverworld().getMinHeight(),
                center.getZ()+OneBlock.getFileConfig().getMaxRadius());
        //for(Entity ent : center.getWorld().getNearbyEntities(boundingBox))
        //    ent.remove();
        //for(Entity ent : center.getWorld().getNearbyEntities(center, Constantes.MAX_RADIUS, OneBlock.getOverworld().getMaxHeight(), Constantes.MAX_RADIUS))
        //    ent.remove();

        // Remove all blocks
        for(int x = center.getBlockX() - OneBlock.getFileConfig().getMaxRadius(); x <= center.getBlockX()+OneBlock.getFileConfig().getMaxRadius(); x++)
            for(int z = center.getBlockX() - OneBlock.getFileConfig().getMaxRadius(); z <= center.getBlockX()+OneBlock.getFileConfig().getMaxRadius(); z++)
                for(int y = OneBlock.getOverworld().getMinHeight(); y <= OneBlock.getOverworld().getMaxHeight(); y++) {
                    Location locTmp = new Location(OneBlock.getOverworld(), x, y, z);
                    locTmp.getBlock().setType(Material.AIR);
                }
    }

    /**
     * Changer le home de l'île
     * @param uuid : UUID du joueur
     * @param home Location du joueur
     */
    public boolean changeHomeIsland(UUID uuid, Location home) {
        Island is = getIslandOfPlayer(uuid);
        if(!is.getOwner().equals(uuid))
            return false;
        is.setHome(home);
        OneBlock.getPlayerFile().updateIslandInFile(is);
        return true;
    }



    /**
     * Verifie si un joueur est propriétaire d'une île
     * @param uuid : UUID du joueur
     * @return true si le joueur est propriétaire
     */
    private boolean isOwner(UUID uuid) {
        for(Island is : this.islands)
            if(is.getOwner().equals(uuid))
                return true;

        return false;
    }


    public boolean changeBiomeOfIsland() {
        return true;
    }

    public Island getIslandOfPlayer(UUID uuid){
        for(Island is : this.islands) {
            if(is.getOwner().equals(uuid) || is.getMembers().contains(uuid.toString()))
                return is;
        }
        return null;
    }

    public boolean playerHasIsland(UUID uuid) {
        for(Island is : this.islands) {
            if(is.playerInIsland(uuid))
                return true;
        }
        return false;
    }

    public boolean playerIsOwner(Island is, UUID uuid) {
        return is.getOwner().equals(uuid);
    }



    public IslandManager getIslandManager() {
        return islandManager;
    }

    public List<Island> getIslands() {
        return islands;
    }
}
