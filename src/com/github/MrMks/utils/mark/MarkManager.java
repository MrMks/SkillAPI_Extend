package com.github.MrMks.utils.mark;

import com.sucy.skill.SkillAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.util.LinkedHashMap;
import java.util.UUID;

public class MarkManager {
    private static LinkedHashMap<UUID,LinkedHashMap<String, Integer>> map = new LinkedHashMap<>();

    public static boolean hasMark(LivingEntity target, String key){
        return hasMark(target.getUniqueId(),key);
    }

    private static boolean hasMark(UUID uuid, String key){
        return map.containsKey(uuid) && map.get(uuid).containsKey(key);
    }

    public static void addMark(LivingEntity target, String key, int amount, int max, int ticks){
        addMark(target.getUniqueId(), key, amount, max, ticks);
    }

    private static void addMark(UUID uuid, String key, int amount, int max, int ticks){
        if (!map.containsKey(uuid)){
            map.put(uuid,new LinkedHashMap<>());
        }

        if (!map.get(uuid).containsKey(key)){
            setMark(uuid, key, amount,max,ticks);
        } else {
            amount += map.get(uuid).get(key);
            if (amount <= 0){
                removeMark(uuid,key);
            } else {
                amount = amount > max ? max : amount;
                map.get(uuid).put(key,amount);
                refreshCleaner(uuid,key,ticks);
            }
        }
    }

    public static void setMark(LivingEntity target, String key, int value, int max, int ticks){
        setMark(target.getUniqueId(), key, value, max, ticks);
    }

    private static void setMark(UUID uuid, String key, int value, int max, int ticks){
        if (value <= 0) return;
        if (!map.containsKey(uuid)){
            map.put(uuid,new LinkedHashMap<>());
        }

        value = value > max ? max : value;

        LinkedHashMap<String,Integer> subMap = map.get(uuid);
        subMap.put(key,value);

        addCleaner(uuid,key,ticks);
    }

    public static int getMarkCount(LivingEntity entity, String key){
        return getMarkCount(entity.getUniqueId(), key);
    }

    private static int getMarkCount(UUID uuid, String key){
        if(hasMark(uuid, key)){
            return map.get(uuid).get(key);
        }
        return 0;
    }

    public static void removeMark(LivingEntity target, String key){
        removeMark(target.getUniqueId(), key);
    }

    static void removeMark(UUID uuid, String key){
        if (hasMark(uuid, key)) map.get(uuid).remove(key);
        removeCleaner(uuid,key);
    }

    private static LinkedHashMap<UUID,LinkedHashMap<String, MarkCleaner>> cleaners = new LinkedHashMap<>();

    public static void addCleaner(LivingEntity tar, String key, int radius){
        addCleaner(tar.getUniqueId(), key, radius);
    }

    private static void addCleaner(UUID uuid, String key, int ticks){
        if (!cleaners.containsKey(uuid)){
            cleaners.put(uuid,new LinkedHashMap<>());
        }

        MarkCleaner runnable = new MarkCleaner(uuid,key);
        cleaners.get(uuid).put(key,runnable);
        //SkillAPI.schedule(runnable,ticks);
        //runnable.runTaskLater(Bukkit.getPluginManager().getPlugin("skillapi_addon"),ticks);
        runnable.runTaskLater(ticks);
    }

    public static void refreshCleaner(LivingEntity tar, String key, int ticks){
        refreshCleaner(tar.getUniqueId(), key, ticks);
    }

    private static void refreshCleaner(UUID uuid, String key, int ticks){
        if (!(cleaners.containsKey(uuid) && cleaners.get(uuid).containsKey(key))){
            addCleaner(uuid, key, ticks);
            return;
        }

        removeCleaner(uuid, key);
        addCleaner(uuid, key, ticks);
    }

    public static void removeCleaner(LivingEntity livingEntity, String key){
        removeCleaner(livingEntity.getUniqueId(),key);
    }

    synchronized static void removeCleaner(UUID uuid, String key){
        if(!(cleaners.containsKey(uuid) && cleaners.get(uuid).containsKey(key))) return;
        cleaners.get(uuid).get(key).stop();
        //cleaners.get(uuid).get(key).cancel();
        cleaners.get(uuid).remove(key);
    }
}
