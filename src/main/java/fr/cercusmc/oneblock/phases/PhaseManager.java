package fr.cercusmc.oneblock.phases;

import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PhaseManager {

    private ArrayList<Phase> phases = OneBlock.getPhases();

    public void mysteriousBlock(Location loc, int phase, Player p) {
        Random rand = new Random();
        int r = rand.nextInt(100); // Valeur comprise entre [0;100[

        if(r < 70) { // Blocks
            generateBlock(loc, phase, p);
        } else if(r < 90) { // Mobs
            generateMobs(loc, phase, p);
        } else { // Items
            generateItems(loc, phase, p);
        }

    }

    /**
     * Obtenir toutes les caractéristiques d'une phase donnée
     * @param id : Id de la phase
     * @return La phase
     */
    public Phase getPhaseById(int id){
        for(Phase p : phases){
            if(p.getId() == id)
                return p;
        }
        return null;
    }

    /**
     * Generer un ensemble d'item dans un coffre
     * @param loc : Location du block
     * @param phase : Phase du joueur
     * @param p : Joueur
     */
    private void generateItems(Location loc, int phase, Player p) {

    }

    /**
     * Generer un mob
     * @param loc : Location du block
     * @param phase : Phase du joueur
     * @param p : Joueur
     */
    public void generateMobs(Location loc, int phase, Player p) {
        HashMap<EntityType, Integer> mobs = getPhaseById(phase).getEntities();
        loc.getWorld().spawnEntity(ToolsFunctions.getCenterOfBlock(loc.clone().add(0, 2, 0)), chooseRandomEntity(mobs));
        loc.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc.clone().add(0, 2, 0), 100);
        loc.getWorld().playSound(loc, Sound.BLOCK_ANVIL_HIT, 1.0f, 1.0f);
        loc.getBlock().setType(Material.GRASS_BLOCK);
    }

    /**
     * Generer un bloc
     * @param loc : Location du block centrale de l'île
     * @param phase : Phase du joueur
     * @param p : Joueur
     */
    public void generateBlock(Location loc, int phase, Player p) {
        HashMap<Material, Integer> blocks = getPhaseById(phase).getBlocks();
        loc.getBlock().setType(chooseRandomMaterial(blocks));
        loc.getWorld().spawnParticle(Particle.CRIT, loc, 50);
    }

    /**
     * Choix d'un block en fonction de sa probabilité
     * @param blocks : HashMap contenant la liste des blocks associé a leurs poids
     * @return Le materiel choisi de facon aléatoire
     */
    public Material chooseRandomMaterial(HashMap<Material, Integer> blocks) {
        ArrayList<Material> mats = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();
        Random random = new Random();
        int r = random.nextInt(100);
        blocks.forEach((k, v) -> {
            mats.add(k);
            weight.add(v);
        });

        ArrayList<Double> probas = getProbabilityAssociate(weight);
        return algorithmRandomMaterial(probas, mats, r);

    }

    /**
     * Choix d'une entité en fonction de sa probabilité
     * @param mobs : HashMap contenant la liste des entités associé a leurs poids
     * @return L'entité choisi de facon aléatoire
     */
    public EntityType chooseRandomEntity(HashMap<EntityType, Integer> mobs) {
        ArrayList<EntityType> ents = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();
        Random random = new Random();
        int r = random.nextInt(100);
        mobs.forEach((k, v) -> {
            ents.add(k);
            weight.add(v);
        });
        ArrayList<Double> probas = getProbabilityAssociate(weight);
        return algorithmRandomEntities(probas, ents, r);
    }

    /**
     * Obtenir pour chaque valeur d'une liste sa probilité par rapport a la somme totale
     * @param weight : Liste d'entier
     * @return Liste des probabilités pour chaque valeurs présente dans la liste
     */
    public ArrayList<Double> getProbabilityAssociate(ArrayList<Integer> weight) {
        ArrayList<Double> probas = new ArrayList<>();
        int somme = ToolsFunctions.somme(weight);
        for(int i : weight) {
            probas.add(((double)i)/somme*100.0);
        }
        return probas;
    }

    /**
     * Fonction qui applique un algorithme permettant de choisir un materiel de facon aléatoire
     *
     * Exemple :
     *
     * mots = ["arbre", "branche", "feuille", "racine", "sève", "écorce"]
     * probas = [16.6, 13.6, 12.6, 20.6, 29.6, 7]
     *
     * Si r = 20, alors l'item selectionné sera "branche" car 0 < r < 16.6 n'est pas vérifié mais 16.6 < r < 16.6+13.6 = 30.2 l'est
     *
     * @param probas : liste des probabilités de chaque materiel
     * @param mats : Liste des materiels
     * @param r : Nombre aléatoire compris entre 0 et 100
     * @return Le materiel selectionné
     */
    public Material algorithmRandomMaterial(ArrayList<Double> probas, ArrayList<Material> mats, int r) {
        int number = 0;
        Material mat = Material.GRASS_BLOCK;
        for(int i = 0; i < probas.size(); i++) {
            if(r > number && r <= number + probas.get(i)) {
                mat = mats.get(i);
                return mat;
            } else {
                number += probas.get(i);
            }
        }
        return mat;
    }

    /**
     * Fonction qui applique un algorithme permettant de choisir une entité de facon aléatoire
     *
     * Exemple :
     *
     * mots = ["arbre", "branche", "feuille", "racine", "sève", "écorce"]
     * probas = [16.6, 13.6, 12.6, 20.6, 29.6, 7]
     *
     * Si r = 20, alors l'item selectionné sera "branche" car 0 < r < 16.6 n'est pas vérifié mais 16.6 < r < 16.6+13.6 = 30.2 l'est
     *
     * @param probas : liste des probabilités de chaque entité
     * @param ents : Liste des entités
     * @param r : Nombre aléatoire compris entre 0 et 100
     * @return L'entité selectionné
     */
    public EntityType algorithmRandomEntities(ArrayList<Double> probas, ArrayList<EntityType> ents, int r) {
        int number = 0;
        EntityType ent = EntityType.PIG;
        for(int i = 0; i < probas.size(); i++) {
            double tmp = number + probas.get(i);
            //System.out.println(number + "< " + r + " <= " + tmp);
            if(r > number && r <= tmp) {
                ent = ents.get(i);
                return ent;
            } else {
                number += probas.get(i);
            }
        }
        return ent;
    }


}
