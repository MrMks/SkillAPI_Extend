package com.github.MrMks.comp.mach;

import com.github.MrMks.utils.mark.MarkManager;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class MarkRemoveMach extends Mach_Base {
    @Override
    public String getKey() {
        return "Mark Remove";
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
        String key = settings.getString("key");
        for (LivingEntity e :
                list) {
            //MarkManager.removeCleaner(e, key);
            MarkManager.removeMark(e,key);
        }
        return true;
    }
}
