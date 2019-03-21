package com.github.MrMks.comp.mach;

import com.github.MrMks.utils.CNPC;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.UUID;

public class CNPC_ClearData extends CNPC_Base {
    @Override
    public String getKey() {
        return "CNPC Clear Data";
    }

    @Override
    public String getDescription() {
        return "Clear a Cnpc Data";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.dropdown("tar","Target","[tar]T means targets and F means caster",booleanList),
                EditorOption.dropdown("type","Type","[type]T means Stored and F means Temp",booleanList)
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        boolean tar     = settings.getBool("tar",true);
        boolean type    = settings.getBool("type",true);

        if (tar){
            for (LivingEntity e : list){
                clearData(e.getUniqueId(),type);
            }
        } else {
            clearData(livingEntity.getUniqueId(),type);
        }

        return true;
    }

    private void clearData(UUID uuid, boolean type){
        if (type){
            CNPC.clearStoredData(uuid);
        } else{
            CNPC.clearTempData(uuid);
        }
    }
}
