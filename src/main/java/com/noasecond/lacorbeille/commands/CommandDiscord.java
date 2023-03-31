package com.noasecond.lacorbeille.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDiscord implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("discord"))
            return false;
        sender.sendMessage("Rejoins le Discord §lLaCorbeille §r§9§uhttps://discord §9§u.gg/hmPzS4k §r§f!");
        if (sender instanceof Player p)
            p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
        return true;
    }
}
