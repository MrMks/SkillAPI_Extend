package com.github.MrMks.comp.cond.checkUntil;

import com.sucy.skill.api.util.FlagManager;
import com.sucy.skill.dynamic.EffectComponent;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CheckUntilThread extends BukkitRunnable {

    private CustomEffectComponent component;

    private LivingEntity caster;
    private int level;
    private List<LivingEntity> targets;
    public CheckUntilThread(CustomEffectComponent component){
        this.component = component;
    }

    public void init(LivingEntity livingEntity, int i, List<LivingEntity> list){
        caster = livingEntity;
        level = i;
        targets = list;
    }

    @Override
    public void run() {
        String flag = component.getSettings().getString("flag");
        AtomicBoolean flag_t = new AtomicBoolean(true);
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            flag_t.set(false);
        });

        thread.start();

        while (true) {
            if (!flag_t.get()) {
                thread.interrupt();
                return;
            }

            if (!FlagManager.hasFlag(caster, flag)) {
                thread.interrupt();
                for (EffectComponent c :
                        component.children) {
                    c.execute(caster, level, targets);
                }
            }
        }
    }
}
