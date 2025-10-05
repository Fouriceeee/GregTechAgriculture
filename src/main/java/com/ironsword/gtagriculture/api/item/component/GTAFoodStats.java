package com.ironsword.gtagriculture.api.item.component;

import com.gregtechceu.gtceu.api.item.component.FoodStats;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class GTAFoodStats extends FoodStats {
    private static final int C = 273;
    private final int nutrition, alcohol, caffeine, dehydration, sugar, fat, radiation;
    private final float saturation, hydration, temperature, temperatureEffect;
    public boolean isExplosive = false, isMilk = false,isRotten = false, canExtinguish = false ;
    public int rebreathe = 0;

    public GTAFoodStats(
            FoodProperties properties,
            boolean isDrink,
            boolean hasPotionEffects,
            @Nullable Supplier<ItemStack> containerItem,
            int nutrition,
            int alcohol,
            int caffeine,
            int dehydration,
            int sugar,
            int fat,
            int radiation,
            float saturation,
            float hydration,
            float temperature,
            float temperatureEffect,
            boolean isExplosive,
            boolean isMilk,
            boolean isRotten,
            boolean canExtinguish,
            int rebreathe) {
        super(properties, isDrink, hasPotionEffects, containerItem);
        this.nutrition = nutrition;
        this.alcohol = alcohol;
        this.caffeine = caffeine;
        this.dehydration = dehydration;
        this.sugar = sugar;
        this.fat = fat;
        this.radiation = radiation;
        this.saturation = saturation;
        this.hydration = hydration;
        this.temperature = temperature;
        this.temperatureEffect = temperatureEffect;
        this.isExplosive = isExplosive;
        this.isMilk = isMilk;
        this.isRotten = isRotten;
        this.canExtinguish = canExtinguish;
        this.rebreathe = rebreathe;
    }

    public GTAFoodStats(int nutrition, float saturation) {
        super(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build());
        this.nutrition = nutrition;
        this.alcohol = 0;
        this.caffeine = 0;
        this.dehydration = 0;
        this.sugar = 0;
        this.fat = 0;
        this.radiation = 0;
        this.saturation = saturation;
        this.hydration = 0;
        this.temperature = C + 37;
        this.temperatureEffect = 0;
        this.isExplosive = false;
        this.isMilk = false;
        this.isRotten = false;
        this.canExtinguish = false;
        this.rebreathe = 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        m2(tooltipComponents);
    }

    public void m2(List<Component> tooltipComponents){
        tooltipComponents.add(Component.literal("§7Nutrition: "+nutrition));
        tooltipComponents.add(Component.literal("§7Saturation: "+saturation));
    }

    public void m1(List<Component> tooltipComponents) {
        tooltipComponents.add(Component.literal("§cNutrition: " + nutrition + " - Saturation: " + saturation));
        StringBuilder builder = new StringBuilder();
        if (temperature >= C + 40.0F) {
            builder.append("Hot");
        }else if (temperature >= C + 38.0F) {
            builder.append("Warm");
        } else if (temperature <= C + 34.0F) {
            builder.append("Very Cold");
        } else if (temperature <= C + 36.0F) {
            builder.append("Cold");
        }
        if (hydration != 0) {
            builder.append(builder.isEmpty()?"":" - ");
            builder.append(hydration>0?"Hydration: "+hydration:"Dehydration: "+ (-hydration));
        }
        if (!builder.isEmpty()) {
            tooltipComponents.add(Component.literal("§c"+builder));
        }
        if (isExplosive) {
            tooltipComponents.add(Component.literal("§cSmells like explosives"));
        }
        if (isRotten) {
            tooltipComponents.add(Component.literal("§cSmells rotten"));
        }
    }
}
