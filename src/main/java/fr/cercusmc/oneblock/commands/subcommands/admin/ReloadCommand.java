package fr.cercusmc.oneblock.commands.subcommands.admin;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.commands.SubCommand;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ReloadCommand implements SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getPermission() {
        return "oneblock.admin.reload";
    }

    @Override
    public String getDescription() {
        return OneBlock.getMessageFile().getAdminReloadCommandDescription();
    }

    @Override
    public void perform(Player p, String[] args) {
        if(args.length == 2) {
            // Reload config.yml, levels.yml, phases.yml, messages.yml et biomes.yml
            if(reloadConfig(p) && reloadLevelsFile(p) && reloadPhaseFile(p) && reloadMessageFile(p) && reloadBiomeFile(p))
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getSuccessReloadAllFiles(), null, null);
            else
                ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getFailReloadAllFiles(), null, null);

        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getTooMuchArgs(), null, null);
        }
    }

    private boolean reloadConfig(Player p) {
        try {
            OneBlock.getInstance().reloadConfig();
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAdminSuccessReloadFile(), Arrays.asList("%file%"), Arrays.asList("config.yml"));
            return true;
        } catch(Exception e) {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAdminFailReloadFile(), Arrays.asList("%file%"), Arrays.asList("config.yml"));
            return false;
        }
    }

    private boolean reloadLevelsFile(Player p) {
        if(OneBlock.getLevelFile().reloadFile()) {
            OneBlock.setLevels(OneBlock.getLevelFile().getAllLevelsBlocks());
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAdminSuccessReloadFile(), Arrays.asList("%file%"), Arrays.asList("levels.yml"));
            return true;
        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAdminFailReloadFile(), Arrays.asList("%file%"), Arrays.asList("levels.yml"));
            return false;
        }
    }

    private boolean reloadPhaseFile(Player p) {
        if(OneBlock.getPhaseFile().reloadFile()) {
            OneBlock.setPhases(OneBlock.getPhaseFile().getAllPhases());
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAdminSuccessReloadFile(), Arrays.asList("%file%"), Arrays.asList("phases.yml"));
            return true;
        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAdminFailReloadFile(), Arrays.asList("%file%"), Arrays.asList("phases.yml"));
            return false;
        }
    }

    private boolean reloadMessageFile(Player p) {
        if(OneBlock.getMessageFile().reloadFile()) {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAdminSuccessReloadFile(), Arrays.asList("%file%"), Arrays.asList("messages.yml"));
            return true;
        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAdminFailReloadFile(), Arrays.asList("%file%"), Arrays.asList("messages.yml"));
            return false;
        }
    }

    private boolean reloadBiomeFile(Player p) {
        if(OneBlock.getBiomeFile().reloadFile()) {
            OneBlock.setBiomes(OneBlock.getBiomeFile().getAllBiomes());
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAdminSuccessReloadFile(), Arrays.asList("%file%"), Arrays.asList("biomes.yml"));
            return true;
        } else {
            ToolsFunctions.sendMessage(p.getUniqueId(), OneBlock.getMessageFile().getAdminFailReloadFile(), Arrays.asList("%file%"), Arrays.asList("biomes.yml"));
            return false;
        }
    }

    @Override
    public String getSyntax() {
        return "&e/ob admin reload";
    }

}
