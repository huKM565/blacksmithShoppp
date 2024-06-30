package org.hukm.blacksmithshop.events;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.hukm.api.Api;
import org.hukm.bigemerald.items.bigEmeraldItem;
import org.hukm.blacksmithshop.BlacksmithShop;
import org.hukm.blacksmithshop.inventories.unityMenuInventory;
import org.hukm.blacksmithshop.inventories.upgradeMenuInventory;
import org.hukm.blacksmithshop.inventoryHolders.mainMenuHolder;
import org.hukm.blacksmithshop.inventoryHolders.unityMenuHolder;
import org.hukm.blacksmithshop.inventoryHolders.upgradeMenuHolder;
import org.hukm.blacksmithshop.items.grayGlassPane;
import org.hukm.blacksmithshop.items.mainMenuItems.unityMenuItem;
import org.hukm.blacksmithshop.items.menuItemClass;
import org.hukm.blacksmithshop.items.unityMenuItems.unityAcceptItem;
import org.hukm.blacksmithshop.items.unityMenuItems.unityCancelItem;
import org.hukm.blacksmithshop.items.unityMenuItems.unityRunItem;
import org.hukm.blacksmithshop.items.unityMenuItems.unityWaitItem;
import org.hukm.blacksmithshop.items.mainMenuItems.upgradeMenuItem;
import org.hukm.blacksmithshop.items.upgradeMenuItems.*;

import java.util.*;

public class playerClickInventoryEvent implements Listener {

    static Map<Player, BukkitTask> timers = new HashMap<>();

    @EventHandler
    public static void onPlayerClickInventoryEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack item = event.getCurrentItem();

        if (item != null && (inventory.getHolder() instanceof upgradeMenuHolder || inventory.getHolder() instanceof unityMenuHolder)) {
            Object[] i = menuItemClass.class.getSigners();
            Bukkit.broadcastMessage(i.toString());
            for(String displayNameMenuItem: new String[]{grayGlassPane.getName(), upgradeWaitItem.getName(), upgradeErrorItem.getName(), upgradeRunItem.getName(), upgradeAcceptItem.getName(), upgradeCancelItem.getName(), unityWaitItem.getName(), unityAcceptItem.getName(), unityCancelItem.getName(), unityRunItem.getName()}) {
                if(Api.equalDisplayName(item, displayNameMenuItem)) event.setCancelled(true);
            }
        }

