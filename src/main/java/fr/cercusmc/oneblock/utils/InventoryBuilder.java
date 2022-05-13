package fr.cercusmc.oneblock.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryBuilder {

    private int size;
    private Inventory inventory;
    private String name;
    private HashMap<Integer, ItemBuilder> contents;

    public InventoryBuilder(int size, String name, HashMap<Integer, ItemBuilder> contents) {
        this.size = size;
        this.name = name;
        this.contents = contents;
        this.inventory = Bukkit.createInventory(null, size, name);
        setContents(contents);
    }

    public InventoryBuilder(int size, String name) {
        this(size, name, null);
    }


    public String getName() {
        return name;
    }

    public InventoryBuilder addContent(ItemBuilder it, int slot) {
        this.contents.replace(slot, it);
        this.inventory.setItem(slot, it.build());
        return this;
    }

    public InventoryBuilder setContents(HashMap<Integer, ItemBuilder> contents) {
        this.contents = contents;
        if(contents != null)
            contents.forEach((k, v) -> this.inventory.setItem(k, v.build()));
        return this;
    }

    public HashMap<Integer, ItemBuilder> getContents() {
        return contents;
    }

    public int getSize() {
        return size;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void openInventory(Player p ) {
        p.openInventory(inventory);
    }
}
