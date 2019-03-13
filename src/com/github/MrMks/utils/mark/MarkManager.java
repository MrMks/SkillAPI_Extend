package com.github.MrMks.utils.mark;

import com.sucy.skill.SkillAPI;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

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

    public static void addMark(LivingEntity target, String key, int amount, int max){
        addMark(target.getUniqueId(), key, amount, max);
    }

    private static void addMark(UUID uuid, String key, int amount, int max){
        if (!map.containsKey(uuid)){
            map.put(uuid,new LinkedHashMap<>());
        }

        if (!map.get(uuid).containsKey(key)){
            setMark(uuid, key, amount,max);
        } else {
            amount += map.get(uuid).get(key);
            if (amount <= 0){
                removeMark(uuid,key);
            } else {
                amount = amount > max ? max : amount;
                map.get(uuid).remove(key);
                map.get(uuid).put(key,amount);
            }
        }

        /*
        System.out.print("addMark");
        System.out.print(map);
        System.out.println();
        */
    }

    public static void setMark(LivingEntity target, String key, int value, int max){
        setMark(target.getUniqueId(), key, value, max);

    }

    private static void setMark(UUID uuid, String key, int value, int max){
        if (value <= 0) return;
        if (!map.containsKey(uuid)){
            map.put(uuid,new LinkedHashMap<>());
        }

        value = value > max ? max : value;

        LinkedHashMap<String,Integer> subMap = map.get(uuid);

        subMap.remove(key);
        subMap.put(key,value);
    }

    public static int getMarkCount(LivingEntity entity, String key){
        return getMarkCount(entity.getUniqueId(), key);
    }

    private static int getMarkCount(UUID uuid, String key){
        if(hasMark(uuid, key)){

            /*
            System.out.print("getCount");
            System.out.print(map.get(uuid).get(key));
            System.out.println();
            */

            return map.get(uuid).get(key);
        }
        return 0;
    }

    public static void removeMark(LivingEntity target, String key){
        removeMark(target.getUniqueId(), key);
    }

    static void removeMark(UUID uuid, String key){
        if (hasMark(uuid, key)) map.get(uuid).remove(key);
    }


    private static LinkedHashMap<UUID,LinkedHashMap<String, MarkCleaner>> cleaners = new LinkedHashMap<>();

    public static void addCleaner(LivingEntity tar, String key, int radius){
        addCleaner(tar.getUniqueId(), key, radius);
    }

    private static void addCleaner(UUID uuid, String key, int radius){
        if (!cleaners.containsKey(uuid)){
            cleaners.put(uuid,new LinkedHashMap<>());
        }

        MarkCleaner runnable = new MarkCleaner(uuid,key);
        cleaners.get(uuid).put(key,runnable);
        SkillAPI.schedule(runnable,radius);
    }

    public static void refreshCleaner(LivingEntity tar, String key, int radius){
        refreshCleaner(tar.getUniqueId(), key, radius);
    }

    private static void refreshCleaner(UUID uuid, String key, int radius){
        if (!(cleaners.containsKey(uuid) && cleaners.get(uuid).containsKey(key))){
            addCleaner(uuid, key, radius);
            return;
        }

        MarkCleaner runnable = cleaners.get(uuid).get(key);

        runnable.stop();
        cleaners.get(uuid).remove(key);
        addCleaner(uuid, key, radius);

        /*
        System.out.print("refresh");
        System.out.print(cleaners);
        System.out.println();
        */
    }

    public static void removeCleaner(LivingEntity livingEntity, String key){
        removeCleaner(livingEntity.getUniqueId(),key);
    }

    static void removeCleaner(UUID uuid, String key){
        cleaners.get(uuid).get(key).cancel();
        cleaners.get(uuid).remove(key);

        /*
        System.out.print("remove");
        System.out.print(cleaners);
        System.out.println();
        */
    }
}
