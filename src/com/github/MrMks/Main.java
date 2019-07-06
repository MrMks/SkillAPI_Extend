package com.github.MrMks;

import com.github.MrMks.bug.BugFixer;
import com.github.MrMks.comp.cond.*;
import com.github.MrMks.comp.mach.*;
import com.github.MrMks.comp.trig.PlayerLevelUp;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.SkillPlugin;
import com.sucy.skill.dynamic.ComponentRegistry;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.trigger.Trigger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements SkillPlugin {

    @Override
    public void onLoad() {
        super.onLoad();
        BugFixer.run();
    }

    @Override
    public void registerSkills(SkillAPI skillAPI) {
        //skillAPI.addSkill(new NonSkill());
    }

    @Override
    public void registerClasses(SkillAPI skillAPI) {
        //skillAPI.addClass(new NonClass());
    }

    @Override
    public List<Trigger> getTriggers() {
        ImmutableList<Trigger> list = ImmutableList.of(
                new PlayerLevelUp()
        );

        //Ignore Registered Triggers
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
                new CheckUntil(),
                new AutoMaxHealth(),
                new AutoMaxMana(),
                new MarkAddMach(),
                new MarkSetMach(),
                new MarkRemoveMach(),
                new MarkToValue(),
                new HaveMark(),
                new ValueOnDurability(),
                //npc related
                new CNPC_StoredData(),
                new CNPC_TempData(),
                new CNPC_StoredDataEqual(),
                new CNPC_TempDataEqual(),
                new CNPC_ValueData(),
                new CNPC_RemoveData(),
                new CNPC_ClearData(),
                new CNPC_Attr_Fic()
        );
    }

    /*
    @Override
    public void onEnable() {
        super.onEnable();
        try {
            Class.forName("com.github.com.SAPI").getMethod("print").invoke(Class.forName("com.github.com.SAPI"));
        } catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    */
}
