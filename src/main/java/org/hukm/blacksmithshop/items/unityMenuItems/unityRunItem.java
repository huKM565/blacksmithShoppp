package org.hukm.blacksmithshop.items.unityMenuItems;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.hukm.blacksmithshop.items.menuItemClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class unityRunItem extends menuItemClass {

    unityRunItem(int seconds) {
        name = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "Ожидайте";
        lore = Arrays.asList(ChatColor.WHITE.toString() + "До объединения: " + seconds);
        material = Material.LIME_WOOL;
    }

}
