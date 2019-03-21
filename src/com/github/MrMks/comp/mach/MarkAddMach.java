package com.github.MrMks.comp.mach;

import com.github.MrMks.utils.mark.MarkManager;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class MarkAddMach extends Mach_Base {
    @Override
    public String getKey() {
        return "Mark Add";
    }

    @Override
    public String getDescription() {
        return "Add marks to target.The amount can be negative.The sum of mark will not be over than max";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key","Key","[key]","key"),
                EditorOption.number("amount","Amount","[amount]",1,0),
                EditorOption.number("max","Max","[max]",10,0),
                EditorOption.number("duration","Duration","[radius]",10,0)
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String key = settings.getString("key");
        int amount = (int) Math.round(parseValues(livingEntity,"amount",i,1));
        int max = (int)Math.round(parseValues(livingEntity,"max",i,10));
        int ticks = (int)Math.round(parseValues(livingEntity,"duration",i,10)) * 20;

        amount = amount > max ? max : amount;
        for(LivingEntity entity:list){
            MarkManager.addMark(entity,key,amount,max,ticks);
            //MarkManager.refreshCleaner(entity,key,ticks);
        }

        return true;
    }
}
