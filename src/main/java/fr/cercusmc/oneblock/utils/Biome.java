package fr.cercusmc.oneblock.utils;

import org.bukkit.Material;

import java.util.ArrayList;

public class Biome {

    private org.bukkit.block.Biome biome;
    private String name;
    private Material icon;
    private ArrayList<String> description;
    private boolean permission;

    public Biome(org.bukkit.block.Biome biome, String name, Material icon, ArrayList<String> description, boolean permission) {
        this.biome = biome;
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.permission = permission;
    }

    public org.bukkit.block.Biome getBiome() {
        return biome;
    }

    public void setBiome(org.bukkit.block.Biome biome) {
        this.biome = biome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Material getIcon() {
        return icon;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
