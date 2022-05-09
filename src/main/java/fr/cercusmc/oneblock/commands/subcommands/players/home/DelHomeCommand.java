package fr.cercusmc.oneblock.commands.subcommands.players.home;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.SubCommand;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class DelHomeCommand implements SubCommand {

    @Override
    public String getName() {
        return "delhome";
    }

    @Override
    public String getPermission() {
        return "oneblock.player.delhome";
    }

    @Override
    public String getDescription() {
        return OneBlock.getMessageFile().getDelhomeCommandDescription();
    }

    @Override
    public void perform(Player p, String[] args) {
        if(args.length == 1) {
            if(!OneBlock.getIslandManager().playerHasIsland(p.getUniqueId()))
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getNoIsland(), null, null);
            else {

                boolean checkDelHome = OneBlock.getIslandManager().changeHomeIsland(p.getUniqueId(), OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId()).getCenterBlock());
                if(checkDelHome)
                    ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getSuccessDelhome(), null, null);
                else
                    ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getPlayerNotOwner(), null, null);
            }
        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getTooMuchArgs(), Arrays.asList("%syntax%"), Arrays.asList(getSyntax()));
        }

    }

    @Override
    public String getSyntax() {
        return "&e/ob delhome";
    }
}
