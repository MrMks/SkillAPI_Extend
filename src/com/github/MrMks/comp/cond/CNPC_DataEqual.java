package com.github.MrMks.comp.cond;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class CNPC_DataEqual extends Cond_Base {
    private List<String> tars = new ArrayList<>();
    {
        tars.add("True");
        tars.add("False");
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key1","Key 1","[key1]","key1"),
                EditorOption.dropdown("tar1","Target Caster","[tar1]",tars),
                EditorOption.text("key2","Key 2","[key2]","key2"),
                EditorOption.dropdown("tar2","Target Caster","[tar2]",tars)
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String key1     = settings.getString("key1","key");
        String key2     = settings.getString("key2","key");
        boolean tar1    = settings.getBool("tar1",true);
        boolean tar2    = settings.getBool("tar2",true);

        List<LivingEntity> entities = new ArrayList<>();

        for (LivingEntity e:list){
            UUID uuid1 = tar1 ? livingEntity.getUniqueId() : e.getUniqueId();
            UUID uuid2 = tar2 ? livingEntity.getUniqueId() : e.getUniqueId();
            if (check(uuid1,key1,uuid2,key2)) entities.add(e);
        }

        return entities.size()> 0 && executeChildren(livingEntity, i, entities);
    }

    abstract boolean check(UUID uuid1, String key1, UUID uuid2, String key2);
}
