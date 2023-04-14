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
    private int taskID;

    @Override
    public void onEnable() {
        System.out.println("onenable");
        //Commands
        getCommand("discord").setExecutor(new CommandDiscord());
        getCommand("lobby").setExecutor(new CommandLobby());
        getCommand("color").setExecutor(new CommandColor(this));

        //Events
        getServer().getPluginManager().registerEvents(new JoinLobbyEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);

        //Save&Close
        int currentTimeHour = LocalTime.now().getHour();
        int currentTimeMinute = LocalTime.now().getMinute();
        LocalTime currentTime = LocalTime.of(currentTimeHour, currentTimeMinute);

        int targetTimeHour = 16;
        int targetTimeMinute = 8;
        LocalTime targetTime = LocalTime.of(targetTimeHour, targetTimeMinute);

        Duration timeRemaining = Duration.between(currentTime, targetTime);
        long delay = (timeRemaining.toMillis() / 1000) * 20L;

        taskID = SaveAndCloseEvent.saveAndClosePlanning(delay, this);

        //Logger
        getLogger().info("Plugin active !");
    }


    @Override
    public void onDisable() {
        //Logger
        getLogger().info("Plugin desactive !");
        Bukkit.getScheduler().cancelTask(taskID);
    }
}