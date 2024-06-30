package org.hukm.blacksmithshop.items.unityMenuItems;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class unityAcceptItem {

    unityAcceptItem() {

    }

    static String displayName = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "Можно соединить";

    public static String getName() {
        return displayName;
    }

    public static ItemStack get(int count) {

        ItemStack item = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(displayName);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE.toString() + "Объединение будет стоить " + count + " осколков аметиста");
        lore.add(ChatColor.WHITE.toString() + "У вас будет 5 секунд, чтобы отменить объединение");
        lore.add(ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "Нажмите, чтобы начать улучшение");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;

    }

}
