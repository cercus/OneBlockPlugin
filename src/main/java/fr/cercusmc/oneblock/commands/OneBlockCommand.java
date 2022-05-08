package fr.cercusmc.oneblock.commands;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.subcommands.players.home.DelHomeCommand;
import fr.cercusmc.oneblock.commands.subcommands.players.home.HomeCommand;
import fr.cercusmc.oneblock.commands.subcommands.players.home.SetHomeCommand;
import fr.cercusmc.oneblock.commands.subcommands.players.island.CreateCommand;
import fr.cercusmc.oneblock.commands.subcommands.players.island.LevelCommand;
import fr.cercusmc.oneblock.islands.IslandManager;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class OneBlockCommand implements CommandExecutor, TabCompleter {

    private static ArrayList<SubCommand> subCommands = new ArrayList<>();

    public OneBlockCommand() {

        subCommands.add(new SetHomeCommand());
        subCommands.add(new HomeCommand());
        subCommands.add(new DelHomeCommand());
        subCommands.add(new CreateCommand());
        subCommands.add(new LevelCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length > 0) {
                for(int i = 0; i < subCommands.size(); i++) {
                    if(args[0].equalsIgnoreCase(subCommands.get(i).getName())) {
                        if(p.hasPermission(subCommands.get(i).getPermission()))
                            subCommands.get(i).perform(p, args);
                        else
                            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getNoPermission(), null,null);
                        return true;
                    }
                }
            } else if(args.length == 0) {
                IslandManager im = OneBlock.getIslandManager();
                if(im.playerHasIsland(p.getUniqueId())) {
                    p.teleport(ToolsFunctions.getCenterOfBlock(im.getIslandOfPlayer(p.getUniqueId()).getCenterBlock().add(0, 2, 0)));
                } else {
                    im.createIsland(p.getUniqueId());
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> subCmds = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        for(SubCommand s : subCommands)
            subCmds.add(s.getName());
        if(args.length == 1) {
            list = StringUtil.copyPartialMatches(args[0], subCmds, list);
        }
        return list;
    }

    public static ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
