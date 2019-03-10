package com.github.MrMks.comp.mach;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.List;

public class Wait extends CustomEffectComponent {
    @Override
    public String getKey() {
        return "Wait";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.MECHANIC;
    }

    @Override
    public String getDescription() {
        return "Wait for a short time";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.number("radius","Radius","[radius]The seconds will wait.",(double)3,(double)-1)
        );
    }

    @Override
    public String getDisplayName() {
        return "Wait";
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        //HashMap<String,Object> data = DynamicSkill.getCastData(livingEntity);
        long miles = Math.round(parseValues(livingEntity,"radius",i,3)*1000);
        System.out.print(miles);
        try {
            Thread.sleep(miles);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
