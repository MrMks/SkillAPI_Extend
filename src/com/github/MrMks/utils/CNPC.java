package com.github.MrMks.utils;

import net.minecraft.entity.Entity;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class CNPC {
    private static NpcAPI api;
    private static IEntity getEntity(UUID uuid){
        if(api == null) api = NpcAPI.Instance();
        org.bukkit.entity.Entity entity = Bukkit.getEntity(uuid);
        try {
            return api.getIEntity((Entity)entity.getClass().getMethod("getHandle").invoke(entity));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    methods following to provide a method access cnpc saved datas;
     */
    //temp_data start
    public static boolean hasTempData(UUID uuid, String key){
        IEntity entity = getEntity(uuid);
        if(entity == null) return false;
        return entity.getTempdata().has(key);
    }
    
    public static Object getTempData(UUID uuid, String key){
        IEntity entity = getEntity(uuid);
        if(entity == null) return null;
        return entity.getTempdata().get(key);
    }
    
    public static void putTempData(UUID uuid, String key, Object value){
        IEntity entity = getEntity(uuid);
        if(entity == null) return;
        entity.getTempdata().put(key,value);
    }
    
    public static void removeTempData(UUID uuid, String key){
        IEntity entity = getEntity(uuid);
        if(entity == null) return;
        entity.getTempdata().remove(key);
    }
    
    public static String[] getTempDataKeys(UUID uuid){
        IEntity entity = getEntity(uuid);
        if(entity == null) return new String[0];
        return entity.getTempdata().getKeys();
    }
    
    public static void clearTempData(UUID uuid){
        IEntity entity = getEntity(uuid);
        if(entity == null) return;
        entity.getTempdata().clear();
    }
    //temp_data end
    
    //stored_data start
    public static boolean hasStoredData(UUID uuid, String key){
        IEntity entity = getEntity(uuid);
        if(entity == null) return false;
        return entity.getStoreddata().has(key);
    }

    public static Object getStoredData(UUID uuid, String key){
        IEntity entity = getEntity(uuid);
        if(entity == null) return null;
        return entity.getStoreddata().get(key);
    }

    public static void putStoredData(UUID uuid, String key, Object value){
        IEntity entity = getEntity(uuid);
        if(entity == null) return;
        entity.getStoreddata().put(key,value);
    }

    public static void removeStoredData(UUID uuid, String key){
        IEntity entity = getEntity(uuid);
        if(entity == null) return;
        entity.getStoreddata().remove(key);
    }

    public static String[] getStoredDataKeys(UUID uuid){
        IEntity entity = getEntity(uuid);
        if(entity == null) return new String[0];
        return entity.getStoreddata().getKeys();
    }

    public static void clearStoredData(UUID uuid){
        IEntity entity = getEntity(uuid);
        if(entity == null) return;
        entity.getStoreddata().clear();
    }
    //stored_data end


    public static void setPlayerFactionFoint(UUID uuid, int fac, int amount){
        IEntity entity = getEntity(uuid);
        if(entity == null) return;
        IPlayer player = (IPlayer) entity;
        player.addFactionPoints(fac,amount - player.getFactionPoints(fac));
    }

    public static int getPlayerFactionPoint(UUID uuid, int fac){
        IPlayer player = (IPlayer) getEntity(uuid);
        return player != null ? player.getFactionPoints(fac) : -1;
    }
}
