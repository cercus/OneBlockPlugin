package fr.cercusmc.oneblock.events;


import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.islands.Island;
import fr.cercusmc.oneblock.phases.PhaseManager;
import fr.cercusmc.oneblock.utils.BossBarBuilder;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class BreakListener implements Listener {

    private ArrayList<Material> blocNot16px;

    public BreakListener(){
        this.blocNot16px = new ArrayList<>();
        this.blocNot16px.add(Material.DIRT_PATH);
        this.blocNot16px.add(Material.SOUL_SAND);
        this.blocNot16px.add(Material.CHEST);
        this.blocNot16px.add(Material.TRAPPED_CHEST);
        this.blocNot16px.add(Material.ENDER_CHEST);
        this.blocNot16px.add(Material.BREWING_STAND);
        this.blocNot16px.add(Material.ENCHANTING_TABLE);

    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        PhaseManager pManager = new PhaseManager();
        if(e.getBlock().getLocation().equals(OneBlock.getIslandManager().getIslandOfPlayer(e.getPlayer().getUniqueId()).getCenterBlock())) {

            treatmentContainer(e);

            e.getBlock().getDrops().forEach(k -> e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation().clone().add(0, 1, 0), k));
            e.setCancelled(true);

            teleportPlayerIfInBlock(e);

            updateBossBarAndIsland(e);

            pManager.mysteriousBlock(e.getBlock().getLocation(), OneBlock.getIslandManager().getIslandOfPlayer(e.getPlayer().getUniqueId()).getPhase(), e.getPlayer());
        }
    }

    private void treatmentContainer(BlockBreakEvent e) {
        if(e.getBlock().getState() instanceof Container) {
            ItemStack[] its = ((Container) e.getBlock().getState()).getInventory().getContents();
            for (ItemStack i : its)
                if (i != null)
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation().clone().add(0, 1, 0), i);

        }
    }

    private void updateBossBarAndIsland(BlockBreakEvent e) {
        Island is = OneBlock.getIslandManager().getIslandOfPlayer(e.getPlayer().getUniqueId());
        is.setNbBlocks(is.getNbBlocks()+1);
        OneBlock.getPlayerFile().updateIslandInFile(is);
        for(String i : is.getMembers()) {
            if(OneBlock.getBossbars().containsKey(UUID.fromString(i))) {
                BossBarBuilder bar = OneBlock.getBossbars().get(UUID.fromString(i));
                bar.setProgress(ToolsFunctions.computeProgress(UUID.fromString(i), is.getPhase(), is.getNbBlocks()));
                OneBlock.getBossbars().replace(UUID.fromString(i), bar);
            }
        }
    }

    private void teleportPlayerIfInBlock(BlockBreakEvent e) {
        if(blocNot16px.contains(e.getBlock().getType())) {
            if (ToolsFunctions.playerInBlock(e.getPlayer(), e.getBlock())) {
                Location newLoc = ToolsFunctions.getCenterOfBlock(e.getBlock().getLocation().clone().add(0, 1, 0));
                newLoc.setPitch(e.getPlayer().getLocation().getPitch());
                newLoc.setYaw(e.getPlayer().getLocation().getYaw());
                e.getPlayer().teleport(newLoc);
            }
        }
    }

}
