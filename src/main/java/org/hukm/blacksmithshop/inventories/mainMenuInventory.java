package org.hukm.blacksmithshop.inventories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.hukm.blacksmithshop.inventoryHolders.mainMenuHolder;
import org.hukm.blacksmithshop.items.mainMenuItems.unityMenuItem;
import org.hukm.blacksmithshop.items.mainMenuItems.upgradeMenuItem;

public class mainMenuInventory {

    public static Inventory get() {

        Inventory inventory = Bukkit.createInventory(new mainMenuHolder(), 27, "Кузница");

        inventory.setItem(12, upgradeMenuItem.get());
        inventory.setItem(14, unityMenuItem.get());

        return inventory;

    }

}
