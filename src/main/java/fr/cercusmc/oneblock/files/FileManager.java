package fr.cercusmc.oneblock.files;

import fr.cercusmc.oneblock.OneBlock;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class FileManager {

    private File file;
    private FileConfiguration fileConfiguration;

    public FileManager(String name) {
        setUp(name);
    }

    /**
     * FOnction pour setUp un fichier
     * @param fileName : Nom du fichier
     * @return true i le setup s'est bien passé
     */
    private boolean setUp(String fileName) {
        file = new File(OneBlock.getInstance().getDataFolder(), fileName);
        if(!file.exists())
            OneBlock.getInstance().saveResource(fileName, false);

        fileConfiguration = new YamlConfiguration();
        try {

            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
        /*
        if(!file.exists()) {
            // Tentative de création d'un nouveau fichier
            try {
                file.createNewFile();
            } catch (IOException | SecurityException e) {
                e.printStackTrace();
                return false;
            }
        }

        fileConfiguration = new YamlConfiguration();
        try {
            fileConfiguration.load(file);
            //fileConfiguration.options().copyDefaults(true);
            OneBlock.getInstance().saveResource(fileName, false);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }

        reloadFile();
        return true;

            //Tentative de chargment du fichier
        try {
            fileConfiguration.loadConfiguration(file);
        } catch(IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            return false;
        }
        */

    }

    /**
     * Fonction pour recharger un fichier
     * @return true si le chargemtn c'est bien passé
     */
    public boolean reloadFile() {
        try {
            fileConfiguration.load(file);
            return true;
        } catch(IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Fonction pour sauvegarde un fichier
     * @return true si la save est ok
     */
    public boolean save() {
        try {
            fileConfiguration.save(file);
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public File getFile() {
        return file;
    }
}
