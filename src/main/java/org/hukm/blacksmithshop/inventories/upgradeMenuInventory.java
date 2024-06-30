package org.hukm.blacksmithshop.inventories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.hukm.blacksmithshop.inventoryHolders.upgradeMenuHolder;
import org.hukm.blacksmithshop.items.grayGlassPane;
import org.hukm.blacksmithshop.items.upgradeMenuItems.upgradeWaitItem;

public class upgradeMenuInventory {

    public static Inventory get() {

        Inventory inventory = Bukkit.createInventory(new upgradeMenuHolder(), 27 , "Улучшить зачарованную книгу");

        inventory.setItem(4, upgradeWaitItem.get());

        for(int index = 0; index < inventory.getSize(); index++) {
            if(inventory.getItem(index) == null) inventory.setItem(index, grayGlassPane.get());
        }

        inventory.setItem(13, null);

        return inventory;
    }
}
