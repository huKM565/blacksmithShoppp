package org.hukm.blacksmithshop.items.unityMenuItems;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.hukm.blacksmithshop.items.menuItemClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class unityWaitItem extends menuItemClass {

    unityWaitItem() {
        name = ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + "Ожидание предметов";
        lore = Arrays.asList(ChatColor.WHITE.toString() + "Переместите в левый слот инструмент или оружие,", ChatColor.WHITE.toString() + "которое вы хотите улучшить, а справа - зачар. книгу");
        material = Material.YELLOW_WOOL;
    }


}
