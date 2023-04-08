package com.noasecond.lacorbeille.commands;

import com.noasecond.lacorbeille.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandColor implements CommandExecutor, TabCompleter {
    private static ConfigManager configManager;
    public CommandColor(Plugin plugin) {
        configManager = new ConfigManager(plugin, "colors.yml");
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> firstArg = new ArrayList<String>();
            firstArg.add("list");
            firstArg.add(ChatColor.BLACK.name().toLowerCase());
            firstArg.add(ChatColor.DARK_BLUE.name().toLowerCase());
            firstArg.add(ChatColor.DARK_GREEN.name().toLowerCase());
            firstArg.add(ChatColor.DARK_AQUA.name().toLowerCase());
            firstArg.add(ChatColor.DARK_RED.name().toLowerCase());
            firstArg.add(ChatColor.DARK_PURPLE.name().toLowerCase());
            firstArg.add(ChatColor.GOLD.name().toLowerCase());
            firstArg.add(ChatColor.GRAY.name().toLowerCase());
            firstArg.add(ChatColor.DARK_GRAY.name().toLowerCase());
            firstArg.add(ChatColor.BLUE.name().toLowerCase());
            firstArg.add(ChatColor.GREEN.name().toLowerCase());
            firstArg.add(ChatColor.AQUA.name().toLowerCase());
            firstArg.add(ChatColor.RED.name().toLowerCase());
            firstArg.add(ChatColor.LIGHT_PURPLE.name().toLowerCase());
            firstArg.add(ChatColor.YELLOW.name().toLowerCase());
            firstArg.add(ChatColor.WHITE.name().toLowerCase());
            return firstArg;
        } else {
            return null;
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Utilisation: /color list");
            player.sendMessage("Utilisation: /color <couleur>");
            return true;
        }

        ChatColor color = null;

        try {
            color = ChatColor.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            if (!args[0].toUpperCase().equals("LIST")) {
                player.sendMessage("Cette couleur n'est pas valide !");
            }
            String colorList = "Couleurs : ";
            for (ChatColor c : ChatColor.values()) {
                if (c.isColor()) {
                    colorList += c.toString() + c.name().toLowerCase() + " ";
                }
            }
            player.sendMessage(colorList);
            return true;
        }

        player.setDisplayName(color + player.getName() + ChatColor.RESET); //Color in tchat
        player.setPlayerListName(color + player.getName()); //Color in tablist
        configManager.setPlayerColor(player, color); //Save color in conf
        player.sendMessage("Votre nom a été coloré en " + color + args[0]);
        player.playSound(player.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 7, 1);

        return true;
    }
}