package com.github.MrMks.comp.mach;

import com.github.MrMks.utils.CNPC;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CNPC_ValueData extends CNPC_Base {
    @Override
    public String getKey() {
        return "CNPC Value From";
    }

    @Override
    public String getDescription() {
        return "Value the data from/to cnpc to/from sapi";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.dropdown("from","From Cnpc","[from]False means value sapi data to cnpc",booleanList),
                EditorOption.text("ckey","Cnpc Key","[ckey]","key"),
                EditorOption.dropdown("ctar","Cnpc Target","[ctar]True means read from target and False means read from caster.If your have many targets, only the first one will be read.",booleanList),
                EditorOption.dropdown("type","Cnpc Data Type","[type]True means Stored and False means Temp",booleanList),
                EditorOption.text("skey","Sapi Key","[skey]","key")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        boolean from    = settings.getBool("from",true);
        String ckey     = settings.getString("ckey","key");
        boolean ctar    = settings.getBool("ctar",true);
        boolean type    = settings.getBool("type",true);
        String skey     = settings.getString("skey","key");

        UUID uuid = ctar ? list.get(0).getUniqueId() : livingEntity.getUniqueId();

        HashMap<String,Object> s_data = DynamicSkill.getCastData(livingEntity);

        if (from) {
            s_data.put(skey,type ? CNPC.getStoredData(uuid,ckey) : CNPC.getTempData(uuid,ckey));
        } else {
            if (type) {
                CNPC.putStoredData(uuid,ckey,s_data.get(skey));
            } else {
                CNPC.putTempData(uuid,ckey,s_data.get(skey));
            }
        }
        return true;
    }
}
