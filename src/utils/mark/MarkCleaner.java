package utils.mark;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class MarkCleaner extends BukkitRunnable {

    private UUID uuid;
    private String key;

    MarkCleaner(UUID uuid, String key){
        this.uuid = uuid;
        this.key = key;
    }

    @Override
    public void run() {
        MarkManager.removeMark(uuid,key);
        MarkManager.removeCleaner(uuid,key);
    }
}
