package com.github.MrMks.utils.mark;

import com.github.MrMks.utils.SkillAPIAddonRunnable;

import java.util.UUID;

public class MarkCleaner extends SkillAPIAddonRunnable {

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

        if (stopped) {
            cancel();
            return;
        }

        MarkManager.removeMark(uuid,key);
        MarkManager.removeCleaner(uuid,key);
    }

    /*
    public MarkCleaner newCleaner(){
        return new MarkCleaner(uuid,key);
    }
    */
}
