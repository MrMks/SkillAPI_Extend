package com.github.MrMks.comp.cond;

import com.github.MrMks.utils.CNPC;

import java.util.Objects;
import java.util.UUID;

public class CNPC_TempDataEqual extends CNPC_DataEqual {
    @Override
    boolean check(UUID uuid1, String key1, UUID uuid2, String key2) {
        return Objects.equals(CNPC.getTempData(uuid1,key1),CNPC.getTempData(uuid2,key2));
    }

    @Override
    public String getKey() {
        return "CNPC Temp Data Equal";
    }

    @Override
    public String getDescription() {
        return "Check if the two Tempdata is equal";
    }
}
