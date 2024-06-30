package org.hukm.blacksmithshop.items.mainMenuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class upgradeMenuItem {

    static String displayName = ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + "Улучшение зачарованной книги";

    public static String getName() {
        return displayName;
    }

    public static ItemStack get() {

        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(displayName);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE.toString() + "Позволяет улучшить книгу выше макс. уровня");
        lore.add(ChatColor.WHITE.toString() + "(Подробнее читайте в нашем Discord либо на сайте)");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;

    }

}
