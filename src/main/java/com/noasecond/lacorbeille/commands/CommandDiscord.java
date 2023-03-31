package com.noasecond.lacorbeille.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandDiscord implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("discord"))
            return false;
        sender.sendMessage("https://discord.gg/hmPzS4k");
        return true;
    }
}
