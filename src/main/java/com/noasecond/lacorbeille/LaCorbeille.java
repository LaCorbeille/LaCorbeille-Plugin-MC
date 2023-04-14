package com.noasecond.lacorbeille;

import com.noasecond.lacorbeille.commands.CommandColor;
import com.noasecond.lacorbeille.commands.CommandDiscord;
import com.noasecond.lacorbeille.commands.CommandLobby;
import com.noasecond.lacorbeille.events.JoinEvent;
import com.noasecond.lacorbeille.events.calendarEvents.SaveAndCloseEvent;
import com.noasecond.lacorbeille.events.lobbyevents.JoinLobbyEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;

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

        //CalendarEvent
        Calendar now = Calendar.getInstance();
        int currentHour = now.get(Calendar.HOUR_OF_DAY);
        SaveAndCloseEvent saveAndCloseEvent = new SaveAndCloseEvent(now, currentHour, this);

        //Logger
        getLogger().info("Plugin active !");
    }

    @Override
    public void onDisable() {
        //Logger
        getLogger().info("Plugin desactive !");
    }
}