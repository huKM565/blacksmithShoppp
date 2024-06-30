package org.hukm.blacksmithshop;

import org.bukkit.plugin.java.JavaPlugin;
import org.hukm.blacksmithshop.commands.blacksmithShopCommand;
import org.hukm.blacksmithshop.events.playerClickInventoryEvent;

public final class BlacksmithShop extends JavaPlugin {

    static BlacksmithShop instance;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new playerClickInventoryEvent(), this);

        getCommand("blacksmithShop").setExecutor(new blacksmithShopCommand());

        instance = this;

    }

    public static BlacksmithShop getInstance() {
        return instance;
    }
}
