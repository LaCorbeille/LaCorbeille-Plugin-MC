package com.noasecond.lacorbeille.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
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
        if (!(sender instanceof Player p))
            return false;
        ChatColor orangeForLink = ChatColor.of("#fd9644");

        TextComponent discord = new TextComponent("Rejoins le Discord §lLaCorbeille §r" +
                orangeForLink+"§l§9https://discord.gg/hmPzS4k" +
                " §r§f!");
        discord.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/hmPzS4k"));
        discord.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§bhttps://discord.gg/hmPzS4k")));
        p.spigot().sendMessage(discord);
        p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
        return true;
    }
}
