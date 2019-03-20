package com.github.MrMks.comp.mach;

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class MaxValueChange extends CustomEffectComponent {

    @Override
    public ComponentType getType() {
        return ComponentType.MECHANIC;
    }

    @Override
    public String getDescription() {
        return "Change caster's " + getKey().toLowerCase();
    }

    @Override
    public List<EditorOption> getOptions() {
        ArrayList<EditorOption> list = new ArrayList<>();
        list.add(EditorOption.number("amount","Amount","[amount]The value to add", 2,0));
        return list;
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public boolean execute(LivingEntity caster, int i, List<LivingEntity> list) {
        double amount = parseValues(caster,"amount",i,2);
        //SkillAPI.getPlayerData((OfflinePlayer) caster).addMaxHealth(amount);
        e(SkillAPI.getPlayerData((OfflinePlayer) caster),amount);
        return true;
    }

    protected abstract void e(PlayerData data,double v);
}
