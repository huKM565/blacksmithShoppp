package org.hukm.blacksmithshop.items.upgradeMenuItems;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.hukm.blacksmithshop.items.menuItemClass;

import java.util.Arrays;

public class upgradeWaitItem extends menuItemClass {

    upgradeWaitItem() {
        name = ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + "Ожидание предмета";
        lore = Arrays.asList(ChatColor.WHITE.toString() + "Переместите в слот ниже книгу, которую хотите улучшить");
        material = Material.YELLOW_WOOL;
    }

}
