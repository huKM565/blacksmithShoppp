package org.hukm.blacksmithshop.items.upgradeMenuItems;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.hukm.blacksmithshop.items.menuItemClass;

import java.util.Arrays;

public class upgradeAcceptItem extends menuItemClass {

    upgradeAcceptItem(int nextLevel, int countBigEmerald, int countExpirienceBottle) {
        name = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "Предмет можно улучшить";
        lore = Arrays.asList(ChatColor.WHITE.toString() + "Книгу можно улучшить до " + nextLevel + " уровня", ChatColor.WHITE.toString() + "Необходимые вещи для учучшения: ", ChatColor.WHITE.toString() + " - "  + countBigEmerald + " большых изумрудов", ChatColor.WHITE.toString() + " - " + countExpirienceBottle + " бутыльков опыта", ChatColor.WHITE.toString() + "У вас будет 5 секунд, чтобы отменить крафт", ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "Нажмите, чтобы начать улучшение");
        material = Material.GREEN_WOOL;
    }

}
