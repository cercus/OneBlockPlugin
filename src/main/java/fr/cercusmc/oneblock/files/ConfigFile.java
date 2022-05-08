package fr.cercusmc.oneblock.files;

import fr.cercusmc.oneblock.OneBlock;

public class ConfigFile {

    public ConfigFile() {

    }

    /**
     * Obtenir le rayon max d'île
     * @return le rayon max d'île
     */
    public int getMaxRadius() {
        return OneBlock.getInstance().getConfig().getInt("islands.max_radius");
    }

    /**
     * Obtenir le rayon min d'île
     * @return le rayon min d'île
     */
    public int getMinRadius() {
        return OneBlock.getInstance().getConfig().getInt("islands.min_radius");
    }
}
