package com.github.MrMks.comp.cond;

import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;

public abstract class Cond_Base extends CustomEffectComponent {
    @Override
    public ComponentType getType() {
        return ComponentType.CONDITION;
    }
}
