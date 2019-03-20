package com.github.MrMks.comp.mach;

import com.github.MrMks.utils.timer.TimerRunnable;
import com.sucy.skill.api.util.FlagManager;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class TimerStart extends CustomEffectComponent {
    @Override
    public String getKey() {
        return "Timer Start";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.MECHANIC;
    }

    @Override
    public String getDescription() {
        return "Start a timer on caster. If the caster hosting a timer, then this mechanic will do nothing You can use the \'Timer stop\' to stop the timer";
    }

    @Override
    public List<EditorOption> getOptions() {
        return new ArrayList<>();
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if(FlagManager.hasFlag(livingEntity,"skillapi_addon_timer")) return false;

        //PlayerData data = getSkillData(livingEntity).getPlayerData();
        //double start = System.currentTimeMillis();
        //HashMap<String, Object> data = DynamicSkill.getCastData(livingEntity);
        //data.put("skillapi_addon_timer_start",start);
        //SkillAPI.schedule(new TimerRunnable(livingEntity.getName()),0,0);
        (new TimerRunnable(livingEntity.getName())).runTaskTimer(0,0);
        FlagManager.addFlag(livingEntity,"skillapi_addon_timer",-1);
        return true;
    }
}
