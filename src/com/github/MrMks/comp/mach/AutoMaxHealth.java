package com.github.MrMks.comp.mach;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class AutoMaxHealth extends CustomEffectComponent {
    @Override
    public String getKey() {
        return "Auto Max Health";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.MECHANIC;
    }

    @Override
    public String getDescription() {
        return  "Add the Max Health. The value will be \'the amount * (the player level - from - 1)\'";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.number("from","From","[from]",0,0),
                EditorOption.number("amount","Amount","[amount]",2,1)
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        int level = SkillAPI.getPlayerData((OfflinePlayer)livingEntity).getSkill(skill.getName()).getPlayerClass().getLevel() - 1;
        double amount = parseValues(livingEntity,"amount",i,0);
        double from = parseValues(livingEntity,"from",i,0);

        level -= Math.round(Math.floor(from));

        SkillAPI.getPlayerData((OfflinePlayer) livingEntity).addMaxHealth(amount * level);
        return true;
    }
}
