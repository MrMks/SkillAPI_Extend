package com.github.MrMks.comp.mach;

import com.sucy.skill.dynamic.ComponentType;

import java.util.ArrayList;
import java.util.List;

public abstract class CNPC_Base extends Mach_Base {
    @Override
    public ComponentType getType() {
        return ComponentType.MECHANIC;
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    List<String> booleanList = new ArrayList<>();
    {
        booleanList.add("True");
        booleanList.add("False");
    }
}
