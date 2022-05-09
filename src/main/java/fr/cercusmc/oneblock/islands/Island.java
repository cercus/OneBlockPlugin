package fr.cercusmc.oneblock.islands;

import fr.cercusmc.oneblock.OneBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.List;
import java.util.UUID;

public class Island {

    private UUID owner;
    private Location spawn;
    private Location warp;
    private Location home;
    private Location centerBlock;
    private List<String> members;
    private List<String> bans;
    private double level;
    private int phase;
    private int nbBlocks;
    private int radius;
    private Biome biome;

    public Island(UUID owner, Location spawn, Location warp, Location home, Location centerBlock, List<String> members,
                  List<String> bans, double level, int phase, int nbBlocks, int radius, Biome biome) {
        this.owner = owner;
        this.spawn = spawn;
        this.warp = warp;
        this.home = home;
        this.centerBlock = centerBlock;
        this.members = members;
        this.bans = bans;
        this.level = level;
        this.phase = phase;
        this.nbBlocks = nbBlocks;
        this.radius = radius;
        this.biome = biome;
    }

    /**
     * Fonction pour vérifier si un joueur se trouve bien dans son île
     * @param loc : Location du joueur
     * @return true si le joueur est sur son île
     */
    public boolean isAt(Location loc) {
        if(loc == null)
            return false;
        return loc.distance(this.centerBlock) <= this.radius;
    }

    /**
     * Enlever un membre de l'île
     * @param uuid : UUID du joueur a enlever
     * @return true si le joueur est bien enlevé
     */
    public boolean removePlayerInIsland(UUID uuid) {
        if(this.owner.equals(uuid) || !this.members.contains(uuid))
            return false;
        this.members.remove(uuid.toString());
        OneBlock.getPlayerFile().updateIslandInFile(this);
        return true;
    }

    /**
     * Ajoute un joueur à l'île
     * @param uuid : UUID du joueur a enlever
     * @return true si le joueur est bien ajouté
     */
    public boolean addPlayerInIsland(UUID uuid){
        if(playerInIsland(uuid))
            return false;
        this.members.add(uuid.toString());
        OneBlock.getPlayerFile().updateIslandInFile(this);
        return true;
    }

    public boolean playerInIsland(UUID uuid) {
        return this.owner.equals(uuid) || this.members.contains(uuid.toString());
    }

    public boolean addBanPlayerInIsland(UUID target) {
        if(isBanned(target))
            return false;
        this.bans.add(target.toString());
        OneBlock.getPlayerFile().updateIslandInFile(this);
        return true;
    }

    public boolean isBanned(UUID uuid) {
        return this.bans.contains(uuid.toString());
    }

    /**
     * Calcule le niveau de l'île
     * @return Le niveau de l'île
     */
    public double computeLevelOfIsland() {
        double level = 0;
        for(int x = this.getCenterBlock().getBlockX() - OneBlock.getFileConfig().getMaxRadius(); x <= this.getCenterBlock().getBlockX() + OneBlock.getFileConfig().getMaxRadius(); x++) {
            for (int z = this.getCenterBlock().getBlockZ() - OneBlock.getFileConfig().getMaxRadius(); z <= this.getCenterBlock().getBlockZ() + OneBlock.getFileConfig().getMaxRadius(); z++) {
                for (int y = OneBlock.getOverworld().getMinHeight(); y < OneBlock.getOverworld().getMaxHeight(); y++) {
                    Location locTmp = new Location(OneBlock.getOverworld(), x, y, z);
                    if (locTmp.getBlock().getType() != null && !locTmp.getBlock().getType().equals(Material.AIR))
                        level += OneBlock.getLevelFile().getValueFromBlock(OneBlock.getLevels(), locTmp.getBlock().getType());

                }
            }
        }
        this.level = Math.round(level*100)/100;
        OneBlock.getPlayerFile().updateIslandInFile(this);
        return this.level;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public void setWarp(Location warp) {
        this.warp = warp;
    }

    public void setHome(Location home) {
        this.home = home;
    }

    public void setCenterBlock(Location centerBlock) {
        this.centerBlock = centerBlock;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public void setBans(List<String> bans) {
        this.bans = bans;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public void setNbBlocks(int nbBlocks) {
        this.nbBlocks = nbBlocks;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public UUID getOwner() {
        return owner;
    }

    public Location getSpawn() {
        return spawn;
    }

    public Location getWarp() {
        return warp;
    }

    public Location getHome() {
        return home;
    }

    public Location getCenterBlock() {
        return centerBlock;
    }

    public List<String> getMembers() {
        return members;
    }

    public List<String> getBans() {
        return bans;
    }

    public int getPhase() {
        return phase;
    }

    public int getNbBlocks() {
        return nbBlocks;
    }

    public int getRadius() {
        return radius;
    }

    public Biome getBiome() {
        return biome;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }
}
