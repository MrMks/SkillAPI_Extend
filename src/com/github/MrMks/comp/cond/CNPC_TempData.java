package com.github.MrMks.comp.cond;

import com.github.MrMks.utils.CNPC;

import java.util.UUID;

public class CNPC_TempData extends CNPC_Data {
    @Override
    public String getKey() {
        return "CNPC Temp Data";
    }

    @Override
    public String getDescription() {
        return "Check whether the target has the Tempdata";
    }

    @Override
    boolean check(UUID uuid, String key) {
        return CNPC.hasTempData(uuid, key);
    }
}
