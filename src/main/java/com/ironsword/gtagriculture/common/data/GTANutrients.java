package com.ironsword.gtagriculture.common.data;

import com.ironsword.gtagriculture.api.data.nutrient.Nutrient;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potions;

public class GTANutrients {
    public static final Nutrient
            ALCOHOL = new Nutrient("alcohol",(player, amount) -> {
                if (amount>=100) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,100,3));
                }else if (amount>=25){
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,100,0));
                }
    }),
            CAFFEINE = new Nutrient("caffeine"),
            DEHYDRATION = new Nutrient("dehydration"),
            SUGAR = new Nutrient("sugar"),
            FAT = new Nutrient("fat"),
            RADIATION = new Nutrient("radiation");


    //a
    //>=100 反胃3，力量4，受伤
    //>=75 反胃2，力量3
    //>=50 反胃，力量2
    //>=25 力量

    //c
    //>=100 虚弱3，急迫4，受伤
    //>=75 虚弱2，急迫3
    //>=50 虚弱，急迫2
    //>=25 急迫

    //d

    //s
    //>=100 挖掘疲劳3，跳跃提升4，速度4，受伤
    //>=75 挖掘疲劳2，跳跃提升3，速度3
    //>=50 挖掘疲劳，跳跃提升2，速度2
    //>=25 跳跃提升，速度

    //f
    //>=100 缓慢3，抗性提升4，受伤
    //>=75 缓慢2，抗性提升3
    //>=50 缓慢，抗性提升2
    //>=25 抗性提升

    //r
}
