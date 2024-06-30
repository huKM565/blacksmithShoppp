package org.hukm.blacksmithshop.items.upgradeMenuItems;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.hukm.blacksmithshop.items.menuItemClass;

import java.util.Arrays;

public class upgradeErrorItem extends menuItemClass {

    upgradeErrorItem(String error) {
        name = ChatColor.RED.toString() + ChatColor.BOLD.toString() + "Ошибка:";
        lore = Arrays.asList(ChatColor.WHITE.toString() + error);
        material = Material.RED_WOOL;
    }

}
