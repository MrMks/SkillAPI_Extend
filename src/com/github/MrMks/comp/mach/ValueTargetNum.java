package com.github.MrMks.comp.mach;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.List;

public class ValueTargetNum extends Mach_Base {
    @Override
    public String getKey() {
        return "Value Target Number";
    }

    @Override
    public String getDescription() {
        return "Value the number of the target under a unique key";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key","Key","[key]The unique key to store the value under","value")
        );
    }

    @Override
    public boolean execute(LivingEntity caster, int level, List<LivingEntity> targets) {
        String key = settings.getString("key");
        int number = targets.size();
        HashMap<String,Object> data = DynamicSkill.getCastData(caster);
        data.put(key,(double)number);
        return false;
    }
}
