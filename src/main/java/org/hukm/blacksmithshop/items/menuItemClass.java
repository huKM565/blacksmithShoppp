package org.hukm.blacksmithshop.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class menuItemClass {

    protected static String name;
    protected static List<String> lore;
    protected static Material material;

    public static ItemStack get() {

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }

    public static String getName() {
        return name;
    }

}
