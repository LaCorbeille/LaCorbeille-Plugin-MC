package com.noasecond.lacorbeille.events;

import com.noasecond.lacorbeille.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    private final ConfigManager configManager;
    public JoinEvent(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ChatColor color = configManager.getPlayerColor(player);
        if (color != null) {
            player.setDisplayName(color + player.getName());
            player.setPlayerListName(color + player.getName());
            Bukkit.getServer().broadcastMessage(player.getName() + " a rejoint le serveur avec la couleur " + color + " !");
        } else {
            Bukkit.getServer().broadcastMessage(player.getName() + " a rejoint le serveur sans couleur !");
        }
    }
}
