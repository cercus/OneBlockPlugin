package fr.cercusmc.oneblock.utils;

import fr.cercusmc.oneblock.OneBlock;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolsFunctions {

    private static final Pattern pattern  = Pattern.compile("#[a-fA-F0-9]{6}");

    private ToolsFunctions() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    /**
     * Obtenir le centre d'un block
     * @param loc : Location ou on veut le centre
     * @return La location au centre du block
     */
    public static Location getCenterOfBlock(Location loc) {
        return new Location(loc.getWorld(), loc.clone().getBlockX()+0.5, loc.getBlockY(), loc.clone().getBlockZ()+0.5);
    }

    /**
     * Fonction pour verifier si une valeur est present dans une enum
     * @param aEnum : CLass Enum
     * @param value : La valeur a tester
     * @param <E>
     * @return true si la valeur est dans l'enum
     */
    public static <E extends Enum<E>> boolean checkEnum(Class<E> aEnum, String value) {
        if(value == null || !aEnum.isEnum()) return false;
        try {
            Enum.valueOf(aEnum, value.toUpperCase());
            return true;
        } catch(IllegalArgumentException | NullPointerException e){
            return false;
        }
    }

    /**
     * Convertir les &amp; en couleur ainsi que les couleurs hexa
     * @param message Message a convertir
     * @param placeholders Liste des placeholders dans le message
     * @param values Liste des valeurs à remplacer
     * @return le message formaté
     */
    public static String format(String message, @Nullable List<String> placeholders, @Nullable List<String> values) {
        if(placeholders.size() != values.size())
            return message;
        Matcher match = pattern.matcher(message);
        while(match.find()) {
            String color = message.substring(match.start(), match.end());
            message = message.replace(color, ChatColor.of(color) + "");
            match = pattern.matcher(message);
        }
        if(placeholders != null && values != null)
            for(int index = 0; index < placeholders.size(); index++)
                message = message.replaceAll(placeholders.get(index), values.get(index));

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Convertir les &amp; en couleur ainsi que les couleurs hexa
     * @param message Message a convertir
     * @return le message formaté
     */
    public static String format(String message) {

        Matcher match = pattern.matcher(message);
        while(match.find()) {
            String color = message.substring(match.start(), match.end());
            message = message.replace(color, ChatColor.of(color) + "");
            match = pattern.matcher(message);
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Convertir les &amp; en couleur ainsi que les couleurs hexa
     * @param messages ArrayList de message a convertir
     * @return le message formaté
     */
    public static ArrayList<String> format(ArrayList<String> messages) {
        ArrayList<String> res = new ArrayList<>();
        for(String message : messages) {
            String tmp = message;
            Matcher match = pattern.matcher(message);
            while (match.find()) {

                String color = tmp.substring(match.start(), match.end());
                tmp = tmp.replace(color, ChatColor.of(color) + "");
                match = pattern.matcher(tmp);
            }
            res.add(ChatColor.translateAlternateColorCodes('&', tmp));
        }

        return res;
    }


    /**
     * Envoyer un message a un joueur
     * @param uuid UUID du joueur
     * @param message Message
     * @param placeholders Liste des placeholders dans le message
     * @param values Liste des valeurs à remplacer
     */
    public static void sendMessage(UUID uuid, String message, List<String> placeholders, List<String> values) {
        Bukkit.getPlayer(uuid).sendMessage(format(message, placeholders, values));
    }

    /**
     * Permet de trouver la prochaine île disponible selon un algorithme de grille en spirale :
     * 15 16 17 18
     * 4  3  2  11
     * 5  0  1  10
     * 6  7  8  9
     * @param num : Nombre d'île maximum
     * @return La position pour la nouvelle île
     */
    public static Position findNext(int num){
        // Direction du mouvement de la spirale
        int dx = 0;
        int dz = 2*OneBlock.getFileConfig().getMaxRadius()+10;

        // Taille du segment courant
        int segmentLength = 1;

        // Position actuelle
        int x = 0;
        int z = 0;
        // Nombre de segments passés
        int segmentPassed = 0;
        // Si c'est le centre
        if(num == 0) {
            //System.out.println("(" + x + ";" + z + ")");
            return new Position(x, 73, z);
        }

        for(int n = 0; n < num; ++n){

            // Incrementation de la position actuelle
            x += dx;
            z += dz;
            ++segmentPassed;
            if(segmentLength == segmentPassed) {
                segmentPassed = 0;

                // On change de direction
                int buffer = dz;
                dz = -dx;
                dx = buffer;

                // Augmentation de la taille du segment si necessaire
                if(dx == 0)
                    ++segmentLength;
            }
        }
        //System.out.println("(" + x + ";" + z + ")");
        return new Position(x, 73, z);

    }

    /**
     * Calcule la somme d'une liste d'entier
     * @param list : Liste d'entier
     * @return la somem de la liste
     */
    public static int somme(ArrayList<Integer> list) {
        int somme = 0;
        for(int i : list)
            somme += i;
        return somme;
    }

    /**
     * Génère un nombre aléatoire entre a et b
     * @param a : borne min
     * @param b : borne max
     * @return Un nombre aléatoire entre  a et b
     */
    public static int rand(int a, int b) {
        Random rand = new Random();
        return a+rand.nextInt(b-a);
    }

}
