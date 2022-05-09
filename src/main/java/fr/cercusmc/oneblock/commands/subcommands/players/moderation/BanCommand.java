package fr.cercusmc.oneblock.commands.subcommands.players.moderation;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.SubCommand;
import fr.cercusmc.oneblock.islands.Island;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

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

    @SuppressWarnings("deprecation")
    @Override
    public void perform(Player p, String[] args) {
        if(args.length == 2) {
            if(!OneBlock.getIslandManager().playerHasIsland(p.getUniqueId()))
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getNoIsland(), null, null);
            if(!OneBlock.getIslandManager().playerIsOwner(OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId()), p.getUniqueId()))
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getPlayerNotOwner(), null, null);

            Player target = getTarget(args[1]);
            if(target != null){
                Island is = OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId());
                if(is.isBanned(target.getUniqueId())) {
                    ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAlreadyBan(), null, null);
                } else {
                    is.addBanPlayerInIsland(target.getUniqueId());
                    OneBlock.getIslandManager().getIslands().set(OneBlock.getIslandManager().getPositionIslandInList(is), is);
                    ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getSuccessBan(), Arrays.asList("%target"), Arrays.asList(target.getName()));
                }
                /*
                if(!OneBlock.getIslandManager().getIslandOfPlayer(p.getUniqueId()).playerInIsland(target.getUniqueId()))
                    ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getPlayerNotInIslandOfPlayer(), null, null);
                else {

                }

                 */
            } else {
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getNotAPlayer(), null, null);
            }
        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getTooMuchArgs(), Arrays.asList("%syntax%"), Arrays.asList(getSyntax()));
        }
    }

    private Player getTarget(String name) {
        List<Player> players = Bukkit.getServer().matchPlayer(name);
        for(int index = 0; index < players.size(); index++) {
            if(players.get(index).getName().equals(name)) {
                return players.get(index);
            }
        }
        return null;
    }

    @Override
    public String getSyntax() {
        return "&e/ob ban <player>";
    }
}
