package com.github.MrMks.bug;

import com.github.MrMks.bug.CustomCondition;
import org.bukkit.entity.LivingEntity;

import java.util.regex.Pattern;

public class NameCondition extends CustomCondition {

    private static final String CONTAINS = "contains";
    private static final String REGEX    = "regex";
    private static final String STRING   = "text";


    @Override
    boolean test(LivingEntity caster, int level, LivingEntity target) {
        boolean contains = !settings.getString(CONTAINS, "true").toLowerCase().equals("false");
        boolean regex = settings.getString(REGEX, "false").toLowerCase().equals("true");
        String str = settings.getString(STRING, "");

        String name = target.getCustomName();
        name = name == null ? target.getName() : name;
        return name != null && (regex
                ? Pattern.compile(str).matcher(name).find() == contains
                : name.contains(str) == contains);
    }

    @Override
    public String getKey() {
        return "name";
    }
}
