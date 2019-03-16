package com.github.MrMks.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public abstract class SkillAPIAddonRunnable extends BukkitRunnable {

    public synchronized BukkitTask runTask() throws IllegalArgumentException, IllegalStateException {
        return super.runTask(getPlugin());
    }

    public synchronized BukkitTask runTaskLater(long delay) throws IllegalArgumentException, IllegalStateException {
        return super.runTaskLater(getPlugin(), delay);
    }

    public synchronized BukkitTask runTaskTimer(long delay, long period) throws IllegalArgumentException, IllegalStateException {
        return super.runTaskTimer(getPlugin(), delay, period);
    }

    private Plugin getPlugin(){
        return Bukkit.getPluginManager().getPlugin("skillapi_addon");
    }
}
