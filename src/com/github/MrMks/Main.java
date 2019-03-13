package com.github.MrMks;

import com.github.MrMks.classes.NonClass;
import com.github.MrMks.comp.cond.CheckUntil;
import com.github.MrMks.comp.cond.HaveMark;
import com.github.MrMks.comp.mach.*;
import com.github.MrMks.comp.trig.PlayerLevelUp;
import com.github.MrMks.skill.NonSkill;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.SkillPlugin;
import com.sucy.skill.dynamic.ComponentRegistry;
import com.sucy.skill.dynamic.TriggerHandler;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.trigger.Trigger;
import org.apache.logging.log4j.core.config.builder.api.Component;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
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
        ImmutableList<Trigger> list = ImmutableList.of(
                new PlayerLevelUp()
        );
        ArrayList<Trigger> arrayList = new ArrayList<>();

        for (Trigger trigger :
                list) {
            if (null == ComponentRegistry.getTrigger(trigger.getKey())){
                arrayList.add(trigger);
            }
        }
        return arrayList;
    }

    @Override
    public List<CustomEffectComponent> getComponents() {
        return ImmutableList.of(
                new ValueTargetNum(),
                new MaxMana(),
                new MaxHealth(),
                new TimerStart(),
                new TimerStop(),
                //new Wait(),
                new CheckUntil(),
                new AutoMaxHealth(),
                new AutoMaxMana(),
                new MarkAddMach(),
                new MarkSetMach(),
                new MarkRemoveMach(),
                new MarkToValue(),
                new HaveMark()
        );
    }
}
