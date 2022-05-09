package fr.cercusmc.oneblock.commands.subcommands.players.home;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.SubCommand;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetHomeCommand implements SubCommand {
    @Override
    public String getName() {
        return "sethome";
    }

    @Override
    public String getPermission() {
        return "oneblock.player.sethome";
    }

    @Override
    public String getDescription() {
        return OneBlock.getMessageFile().getSethomeCommandDescription();
    }

    @Override
    public void perform(Player p, String[] args) {
        if(args.length == 1) {
            if(!OneBlock.getIslandManager().playerHasIsland(p.getUniqueId()))
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getNoIsland(), null, null);
            else {
                boolean checkChangeHome = OneBlock.getIslandManager().changeHomeIsland(p.getUniqueId(), p.getLocation());
                if(checkChangeHome) {
                    List<String> locSave = new ArrayList<>();
                    locSave.add(Integer.toString(p.getLocation().getBlockX()));
                    locSave.add(Integer.toString(p.getLocation().getBlockY()));
                    locSave.add(Integer.toString(p.getLocation().getBlockZ()));
                    ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getSuccessSethome(), Arrays.asList("%x%", "%y%", "%z%"), locSave);
                } else {
                    ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getPlayerNotOwner(), null, null);
                }
            }
        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getTooMuchArgs(), Arrays.asList("%syntax%"), Arrays.asList(getSyntax()));
        }

    }

    @Override
    public String getSyntax() {
        return "&e/ob sethome";
    }
}
