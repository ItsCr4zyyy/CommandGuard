package org.wcrazy.listener;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class TabCompleteListener implements Listener {

    public TabCompleteListener() {}

    @EventHandler
    public void onAsyncTabComplete(AsyncTabCompleteEvent event) {
        String buffer = event.getBuffer();
        List<String> completions = new ArrayList<>();
        final String baseCommand = "/msg ";

        if (!buffer.toLowerCase().startsWith(baseCommand)) {
            return;
        }

        String remainder = buffer.substring(baseCommand.length()).trim();
        int argIndex = 0;

        if (buffer.endsWith(" ")) {
            argIndex = buffer.substring(baseCommand.length()).split(" ").length;
        } else if (!remainder.isEmpty()) {
            argIndex = buffer.substring(baseCommand.length()).split(" ").length - 1;
        }

        String currentInput = "";
        String[] argsInBuffer = remainder.split("\\s+");
        if (argsInBuffer.length > 0 && !buffer.endsWith(" ")) {
            currentInput = argsInBuffer[argsInBuffer.length - 1];
        }

        if (argIndex == 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().toLowerCase().startsWith(currentInput.toLowerCase())) {
                    completions.add(p.getName());
                }
            }
        } else {
            event.setHandled(true);
            return;
        }

        event.setCompletions(completions);
    }
}