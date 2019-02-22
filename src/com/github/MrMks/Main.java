package com.github.MrMks;

import com.github.MrMks.classes.NonClass;
import com.github.MrMks.comp.mach.ValueTargetNum;
import com.github.MrMks.skill.NonSkill;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.SkillPlugin;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.trigger.Trigger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class Main extends JavaPlugin implements SkillPlugin {
    @Override
    public void registerSkills(SkillAPI skillAPI) {
        skillAPI.addSkill(new NonSkill());
    }

    @Override
    public void registerClasses(SkillAPI skillAPI) {
        skillAPI.addClass(new NonClass());
    }

    @Override
    public List<Trigger> getTriggers() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<CustomEffectComponent> getComponents() {
        return ImmutableList.of(
                new ValueTargetNum()
        );
    }
}
