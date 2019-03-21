package com.github.MrMks.comp.mach;

import com.github.MrMks.utils.timer.TimerRunnable;
import com.sucy.skill.api.util.FlagManager;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimerStop extends Mach_Base {
    @Override
    public String getKey() {
        return "Timer Stop";
    }

    @Override
    public String getDescription() {
        return "Stop the last timer. The time will be stored under \'last_time\' as a value. If there was no Timer, the value will be 0";
    }

    @Override
    public List<EditorOption> getOptions() {
        return new ArrayList<>();
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        //return ;
        boolean is = FlagManager.hasFlag(livingEntity,"skillapi_addon_timer");

        HashMap<String,Object> data = DynamicSkill.getCastData(livingEntity);
        if(!is){
            data.put("last_time",(double)0);
        } else {
            //double start = (double)data.get("skillapi_addon_timer_start");
            int ticks = TimerRunnable.getTimer(livingEntity.getName()).getTicks();
            TimerRunnable.getTimer(livingEntity.getName()).cancel();
            data.put("last_time",ticks/20.0);
            FlagManager.removeFlag(livingEntity,"skillapi_addon_timer");
        }
        return true;
    }
}
