package com.github.MrMks.comp.cond;

import com.github.MrMks.utils.CNPC;

import java.util.Objects;
import java.util.UUID;

public class CNPC_StoredDataEqual extends CNPC_DataEqual {
    @Override
    public String getKey() {
        return "CNPC Stored Data Equal";
    }

    @Override
    public String getDescription() {
        return "Check if the two Objects are same";
    }

    protected boolean check(UUID uuid1, String key1, UUID uuid2, String key2){
        return Objects.equals(CNPC.getStoredData(uuid1, key1), CNPC.getStoredData(uuid2, key2));
    }
}
