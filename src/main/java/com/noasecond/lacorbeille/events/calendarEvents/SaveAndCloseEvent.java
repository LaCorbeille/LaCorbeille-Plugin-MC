package com.noasecond.lacorbeille.events.calendarEvents;

import com.noasecond.lacorbeille.LaCorbeille;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class SaveAndCloseEvent {
    public SaveAndCloseEvent(Calendar now, int currentHour, LaCorbeille laCorbeille) {
        int delay = (currentHour >= 22 ? 24 : 0) + (22 - currentHour);
        long ticks = TimeUnit.HOURS.toSeconds(delay) * 20;

        //Warning message
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.RED + "Le serveur ferme dans 5 minutes !");
            }
        }.runTaskLater(laCorbeille, ticks - TimeUnit.MINUTES.toSeconds(5));

        //Save-all
        BukkitTask saveAllTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
            }
        }.runTaskTimer(laCorbeille, ticks, TimeUnit.DAYS.toSeconds(1));

        //Stop
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getScheduler().isCurrentlyRunning(saveAllTask.getTaskId())) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
                }
            }
        }.runTaskLater(laCorbeille, ticks + TimeUnit.SECONDS.toSeconds(1));
    }
}