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

    public String getPlayerNotOwner() { return messageConfig.getString("islands.player_not_owner"); }

    public String getNotAPlayer() { return messageConfig.getString("islands.not_a_player"); }

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

    public String getSuccessCreateIsland() { return messageConfig.getString("commands.island.success_create_island"); }

    public String getLevelCommandDescription() { return messageConfig.getString("commands.island.level_command_description"); }

    public String getWaitingScanningIsland() { return messageConfig.getString("commands.island.wait_scanning_island"); }

    public String getSuccessScanningIsland() { return messageConfig.getString("commands.island.success_scanning_island"); }

    public String getPhaseCommandDescription() { return messageConfig.getString("commands.island.phase_command_description"); }

    public String getBanCommandDescription() { return messageConfig.getString("commands.moderation.ban_command_description"); }

    public String getSuccessBan() {
        return messageConfig.getString("commands.moderation.success_ban");
    }

    public String getAlreadyBan() {
        return messageConfig.getString("commands.moderation.already_ban");
    }

    public String getAdminCommandDescription() { return messageConfig.getString("commands.administration.admin_command_description"); }

    public String getAdminReloadCommandDescription() { return messageConfig.getString("commands.administration.admin_reload_command_description"); }

    public String getAdminSuccessReloadFile() { return messageConfig.getString("commands.administration.success_admin_reload_file"); }

    public String getAdminFailReloadFile() { return messageConfig.getString("commands.administration.fail_admin_reload_file"); }

    public String getSuccessReloadAllFiles() { return messageConfig.getString("commands.administration.success_reload_all_files"); }

    public String getFailReloadAllFiles() { return messageConfig.getString("commands.administration.fail_reload_all_files"); }

    public String getBiomeCommandDescription() { return messageConfig.getString("commands.tools.biome_command_description"); }

    public String getNameInventoryBiome() { return messageConfig.getString("inventory.name_inventory_biome"); }

    public String getNameInventoryPhase() { return messageConfig.getString("inventory.name_inventory_phases"); }






}
