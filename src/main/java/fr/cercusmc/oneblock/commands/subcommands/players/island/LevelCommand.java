package fr.cercusmc.oneblock.commands.subcommands.players.island;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.SubCommand;
import fr.cercusmc.oneblock.islands.Island;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class LevelCommand implements SubCommand {
    @Override
    public String getName() {
        return "level";
    }

    @Override
    public String getPermission() {
        return "oneblock.player.level";
    }

    @Override
    public String getDescription() {
        return OneBlock.getMessageFile().getLevelCommandDescription();
    }

    @Override
    public void perform(Player p, String[] args) {
        if(args.length == 1) {
            if(!OneBlock.getIslandManager().playerHasIsland(p.getUniqueId()))
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getNoIsland(), null, null);
            else {
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getWaitingScanningIsland(), null, null);
                Island island = OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId());
                double level = island.computeLevelOfIsland();
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getSuccessScanningIsland(), Arrays.asList("%level%"), Arrays.asList(Double.toString(level)));
            }
        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getTooMuchArgs(), Arrays.asList("%syntax%"), Arrays.asList(getSyntax()));
        }
    }

    @Override
    public String getSyntax() {
        return "&e/ob level";
    }
}
