package fr.cercusmc.oneblock.phases;

import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;

public class Phase {

    private int id;
    private String name;
    private ArrayList<String> description;
    private Material icon;
    private int blockToReach;
    private HashMap<Material, Integer> blocks;
    private HashMap<EntityType, Integer> entities;
    private ArrayList<Material> items;

    public Phase(int id, String name, ArrayList<String> description, Material icon, int blockToReach, HashMap<Material, Integer> blocks, HashMap<EntityType, Integer> entities,ArrayList<Material> items){
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
        return ToolsFunctions.format(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDescription() { return ToolsFunctions.format(description); }

    public void setDescription(ArrayList<String> description) {
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
