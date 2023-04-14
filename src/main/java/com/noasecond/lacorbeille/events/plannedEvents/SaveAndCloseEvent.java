package com.noasecond.lacorbeille.events.plannedEvents;

import com.noasecond.lacorbeille.LaCorbeille;
import org.bukkit.Bukkit;

import java.time.Duration;
import java.time.LocalTime;

public class SaveAndCloseEvent {
    private static final int TARGET_TIME_HOUR = 22;
    private static final int TARGET_TIME_MINUTE = 15;
    private static int taskID;
    private static long delay;

    public static void saveAndClosePlanning(LaCorbeille plugin) {
        int currentTimeHour = LocalTime.now().getHour();
        int currentTimeMinute = LocalTime.now().getMinute();
        LocalTime currentTime = LocalTime.of(currentTimeHour, currentTimeMinute);


        LocalTime targetTime = LocalTime.of(TARGET_TIME_HOUR, TARGET_TIME_MINUTE);

        Duration timeRemaining = Duration.between(currentTime, targetTime);
        delay = (timeRemaining.toMillis() / 1000) * 20L;
        delay = delay < 0 ? delay + 86400L * 20L : delay; //+24h
        taskID = Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Bukkit.broadcastMessage("Le serveur va s'éteindre dans 5 minutes !");
            //save-all
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
                //stop
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
                }, 3 * 60 * 20L); //2min
            }, (2 * 60) * 20L); //2min
        }, delay).getTaskId();
    }

    public static int getTaskID() {
        return taskID;
    }

    public static long getDelay() {
        return delay;
    }
}