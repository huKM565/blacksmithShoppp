package org.hukm.blacksmithshop.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hukm.blacksmithshop.inventories.mainMenuInventory;

public class blacksmithShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) ((Player) commandSender).openInventory(mainMenuInventory.get());
        return true;
    }
}
