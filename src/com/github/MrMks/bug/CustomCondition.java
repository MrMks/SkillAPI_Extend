package com.github.MrMks.bug;

import com.github.MrMks.comp.cond.Cond_Base;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CustomCondition extends Cond_Base {
    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of();
    }

    @Override
    public ComponentType getType() {
        return ComponentType.CONDITION;
    }

    public boolean execute(LivingEntity var1, int var2, List<LivingEntity> var3) {
        List<LivingEntity> var4 = var3.stream().filter((var3x) -> this.test(var1, var2, var3x)).collect(Collectors.toList());
        return var4.size() > 0 && this.executeChildren(var1, var2, var4);
    }

    abstract boolean test(LivingEntity caster, int level, LivingEntity target);
}
