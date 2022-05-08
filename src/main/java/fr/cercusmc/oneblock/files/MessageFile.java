package fr.cercusmc.oneblock.files;

import org.bukkit.configuration.file.FileConfiguration;

public class MessageFile extends FileManager {

    private FileConfiguration messageConfig;

    public MessageFile() {
        super("messages.yml");
        this.messageConfig = super.getFileConfiguration();
    }

    public FileConfiguration getMessageConfig() {
        return messageConfig;
    }

    public String getNoIsland() { return messageConfig.getString("islands.no_island"); }

    public String getAlreadyIsland() { return messageConfig.getString("islands.player_has_island"); }

    public String getPlayerNotInIslandOfPlayer() { return messageConfig.getString("islands.player_not_in_island_of_player"); }

    public String getNoPermission() {
        return messageConfig.getString("commands.no_permission");
    }

    public String getTooMuchArgs() {
        return messageConfig.getString("commands.to_much_args");
    }

    public String getTooLessArgs() {
        return messageConfig.getString("commands.to_less_args");
    }

    public String getSethomeCommandDescription() { return messageConfig.getString("commands.home.sethome_command_description"); }

    public String getSuccessSethome() {
        return messageConfig.getString("commands.home.success_sethome");
    }

    public String getDelhomeCommandDescription() { return messageConfig.getString("commands.home.delhome_command_description"); }

    public String getSuccessDelhome() {
        return messageConfig.getString("commands.home.success_delhome");
    }

    public String getHomeCommandDescription() { return messageConfig.getString("commands.home.home_command_description"); }

    public String getSuccessHome() {
        return messageConfig.getString("commands.home.success_home");
    }

    public String getCreateCommandDescription() { return messageConfig.getString("commands.island.create_command_description"); }

    public String getWaitingCreateIsland() { return messageConfig.getString("commands.island.wait_creating_island"); }

    public String getSuccessCreateIsland() { return messageConfig.getString("commands.island.success_creating_island"); }

    public String getLevelCommandDescription() { return messageConfig.getString("commands.island.level_command_description"); }

    public String getWaitingScanningIsland() { return messageConfig.getString("commands.island.wait_scanning_island"); }

    public String getSuccessScanningIsland() { return messageConfig.getString("commands.island.success_scanning_island"); }

    public String getBanCommandDescription() { return messageConfig.getString("commands.moderation.ban_command_description"); }

    public String getSuccessBan() {
        return messageConfig.getString("commands.moderation.success_ban");
    }


}
