package fr.cercusmc.oneblock.commands.subcommands.players.moderation;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.SubCommand;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BanCommand implements SubCommand {
    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public String getPermission() {
        return "oneblock.player.ban";
    }

    @Override
    public String getDescription() {
        return OneBlock.getMessageFile().getBanCommandDescription();
    }

    @Override
    public void perform(Player p, String[] args) {
        if(args.length == 2) {
            if(!OneBlock.getIslandManager().playerHasIsland(p.getUniqueId()))
                return;
            if(!OneBlock.getIslandManager().playerIsOwner(OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId()), p.getUniqueId()))
                return;
        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getTooMuchArgs(), Arrays.asList("%syntax%"), Arrays.asList(getSyntax()));
        }
    }

    @Override
    public String getSyntax() {
        return "&e/ob ban <player>";
    }
}
