package com.github.MrMks.comp.mach.timer;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedHashMap;

public class TimerRunnable extends BukkitRunnable {

    public TimerRunnable(String key){
        list.put(key,this);
    }

    private int ticks;
    @Override
    public void run() {
        ticks += 1;
    }

    public int getTicks() {
        return ticks;
    }

    private static LinkedHashMap<String,TimerRunnable> list = new LinkedHashMap<>();
    public static TimerRunnable getTimer(String key){
        return list.get(key);
    }
}
