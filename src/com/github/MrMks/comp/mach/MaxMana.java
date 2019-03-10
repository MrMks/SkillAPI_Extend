package com.github.MrMks.comp.mach;

import com.sucy.skill.api.player.PlayerData;

public class MaxMana extends MaxValueChange{
    @Override
    protected void e(PlayerData data, double v) {
        data.addMaxMana(v);
    }

    @Override
    public String getKey() {
        return "Max Mana";
    }
}
