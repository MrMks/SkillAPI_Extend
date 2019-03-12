package com.github.MrMks.comp.mach;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;
import utils.mark.MarkManager;

import java.util.List;

public class MarkAddMach extends CustomEffectComponent {
    @Override
    public String getKey() {
        return "Mark Add";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.MECHANIC;
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
                EditorOption.number("radius","Radius","[radius]",10,0)
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String key = (String) DynamicSkill.getCastData(livingEntity).get("key");
        long amount = Math.round(parseValues(livingEntity,"amount",i,1));
        long max = Math.round(parseValues(livingEntity,"max",i,10));
        long radius = Math.round(parseValues(livingEntity,"radius",i,10)) * 20;

        amount = amount > max ? max : amount;
        if (MarkManager.hasMark(livingEntity,key)){
            amount += MarkManager.getMarkCount(livingEntity,key);
            amount = amount > max ? max : amount;
            MarkManager.addMark(livingEntity,key,(int)amount);
            MarkManager.refreshCleaner(livingEntity,key,(int)radius);
        }

        return true;
    }
}
