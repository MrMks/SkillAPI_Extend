package com.github.MrMks.comp.mach;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class AutoMaxMana extends Mach_Base {
    @Override
    public String getKey() {
        return "Auto Max Mana";
    }

    @Override
    public String getDescription() {
        return "Add the Max Mana. The value will be \'the amount * (the player level - from + 1)\'";
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
        //return false;
        PlayerSkill skill1 = SkillAPI.getPlayerData((OfflinePlayer) livingEntity).getSkill(skill.getName());
        //double mana_base = skill1.getPlayerClass().getData().getBaseMana();
        int player_level = skill1.getPlayerClass().getLevel() + 1;

        double amount = parseValues(livingEntity,"amount",i,0);
        double from = parseValues(livingEntity,"from",i,0);

        player_level -= Math.round(Math.floor(from));

        skill1.getPlayerData().addMaxMana(amount * player_level);

        return true;
    }
}
