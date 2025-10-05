package com.ironsword.gtagriculture.api.data.nutrient;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Nutrient {
    public static final Map<String,Nutrient> NUTRIENTS = new HashMap<>();

    @Getter
    public final String name;

    public Nutrient(String name) {
        this.name = name;

        NUTRIENTS.put(name,this);
    }
}
