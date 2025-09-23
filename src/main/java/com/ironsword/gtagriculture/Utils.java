package com.ironsword.gtagriculture;

import com.tterrag.registrate.util.entry.ItemEntry;

public class Utils {
    public static String id2Name(String id) {
        StringBuilder builder = new StringBuilder();
        String[] subStrings = id.split("_");
        for (String s:subStrings) {
            if (!builder.isEmpty()) {
                builder.append(" ");
            }
            builder.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        return builder.toString();
    }

}
