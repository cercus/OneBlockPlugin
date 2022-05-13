package fr.cercusmc.oneblock.commands;


import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.subcommands.admin.ReloadCommand;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.entity.Player;
import java.util.ArrayList;


public class OneBlockAdminCommand implements SubCommand {

    private static ArrayList<SubCommand> subCommands = new ArrayList<>();

    public OneBlockAdminCommand(){
        subCommands.add(new ReloadCommand());
    }

    @Override
    public String getName() {
        return "admin";
    }

    @Override
    public String getPermission() {
        return "oneblock.admin";
    }

    @Override
    public String getDescription() {
        return OneBlock.getMessageFile().getAdminCommandDescription();
    }

    @Override
    public void perform(Player p, String[] args) {
        if(args.length >= 2 && args[0].equalsIgnoreCase("admin")){
            for(int i = 0; i < subCommands.size(); i++) {
                if(args[1].equalsIgnoreCase(subCommands.get(i).getName())) {
                    if(p.hasPermission(subCommands.get(i).getPermission()) || p.isOp())
                        subCommands.get(i).perform(p, args);
                    else
                        ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getNoPermission(), null,null);
                }
            }
        }
    }

    @Override
    public String getSyntax() {
        return "&e/ob admin <args>";
    }

    public static ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
