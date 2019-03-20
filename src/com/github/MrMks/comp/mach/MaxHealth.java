package com.github.MrMks.comp.mach;

import com.sucy.skill.api.player.PlayerData;

public class MaxHealth extends MaxValueChange {

    @Override
    protected void e(PlayerData data,double v) {
        data.addMaxHealth(v);
    }

    @Override
    public String getKey() {
        return "Max Health";
    }
}
