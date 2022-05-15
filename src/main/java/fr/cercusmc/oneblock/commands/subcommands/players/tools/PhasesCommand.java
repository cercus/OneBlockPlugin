package fr.cercusmc.oneblock.commands.subcommands.players.tools;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.SubCommand;
import fr.cercusmc.oneblock.phases.Phase;
import fr.cercusmc.oneblock.utils.InventoryBuilder;
import fr.cercusmc.oneblock.utils.ItemBuilder;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class PhasesCommand implements SubCommand {

    @Override
    public String getName() {
        return "phase";
    }

    @Override
    public String getPermission() {
        return "oneblock.player.phase";
    }

    @Override
    public String getDescription() {
        return OneBlock.getMessageFile().getPhaseCommandDescription();
    }

    @Override
    public void perform(Player p, String[] args) {
        if(args.length != 1) {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getTooMuchArgs(), Arrays.asList("%syntax%"), Arrays.asList(getSyntax()));
            return;
        }
        if(!(OneBlock.getIslandManager().playerHasIsland(p.getUniqueId()))) {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getNoIsland(), null, null);
            return;
        }
        InventoryBuilder inv = new InventoryBuilder(9*6, OneBlock.getMessageFile().getNameInventoryPhase());
        int count = 0;
        for(Phase phase : OneBlock.getPhases()) {
            ItemBuilder itTmp = buildItem(phase, p);
            inv.addContent(itTmp, count);
            count++;
        }
        inv.openInventory(p);

    }

    @Override
    public String getSyntax() {
        return "&e/ob phases";
    }

    private ItemBuilder buildItem(Phase phase, Player p) {
        ItemBuilder itTmp = new ItemBuilder(phase.getIcon(), 1);
        if(OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId()).getPhase() == phase.getId()) {
            itTmp.addEnchant(Enchantment.DAMAGE_ALL, 1).hideAttributesEnchantment(true);
        }
        itTmp.setDisplayName(phase.getName()).setLore(phase.getDescription());
        return itTmp;
    }
}
