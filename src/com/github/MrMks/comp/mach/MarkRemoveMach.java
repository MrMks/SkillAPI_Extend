package com.github.MrMks.comp.mach;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;
import utils.mark.MarkManager;

import java.util.List;

public class MarkRemoveMach extends CustomEffectComponent {
    @Override
    public String getKey() {
        return "Mark Remove";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.MECHANIC;
    }

    @Override
    public String getDescription() {
        return "Remove the Mark";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
               EditorOption.text("key","Key","[key]","key")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String key = (String) DynamicSkill.getCastData(livingEntity).get("key");
        for (LivingEntity e :
                list) {
            MarkManager.removeCleaner(e, key);
            MarkManager.removeMark(e,key);
        }
        return true;
    }
}
