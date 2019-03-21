package com.github.MrMks.utils.checkUntil;

import com.github.MrMks.comp.cond.CheckUntil;
import com.github.MrMks.utils.SkillAPIAddonRunnable;
import com.sucy.skill.api.util.FlagManager;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class CheckUntilRunnable extends SkillAPIAddonRunnable {

    private CustomEffectComponent component;

    private LivingEntity caster;
    private int level;
    private List<LivingEntity> targets;
    public CheckUntilRunnable(CustomEffectComponent component){
        this.component = component;
    }

    public void init(LivingEntity livingEntity, int i, List<LivingEntity> list){
        caster = livingEntity;
        level = i;
        targets = list;
    }

    private int timer = 0;
    //private boolean switcher = true;

    @Override
    public void run() {
        if(FlagManager.hasFlag(caster,component.getSettings().getString("flag"))){
            if(timer >= 120 * 20){
                System.out.print(timer);
                cancel();
            } else {
                timer += 1;
                //SkillAPI.schedule(this,1);
            }
        } else {
            cancel();
            ((CheckUntil)component).ec(caster,level,targets);
        }
    }
}
