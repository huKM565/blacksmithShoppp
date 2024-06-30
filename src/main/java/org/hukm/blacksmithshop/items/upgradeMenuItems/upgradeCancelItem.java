package org.hukm.blacksmithshop.items.upgradeMenuItems;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.hukm.blacksmithshop.items.menuItemClass;

import java.util.Arrays;

public class upgradeCancelItem extends menuItemClass{

    upgradeCancelItem(int seconds) {
        name = ChatColor.RED.toString() + ChatColor.BOLD.toString() + "Отменить улучшение";
        lore = Arrays.asList(ChatColor.WHITE.toString() + "До улучшения: " + seconds);
        material = Material.RED_WOOL;
    }

}
