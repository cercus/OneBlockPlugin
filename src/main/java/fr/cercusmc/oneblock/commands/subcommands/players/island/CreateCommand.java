package fr.cercusmc.oneblock.commands.subcommands.players.island;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.SubCommand;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.entity.Player;

public class CreateCommand implements SubCommand {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getPermission() {
        return "oneblock.player.create";
    }

    @Override
    public String getDescription() {
        return OneBlock.getMessageFile().getCreateCommandDescription();
    }

    @Override
    public void perform(Player p, String[] args) {
        if(args.length == 1) {
            if(OneBlock.getIslandManager().playerHasIsland(p.getUniqueId()))
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAlreadyIsland(), null, null);
            else {
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getWaitingCreateIsland(), null, null);
                OneBlock.getIslandManager().createIsland(p.getUniqueId());
                p.teleport(OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId()).getCenterBlock());
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getSuccessCreateIsland(), null, null);

            }
        }
    }

    @Override
    public String getSyntax() {
        return "&e/ob create";
    }
}
