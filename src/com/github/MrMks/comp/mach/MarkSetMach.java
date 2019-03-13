package com.github.MrMks.comp.mach;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;
import com.github.MrMks.utils.mark.MarkManager;

import java.util.List;

public class MarkSetMach extends CustomEffectComponent {
    @Override
    public String getKey() {
        return "Mark Set";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.MECHANIC;
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
                EditorOption.number("radius","Radius","[radius]",3,1)
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String key = settings.getString("key");
        int amount = (int)Math.round( parseValues(livingEntity,"amount",i,1));
        int max = (int) Math.round(parseValues(livingEntity,"max",i,10));
        int radius = (int) Math.round(parseValues(livingEntity,"radius",i,3));

        for (LivingEntity e :
                list) {
            MarkManager.setMark(e, key, amount,max);
            MarkManager.addCleaner(e,key,radius * 20);
        }

        return true;
    }
}
