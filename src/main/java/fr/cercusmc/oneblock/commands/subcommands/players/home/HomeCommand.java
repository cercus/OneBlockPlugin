package fr.cercusmc.oneblock.commands.subcommands.players.home;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.SubCommand;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class HomeCommand implements SubCommand {

    @Override
    public String getName() {
        return "home";
    }

    @Override
    public String getPermission() {
        return "oneblock.player.home";
    }

    @Override
    public String getDescription() {
        return OneBlock.getMessageFile().getHomeCommandDescription();
    }

    @Override
    public void perform(Player p, String[] args) {
        if(args.length == 1) {
            if(!OneBlock.getIslandManager().playerHasIsland(p.getUniqueId()))
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getNoIsland(), null, null);
            else {
                p.teleport(OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId()).getHome());
                //OneBlock.getIslandManager().changeHomeIsland(p.getUniqueId(), p.getLocation());
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getSuccessHome(), null, null);
            }
        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getTooMuchArgs(), Arrays.asList("%syntax%"), Arrays.asList(getSyntax()));
        }

    }

    @Override
    public String getSyntax() {
        return "&e/ob home";
    }
}
