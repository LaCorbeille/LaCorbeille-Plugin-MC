package com.noasecond.lacorbeille.events.plannedEvents;

import com.noasecond.lacorbeille.LaCorbeille;
import org.bukkit.Bukkit;

public class SaveAndCloseEvent {
    public static int saveAndClosePlanning(long delay, LaCorbeille plugin) {
        return Bukkit.getScheduler().runTaskLater(plugin, () -> {
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
}