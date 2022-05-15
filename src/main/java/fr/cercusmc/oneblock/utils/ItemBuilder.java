package fr.cercusmc.oneblock.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ItemBuilder {

    private Material mat;
    private String name;
    private ArrayList<String> lore;
    private String[] lore2;
    private HashMap<Enchantment, Integer> enchantments;
    private int count;
    private ItemStack it;
    private ItemMeta meta;

    public ItemBuilder(Material mat, String name, ArrayList<String> lore, HashMap<Enchantment, Integer> enchantments, int count) {
        this.mat = mat;
        this.name = name;
        this.lore = lore;
        this.enchantments = enchantments;
        this.count = count;
        this.it = new ItemStack(mat, count);
        this.meta = it.getItemMeta();
        this.meta.setDisplayName(name);
        this.meta.setLore(lore);
        this.it.addUnsafeEnchantments(enchantments);

    }

    public ItemBuilder(Material mat, String name, String[] lore2, HashMap<Enchantment, Integer> enchantments, int count) {
        this.mat = mat;
        this.name = name;
        this.lore2 = lore2;
        this.enchantments = enchantments;
        this.count = count;
        this.it = new ItemStack(mat, count);
        this.meta = it.getItemMeta();
        this.meta.setDisplayName(name);
        this.meta.setLore(Arrays.asList(lore2));
        this.it.addUnsafeEnchantments(enchantments);


    }

    public ItemBuilder(Material mat, int count) {
        this.mat = mat;
        this.count = count;
        this.it = new ItemStack(mat, count);
        this.meta = this.it.getItemMeta();
    }

    /**
     * Set un nom sur l'item
     * @param name : Nom de l'item
     * @return ItemBuilder
     */
    public ItemBuilder setDisplayName(String name) {
        this.name = ToolsFunctions.format(name);
        this.meta.setDisplayName(ToolsFunctions.format(name));
        return this;
    }


    public ItemBuilder setLore(String[] lore) {
        this.lore2 = lore;
        this.meta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setLore(ArrayList<String> lore){
        this.lore = lore;
        this.meta.setLore(lore);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int power) {
        this.meta.addEnchant(ench, power, true);
        return this;
    }

    public ItemBuilder addEnchant(HashMap<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        enchantments.forEach((k, v) -> this.meta.addEnchant(k, v, true));
        return this;
    }

    public ItemBuilder hideAttributesEnchantment(boolean hideAttribute) {
        if(hideAttribute)
            this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder removeAllEnchantment() {
        this.it.getEnchantments().forEach((k, v) -> this.it.removeEnchantment(k));
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        if(this.it.getEnchantments().containsKey(enchantment))
            this.it.removeEnchantment(enchantment);
        return this;
    }

    public ItemStack build() {
        this.it.setItemMeta(meta);
        return this.it;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getLore() {
        return lore;
    }

    public HashMap<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public int getCount() {
        return count;
    }

    public ItemMeta getMeta() {
        return meta;
    }

    public ItemStack getIt() {
        return it;
    }

    public Material getMat() {
        return mat;
    }

    public String[] getLore2() {
        return lore2;
    }
}
