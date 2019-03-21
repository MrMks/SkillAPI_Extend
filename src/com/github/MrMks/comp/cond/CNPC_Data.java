package com.github.MrMks.comp.cond;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class CNPC_Data extends Cond_Base {

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key","Key","[key]","key")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String key = settings.getString("key","key");
        List<LivingEntity> entities = new ArrayList<>();
        for (LivingEntity e : list){
            if (check(e.getUniqueId(),key)) entities.add(e);
        }
        return entities.size() <= 0 && executeChildren(livingEntity, i, entities);
    }

    abstract boolean check(UUID uuid, String key);
}
