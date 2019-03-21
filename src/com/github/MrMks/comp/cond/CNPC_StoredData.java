package com.github.MrMks.comp.cond;

import com.github.MrMks.utils.CNPC;

import java.util.UUID;

public class CNPC_StoredData extends CNPC_Data {
    @Override
    public String getKey() {
        return "CNPC Stored Data";
    }

    @Override
    public String getDescription() {
        return "check whether the target have the key in Storeddata";
    }

    @Override
    boolean check(UUID uuid, String key) {
        return CNPC.hasStoredData(uuid,key);
    }

}
