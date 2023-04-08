package com.noasecond.lacorbeille;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private final Plugin plugin;
    private File configFile;
    private FileConfiguration config;

    public ConfigManager(Plugin plugin, String filename) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), filename);
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerColor(Player player, ChatColor color) {
        config.set(player.getUniqueId().toString(), color.name());
        saveConfig();
    }

    public ChatColor getPlayerColor(Player player) {
        if (config.contains(player.getUniqueId().toString())) {
            return ChatColor.valueOf(config.getString(player.getUniqueId().toString()));
        } else {
            return null;
        }
    }
}