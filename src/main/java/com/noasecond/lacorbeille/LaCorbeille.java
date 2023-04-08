package com.noasecond.lacorbeille;

import com.noasecond.lacorbeille.commands.CommandColor;
import com.noasecond.lacorbeille.commands.CommandDiscord;
import com.noasecond.lacorbeille.commands.CommandLobby;
import com.noasecond.lacorbeille.lobbyevents.JoinLobbyEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class LaCorbeille extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("discord").setExecutor(new CommandDiscord());
        getCommand("lobby").setExecutor(new CommandLobby());
        getCommand("color").setExecutor(new CommandColor());
        getServer().getPluginManager().registerEvents(new JoinLobbyEvent(), this);

    }

    @Override
    public void onDisable() {
    }
}