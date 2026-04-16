package org.wcrazy.listener;

import org.wcrazy.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CommandSendListener implements Listener {

    private final ConfigManager configManager;

    public CommandSendListener(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @EventHandler
    public void onCommandSend(PlayerCommandSendEvent event) {
        if (configManager.isBypassIfPermission() && event.getPlayer().hasPermission("commandguard.bypass")) {
            return;
        }

        event.getCommands().removeIf(command -> {
            String baseCommand = command.contains(":") ? command.split(":", 2)[1] : command;
            return !configManager.getAllowedCommands().contains(baseCommand);
        });
    }
}