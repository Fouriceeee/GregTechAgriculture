package com.ironsword.gtagriculture.api.item.component;

import com.gregtechceu.gtceu.api.item.component.IAddInformation;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.ironsword.gtagriculture.api.data.nutrient.Nutrient;
import it.unimi.dsi.fastutil.objects.Object2ByteMap;
import it.unimi.dsi.fastutil.objects.Object2ByteOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FoodNutrients implements IAddInformation {
    protected final Object2IntMap<Nutrient> nutrients = new Object2IntOpenHashMap<>();

    public FoodNutrients() {
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        for (Nutrient nutrient: nutrients.keySet()) {
            tooltipComponents.add(Component.literal(nutrient.getName()+" : "+nutrients.getInt(nutrient)));
        }
    }

    public FoodNutrients addNutrient(Nutrient nutrient,int amount) {
        nutrients.put(nutrient,amount);
        return this;
    }
}
