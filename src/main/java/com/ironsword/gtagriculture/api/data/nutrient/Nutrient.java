package com.ironsword.gtagriculture.api.data.nutrient;

import lombok.Getter;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Interface;

import java.util.HashMap;
import java.util.Map;

public class Nutrient {
    public static final Map<String,Nutrient> NUTRIENTS = new HashMap<>();
    public static final Effect NO_EFFECT = (player,amount)->{
        ;
    };

    private final Effect effect;

    @Getter
    public final String name;

    public Nutrient(String name,Effect effect) {
        this.name = name;
        this.effect = effect;

        NUTRIENTS.put(name,this);
    }

    public Nutrient(String name) {
        this(name,NO_EFFECT);
    }

    public void applyEffect(Player player,int amount) {
        effect.apply(player,amount);
    }

    @FunctionalInterface
    public interface Effect{
        void apply(Player player,int amount);
    }
}
