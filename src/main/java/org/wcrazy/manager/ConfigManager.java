package org.wcrazy.manager;

import org.wcrazy.CommandGuard;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;

public class ConfigManager {

    private final CommandGuard plugin;
    private Set<String> allowedCommands;
    private boolean bypassIfPermission;
    private String noPermissionMessage;

    public ConfigManager(CommandGuard plugin) {
        this.plugin = plugin;
        this.plugin.saveDefaultConfig();
        loadConfig();
    }

    public boolean reloadConfiguration() {
        try {
            this.plugin.reloadConfig();
            loadConfig();
            return true;
        } catch (Exception e) {
            this.plugin.getLogger().severe("Failed to reload config: " + e.getMessage());
            return false;
        }
    }

    private void loadConfig() {
        FileConfiguration config = this.plugin.getConfig();

        this.allowedCommands = new HashSet<>(config.getStringList("allowed-commands"));
        this.bypassIfPermission = config.getBoolean("bypass-if-permission", true);

        String rawMessage = config.getString("no-permission", "&cNo permission.");
        this.noPermissionMessage = ChatColor.translateAlternateColorCodes('&', rawMessage);
    }

    public Set<String> getAllowedCommands() {
        return this.allowedCommands;
    }

    public boolean isBypassIfPermission() {
        return this.bypassIfPermission;
    }

    public String getNoPermissionMessage() {
        return this.noPermissionMessage;
    }
}