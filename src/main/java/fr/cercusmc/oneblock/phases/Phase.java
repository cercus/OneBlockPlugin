package fr.cercusmc.oneblock.phases;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;

public class Phase {

    private int id;
    private String name;
    private String description;
    private Material icon;
    private int blockToReach;
    private HashMap<Material, Integer> blocks;
    private HashMap<EntityType, Integer> entities;
    private ArrayList<Material> items;

    public Phase(int id, String name, String description, Material icon, int blockToReach, HashMap<Material, Integer> blocks, HashMap<EntityType, Integer> entities,ArrayList<Material> items){
        this.id = id;
        this.icon = icon;
        this.blockToReach = blockToReach;
        this.blocks = blocks;
        this.description = description;
        this.entities = entities;
        this.items = items;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<Material, Integer> getBlocks() {
        return blocks;
    }

    public void setBlocks(HashMap<Material, Integer> blocks) {
        this.blocks = blocks;
    }

    public HashMap<EntityType, Integer> getEntities() {
        return entities;
    }

    public void setEntities(HashMap<EntityType, Integer> entities) {
        this.entities = entities;
    }

    public ArrayList<Material> getItems() {
        return items;
    }

    public void setItems(ArrayList<Material> items) {
        this.items = items;
    }

    public int getBlockToReach() {
        return blockToReach;
    }

    public void setBlockToReach(int blockToReach) {
        this.blockToReach = blockToReach;
    }

    public Material getIcon() {
        return icon;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Phase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon=" + icon +
                ", blockToReach=" + blockToReach +
                ", blocks=" + blocks +
                ", entities=" + entities +
                ", items=" + items +
                '}';
    }
}
