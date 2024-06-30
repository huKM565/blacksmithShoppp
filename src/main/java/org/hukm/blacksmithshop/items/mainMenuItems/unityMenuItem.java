package org.hukm.blacksmithshop.items.mainMenuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class unityMenuItem {

    static String displayName = ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + "Улучшение инструментов/оружия";

    public static String getName() {
        return displayName;
    }

    public static ItemStack get() {

        ItemStack item = new ItemStack(Material.ANVIL);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(displayName);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE.toString() + "Позволяет объединить зачарованную");
        lore.add(ChatColor.WHITE.toString() + "книгу с инструментом или оружием");
        lore.add(ChatColor.WHITE.toString() +"(Подробнее читайте в нашем Discord либо на сайте)");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;

    }

}
