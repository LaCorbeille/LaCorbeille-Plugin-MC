package com.noasecond.lacorbeille;

import com.noasecond.lacorbeille.commands.CommandColor;
import com.noasecond.lacorbeille.commands.CommandDiscord;
import com.noasecond.lacorbeille.commands.CommandLobby;
import com.noasecond.lacorbeille.events.JoinEvent;
import com.noasecond.lacorbeille.events.plannedEvents.SaveAndCloseEvent;
import com.noasecond.lacorbeille.events.lobbyevents.JoinLobbyEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.time.LocalTime;

public final class LaCorbeille extends JavaPlugin {

    @Override
    public void onEnable() {
        //Commands
        getCommand("discord").setExecutor(new CommandDiscord());
        getCommand("lobby").setExecutor(new CommandLobby());
        getCommand("color").setExecutor(new CommandColor(this));

        //Events
        getServer().getPluginManager().registerEvents(new JoinLobbyEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);

        //PlannedEvents
        SaveAndCloseEvent.saveAndClosePlanning(this);

        //Logger
        getLogger().info("Plugin active !");
        getLogger().info("Delay en secondes avant save&close : "
                + SaveAndCloseEvent.getDelay() / 20L + "s");
    }


    @Override
    public void onDisable() {
        //Logger
        getLogger().info("Plugin desactive !");
        Bukkit.getScheduler().cancelTask(SaveAndCloseEvent.getTaskID());
    }
}