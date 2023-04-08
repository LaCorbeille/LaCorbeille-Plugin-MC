package com.noasecond.lacorbeille.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CommandColor implements CommandExecutor {
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

        player.setDisplayName(color + player.getName()); //Couleur dans le tchat
        player.setPlayerListName(color + player.getName()); //Couleur dans le menu tab
        player.sendMessage("Votre nom a été coloré en " + color + args[0]);
        return true;
    }
}
