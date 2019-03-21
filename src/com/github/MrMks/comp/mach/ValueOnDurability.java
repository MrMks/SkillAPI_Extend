package com.github.MrMks.comp.mach;

import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueOnDurability extends Mach_Base {
    private Pattern pattern =
            Pattern.compile("\\s*(\\d+(\\.\\d+)?)%\\s*-\\s*(\\d+(\\.\\d+)?)%\\s*,\\s*(\\d+(\\.\\d+)?)");
    private final List<String> drop = new ArrayList<>();
    {
        drop.add("False");
        drop.add("True");
    }
    @Override
    public String getKey() {
        return "Value On Durability";
    }

    @Override
    public String getDescription() {
        return "Please input '<present> - <present> <number>'";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key","Key","[key]","key"),
                EditorOption.dropdown("offhand","Offhand","[offhand]",drop),
                EditorOption.number("error","On Error","[error]",1,0),
                EditorOption.list("list","List","[list]", Collections.emptyList())
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        //return false;
        String key = settings.getString("key");
        double err = parseValues(livingEntity,"error",i,1);
        boolean offhand = settings.getBool("offhand");
        ArrayList<String> list1 = new ArrayList<>(settings.getStringList("list"));

        Matcher matcher;
        boolean flag = false;
        double result = -1;

        float first, second, value;
        ItemStack itemStack = offhand ? livingEntity.getEquipment().getItemInOffHand():livingEntity.getEquipment().getItemInMainHand();
        int durability = itemStack.getDurability();
        for (String line : list1) {
            matcher = pattern.matcher(line);
            if(matcher.find()){
                try{
                    first = Float.parseFloat(matcher.group(1));
                    second = Float.parseFloat(matcher.group(2));
                    value = Float.parseFloat(matcher.group(3));

                    if(Float.compare(durability,first)*Float.compare(durability,second) <= 0){
                        result = value;
                        flag = true;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }

        if(!flag) result = err;

        DynamicSkill.getCastData(livingEntity).put(key,result);
        return true;
    }
}

