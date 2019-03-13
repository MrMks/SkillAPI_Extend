package com.github.MrMks.comp.mach;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;
import com.github.MrMks.utils.mark.MarkManager;

import java.util.List;

public class MarkToValue extends CustomEffectComponent {
    @Override
    public String getKey() {
        return "Mark To Value";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.MECHANIC;
    }

    @Override
    public String getDescription() {
        return "Count tbe marks amd store it to the Caster.If you choose many target, then no one can know what will happen";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("vkey","Value key","[vkey]","key"),
                EditorOption.text("mkey","Mark Key","[mkey]","key")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (list.isEmpty()) return false;

        String vkey = settings.getString("vkey");
        String mkey = settings.getString("mkey");


        DynamicSkill.getCastData(livingEntity).put(vkey, (double)MarkManager.getMarkCount(list.get(0),mkey));
        return true;
    }
}
