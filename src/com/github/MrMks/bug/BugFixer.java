package com.github.MrMks.bug;

import com.sucy.skill.dynamic.ComponentRegistry;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;

public class BugFixer {
    public static void run(){
        r(new NameCondition());
    }

    private static void r(CustomEffectComponent component){
        ComponentRegistry.register(component);
    }
}
