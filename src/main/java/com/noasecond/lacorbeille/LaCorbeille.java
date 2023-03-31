package com.noasecond.lacorbeille;

import com.noasecond.lacorbeille.commands.CommandDiscord;
import com.noasecond.lacorbeille.commands.CommandLobby;
import org.bukkit.plugin.java.JavaPlugin;

public final class LaCorbeille extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("discord").setExecutor(new CommandDiscord());
        getCommand("lobby").setExecutor(new CommandLobby());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
