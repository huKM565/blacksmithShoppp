package org.hukm.blacksmithshop.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.hukm.blacksmithshop.inventoryHolders.unityMenuHolder;
import org.hukm.blacksmithshop.items.grayGlassPane;
import org.hukm.blacksmithshop.items.unityMenuItems.unityWaitItem;

public class unityMenuInventory {

    public static Inventory get() {

        Inventory inventory = Bukkit.createInventory(new unityMenuHolder(), 27, "Улучшение предмета");

        inventory.setItem(4, unityWaitItem.get());

        for(int index = 0; index < inventory.getSize(); index++) {
            if(inventory.getItem(index) == null) inventory.setItem(index, grayGlassPane.get());
        }

        inventory.setItem(12, new ItemStack(Material.AIR));
        inventory.setItem(14, new ItemStack(Material.AIR));

        return inventory;

    }

}
