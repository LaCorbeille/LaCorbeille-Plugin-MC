package com.noasecond.lacorbeille.events;

import com.noasecond.lacorbeille.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class JoinEvent implements Listener {
    private Plugin plugin;
    public JoinEvent(Plugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ConfigManager configManager = new ConfigManager(plugin, "colors.yml");
        ChatColor color = configManager.getPlayerColor(player);
        if (color != null) {
            player.setDisplayName(color + player.getName() + ChatColor.RESET); //Color in tchat
            player.setPlayerListName(color + player.getName()); //Color in tablist
        }
    }
}