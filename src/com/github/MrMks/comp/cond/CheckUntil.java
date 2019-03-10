package com.github.MrMks.comp.cond;

import com.github.MrMks.comp.cond.checkUntil.CheckUntilThread;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.api.util.FlagManager;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.CustomComponent;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class CheckUntil extends CustomEffectComponent {
    private boolean flag_t = true;
    @Override
    public String getKey() {
        return "Check Until";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.CONDITION;
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        //return false;
        //String flag = (String) DynamicSkill.getCastData(livingEntity).get("flag");
        String flag = settings.getString("flag");
        System.out.print(flag);

        CheckUntilThread th = new CheckUntilThread(this);
        th.init(livingEntity, i, list);
        Thread thread = new Thread(th);
        thread.start();

        return true;
    }

    @Override
    public String getDescription() {
        return "Check the given flag until the flag removed.Notice that the check only act in one minute.";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("flag","Flag","the flag will watch on","check_until_flag")
        );
    }

}
