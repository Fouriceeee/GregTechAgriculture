package com.ironsword.gtagriculture.api.item.component;

import com.gregtechceu.gtceu.api.item.component.IAddInformation;
import com.gregtechceu.gtceu.api.item.component.IInteractionItem;
import com.ironsword.gtagriculture.api.capability.NutrientTracker;
import com.ironsword.gtagriculture.api.capability.forge.GTACapability;
import com.ironsword.gtagriculture.api.data.nutrient.Nutrient;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FoodNutrients implements IAddInformation, IInteractionItem {
    protected final Object2IntMap<Nutrient> nutrients = new Object2IntOpenHashMap<>();

    public FoodNutrients() {
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        for (Nutrient nutrient: nutrients.keySet()) {
            tooltipComponents.add(Component.literal(nutrient.getName()+" : "+nutrients.getInt(nutrient)));
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer) {
            NutrientTracker tracker = GTACapability.getNutrientTracker(livingEntity);
            for (Nutrient nutrient: nutrients.keySet()){
                tracker.gain(nutrient,nutrients.getInt(nutrient));
            }
        }
        return IInteractionItem.super.finishUsingItem(stack, level, livingEntity);
    }

    public FoodNutrients nutrient(Nutrient nutrient, int amount) {
        nutrients.put(nutrient,amount);
        return this;
    }
}
