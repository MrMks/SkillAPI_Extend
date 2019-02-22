package com.github.MrMks.skill;

import com.sucy.skill.api.skills.Skill;
import com.sucy.skill.api.skills.SkillShot;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;

public class NonSkill extends Skill implements SkillShot {
    public NonSkill(){
        super("NaS","NoS",Material.ACACIA_DOOR,0);
    }

    @Override
    public boolean cast(LivingEntity livingEntity, int i) {
        return false;
    }
}
