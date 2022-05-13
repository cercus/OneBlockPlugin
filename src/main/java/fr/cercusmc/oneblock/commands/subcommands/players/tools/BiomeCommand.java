package fr.cercusmc.oneblock.commands.subcommands.players.tools;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.SubCommand;
import fr.cercusmc.oneblock.utils.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class BiomeCommand implements SubCommand {

    @Override
    public String getName() {
        return "biome";
    }

    @Override
    public String getPermission() {
        return "oneblock.player.biome";
    }

    @Override
    public String getDescription() {
        return OneBlock.getMessageFile().getBiomeCommandDescription();
    }

    @Override
    public void perform(Player p, String[] args) {
        if(!(OneBlock.getIslandManager().playerHasIsland(p.getUniqueId()))) {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getNoIsland(), null, null);
            return;
        }
        if(!OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId()).isOwner(p.getUniqueId())){
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getPlayerNotOwner(), null, null);
            return;
        }
        if(args.length == 1) {
            InventoryBuilder inventoryBuilder = new InventoryBuilder(9*6, OneBlock.getMessageFile().getNameInventoryBiome());
            int count = 0;
            for(Biome b : OneBlock.getBiomes()) {
                if(b.isPermission()) {
                    if (p.hasPermission("oneblock.biome." + b.getBiome().name().toLowerCase())) {
                        ItemBuilder itTmp = buildItem(b, p);
                        inventoryBuilder.addContent(itTmp, count);
                        count++;
                    }
                } else {
                    ItemBuilder itTmp = buildItem(b, p);
                    inventoryBuilder.addContent(itTmp, count);
                    count++;
                }
            }
            inventoryBuilder.openInventory(p);
        }
    }

    private ItemBuilder buildItem(Biome b, Player p) {
        ItemBuilder itTmp = new ItemBuilder(b.getIcon(), 1);
        if (OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId()).getBiome().equals(b.getBiome())) {
            itTmp.addEnchant(Enchantment.DAMAGE_ALL, 1).hideAttributesEnchantment(true);
        }
        itTmp.setDisplayName(b.getName()).setLore(b.getDescription());
        itTmp.setLore(b.getDescription());
        return itTmp;
    }

    @Override
    public String getSyntax() {
        return "&e/ob biome";
    }
}
