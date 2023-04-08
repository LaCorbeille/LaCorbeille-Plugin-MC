package com.noasecond.lacorbeille.commands;

import com.noasecond.lacorbeille.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class CommandColor implements CommandExecutor {
    private static ConfigManager configManager;
    public CommandColor(Plugin plugin) {
        configManager = new ConfigManager(plugin, "colors.yml");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Utilisation: /color <couleur>");
            return true;
        }

        ChatColor color = null;

        try {
            color = ChatColor.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage("Cette couleur n'est pas valide. Couleurs valides : ");
            for (ChatColor c : ChatColor.values()) {
                if (c.isColor()) {
                    player.sendMessage(c.toString() + c.name().toLowerCase());
                }
            }
            return true;
        }

        player.setDisplayName(color + player.getName()); //Color in tchat
        player.setPlayerListName(color + player.getName()); //Color in tablist
        player.sendMessage("Votre nom a été coloré en " + color + args[0]);

        configManager.setPlayerColor(player, color); //Save color in conf

        return true;
    }
}