package fr.cercusmc.oneblock.commands;

import org.bukkit.entity.Player;

public interface SubCommand {

    /**
     * Nom de la commande
     * @return le nom de la commande
     */
    String getName();

    /**
     * Permission associé a la commande
     * @return La permission de la commande
     */
    String getPermission();

    /**
     * Description de la commande
     * @return La description de la commande
     */
    String getDescription();

    /**
     * Action a effectué pour la commande
     * @param p Le joueur qui execute la commande
     * @param args Les arguments de la commande
     */
    void perform(Player p, String args[]);

    /**
     * Obtneir la syntaxe de la commande
     * @return La syntaxe de la commande
     */
    String getSyntax();

}
