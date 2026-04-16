package org.wcrazy;

import org.bukkit.plugin.java.JavaPlugin;
import org.wcrazy.manager.ConfigManager;
import org.wcrazy.listener.CommandSendListener;
import org.wcrazy.listener.TabCompleteListener;
import org.wcrazy.command.ReloadCommand;

public class CommandGuard extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);

        getLogger().info("CommandGuard initializing...");

        CommandSendListener commandSendListener = new CommandSendListener(this.configManager);
        this.getServer().getPluginManager().registerEvents(commandSendListener, this);

        TabCompleteListener tabCompleteListener = new TabCompleteListener();
        this.getServer().getPluginManager().registerEvents(tabCompleteListener, this);

        ReloadCommand reloadCommand = new ReloadCommand(this);
        this.getCommand("cgreload").setExecutor(reloadCommand);
    }

    @Override
    public void onDisable() {
        getLogger().info("CommandGuard shut down successfully.");
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }
}