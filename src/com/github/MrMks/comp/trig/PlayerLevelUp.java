package com.github.MrMks.comp.trig;

import com.sucy.skill.api.Settings;
import com.sucy.skill.api.event.PlayerLevelUpEvent;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.custom.CustomTrigger;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerLevelUp implements CustomTrigger<PlayerLevelUpEvent> {
    @Override
    public ComponentType getType() {
        return ComponentType.TRIGGER;
    }

    @Override
    public String getDescription() {
        return "Apply when the player level up. Now level will stored under the key 'player_levelup_now', amount will be stored under the key player_levelup_amount";
    }

    @Override
    public List<EditorOption> getOptions() {
        return new ArrayList<>();
    }

    @Override
    public String getKey() {
        return "PLAYER_LEVEL_UP";
    }

    @Override
    public String getDisplayName() {
        return "Player Level Up";
    }

    @Override
    public Class<PlayerLevelUpEvent> getEvent() {
        return PlayerLevelUpEvent.class;
    }

    @Override
    public boolean shouldTrigger(PlayerLevelUpEvent event, int i, Settings settings) {
        //return false;
        return true;
    }

    @Override
    public LivingEntity getCaster(PlayerLevelUpEvent event) {
        return event.getPlayerData().getPlayer();
    }

    @Override
    public LivingEntity getTarget(PlayerLevelUpEvent event, Settings settings) {
        return getCaster(event);
    }

    @Override
    public void setValues(PlayerLevelUpEvent event, Map<String,Object> map) {
        map.put("player_levelup_now",event.getLevel());
        map.put("player_levelup_amount",event.getAmount());
    }
}
