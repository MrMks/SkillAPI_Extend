package com.github.MrMks.utils.mark;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class MarkCleaner extends BukkitRunnable {

    private UUID uuid;
    private String key;

    private boolean stopped = false;

    MarkCleaner(UUID uuid, String key){
        this.uuid = uuid;
        this.key = key;
    }

    void stop(){
        this.stopped = true;
    }

    @Override
    public void run() {
        if (stopped) return;
        MarkManager.removeMark(uuid,key);
        MarkManager.removeCleaner(uuid,key);
    }

    public MarkCleaner newCleaner(){
        return new MarkCleaner(uuid,key);
    }
}
