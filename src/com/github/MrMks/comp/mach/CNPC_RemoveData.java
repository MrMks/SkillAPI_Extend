package com.github.MrMks.comp.mach;

import com.github.MrMks.utils.CNPC;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.UUID;

public class CNPC_RemoveData extends CNPC_Base {
    @Override
    public String getKey() {
        return "CNPC Remove Data";
    }

    @Override
    public String getDescription() {
        return "Remove a cnpc data";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.dropdown("tar","Target","[tar]True means revome on targets and False means remove on caster",booleanList),
                EditorOption.dropdown("type","Type","[type]T means Stroed and F means Temp",booleanList),
                EditorOption.text("key","Key","[key]","key")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        boolean tar     = settings.getBool("tar",true);
        boolean type    = settings.getBool("type",true);
        String key      = settings.getString("key","key");
        UUID uuid;
        if (tar) {
            for(LivingEntity e:list){
                uuid = e.getUniqueId();
                removeData(uuid,key,type);
            }
        } else {
            uuid = livingEntity.getUniqueId();
            removeData(uuid,key,type);
        }
        return true;
    }

    private void removeData(UUID uuid, String key, boolean type){
        if (type) {
            CNPC.removeStoredData(uuid, key);
        } else {
            CNPC.removeTempData(uuid, key);
        }
    }
}
