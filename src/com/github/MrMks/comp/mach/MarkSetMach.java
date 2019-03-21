package com.github.MrMks.comp.mach;

import com.github.MrMks.utils.mark.MarkManager;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class MarkSetMach extends Mach_Base {
    @Override
    public String getKey() {
        return "Mark Set";
    }

    @Override
    public String getDescription() {
        return "Set the Mark with the amount and the radius";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key","Key","[amount]","key"),
                EditorOption.number("amount","Amount","[amount]",1,0),
                EditorOption.number("max","Max","[max]",10,0),
                EditorOption.number("duration","Duration","[radius]",3,1)
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String key = settings.getString("key");
        int amount = (int)Math.round( parseValues(livingEntity,"amount",i,1));
        int max = (int) Math.round(parseValues(livingEntity,"max",i,10));
        int ticks = (int) Math.round(parseValues(livingEntity,"duration",i,3)) * 20;

        for (LivingEntity e :
                list) {
            MarkManager.setMark(e, key, amount,max,ticks);
            //MarkManager.addCleaner(e,key,ticks * 20);
        }

        return true;
    }
}
