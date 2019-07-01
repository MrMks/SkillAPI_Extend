package com.github.MrMks.comp.mach;

import com.github.MrMks.utils.CNPC;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;

public class CNPC_Attr_Fic extends CNPC_Base {
    @Override
    public String getKey() {
        return "CNPC Attr Fic";
    }

    @Override
    public String getDescription() {
        return "Copy the SkillAPI Attribute value to/from the CNPC Faction.Notice that this Mechanic always execute on caster.";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.dropdown("from","From","[from]True means copy from faction and false means copy to the fiction", booleanList),
                EditorOption.text("sapi_key","Key in Sapi","[sapi_key]The name of the Attibute",null),
                EditorOption.number("cnpc_key","Key in Cnpc","[cnpc_key]The name of the fiction",0d,0d)
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        boolean from = settings.getBool("from");
        String s_key = settings.getString("sapi_key", "-1");
        int c_key = settings.getInt("cnpc_key", -1);

        if (c_key == -1 || s_key.equals("-1")) return false;

        PlayerData data = SkillAPI.getPlayerData((Player)livingEntity);
        if (from) {
            data.giveAttribute(s_key,CNPC.getPlayerFactionPoint(livingEntity.getUniqueId(),c_key) - data.getInvestedAttribute(s_key));
        } else {
            CNPC.setPlayerFactionFoint(livingEntity.getUniqueId(),c_key,data.getInvestedAttribute(s_key));
        }
        return true;
    }
}
