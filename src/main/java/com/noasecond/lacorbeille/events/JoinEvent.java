package com.noasecond.lacorbeille.events;

import com.noasecond.lacorbeille.ConfigManager;
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

        //PlayerColor
        ConfigManager configManager = new ConfigManager(plugin, "colors.yml");
        ChatColor color = configManager.getPlayerColor(player);
        if (color != null) {
            player.setDisplayName(color + player.getName() + ChatColor.RESET); //Color in tchat
            player.setPlayerListName(color + player.getName()); //Color in tablist
        }

        //WelcomTitle
        String welcomeTitle = "§6La Corbeille";
        String welcomeSubtitle = "§evous souhaite la bienvenue !";
        if (player.hasPlayedBefore()) {
            welcomeTitle = "§6La Corbeille";
            welcomeSubtitle = "§evous souhaite une bonne partie !";
        }
        int fadeInTime = 10;
        int stayTime = 100;
        int fadeOutTime = 10;
        player.sendTitle(welcomeTitle, welcomeSubtitle, fadeInTime, stayTime, fadeOutTime);
    }
}