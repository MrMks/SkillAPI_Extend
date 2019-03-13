package com.github.MrMks.comp.cond;

import com.github.MrMks.utils.mark.MarkManager;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.LinkedList;
import java.util.List;

public class HaveMark extends CustomEffectComponent {
    @Override
    public String getKey() {
        return "Have Mark";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.CONDITION;
    }

    @Override
    public String getDescription() {
        return "Check if the target have mark";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key","Key","[key]","key")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String key = settings.getString("key");
        LinkedList<LivingEntity> tmp = new LinkedList<>();
        boolean re = false;
        for (LivingEntity entity:list){
            if (MarkManager.hasMark(entity,key)){
                tmp.add(entity);
                re = re | executeChildren(livingEntity,i,tmp);
                tmp.clear();
            }
        }
        return re;
    }
}
