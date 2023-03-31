package com.noasecond.lacorbeille.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("lobby"))
            return false;
        if (sender instanceof Player p) {
            p.sendMessage("Vous avez été téléporté au spawn du lobby.");
            p.teleport(Bukkit.getWorld("lobby").getSpawnLocation());
        }
        return true;
    }
}
