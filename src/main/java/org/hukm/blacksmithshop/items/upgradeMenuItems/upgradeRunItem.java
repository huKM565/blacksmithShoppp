package org.hukm.blacksmithshop.items.upgradeMenuItems;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.hukm.blacksmithshop.items.menuItemClass;

import java.util.Arrays;

public class upgradeRunItem extends menuItemClass {

    public upgradeRunItem(int seconds) {
        name = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "Ожидайте";
        lore = Arrays.asList(ChatColor.WHITE.toString() + "До улучшения: " + seconds);
        material = Material.LIME_WOOL;
    }

}
