package org.wcrazy.command;

import org.wcrazy.Colors;
import org.wcrazy.CommandGuard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final CommandGuard plugin;

    public ReloadCommand(CommandGuard plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("commandguard.reload")) {
            sender.sendMessage(Colors.formatHexColors(plugin.getConfigManager().getNoPermissionMessage()));
            return true;
        }

        boolean success = plugin.getConfigManager().reloadConfiguration();

        if (success) {
            sender.sendMessage("§a[CommandGuard] Configuration successfully reloaded!");
        } else {
            sender.sendMessage("§c[CommandGuard] Failed to reload configuration. Check console.");
        }

        return true;
    }
}