package com.github.MrMks.utils.timer;

import com.github.MrMks.utils.SkillAPIAddonRunnable;

import java.util.LinkedHashMap;

public class TimerRunnable extends SkillAPIAddonRunnable {

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