        if (inventory.getHolder() instanceof mainMenuHolder) {
            if (item != null && Api.equalDisplayName(item, upgradeMenuItem.getName()))
                player.openInventory(upgradeMenuInventory.get());
            else if (item != null && Api.equalDisplayName(item, unityMenuItem.getName()))
                player.openInventory(unityMenuInventory.get());

            event.setCancelled(true);
        }
        if (inventory.getHolder() instanceof upgradeMenuHolder) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    delayedUpgradePlayerClickInventoryEvent(event);
                }
            }.runTaskLaterAsynchronously(BlacksmithShop.getInstance(), 2L);
        }
        if (inventory.getHolder() instanceof unityMenuHolder) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    delayedUnityPlayerClickInventoryEvent(event);
                }
            }.runTaskLaterAsynchronously(BlacksmithShop.getInstance(), 2L);

        }
    }

    public static void delayedUpgradePlayerClickInventoryEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack item = event.getCurrentItem();

        int nextLevel = 0;
        int countBigEmerald = 0;
        int countExpirienceBottle = 0;

        Enchantment newEnchantment = null;

        ItemStack infoItem = null;
        ItemStack upgradeItem = inventory.getItem(13);

        if (upgradeItem != null) {
            if (upgradeItem.getType() == Material.ENCHANTED_BOOK) {

                Map<Enchantment, Integer> enchantments = ((EnchantmentStorageMeta) upgradeItem.getItemMeta()).getStoredEnchants();
                Map.Entry<Enchantment, Integer> firstEntry = enchantments.entrySet().stream().findFirst().get();

                Enchantment enchantment = firstEntry.getKey();
                Integer level = firstEntry.getValue();

                if (enchantments.size() == 1) {
                    int maxLevel = 0;
                    switch (enchantment.getMaxLevel()) {
                        case 2:
                            maxLevel = 4;
                            break;
                        case 3:
                            maxLevel = 5;
                            break;
                        case 4:
                            maxLevel = 8;
                            break;
                        case 5:
                            maxLevel = 10;
                            break;
                    }

                    if (level < enchantment.getMaxLevel())
                        infoItem = upgradeErrorItem.get("Уровень зачарования книги ниже максимального");
                    else if (enchantment.getMaxLevel() == 1)
                        infoItem = upgradeErrorItem.get("Книги с первым макс. уровнем зачарования нельзя улучшить");
                    else if (level + 1 > maxLevel) infoItem = upgradeErrorItem.get("Вы достигли максимального уровня");
                    else {

                        nextLevel = level + 1;
                        countBigEmerald = (nextLevel - enchantment.getMaxLevel()) * 64;
                        countExpirienceBottle = (nextLevel - enchantment.getMaxLevel()) * 128;
                        newEnchantment = enchantment;
                        infoItem = upgradeAcceptItem.get(nextLevel, countBigEmerald, countExpirienceBottle);

                    }
                } else infoItem = upgradeErrorItem.get("Книга имеет более одного зачарования");
            } else infoItem = upgradeErrorItem.get("Данный предмет не является зачар. книгой");
        } else {
            infoItem = upgradeWaitItem.get();
            deleteTimers(player, inventory);
        }
        inventory.setItem(4, infoItem);

        if (item != null) {
            if (Api.equalDisplayName(item, upgradeAcceptItem.getName()) && timers.get(player) == null) {

                Enchantment finalNewEnchantment = newEnchantment;
                int finalNextLevel = nextLevel;
                int finalCountBigEmerald = countBigEmerald;
                int finalCountExpirienceBottle = countExpirienceBottle;

                BukkitTask timer = new BukkitRunnable() {
                    int seconds = 5;

                    @Override
                    public void run() {
                        if (seconds <= 0) {
                            if(inventory.getItem(13).isSimilar(upgradeItem)) {
                                if (Api.hasItemsEqualCustomModelData(player.getInventory(), bigEmeraldItem.get(), finalCountBigEmerald) && Api.hasItemsEqualMaterial(player.getInventory(), new ItemStack(Material.EXPERIENCE_BOTTLE), finalCountExpirienceBottle)) {

                                    Api.removeItemsEqualCustomModelData(player.getInventory(), bigEmeraldItem.get(), finalCountBigEmerald);
                                    Api.removeItemsEqualMaterial(player.getInventory(), new ItemStack(Material.EXPERIENCE_BOTTLE), finalCountExpirienceBottle);

                                    inventory.setItem(4, upgradeAcceptItem.get(finalNextLevel + 1, finalCountBigEmerald * 2, finalCountExpirienceBottle * 2));

                                    ItemStack newBook = inventory.getItem(13);

                                    ItemMeta metaNewBook = newBook.getItemMeta();
                                    EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta) metaNewBook;

                                    enchantmentStorageMeta.addStoredEnchant(finalNewEnchantment, finalNextLevel, true);
                                    newBook.setItemMeta(metaNewBook);

                                    player.playSound(player, Sound.ENTITY_VILLAGER_YES, 1, 1);

                                    inventory.setItem(13, newBook);
                                } else inventory.setItem(4, upgradeErrorItem.get("У вас не достаточно предметов для улучшения"));
                            }else inventory.setItem(4, upgradeErrorItem.get("Вы заменили или убрали предмет!"));

                            deleteTimers(player, inventory);
                        } else {
                            seconds = tickTimer(inventory, new upgradeRunItem(seconds).get(), upgradeCancelItem.get(seconds), seconds);
                        }
                    }

                }.runTaskTimer(BlacksmithShop.getInstance(), 0L, 20L);
                timers.put(player, timer);

                player.setItemOnCursor(new ItemStack(Material.AIR));
            } else if (Api.equalDisplayName(item, upgradeCancelItem.getName())) {
                deleteTimers(player, inventory);
                inventory.setItem(4, upgradeAcceptItem.get(nextLevel, countBigEmerald, countExpirienceBottle));
            }
        }
    }

    public static void delayedUnityPlayerClickInventoryEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        ItemStack tool = inventory.getItem(12);
        ItemStack book = inventory.getItem(14);
        ItemStack item = event.getCurrentItem();

        ItemMeta toolMeta = null;

        Enchantment enchantment = null;
        int nextLevel = 0;

        int countAmetist = 0;

        if (tool != null && book != null) {
            toolMeta = tool.getItemMeta();
            if (Tag.ITEMS_BREAKS_DECORATED_POTS.getValues().contains(tool.getType())) {
                if (book.getType() == Material.ENCHANTED_BOOK) {

                    EnchantmentStorageMeta bookEnchantmentMeta = (EnchantmentStorageMeta) book.getItemMeta();
                    Map<Enchantment, Integer> bookStoredEnchantments = bookEnchantmentMeta.getStoredEnchants();

                    if (bookStoredEnchantments.size() == 1) {
                        try {
                            Map<Enchantment, Integer> toolEnchantments = toolMeta.getEnchants();
                            Enchantment key = bookStoredEnchantments.keySet().stream().findFirst().get();

                            if (toolEnchantments.containsKey(key)) {
                                if (toolEnchantments.get(key) < bookStoredEnchantments.get(key)) {
                                    if (bookStoredEnchantments.get(key) > key.getMaxLevel()) {

                                        enchantment = key;
                                        nextLevel = bookStoredEnchantments.get(key);
                                        countAmetist = (bookStoredEnchantments.get(key) - key.getMaxLevel()) * 64;
                                        inventory.setItem(4, unityAcceptItem.get(countAmetist));

                                    } else inventory.setItem(4, upgradeErrorItem.get("Уровнь накладываемой книги должен быть больше максимального"));
                                } else inventory.setItem(4, upgradeErrorItem.get("Уровень зачарования книги не выше, чем у вещи"));
                            } else inventory.setItem(4, upgradeErrorItem.get("Инструмент не имеет данного зачарования"));
                        } catch (NullPointerException e) {
                            inventory.setItem(4, upgradeErrorItem.get("Инструмент не имеет зачарований"));
                            throw e;
                        }
                    } else inventory.setItem(4, upgradeErrorItem.get("Зачар. книга имеет более 1-ого зачарования"));
                } else inventory.setItem(4, upgradeErrorItem.get("Предмет справа - не зачар. книга"));
            } else inventory.setItem(4, upgradeErrorItem.get("Предмет слева - не инструмент"));
        }else {
            inventory.setItem(4, unityWaitItem.get());
            deleteTimers(player, inventory);
        }

        if (item != null) {
            if (Api.equalDisplayName(item, unityAcceptItem.getName()) && timers.get(player) == null) {

                int finalCountAmetist = countAmetist;
                int finalNextLevel = nextLevel;
                Enchantment finalEnchantment = enchantment;
                ItemMeta finalToolMeta = toolMeta;

                BukkitTask timer = new BukkitRunnable() {
                    int seconds = 5;

                    @Override
                    public void run() {
                        if (seconds <= 0) {
                            if(inventory.getItem(12).isSimilar(tool) && inventory.getItem(14).isSimilar(book)) {
                                if (Api.hasItemsEqualMaterial(player.getInventory(), new ItemStack(Material.AMETHYST_SHARD), finalCountAmetist)) {

                                    Api.removeItemsEqualMaterial(player.getInventory(), new ItemStack(Material.AMETHYST_SHARD), finalCountAmetist);
                                    inventory.setItem(4, unityAcceptItem.get(finalCountAmetist * 2));

                                    finalToolMeta.addEnchant(finalEnchantment, finalNextLevel, true);
                                    tool.setItemMeta(finalToolMeta);

                                    inventory.setItem(12, tool);
                                    inventory.setItem(14, new ItemStack(Material.AIR));

                                    player.playSound(player, Sound.ENTITY_VILLAGER_YES, 1, 1);

                                } else inventory.setItem(4, upgradeErrorItem.get("У вас не достаточно предметов для улучшения"));
                            } else inventory.setItem(4, upgradeErrorItem.get("Вы заменили предметы!"));

                            deleteTimers(player, inventory);
                        } else {
                            seconds = tickTimer(inventory, unityRunItem.get(seconds), unityCancelItem.get(seconds), seconds);

                        }
                    }
                }.runTaskTimer(BlacksmithShop.getInstance(), 0L, 20L);
                timers.put(player, timer);
            } else if (Api.equalDisplayName(item, unityCancelItem.getName())) {
                deleteTimers(player, inventory);

                inventory.setItem(22, grayGlassPane.get());
                inventory.setItem(4, unityAcceptItem.get(nextLevel));
            }
        }
    }

    public static void deleteTimers(Player player, Inventory inventory) {
        timers.get(player).cancel();
        timers.remove(player);
        inventory.setItem(22, grayGlassPane.get());
    }

    public static int tickTimer(Inventory inventory, ItemStack runItem, ItemStack cancelItem, int seconds) {

        inventory.setItem(4, runItem);
        inventory.setItem(22, cancelItem);

        return seconds - 1;
    }
}